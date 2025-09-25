package accountmgmt.repository;

import accountmgmt.model.Account;
import accountmgmt.model.Beneficiary;
import accountmgmt.model.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class Memory {
	
	// Data storage
    private final Map<String, Beneficiary> beneficiaries = new ConcurrentHashMap<>();
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Map<String, List<Account>> accountsByBeneficiary = new ConcurrentHashMap<>();
    private final Map<String, List<Transaction>> transactionsByAccount = new ConcurrentHashMap<>();
    
    //formatter for the dates
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

    
    // Load beneficiaries.csv
    public void loadBeneficiaries(Path csvFile) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(csvFile)) {
            String line;
            reader.readLine(); //ignore header
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                Beneficiary b = new Beneficiary(columns[0], columns[1], columns[2]);
                //saving in map(the id with all the costumer's data)
                beneficiaries.put(b.getBeneficiaryId(), b);
            }
        }
    }
    
    // Load accounts.csv
    public void loadAccounts(Path csvFile) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(csvFile)) {
            String line;
            reader.readLine(); //ignore header
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                Account acc = new Account(columns[0], columns[1]);
                //save in map (the id and the account info)
                accounts.put(acc.getAccountId(), acc);

                // map to connect  beneficiaryId with the list of accounts 
                accountsByBeneficiary.computeIfAbsent(acc.getBeneficiaryId(), k -> new ArrayList<>()).add(acc);
            }
        }
    }
    
    // Load transactions.csv
     public void loadTransactions(Path csvFile) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(csvFile)) {
            String line;
            reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                Transaction t = new Transaction(
                		columns[0],
                		columns[1],
                        new BigDecimal(columns[2]),
                        columns[3],
                        LocalDate.parse(columns[4], formatter)
                );
                //map to connect accountId with the transactions list
                transactionsByAccount.computeIfAbsent(t.getAccountId(), k -> new ArrayList<>()).add(t);
            }
        }

        // Sort transactions per account by date
        transactionsByAccount.values().forEach(list -> list.sort(Comparator.comparing(Transaction::getDate)));
    }

    // Query methods
    public Beneficiary getBeneficiary(String id) {
        return beneficiaries.get(id);
    }

    public List<Account> getAccountsOfBeneficiary(String beneficiaryId) {
        return accountsByBeneficiary.getOrDefault(beneficiaryId, Collections.emptyList());
    }

    public List<Transaction> getTransactionsOfBeneficiary(String beneficiaryId) {
        return getAccountsOfBeneficiary(beneficiaryId).stream()
                .flatMap(acc -> transactionsByAccount.getOrDefault(acc.getAccountId(), Collections.emptyList()).stream())
                .collect(Collectors.toList());
    }
    
    public BigDecimal getBalanceOfBeneficiary(String beneficiaryId) {
        List<Account> accounts = getAccountsOfBeneficiary(beneficiaryId);
        BigDecimal balance = BigDecimal.ZERO;
        for (Account acc : accounts) {
            List<Transaction> transactions = transactionsByAccount.getOrDefault(acc.getAccountId(), Collections.emptyList());
            for (Transaction tx : transactions) {
                // if withdrawal we deduct
                if (tx.isWithdrawal()) {
                    balance = balance.subtract(tx.getAmount());
                } else {
                    // if deposit then we add the amount
                    balance = balance.add(tx.getAmount());
                }
            }
        }
        return balance;
    }

    public Optional<BigDecimal> getMaxWithdrawalLastMonth(String beneficiaryId) {
        List<Transaction> allTransactions = getTransactionsOfBeneficiary(beneficiaryId);

        LocalDate now = LocalDate.now();
        LocalDate oneMonthAgo = now.minusMonths(1);
        
        return allTransactions.stream()
            .filter(Transaction::isWithdrawal) // only withdrawals
            .filter(t -> {
                LocalDate txDate = t.getDate(); 
                return (txDate.isEqual(oneMonthAgo) || txDate.isAfter(oneMonthAgo)) &&
                       (txDate.isEqual(now) || txDate.isBefore(now));
            })
            .map(Transaction::getAmount)
            .max(Comparator.naturalOrder());
    }

    // this method is to find the data of the transaction of the Max Withdrawal
    public Optional<Transaction> getMaxWithdrawalTransactionLastMonth(String beneficiaryId) {
        List<Transaction> allTransactions = getTransactionsOfBeneficiary(beneficiaryId);
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return allTransactions.stream()
                .filter(Transaction::isWithdrawal)
                .filter(t -> !t.getDate().isBefore(oneMonthAgo))
                .max(Comparator.comparing(Transaction::getAmount));
    }
    
    @PostConstruct
    public void init() throws IOException {
        Path beneficiariesPath = new ClassPathResource("files/beneficiaries.csv").getFile().toPath();
        Path accountsPath = new ClassPathResource("files/accounts.csv").getFile().toPath();
        Path transactionsPath = new ClassPathResource("files/transactions.csv").getFile().toPath();

        loadBeneficiaries(beneficiariesPath);
        loadAccounts(accountsPath);
        loadTransactions(transactionsPath);
    }
    
    
}



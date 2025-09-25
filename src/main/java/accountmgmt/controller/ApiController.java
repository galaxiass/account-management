package accountmgmt.controller;

import accountmgmt.model.Account;
import accountmgmt.model.Beneficiary;
import accountmgmt.model.Transaction;
import accountmgmt.repository.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/beneficiaries")
public class ApiController {
	
	private final Memory data;
	
	@Autowired
	public ApiController(Memory data) {
		this.data=data;
	}
	
	//get beneficiary data
	@GetMapping("/{id}")
	public Beneficiary getBeneficiary(@PathVariable String id) {
		return data.getBeneficiary(id);
	}
	
	//get Accounts of beneficiary
	@GetMapping("/{id}/accounts")
	public List<Account> getAccounts(@PathVariable String id){
		return data.getAccountsOfBeneficiary(id);
	}
	
	//get transactions of beneficiary
	@GetMapping("/{id}/transactions")
	public List<Transaction> getTransactions(@PathVariable String id){
		return data.getTransactionsOfBeneficiary(id);
	}
	
	// get balance
    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable String id) {
        return data.getBalanceOfBeneficiary(id);
    }

    // get biggest withdrawal of last month
    @GetMapping("/{id}/max-withdrawal")
    public BigDecimal getMaxWithdrawal(@PathVariable String id) {
        return data.getMaxWithdrawalLastMonth(id).orElse(BigDecimal.ZERO);
    }
 

}

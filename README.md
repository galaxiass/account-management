# account-management

Account Management REST API

Αυτό το project είναι μια Spring Boot εφαρμογή που παρέχει REST endpoints για τη διαχείριση δικαιούχων, λογαριασμών και συναλλαγών μέσω αρχείων CSV.



📁 Περιεχόμενα Project

1. AccountManagementApplication.java: Main class για το Spring Boot app
2. controller/ApiController.java: REST endpoints
3. repository/Memory.java: Φόρτωση και αποθήκευση δεδομένων στη μνήμη
4. model/Beneficiary.java, Account.java, Transaction.java: Μοντέλα δεδομένων
5. src/main/resources/files/beneficiaries.csv, accounts.csv, transactions.csv: Δεδομένα σε CSV



🚀 Εκτέλεση

1. Τα αρχεία CSV είναι στον φάκελο src/main/resources/files/
2. Τρέξε την εφαρμογή:
   
   -Από IDE: Εκτέλεσε το AccountManagementApplication.java

   -Από terminal: mvn spring-boot:run ή java -jar target/account-management.jar
   
3. **Η εφαρμογή ακούει στην πόρτα 8088 **




🌐 REST Endpoints

Όλα τα endpoints ξεκινούν με /beneficiaries/{id}

1. GET /beneficiaries/{id}: Επιστρέφει τα στοιχεία του δικαιούχου
2. GET /beneficiaries/{id}/accounts: Επιστρέφει τους λογαριασμούς του δικαιούχου
3. GET /beneficiaries/{id}/transactions: Επιστρέφει τις συναλλαγές του δικαιούχου
4. GET /beneficiaries/{id}/balance: Επιστρέφει το συνολικό υπόλοιπο του δικαιούχου
5. GET /beneficiaries/{id}/max-withdrawal: Επιστρέφει τη μεγαλύτερη ανάληψη του τελευταίου μήνα



📄 Δομή CSV Αρχείων
1. beneficiaries.csv: beneficiaryId,firstName,lastName
2. accounts.csv: accountId,beneficiaryId
3. transactions.csv: transactionId,accountId,amount,type,date (date format: MM/dd/yy ή M/d/yy)




ℹ️ Σημειώσεις

**Αν το endpoint /max-withdrawal επιστρέφει 0 ειναι επειδη δεν υπάρχουν συναλλαγές τύπου withdrawal στον τελευταίο μήνα στα δεδομένα του transactions.csv
**


-->Παράδειγμα χρήσης 

http://localhost:8088/beneficiaries/1

http://localhost:8088/beneficiaries/1/accounts

http://localhost:8088/beneficiaries/1/transactions

http://localhost:8088/beneficiaries/1/balance

http://localhost:8088/beneficiaries/1/max-withdrawal

# account-management
Account Management REST API
Αυτό το project είναι μια Spring Boot εφαρμογή που παρέχει REST endpoints για τη διαχείριση δικαιούχων, λογαριασμών και συναλλαγών μέσω αρχείων CSV.

Περιεχόμενα
AccountManagementApplication.java: Main class για το Spring Boot app

controller/ApiController.java: REST endpoints

repository/Memory.java: Φόρτωση και αποθήκευση δεδομένων στη μνήμη

model/Beneficiary.java, Account.java, Transaction.java: Μοντέλα δεδομένων

src/main/resources/files/beneficiaries.csv, accounts.csv, transactions.csv: Δεδομένα σε CSV

Πώς τρέχεις το project
Βεβαιώσου ότι τα αρχεία CSV είναι στον φάκελο src/main/resources/files/
Τρέξε την εφαρμογή:

-Από IDE: Εκτέλεσε το AccountManagementApplication.java

-Από terminal: mvn spring-boot:run ή java -jar target/account-management.jar

**Η εφαρμογή ακούει στην πόρτα 8088 **

REST Endpoints
Όλα τα endpoints ξεκινούν με /beneficiaries/{id}

GET /beneficiaries/{id}: Επιστρέφει τα στοιχεία του δικαιούχου

GET /beneficiaries/{id}/accounts: Επιστρέφει τους λογαριασμούς του δικαιούχου

GET /beneficiaries/{id}/transactions: Επιστρέφει τις συναλλαγές του δικαιούχου

GET /beneficiaries/{id}/balance: Επιστρέφει το συνολικό υπόλοιπο του δικαιούχου

GET /beneficiaries/{id}/max-withdrawal: Επιστρέφει τη μεγαλύτερη ανάληψη του τελευταίου μήνα

Δομή CSV
beneficiaries.csv: beneficiaryId,firstName,lastName

accounts.csv: accountId,beneficiaryId

transactions.csv: transactionId,accountId,amount,type,date (date format: MM/dd/yy ή M/d/yy)

Σημειώσεις
**Αν το endpoint /max-withdrawal επιστρέφει 0 ειναι επειδη δεν υπάρχουν συναλλαγές τύπου withdrawal στον τελευταίο μήνα στα δεδομένα του transactions.csv
**


Παράδειγμα χρήσης 

http://localhost:8088/beneficiaries/1

http://localhost:8088/beneficiaries/1/accounts

http://localhost:8088/beneficiaries/1/transactions

http://localhost:8088/beneficiaries/1/balance

 http://localhost:8088/beneficiaries/1/max-withdrawal

package accountmgmt.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private String transactionId;
    private String accountId;
    private LocalDate date;
    private BigDecimal amount;
    private String type;
 

    public Transaction() {}

    public Transaction(String transactionId, String accountId, BigDecimal amount, String type, LocalDate date) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public String getTransactionId() { 
    	return transactionId; 
    }
    public void setTransactionId(String transactionId) { 
    	this.transactionId = transactionId; 
    }
    
    
    public String getAccountId() { 
    	return accountId; 
    }
    public void setAccountId(String accountId) { 
    	this.accountId = accountId; 
    }
    
    
    public LocalDate getDate() { 
    	return date; 
    }
    public void setDate(LocalDate date) { 
    	this.date = date; 
    
    }
    
    
    public BigDecimal getAmount() { 
    	return amount; 
    }
    public void setAmount(BigDecimal amount) { 
    	this.amount = amount; 
    }
    
    
    public String getType() { 
    	return type; 
    }
    public void setType(String type) { 
    	this.type = type; 
    }
    
    
    
    public boolean isWithdrawal() {
        return type != null && type.equalsIgnoreCase("withdrawal");
    }

}


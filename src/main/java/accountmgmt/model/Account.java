package accountmgmt.model;

import java.math.BigDecimal;

public class Account {
	
    private String accountId;
    private String beneficiaryId;
   
    public Account() {}

    public Account(String accountId, String beneficiaryId) {
        this.accountId = accountId;
        this.beneficiaryId = beneficiaryId;
    }

    
    
    public String getAccountId() { 
    	return accountId; 
    }
    public void setAccountId(String accountId) { 
    	this.accountId = accountId; 
    }
    
    
    public String getBeneficiaryId() { 
    	return beneficiaryId; 
    }
    public void setBeneficiaryId(String beneficiaryId) { 
    	this.beneficiaryId = beneficiaryId; 
    }
}

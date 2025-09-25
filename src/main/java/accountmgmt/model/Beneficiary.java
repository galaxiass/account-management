package accountmgmt.model;

public class Beneficiary {
	
    private String id;
    private String firstName;
    private String lastName;
 
    public Beneficiary() {}

    public Beneficiary(String beneficiaryId, String firstName, String lastName) {
        this.id = beneficiaryId;
        this.firstName = firstName;
        this.lastName = lastName;
       
    }

    
    
    
    public String getBeneficiaryId() { 
    	return id;
    }
    
    public void setBeneficiaryId(String beneficiaryId) { 
    	this.id = beneficiaryId;
    }
    
    
    
    public String getFirstName() {
    	return firstName; 
    }
    
    public void setFirstName(String firstName) { 
    	this.firstName = firstName; 
    }
    
    
    
    public String getLastName() { 
    	return lastName; 
    }
    
    public void setLastName(String lastName) { 
    	this.lastName = lastName; 
    }
   
}

/**
 * @(#)Name.java
 *
 *
 * @author 	Goh Kok Dong
 * @version 1.00 2019/8/9
 */


public class Name {

	private String firstName;
	private String lastName;
	
    public Name() {
    }
    
    public Name(String firstName, String lastName) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    }
    
    // Getter
    public String getFName() {
    	return firstName;
    }
    
    public String getLName() {
    	return lastName;
    }
    
    public String getName() {
    	return firstName + " " + lastName;
    }
    
    // Setter
    public void setFName(String firstName) {
    	this.firstName = firstName;
    }
    
    public void setLName(String lastName) {
    	this.lastName = lastName;
    }
    
    // String
    public String toString() {
    	return String.format("%-20s %-20s", firstName, lastName);
    }
}
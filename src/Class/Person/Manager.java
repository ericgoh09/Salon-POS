/**
 * @(#)Manager.java
 *
 *
 * @author 	Goh Kok Dong
 * @		Manager details
 * @version 1.00 2019/8/9
 */


public class Manager extends Person {

	private String managerID;
	private String password;
	private static int managerNo = 1;
	
    public Manager() {
    }
    
    public Manager(String firstName, String lastName, String password) {
    	super(new Name(firstName, lastName));
    	managerID = "A" + String.format("%02d", managerNo);
    	this.password = password;
    	managerNo++;
    }
    
    // Getter
    public String getManagerID() {
    	return managerID;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public String getName() {
    	return super.getName();
    }
    
    public String toString() {
    	return String.format("%-16s", managerID) + super.toString();
    }
}
/**
 * @(#)Staff.java
 *
 *
 * @author 	Goh Kok Dong
 * @ 		Staff details
 * @version 1.00 2019/8/8
 */

import java.util.Date;
import java.text.SimpleDateFormat;

public class Staff extends Person{
	
	private String staffID;
	private Date hiredDate;
	private static int staffNo = 1;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	

    public Staff() {
    }
    
    public Staff(String firstName, String lastName) {
		super(new Name(firstName, lastName));
		staffID = "S" + String.format("%05d", staffNo);
		hiredDate = new Date();
		staffNo++;
    }
    
    public String getName() {
    	return super.getName();
    }
    
    public String toString() {
    	return String.format("%-16s", staffID) + super.toString() + String.format("%s", sdf.format(hiredDate));
    }
    
    
}
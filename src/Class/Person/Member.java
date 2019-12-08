/**
 * @(#)Member.java
 *
 *
 * @author 	Goh Kok Dong
 * @ 		Membership info
 * @version 1.00 2019/8/9
 */

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Member extends Person {

	private String memberID;
	private Date regisDate;
	private static int memberNo = 1;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
    public Member() {
    	memberID = "";
    	regisDate = new Date();
    	memberNo++;
    }
    
    public Member(String firstName, String lastName) {
    	super(new Name(firstName, lastName));
    	memberID = "M" + String.format("%06d", memberNo);
    	regisDate = new Date();
    	memberNo++;
    }
    
    // Getter
    public String getMemberID() {
    	return memberID;
    }
    
    public String getName() {
    	return super.getName();
    }
    
    // String
    public String toString() {
    	return String.format("%-12s", memberID) + super.toString() + String.format("%s", sdf.format(regisDate));
    }
    
}
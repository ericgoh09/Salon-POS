/**
 * @(#)Haircare.java
 *
 *
 * @author 	Goh Kok Dong
 * @		Haircare
 * @version 1.00 2019/8/11
 */


public class HairCare extends Service {

	private static int hairCareNo = 1;
	
    public HairCare() {
    }
    
    public HairCare(String desc, double price) {
    	super(desc, price);
    	
    	this.treatmentID = "HC" + String.format("%03d", hairCareNo);
    	hairCareNo++;
    }
   	
    public String toString() {
    	return super.toString();
    }
}
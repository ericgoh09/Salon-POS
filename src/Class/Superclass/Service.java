/**
 * @(#)Service.java
 *
 *
 * @author 	Goh Kok Dong
 * @ 		Superclass for all services
 * @version 1.00 2019/8/9
 */

import java.util.Date;

public abstract class Service {
	
	protected String treatmentID;
	protected String desc;
	protected double price;
	
    public Service() {
    }
    
    public Service(String desc, double price) {
    	this.desc = desc;
    	this.price = price;
    }
    
    public String getTreatmentID() {
    	return treatmentID;
    }
   	
   	public String getDesc() {
   		return desc;
   	}
   	
    public double getPrice() {
    	return price;
    }
    
    public void setDesc(String desc) {
    	this.desc = desc;
    }
    
    public void setPrice(double price) {
    	this.price = price;
    }
    
    public String toString() {
    	return String.format("%-16s%-30s%-8.2f", treatmentID, desc, price);
    }
}
/**
 * @(#)Product.java
 *
 *
 * @author 	Goh Kok Dong
 * @		Product records
 * @version 1.00 2019/8/8
 */
 // not done

public class Product {
	
	private String prodID;
	private String desc;
	private double price;
	private static int prodNo = 1;
	
	public Product() {
	}
	
    public Product(String desc, double price) {
    	this.price = price;
    	this.desc = desc;
		this.prodID = "P" + String.format("%03d", prodNo);
		prodNo++;
    }
    
    public String getProdID() {
    	return this.prodID;
    }
    
    public double getPrice() {
    	return this.price;
    }
    
    public String getDesc() {
    	return this.desc;
    }
  	
  	public void setPrice(double price) {
    	this.price = price;
    }
    
    public void setDesc(String desc) {
    	this.desc = desc;
    }
    
    // String
    public String toString() {
    	return String.format("%-16s%-45s%-8.2f", prodID, desc, price);
    }
    
}
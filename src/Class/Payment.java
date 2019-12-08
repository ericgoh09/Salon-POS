/**
 * @(#)Payment.java
 *
 *
 * @author 	Goh Kok Dong
 * @version 1.00 2019/8/16
 */


public class Payment {
	
	private double totalAmtPay;
	private double totalPayment;
	private double totalCharges;
	private double sstCharges;
	private double discount;
	private double changes;
	
    public Payment() {
    	this.totalPayment = 0;
    	this.totalCharges = 0;
    	this.sstCharges = 0;
    	this.discount = 0;
    	this.totalAmtPay = 0;
    }
    
    // Getter
    public double getTotalPayment() {
    	return totalPayment;
    }
    public double getTotalCharges() {
    	return totalCharges;
    }
    public double getSSTCharges() {
    	return sstCharges;
    }
    public double getDiscount() {
    	return discount;
    }
    public double getTotalAmtPay() {
    	return totalAmtPay;
    }
    public double getChanges() {
    	return changes;
    }
    
    // Setter
    public void setTotalPayment(double totalPayment) {
    	this.totalPayment = totalPayment;
    }
    public void setTotalCharges(double totalCharges) {
    	this.totalCharges = totalCharges;
    }
    public void setSSTCharges(double sstCharges) {
    	this.sstCharges = sstCharges;
    }
    public void setDiscount(double discount) {
    	this.discount = discount;
    }
    public void setTotalAmtPay(double totalAmtPay) {
    	this.totalAmtPay = totalAmtPay;
    }
    public void setChanges(double changes) {
    	this.changes = changes;
    }
    
    // Addition
    public void addTotalPayment(double amt) {
    	this.totalPayment += amt;
    }
    public void addTotalCharges(double amt) {
    	this.totalCharges += amt;
    }
    public void addDiscount(double amt) {
    	this.discount += amt;
    }
    public void addTotalAmtPay(double amt) {
    	this.totalAmtPay += amt;
    }
    
    // String
    public String toString() {
    	return String.format("%-8.2f %-8.2f %-8.2f %-8.2f %-8.2f %-8.2f", totalAmtPay, totalPayment, totalCharges, sstCharges, discount, changes);
    }
}
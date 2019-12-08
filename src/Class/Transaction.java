/**
 * @(#)Payment.java
 *
 *
 * @author 	Goh Kok Dong
 * @version 1.00 2019/8/13
 */

import java.util.Date;
import java.text.SimpleDateFormat;

public class Transaction {
	// Transaction Records
	private String transID;
	private Date date;
	private static int transNo = 1;
	
	// For single transaction
	private Payment payment;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Transaction(){
		this.date = new Date();
		this.transID = "T" + String.format("%06d", transNo);
		transNo++;
		payment = new Payment();
	}

	public Transaction(double totalPayment, double totalCharges) {
		this.date = new Date();
		this.transID = "";
		payment = new Payment();
		payment.setTotalPayment(totalPayment);
		payment.setTotalCharges(totalCharges);
	}
    
    // Getter
    public double getTotalPayment() {
    	return payment.getTotalPayment();
    }
    public double getTotalCharges() {
    	return payment.getTotalCharges();
    }
    public double getTotalAmtPay() {
    	return payment.getTotalAmtPay();
    }
    public double getChanges() {
    	return payment.getChanges();
    }
    public double getDiscount() {
    	return payment.getDiscount();
    }
    public double getSSTCharges() {
    	return payment.getSSTCharges();
    }
    
    public static int getTransNo() {
    	return transNo;
    }
    
    // Setter
    
    public void setTotalPayment(double totalPayment) {
    	payment.setTotalPayment(totalPayment);
    }
    public void setTotalCharges(double totalCharges) {
    	payment.setTotalCharges(totalCharges);
    }
    public void setTotalAmtPay(double totalAmtPay) {
    	payment.setTotalAmtPay(totalAmtPay);
    }
    public void setChanges(double changes) {
    	payment.setChanges(changes);
    }
    public void setDiscount(double discount) {
    	payment.setDiscount(discount);
    }
    public void setSSTCharges(double sstCharges) {
    	payment.setSSTCharges(sstCharges);
    }
    
    // Addition
    public void addTotalPayment(double amt) {
    	payment.addTotalPayment(amt);
    }
    public void addTotalCharges(double amt) {
    	payment.addTotalCharges(amt);
    }
    public void addDiscount(double amt) {
    	payment.addDiscount(amt);
    }
    public void addTotalAmtPay(double amt) {
    	payment.addTotalAmtPay(amt);
    }
    
    // Deduction
    public static void deductTransNo() {
    	transNo--;
    }
    
    // String
    public String toString() {
    	return String.format("%-10s %-10s", transID, sdf.format(date));
    }
    
}
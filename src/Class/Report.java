/**
 * @(#)Report.java
 *
 *
 * @author 	Goh Kok Dong
 * @ 		Daily sales report
 * @version 1.00 2019/8/8
 */
 
import java.util.Date;
import java.text.SimpleDateFormat;

public class Report {	

	private Date rptDate;
	
	// Daily Sales Report
	private int [] qty = new int[12];
    private double[] salesAmt = new double[12];
    private double totalDiscount;
	private double totalSSTCharges;
	private double totalBank;
	private double totalRM;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public Report() {
    	rptDate = new Date();
    	for (int i = 0; i < qty.length; i++) {
			qty[i] = 0;
			salesAmt[i] = 0;
		}
		
		this.totalDiscount = 0;
		this.totalSSTCharges = 0;
		this.totalBank = 0;
		this.totalRM = 0;
    }
    
    // Getter
    public int[] getQty() {
    	return qty;
    }
    public double[] getSalesAmt() {
    	return salesAmt;
    }
	public double getTotalDiscount() {
    	return totalDiscount;
    }
    public double getTotalSSTCharges() {
    	return totalSSTCharges;
    }
    public double getTotalBank() {
    	return totalBank;
    }
    public double getTotalRM() {
    	return totalRM;
    }
    
	// Setter
	public void setQty(int[] qty) {
    	this.qty = qty;
    }
    public void setSalesAmt(double[] salesAmt) {
    	this.salesAmt = salesAmt;
    }
    public void setTotalDiscount(double totalDiscount) {
    	this.totalDiscount = totalDiscount;
    }
    public void setTotalSSTCharges(double totalSSTCharges) {
    	this.totalSSTCharges = totalSSTCharges;
    }
    public void setTotalBank(double totalBank) {
    	this.totalBank = totalBank;
    }
    public void setTotalRM(double totalRM) {
    	this.totalRM = totalRM;
    }
    
    // Addition
    public void addQty(int[] amt) {
    	for(int i = 0; i < qty.length; i++) {
    		this.qty[i] += amt[i];
    	}
    }
    public void addSalesAmt(double[] amt) {
    	for(int i = 0; i < salesAmt.length; i++) {
    		this.salesAmt[i] += amt[i];
    	}
    }
    public void addTotalDiscount(double amt) {
    	this.totalDiscount += amt;
    }
    public void addTotalSSTCharges(double amt) {
    	this.totalSSTCharges += amt;
    }
    public void addTotalBank(double amt) {
    	this.totalBank += amt;
    }
    public void addTotalRM(double amt) {
    	this.totalRM += amt;
    }
    
    // String
    public String toString() {
    	return String.format("%-20s", sdf.format(rptDate));	
    }
    
}
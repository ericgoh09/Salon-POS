/**
 * @(#)SkinCare.java
 *
 *
 * @author 	Goh Kok Dong
 * @ 		Face treament services
 * @version 1.00 2019/8/10
 */

import java.util.Date;
import java.text.SimpleDateFormat;

public class SkinCare extends Service {
	
	private static int skinCareNo = 1;
	
	private final double DEADCELLSREMOVALFEES = 105.00;
	private final double CELLRESTOREFEES = 141.00;
	private final double PIMPLETREATMENTFEES = 126.00;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public SkinCare() {
    }
    
    public SkinCare(String desc) {
    	this.desc = desc;
    	this.price = getTreatmentFees(desc);
		
    	this.treatmentID = "SC" + String.format("%03d", skinCareNo);
    	skinCareNo++;
    }
    
    public double getDeadCellsRemovalFees() {
    	return DEADCELLSREMOVALFEES;
    }
    
    public double getCellRestoreFees() {
    	return CELLRESTOREFEES;
    }
    
    public double getPimpleTreatmentFees() {
    	return PIMPLETREATMENTFEES;
    }
    
    public double getTreatmentFees(String desc) {
    	if(desc.toUpperCase().equals("DEAD CELLS REMOVAL TREATMENT"))
			return DEADCELLSREMOVALFEES;
		else if(desc.toUpperCase().equals("CELL REJUVENATING TREATMENT"))
			return CELLRESTOREFEES;
		else if(desc.toUpperCase().equals("PIMPLE AND SCAR TREATMENT"))
			return PIMPLETREATMENTFEES;
		return 0;
    }
	
	public String toString() {
		return super.toString();
	}
}
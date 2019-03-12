package model.inspection;

import java.util.Date;

public class Inspection {
    private int inspectionID;
    private String inspectionType;
    private Date inspectionDate;
    private String inspector;
    private String outcome;
    
	public int getInspectionID() {
		return inspectionID;
	}
	public void setInspectionID(int inspectionID) {
		this.inspectionID = inspectionID;
	}
	public String getInspectionType() {
		return inspectionType;
	}
	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}
	public Date getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
}

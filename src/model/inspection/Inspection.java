package model.inspection;
import java.util.Date;

public class Inspection extends InspectionAbstract{
	public Inspection(InspectionInterface inspection){
		super(inspection);
	}
	public int getInspectionID(){
		return getInspectionID();
	}
	public void setInspectionID(int inspectionID){
		setInspectionID(inspectionID);
	}

	public String getInspectionType() {
		return getInspectionType();
	}
	public void setInspectionType(String inspectionType) {
		setInspectionType(inspectionType);
	}

	public Date getInspectionDate() {
		return getInspectionDate();
	}
	public void setInspectionDate(Date inspectionDate) {
		setInspectionDate(inspectionDate);
	}

	public String getInspector() {
		return getInspector();
	}
	public void setInspector(String inspector) {
		setInspector(inspector);
	}

	public String getInspectionOutcome() {
		return getInspectionOutcome();
	}
	public void setInspectionOutcome(String inspectionOutcome) {
		setInspectionOutcome(inspectionOutcome);
	}
}

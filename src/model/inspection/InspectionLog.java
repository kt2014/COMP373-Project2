package model.inspection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class InspectionLog {
   
    private List<Inspection> inspections = new ArrayList<Inspection>();

    public List<Inspection> listInspection() {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for (Inspection i : inspections) {        	
        	System.out.println("The inspection " + i.getInspectionID() + " of " + i.getInspectionType() + 
        			" type was done by inspector" + i.getInspector() + " on "+ df.format(i.getInspectionDate()) + 
        			" with " + i.getOutcome() + " outcome.");
        }
        return inspections;
    }

	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}
	
	public Inspection addInspection(Inspection inspection) {
		inspections.add(inspection);
		System.out.println("Inspection added.");
		return inspection;
	}
}

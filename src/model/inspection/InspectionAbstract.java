package model.inspection;
import java.util.Date;

public abstract class InspectionAbstract {
    private InspectionInterface inspection;

    public InspectionAbstract(InspectionInterface inspection){
        this.inspection = inspection;
    }

   public int getInspectionID(){
        return inspection.getInspectionID();
   }
   public void setInspectionID(int inspectionID){
        inspection.setInspectionID(inspectionID);
   }

    public String getInspectionType(){
        return inspection.getInspectionType();
    }
    public void setInspectionType(String inspectionType){
        inspection.setInspectionType(inspectionType);
    }

    public Date getInspectionDate(){
        return inspection.getInspectionDate();
    }
    public void setInspectionDate(Date inspectionDate){
        inspection.setInspectionDate(inspectionDate);
    }

    public String getInspector(){
        return inspection.getInspector();
    }
    public void setInspector(String inspector){
        inspection.setInspector(inspector);
    }

    public String getInspectionOutcome(){
        return inspection.getInspectionOutcome();
    }
    public void setInspectionOutcome(String inspectionOutcome){
        inspection.setInspectionOutcome(inspectionOutcome);
    }
}


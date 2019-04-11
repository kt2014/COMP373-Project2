package model.inspection;
import java.util.Date;

public interface InspectionInterface {

    public int getInspectionID();
    public void setInspectionID(int inspectionID);

    public String getInspector();
    public void setInspector(String inspector);

    public Date getInspectionDate();
    public void setInspectionDate(Date inspectionDate);

    public String getInspectionType();
    public void setInspectionType(String inspectionType);

    public String getInspectionOutcome();
    public void setInspectionOutcome(String inspectionOutcome);




}

package model.facility;

import java.util.Map;
import model.inspection.InspectionLog;
import model.facilityMaintenance.MaintenanceLog;
import model.facilityUse.UsageLog;

public interface Facility {
	public void setInspectionLog(InspectionLog log);
	public void setFacilityUsage(UsageLog log);
	public void setMaintenanceLog(MaintenanceLog log);
	public InspectionLog getInspectionLog();
	public UsageLog getUsageLog();
	public MaintenanceLog getMaintenanceLog();
	public int getFacilityID();
	public Map<String, String> getFacilityInfo();
}

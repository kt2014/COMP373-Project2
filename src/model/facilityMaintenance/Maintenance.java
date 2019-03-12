package model.facilityMaintenance;

import java.util.*;

public class Maintenance {
    private int maintenanceID;
    private String status = "pending";
    private boolean priority;
    private String maintenanceType;
    private String problemDescription;

    private MaintSchedule schedule;

    public MaintSchedule getSchedule() {
        return schedule;
    }
    
    public void setSchedule(MaintSchedule schedule) {
        this.schedule = schedule;
    }

    //creates new MaintSchedule object and sets schedule time
    public MaintSchedule scheduleMaintenance(Date date) {
    	MaintSchedule s = new MaintSchedule(date);
        setSchedule(s);
        setStatus("scheduled");
        return s;
    }

	public int getMaintenanceID() {
		return maintenanceID;
	}

	public void setMaintenanceID(int maintenanceID) {
		this.maintenanceID = maintenanceID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	public String getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}


}

package model.facilityMaintenance;

import java.util.Date;

public class MaintSchedule {
	private int scheduleID;
    private Date dateScheduled;
    private MaintService service = new MaintService();
    
    public MaintSchedule(Date date) {
    	setDateScheduled(date);
    	setService(new MaintService());
    }
    
	public int getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(int scheduleID) {
		this.scheduleID = scheduleID;
	}
	public Date getDateScheduled() {
		return dateScheduled;
	}
	public void setDateScheduled(Date dateScheduled) {
		this.dateScheduled = dateScheduled;
	}
	public MaintService getService() {
		return service;
	}
	public void setService(MaintService service) {
		this.service = service;
	} 
}

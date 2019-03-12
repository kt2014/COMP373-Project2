package model.facilityMaintenance;

import java.util.Date;

public class MaintRequest {
    private int requestID;
    private Date dateRequested;
    private String textDescription;

    private Maintenance maintenance;
    
    public MaintRequest() {
    	setMaintenance(new Maintenance());
    }

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public Date getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}

	public String getTextDescription() {
		return textDescription;
	}

	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}

	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintanence) {
		this.maintenance = maintanence;
	}
}

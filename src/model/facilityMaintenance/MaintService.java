package model.facilityMaintenance;

import java.sql.Timestamp;

public class MaintService {
    private int serviceID;
    private Timestamp serviceTime;
    private String description;
    
    private MaintCost cost = new MaintCost();

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public Timestamp getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Timestamp serviceTime) {
		this.serviceTime = serviceTime;
	}

	public MaintCost getCost() {
		return cost;
	}

	public void setCost(MaintCost cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

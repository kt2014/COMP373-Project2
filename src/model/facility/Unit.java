package model.facility;

import java.util.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;

import model.facilityMaintenance.MaintenanceLog;
import model.facilityUse.UsageLog;
import model.inspection.InspectionLog;

public class Unit implements Facility{
	private int facilityID;
	private String roomName;
	private String roomOwner;
	private String usageType;
	private Address address;
	private Manager manager;
	private InspectionLog inspectionLog;
	private MaintenanceLog maintenanceLog;
	private UsageLog usageLog;
	
	public Unit() {
		setInspectionLog(new InspectionLog());
		setMaintenanceLog(new MaintenanceLog());
		setFacilityUsage(new UsageLog());
	}

	//dependency injection
	@Override
	public void setInspectionLog(InspectionLog log) {
		this.inspectionLog = log;		
	}

	@Override
	public void setFacilityUsage(UsageLog log) {
		this.usageLog = log;
	}

	@Override
	public void setMaintenanceLog(MaintenanceLog log) {
		this.maintenanceLog = log;		
	}

	//getters and setters
	@Override
	public InspectionLog getInspectionLog() { return inspectionLog;	}

	@Override
	public UsageLog getUsageLog() { return usageLog; }

	@Override
	public MaintenanceLog getMaintenanceLog() { return maintenanceLog; }
	
	@Override
	public int getFacilityID() { return facilityID;	}
	public void setFacilityID(int id) {	this.facilityID = id; }
	
	public String getRoomName() { return roomName; }
	public void setRoomName(String roomName) { this.roomName = roomName; }
	
	public String getRoomOwner() {	return roomOwner; }
	public void setRoomOwner(String roomOwner) { this.roomOwner = roomOwner; }
	
	public String getUsageType() {	return usageType; }
	public void setUsageType(String usageType) { this.usageType = usageType; }
	
	public Address getAddress() { return address; }
	public void setAddress(Address address) { this.address = address; }
	
	public Manager getManager() { return manager; }
	public void setManager(Manager manager) { this.manager = manager; }
	
	
	@Override
	public Map<String, String> getFacilityInfo() {
		Map<String, String> info = new HashMap<String, String>();
		info.put("id", Integer.toString(this.getFacilityID()));
		info.put("name", this.getRoomName());
		info.put("address", this.getAddress().toString());
		info.put("owner", this.getRoomOwner());
		info.put("manager", this.getManager().getFirstName() + " " + this.getManager().getLastName());
		info.put("type", this.getUsageType());		
		
		for (Map.Entry<String, String> entry : info.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		return info;
	}
}


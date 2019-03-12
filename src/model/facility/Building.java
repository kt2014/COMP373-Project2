package model.facility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import model.facilityMaintenance.MaintenanceLog;
import model.facilityUse.Usage;
import model.facilityUse.UsageLog;
import model.inspection.InspectionLog;

public class Building implements Groups {
	private int facilityID;
	private String buildingName;
	private String buildingOwner;
	private Date constructionDate;
	private List<Facility> rooms = new ArrayList<Facility>();
	private InspectionLog inspectionLog;
	private MaintenanceLog maintenanceLog;
	private UsageLog usageLog;
	
	public Building() {
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
	public void setFacilityID(int id) {	this.facilityID = id;}

	public String getBuildingName() { return buildingName; }
	public void setBuildingName(String buildingName) { this.buildingName = buildingName; }

	public String getBuildingOwner() { return buildingOwner; }
	public void setBuildingOwner(String buildingOwner) { this.buildingOwner = buildingOwner; }
	
	public Date getConstructionDate() {	return constructionDate; }
	public void setConstructionDate(Date constructionDate) { this.constructionDate = constructionDate; }

	public List<Facility> getRooms() {	return rooms; }
	public void setRooms(Unit room) {
		addNewFacility(room);
	}
	
	
	@Override
	public List<Facility> listFacility() {
		for (Facility r: rooms) {
			System.out.println("Unit ID: " + r.getFacilityID());
		}
		return rooms;
	}

	@Override
	public Map<String, String> getFacilityInfo() {
		Map<String, String> info = new HashMap<String, String>();
		info.put("id", Integer.toString(this.getFacilityID()));
		info.put("name", this.getBuildingName());
		info.put("owner", this.getBuildingOwner());
		info.put("type", "group");		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		info.put("construction date", df.format(constructionDate));
		for (Map.Entry<String, String> entry : info.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
		return info;
	}

	@Override
	public Facility addNewFacility(Facility facility) {
		rooms.add(facility);
		System.out.println("Facility with ID: " + facility.getFacilityID() + " was added.");
		return facility;
	}

	@Override
	public void removeFacility(int id) {
		for (Facility r: rooms) {
			if (r.getFacilityID() == id) {
				rooms.remove(r);
				System.out.println("Unit with ID: " + id + " was removed from building " + getFacilityID());
				return;
			}
		}
		System.out.println("Facility with ID: " + id + " was not found in the building " + getFacilityID());
		
	}

	// returns a list of rooms that are not currently in use
	@Override
	public List<Facility> requestAvailFacility() {
		List<Facility> availableFacility = new ArrayList<>();
		for(Facility f : rooms) {
			Usage u = f.getUsageLog().getUsages().get(0);
			
			//available facility
			if (u.getEndDate() == null) {
				availableFacility.add(f);
			}
		}
		return availableFacility;
	}
}


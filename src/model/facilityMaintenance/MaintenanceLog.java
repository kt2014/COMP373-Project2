package model.facilityMaintenance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class MaintenanceLog {
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private List<MaintRequest> requests = new ArrayList<MaintRequest>();

    //returns a list of requests with not scheduled maintenances
    //and prints to console information about them
    public List<MaintRequest> listFacilityProblems() {
    	List<MaintRequest> problems = new ArrayList<>();
        for (MaintRequest r : requests) {
        	if (r.getMaintenance().getStatus().equals("pending")) {
        		problems.add(r);
        		System.out.println("MaintRequest " + r.getRequestID() + " : " + r.getTextDescription() + 
        				" was made on " + df.format(r.getDateRequested()));
        	}
        }
        return problems;
    }
    
    //creates a new MaintRequest object and returns it back
    public  MaintRequest makeFacilityMaintenanceRequest() {
        MaintRequest r = new MaintRequest();
        requests.add(r);
        return r;
    }

    //prints to console info about all requests for maintenance
    //and returns the list of them
    public List<MaintRequest> listMaintRequest(){
    	for (MaintRequest r : requests) {
        	System.out.println("MaintRequest " + r.getRequestID() + " : " + r.getTextDescription() + 
        				" was made on " + df.format(r.getDateRequested()));
        }
        return requests;
    }

    //prints to console info about all maintenances of the facility
    //and returns the list of them
    public List<Maintenance> listMaintenance() {
    	List<Maintenance> maintenances = new ArrayList<>();
    	for (MaintRequest r : requests) {
    		Maintenance m = r.getMaintenance();
    		maintenances.add(m);
        	System.out.println("Maintenance " + m.getMaintenanceID() + " of " + m.getMaintenanceType() + " type " +
        				" is currently in " + m.getStatus() + " status.");
        }
        return maintenances;
    }

    //calculates down time of facility in days for a given period of time
    public double calcDowntime(Date start, Date end) {
    	double downtime = 0;
    	for (MaintRequest r: requests) {
    		Date rDate = r.getDateRequested();
    		if (rDate.after(start) && rDate.before(end)) {
    			double currentDowntimeInDays = 0;
        		if (r.getMaintenance().getSchedule() != null) {
        			Date scheduleDate = r.getMaintenance().getSchedule().getDateScheduled();
        			if (scheduleDate.before(end)) {
        				currentDowntimeInDays = (scheduleDate.getTime() - rDate.getTime()) / (1000.0 * 60 * 60 * 24);
        			} else {
        				currentDowntimeInDays = (end.getTime() - rDate.getTime()) / (1000.0 * 60 * 60 * 24);
        			}
        			downtime += currentDowntimeInDays;
        		}
    		}
    	}
        return downtime;
    }

    //calculates percentage of down time from the given period of time 
    public double calcProblemRateForFacility(Date start, Date end) {
    	double diffInDays = (end.getTime() - start.getTime()) / (1000.0 * 60 * 60 * 24);
    	double downtime = calcDowntime(start, end);
        return downtime / diffInDays * 100.0;
    }

	public List<MaintRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<MaintRequest> requests) {
		this.requests = requests;
	}
}

package model.facilityUse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class UsageLog {
    private List<Usage> usages = new ArrayList<Usage>();
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	//calculates usage rate in percentages during given interval
	//assuming facility can have one usage at a time
    public double calcUsageRate(Date start, Date end) {
    	int diffInDays = (int) ((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
    	int usageDays = 0;
    	for (Usage u : usages) {
    		Date usageStartDate = u.getStartDate();
    		Date usageEndDate = u.getEndDate();
    		int currentUsageInDays = 0;
    		if (usageStartDate.after(start) && usageStartDate.before(end)) {
        		if (usageEndDate != null && usageEndDate.before(end)) {
        			currentUsageInDays = (int) ((usageEndDate.getTime() - usageStartDate.getTime()) / (1000 * 60 * 60 * 24));        			
        		} else {
        			currentUsageInDays = (int) ((end.getTime() - usageStartDate.getTime()) / (1000 * 60 * 60 * 24));
        		}
        		
    		} else if (usageStartDate.before(start)) {
    			if (usageEndDate == null) {
    				currentUsageInDays = diffInDays;
    			} else if (usageEndDate.after(start)) {
    				currentUsageInDays = (int) ((usageEndDate.getTime() - start.getTime()) / (1000 * 60 * 60 * 24)); 	
    			}
    		}
    		usageDays += currentUsageInDays;
    	}
        return ((double)usageDays)/diffInDays*100.0;
    }

    //prints to console info about all usages of the facility
    //and returns the list of them
    public List<Usage> listActualUsage() {
    	for (Usage u : usages) {        	
        	System.out.print(u.getUser().getFirstName() + " " + u.getUser().getLastName() + 
        			" started using this facility on " + df.format(u.getStartDate()) + " for " +
        			u.getUsageType() + " purpose");
        	if (u.getEndDate() != null) {
        		System.out.println(" and was using it until " + df.format(u.getEndDate()));
        	} else {
        		System.out.println();
        	}
        }
        return usages;
    }

    //sets the most recent Usage end usage date to todays date
    //and returns that Usage
    public Usage vacateFacility() {
        Usage currentUsage = usages.get(0);
        if (currentUsage.getEndDate() == null) {
        	Date todaysDate = Calendar.getInstance().getTime();
        	currentUsage.setEndDate(todaysDate);
        	System.out.println("The facility has been vacated!");
        } else {
        	System.out.println("The facility is currently vacant!");
        }
        return currentUsage;
    }

    //creates new Usage object
    //and returns it back
    public Usage assignFacilityToUse() {
        Usage newUsage = new Usage();
        usages.add(0, newUsage);
        return newUsage;
    }

    //returns true if the facility was in use (even partially) during given interval 
    public boolean isInUseDuringInterval(Date start, Date end) {
    	for (Usage u : usages) {
    		Date usageStartDate = u.getStartDate();
    		Date usageEndDate = u.getEndDate();
    		if (usageStartDate.after(start) && usageStartDate.before(end)) {
    			return true;
    		} else if (usageStartDate.before(start) && ((usageEndDate == null) || usageEndDate.after(start))) {
    			return true;
    		}
    	}
        return false;
    }

	public List<Usage> getUsages() {
		return usages;
	}

	public void setUsages(List<Usage> usages) {
		this.usages = usages;
	}

}

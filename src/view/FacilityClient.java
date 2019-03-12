package view;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import dal.*;
import model.facility.*;
import model.facilityMaintenance.*;
import model.facilityUse.*;
import model.inspection.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class FacilityClient{	
	public static void main (String args[]) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = fmt.parse("20013-02-13");
		Date date2 = fmt.parse("2016-10-10");
		
		Timestamp timestamp = new Timestamp(date1.getTime());
		
		FacilityDAO facilityDAO = new FacilityDAO();
		FacilityUseDAO facilityUseDAO = new FacilityUseDAO();
		FacilityMaintenanceDAO facilityMaintDAO = new FacilityMaintenanceDAO();
		InspectionDAO inspectionDAO = new InspectionDAO();

		FacilityGroup group = new FacilityGroup();
		group.setFacilityID(1);
		group.setGroupName("Information Commons");
		group.setGroupOwner("Michael");

		/**********************************************************************/
		//Bootstrapping the Building instantiation using factory
		Building building1 = (Building) context.getBean("building");
		building1.setFacilityID(2);
		building1.setBuildingName("Corboy Law Center");
		building1.setBuildingOwner("Mary");
		building1.setConstructionDate(date1);		
		
		group.addNewFacility(building1);
		
//		Adding room1 to building 1
		Unit room1 = new Unit();
		room1.setFacilityID(20);
		room1.setRoomName("Conference Unit");
		room1.setRoomOwner("Karina");
		room1.setUsageType("Business");
		
		Address room1Address = new Address();
		room1Address.setAddressID(1);
		room1Address.setUnitNumber(22);
		room1Address.setStreetNumber(25);
		room1Address.setStreet("Pearson");
		room1Address.setCity("Chicago");
		room1Address.setStateProvince("Illinois");
		room1Address.setZipCode(60611);
		
		room1.setAddress(room1Address);
		
		Manager manager1 = new Manager();
		manager1.setManagerID(3344);
	    manager1.setFirstName("George");
		manager1.setLastName("Simon");
		manager1.setCompanyName("Information Commons");
		manager1.setPhoneNumber("8722233335");
		
		room1.setManager(manager1);
		building1.addNewFacility(room1);
		
//		Adding building2 to FacilityGroup		
		Building building2 = new Building();
		building2.setFacilityID(11);
		building2.setBuildingName("Baumhart Hall");
		building2.setBuildingOwner("Sandy");
		building2.setConstructionDate(date2);
		
		group.addNewFacility(building2);
		
//		Adding room 2 to building 2
		Unit room2 = new Unit();
		room2.setFacilityID(2);
		room2.setRoomName("Library");
		room2.setRoomOwner("Mike");
		room2.setUsageType("Business");
		
		Address room2Address = new Address();
		room2Address.setAddressID(2);
		room2Address.setUnitNumber(2);
		room2Address.setStreetNumber(44);
		room2Address.setStreet("Clark");
		room2Address.setCity("Chicago");
		room2Address.setStateProvince("Illinois");
		room2Address.setZipCode(60003);
		
		room2.setAddress(room2Address);	
		
		Manager manager2 = new Manager();
		manager2.setManagerID(456);
	    manager2.setFirstName("Gary");
		manager2.setLastName("Becker");
		manager2.setCompanyName("Student Space");
		manager2.setPhoneNumber("532523522");
		
		room2.setManager(manager2);
		
		building2.addNewFacility(room2);
		
//		Adding room 3 to building 2
		Unit room3 = new Unit();
		room3.setFacilityID(3);
		room3.setRoomName("Cafe");
		room3.setRoomOwner("Katrin");
		room3.setUsageType("Business");
		
		Address room3Address = new Address();
		room3Address.setAddressID(3);
		room3Address.setUnitNumber(3);
		room3Address.setStreetNumber(300);
		room3Address.setStreet("Wabash");
		room3Address.setCity("Chicago");
		room3Address.setStateProvince("Illinois");
		room3Address.setZipCode(60610);
		
		room3.setAddress(room3Address);
		
		Manager manager3 = new Manager();
		manager3.setManagerID(789);
	    manager3.setFirstName("Mery");
		manager3.setLastName("Stewart");
		manager3.setCompanyName("Italy Pizzeria");
		manager3.setPhoneNumber("53523333");
		room3.setManager(manager3);
		
//		Facility Maintenance
//		New maintenance request created; scheduled, serviced and cost assigned 
//	    MaintRequest 1
		MaintRequest request1 = new MaintRequest();
	    request1.setRequestID(1004);
	    request1.setDateRequested(date1);
	    request1.setTextDescription("Broken Windows");
	   
	    Maintenance maintenance1 = request1.getMaintenance();
	    maintenance1.setMaintenanceID(4000);
	    maintenance1.setProblemDescription("Front door is broken");
	    maintenance1.setPriority(true);
	    maintenance1.setStatus("Pending");
	    maintenance1.setMaintenanceType("Fixtures");
	   
	    MaintSchedule schedule1 = new MaintSchedule(date1);
	    schedule1.setScheduleID(2);
	    schedule1.setDateScheduled(date1);
	   
	    maintenance1.setSchedule(schedule1);
	    MaintService service1 = schedule1.getService();
	    service1.setServiceID(222);
	    service1.setDescription("MaintService is scheduled at 4 pm");
	    service1.setServiceTime(timestamp);
	   
	    MaintCost cost1 = service1.getCost();
	    cost1.setCostID(25);
	    cost1.setLaborCost(25);
	    cost1.setMaterialCost(50);
	    cost1.setExtra(20);
	   
	   
//      Second request
	    MaintRequest request2 = new MaintRequest();
	    request2.setRequestID(3001);
	    request2.setDateRequested(date2);
	    request2.setTextDescription("There is broken window");
	   
	    Maintenance maintenance2 = request2.getMaintenance();
	    maintenance2.setMaintenanceID(3000);
	    maintenance2.setProblemDescription("Need to fix broken window");
	    maintenance2.setPriority(true);
	    maintenance2.setStatus("Pending");
	    maintenance2.setMaintenanceType("Glass");
	   	     
	    MaintSchedule schedule2 = new MaintSchedule(date2);
	    schedule2.setScheduleID(22);
	    schedule2.setDateScheduled(date2);
	   
	    maintenance2.setSchedule(schedule2);
	   
	    MaintService service2 = schedule2.getService();
	    service2.setServiceID(333);
	    service2.setDescription("MaintService scheduled for next business day");
	    service2.setServiceTime(timestamp);
	   
	    MaintCost cost2 = service2.getCost();
	    cost2.setCostID(26);
	    cost2.setLaborCost(25);
	    cost2.setMaterialCost(50);
	    cost2.setExtra(20);
	    
//	    Facility Use
//	    Usage, user 1
	    Usage usage1 = new Usage();
	    usage1.setUsageID(400);
	    usage1.setUsageType("Business Property");
	    usage1.setStartDate(date1);
	    usage1.setEndDate(date2);
	   
	    User user1 = new User();
	    user1.setUserID(1);
	    user1.setFirstName("Gloria");
	    user1.setLastName("Fernandes");
	    user1.setGender("F");
	   
	    usage1.setUser(user1);
	    
//	    Usage, user 2
	    Usage usage2 = new Usage();
	    usage2.setUsageID(500);
	    usage2.setUsageType("Residential Property");
	    usage2.setStartDate(date1);
	    usage2.setEndDate(date2);
	   
	    User user2 = new User();
	    user2.setUserID(2);
	    user2.setFirstName("Maili");
	    user2.setLastName("Williams");
	    user2.setGender("F");
	   
	    usage2.setUser(user2);
	   
//	    Inspection 1
	    Inspection inspection1 = new Inspection();
	    inspection1.setInspectionID(52);
	    inspection1.setInspectionType("Annual check");
	    inspection1.setInspector("Kaily");
	    inspection1.setInspectionDate(date1);
	    inspection1.setOutcome("All looks good");
	   
//	    Inspection 2 
	    Inspection inspection2 = new Inspection();
	    inspection2.setInspectionID(85);
	    inspection2.setInspectionType("Monthly check");
	    inspection2.setInspector("Sandra");
	    inspection2.setInspectionDate(date2);
	    inspection2.setOutcome("Needs repair");

	    
//	    Insert into database
	    facilityDAO.setFacility(group);
	    facilityUseDAO.setFacilityUse(usage1);
	    facilityUseDAO.setFacilityUse(usage2);
	   
	    facilityMaintDAO.setFacilityMaintenance(request1);
	    facilityMaintDAO.setFacilityMaintenance(request2);
	    
	    facilityUseDAO.setFacilityUse(usage1);
	    facilityUseDAO.setFacilityUse(usage2);
	    
	    inspectionDAO.setInspection(inspection1);
	    inspectionDAO.setInspection(inspection2);
	}
}

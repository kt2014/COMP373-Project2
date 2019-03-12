package dal;

import model.facilityMaintenance.*;
import model.facility.*;
import java.sql.*;


public class FacilityMaintenanceDAO {
	public void setFacilityMaintenance(MaintRequest r) throws Exception {
		Connection connection = DBHelper.getConnection();
		
		PreparedStatement requestps = null;
		PreparedStatement maintenanceps = null;
		PreparedStatement scheduleps = null;
		PreparedStatement serviceps = null;
		PreparedStatement costps = null;
		
		//set MaintRequest
		String st1 = "INSERT INTO MaintRequest (Request_ID, DateRequested, TextDescription) VALUES"
				+ " (?, ?, ?)";
		requestps = connection.prepareStatement(st1);
		requestps.setInt(1, r.getRequestID());
		requestps.setDate(2, new Date(r.getDateRequested().getTime()));
		requestps.setString(3, r.getTextDescription());
		
		requestps.executeUpdate();
		
		// set maintenance
		String st2 = "INSERT INTO Maintenance (Maintenance_ID, Status, Priority, MaintenanceType,"
				+ " IssueDescription, Request_ID) VALUES (?, ?, ?, ?, ?, ?)";
		maintenanceps = connection.prepareStatement(st2);
		Maintenance m = r.getMaintenance();
		maintenanceps.setInt(1, m.getMaintenanceID());
		maintenanceps.setString(2, m.getStatus());
		maintenanceps.setBoolean(3, m.isPriority());
		maintenanceps.setString(4,  m.getMaintenanceType());
		maintenanceps.setString(5, m.getProblemDescription());
		maintenanceps.setInt(6, r.getRequestID());
		
		maintenanceps.executeUpdate();
		
		// set schedule
		String st3 = "INSERT INTO MaintSchedule (Schedule_ID, DateScheduled, Maintenance_ID) "
				+ "VALUES (?, ?, ?)";
		scheduleps = connection.prepareStatement(st3);
		MaintSchedule sch = m.getSchedule();
		scheduleps.setInt(1, sch.getScheduleID());
		scheduleps.setDate(2, new Date(sch.getDateScheduled().getTime()));
		scheduleps.setInt(3, m.getMaintenanceID());
		
		scheduleps.executeUpdate();
		
		// set service
		String st4 = "INSERT INTO MaintService (Service_ID, ServiceTime, Schedule_ID) VALUES (?,"
				+ " ?, ?)";
		serviceps = connection.prepareStatement(st4);
		MaintService s = sch.getService();
		serviceps.setInt(1, s.getServiceID());
		serviceps.setTimestamp(2, s.getServiceTime());
		serviceps.setInt(3, sch.getScheduleID());
		
		serviceps.executeUpdate();
		
		// set cost
		String st5 = "INSERT INTO MaintCost (Cost_ID, LaborCost, MaterialCost, Extra, Service_ID) "
				+ "VALUES (?, ?, ?, ?, ?)";
		costps = connection.prepareStatement(st5);
		MaintCost c = s.getCost();
		costps.setInt(1, c.getCostID());
		costps.setFloat(2, c.getLaborCost());
		costps.setFloat(3, c.getMaterialCost());
		costps.setFloat(4, c.getExtra());
		costps.setInt(5, s.getServiceID());
		
		costps.executeUpdate();
			
		if (costps != null) {
			requestps.close();
			maintenanceps.close();
			scheduleps.close();
			serviceps.close();
			costps.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

		
	 // getter
	public MaintRequest getFacilityMaintenance (int rID) throws Exception {
		Statement st = DBHelper.getConnection().createStatement();
		
	// get MaintRequest
		String st1 = "SELECT Request_ID, DateRequested, TextDescription FROM FacilityUsage "
				+ "WHERE Request_ID = " + rID;
		ResultSet rRS = st.executeQuery(st1);
		MaintRequest r = new MaintRequest();
		while (rRS.next()) {
			r.setRequestID(rRS.getInt("Reqeust_ID"));
			r.setDateRequested(rRS.getTimestamp("DateRequested"));
			r.setTextDescription(rRS.getString("TextDescription"));
		}
		rRS.close();
	
		// get Maintenance
		String st2 = "SELECT Maintenance_ID, Status, Priority, MaintenanceType, IssueDescription"
				+ " FROM Maintenance WHERE Request_ID = " + rID;
		ResultSet mRS = st.executeQuery(st2);
		Maintenance m = new Maintenance();
		while (mRS.next()) {
			m.setMaintenanceID(mRS.getInt("MaintenanceID"));
			m.setStatus(mRS.getString("Status"));
			m.setPriority(mRS.getBoolean("Priority"));
			m.setMaintenanceType(mRS.getString("MainenanceType"));
			m.setProblemDescription(mRS.getString("IssueDescription"));	
		}
		r.setMaintenance(m);
		mRS.close();
		
		// get MaintSchedule
		String st3 = "SELECT Schedule_ID, DateScheduled FROM MaintSchedule WHERE Maintenance_ID = "
				+ m.getMaintenanceID();
		ResultSet schRS = st.executeQuery(st3);
		MaintSchedule sch = new MaintSchedule(new java.util.Date(schRS.getDate("DateScheduled").getTime()));
		while (schRS.next()) {
			sch.setScheduleID(schRS.getInt("schedule_ID"));
		}
		m.setSchedule(sch);
		schRS.close();
		
		// get MaintService
		String st4 = "SELECT Service_ID, ServiceTime FROM MaintService WHERE Schedule_ID = " 
				+ sch.getScheduleID();
		ResultSet sRS = st.executeQuery(st4);
		MaintService s = new MaintService();
		while (sRS.next()) {
			s.setServiceID(sRS.getInt("Service_ID"));
			s.setServiceTime(sRS.getTimestamp("ServiceTime"));
		}
		sch.setService(s);
		sRS.close();
		
		// get MaintCost
		String st5 = "SELECT Cost_ID, LaborCost, MaterialCost, Extra FROM MaintCost WHERE Service_ID"
				+ " = " + s.getServiceID();
		ResultSet cRS = st.executeQuery(st5);
		MaintCost c = new MaintCost();
		while (cRS.next()) {
			c.setCostID(cRS.getInt("Cost_ID"));
			c.setLaborCost(cRS.getFloat("LaborCost"));
			c.setMaterialCost(cRS.getFloat("MaterialCost"));
			c.setExtra(cRS.getFloat("Extra"));
		}
		s.setCost(c);
		cRS.close();
		st.close();	
		
		return r;
	}
}

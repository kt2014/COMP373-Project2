package dal;

import model.facility.*;
import java.sql.*;


public class FacilityDAO {
	// setter
	public void setFacility(FacilityGroup group) throws Exception {
		Connection connection = DBHelper.getConnection();;
		
		PreparedStatement gPS = null;
		PreparedStatement bPS = null;
		PreparedStatement rPS = null;
		PreparedStatement aPS = null;
		PreparedStatement mPS = null;
		
		// set FacilityGroup
		String st1 = "INSERT INTO BuildingGroup (Group_ID, GroupName, GroupOwner) VALUES"
				+ " (?, ?, ?)";		
		gPS = connection.prepareStatement(st1);
		gPS.setInt(1, group.getFacilityID());
		gPS.setString(2, group.getGroupName());
		gPS.setString(3, group.getGroupOwner());
		
		gPS.executeUpdate();
			
		// set Building	
		for (Facility f : group.getBuildings()) {
			String st2 = "INSERT INTO Building (Building_ID, BuildingName, BuildingOwner,"
					+ "ConstructionDate, Group_ID) VALUES (?, ?, ?, ?, ?)";
			bPS = connection.prepareStatement(st2);
			Building b = (Building)f;
			bPS.setInt(1, b.getFacilityID());
			bPS.setString(2, b.getBuildingName());
			bPS.setString(3, b.getBuildingOwner());
			bPS.setDate(4, new Date(b.getConstructionDate().getTime()));
			bPS.setInt(5, group.getFacilityID());
			
			bPS.executeUpdate();
			
			// set Unit	
			for (Facility fr : b.getRooms()) {
				String st3 = "INSERT INTO Unit (Room_ID, RoomName, RoomOwner, UsageType, Building_ID)"
						+ " VALUES (?, ?, ?, ?, ?)";
				rPS = connection.prepareStatement(st3);
				Unit r = (Unit)fr;
				rPS.setInt(1, r.getFacilityID());
				rPS.setString(2, r.getRoomName());
				rPS.setString(3, r.getRoomOwner());
				rPS.setString(4, r.getUsageType());
				rPS.setInt(5, b.getFacilityID());
				
				rPS.executeUpdate();
				
				//set Address
				String st4 = "INSERT INTO Address (Address_ID, UnitNumber, StreetNumber, "
						+ "Street, City, StateProvince, Room_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
				aPS = connection.prepareStatement(st4);
				aPS.setInt(1, r.getAddress().getAddressID());
				aPS.setInt(2, r.getAddress().getUnitNumber());
				aPS.setInt(3, r.getAddress().getStreetNumber());
				aPS.setString(4, r.getAddress().getStreet());
				aPS.setString(5, r.getAddress().getCity());
				aPS.setString(6, r.getAddress().getStateProvince());
				aPS.setInt(7, r.getFacilityID());
				
				aPS.executeUpdate();
				
				//set Manager
				String st5 = "INSERT INTO Manager (Manager_ID, FName, LName, Phone, CompanyName,"
						+ " Room_ID) VALUES (?, ?, ?, ?, ?, ?)";
				mPS = connection.prepareStatement(st5);
				mPS.setInt(1, r.getManager().getManagerID());
				mPS.setString(2, r.getManager().getFirstName());
				mPS.setString(3, r.getManager().getLastName());
				mPS.setString(4, r.getManager().getPhoneNumber());
				mPS.setString(5, r.getManager().getCompanyName());
				mPS.setInt(6, r.getFacilityID());
				
				mPS.executeUpdate();
			}
		}
			
		if (mPS != null) {
			rPS.close();
			bPS.close();
			gPS.close();	
			aPS.close();
			mPS.close();
		}		
		if (connection != null) {
			connection.close();
		}
	}
	
	
	// getter
	public FacilityGroup getFacility(int groupID) throws Exception {
		Statement st = DBHelper.getConnection().createStatement();
		
		// get FacilityGroup
		String st1 = "SELECT Group_ID, GroupName, GroupOwner FROM BuildingGroup WHERE Group_ID"
				+ " = " + groupID;
		FacilityGroup g = new FacilityGroup();	
		ResultSet gRS = st.executeQuery(st1);
		while (gRS.next()) {
			g.setFacilityID(gRS.getInt("Group_ID"));
			g.setGroupName(gRS.getString("GroupName"));
			g.setGroupOwner(gRS.getString("GroupOwner"));
		}
		gRS.close();
		
		// get Building
		for (Facility f : g.getBuildings()){
			String st2 = "SELECT Building_ID, BuildingName, BuildingOwner, ConstructionDate FROM "
					+ "Building WHERE Group_ID = " + groupID;
			f = new Building();
			Building b = (Building)f;
			ResultSet bRS = st.executeQuery(st2);
			while (bRS.next()){
				b.setFacilityID(bRS.getInt("Building_ID"));
				b.setBuildingName(bRS.getString("BuildingName"));
				b.setBuildingOwner(bRS.getString("BuildingOwner"));
				b.setConstructionDate(new java.util.Date(bRS.getDate("ConstructionDate").getTime()));
			}
			g.addNewFacility(b);
			bRS.close();
			
			// get Unit
			for (Facility fr : b.getRooms()){
				String st3 = "SELECT Room_ID, RoomName, RoomOwner, UsageType FROM Unit WHERE "
						+ "Building_ID = " + b.getFacilityID();
				fr = new Unit();
				Unit r = (Unit)fr;
				ResultSet rRS = st.executeQuery(st3);
				while (rRS.next()) {
					r.setFacilityID(rRS.getInt("Room_ID"));
					r.setRoomName(rRS.getString("RoomName"));
					r.setRoomOwner(rRS.getString("RoomOwner"));
					r.setUsageType(rRS.getString("UsageType"));
				}
				b.addNewFacility(r);
				rRS.close();
				
				// get Address
				String st4 = "SELECT Address_ID, UnitNumber, StreetNumber, Street, City, "
						+ "StateProvince FROM Address WHERE Room_ID = " + r.getFacilityID();
				Address a = new Address();
				ResultSet aRS = st.executeQuery(st4);
				while (aRS.next()) {
					a.setAddressID(aRS.getInt("Address_ID"));
					a.setUnitNumber(aRS.getInt("UnitNumber"));
					a.setStreetNumber(aRS.getInt("StreetNumber"));
					a.setStreet(rRS.getString("Street"));
					a.setCity(rRS.getString("City"));
					a.setStateProvince(rRS.getString("StateProvince"));
				}
				r.setAddress(a);
				aRS.close();
				
				// get Manager
				String st5 = "SELECT Manager_ID, FName, LName, Phone, CompanyName FROM "
						+ "Manager WHERE Room_ID = " + r.getFacilityID();
				Manager m = new Manager();
				ResultSet mRS = st.executeQuery(st5);
				while (mRS.next()) {
					m.setManagerID(mRS.getInt("Manager_ID"));
					m.setFirstName(mRS.getString("FName"));
					m.setLastName(mRS.getString("LName"));
					m.setPhoneNumber(mRS.getString("Phone"));
					m.setCompanyName(mRS.getString("CompanyName"));
				}	
				r.setManager(m);
				mRS.close();
			}	
		}
		st.close();
		return g;
	}
}

package dal;

import model.facilityUse.*;
import model.facility.*;
import java.sql.*;



public class FacilityUseDAO {
	public void setFacilityUse(Usage u) throws Exception {
		//setter
		Connection connection = DBHelper.getConnection();
		
		PreparedStatement uPS = null;
		PreparedStatement urPS = null;
		
		//set Usage
		String st1 = "INSERT INTO FacilityUsage (Usage_ID, UsageType, StartDate, EndDate) "
				+ "VALUES (?, ?, ?, ?)";
		uPS = connection.prepareStatement(st1);
		uPS.setInt(1, u.getUsageID());
		uPS.setString(2, u.getUsageType());
		uPS.setDate(3, new Date(u.getStartDate().getTime()));
		uPS.setDate(4, new Date(u.getEndDate().getTime()));

		uPS.executeUpdate();
		
		//set User
		String st2 = "INSERT INTO FacilityUser (User_ID, FName, LName, Gender, Usage_ID) VALUES"
				+ " (?, ?, ?, ?, ?)";
		urPS = connection.prepareStatement(st2);
		urPS.setInt(1, u.getUser().getUserID());
		urPS.setString(2, u.getUser().getFirstName());
		urPS.setString(3, u.getUser().getLastName());
		urPS.setString(4,  u.getUser().getGender());
		urPS.setInt(5, u.getUsageID());
		
		urPS.executeUpdate();
		
		if (urPS != null) {
			uPS.close();
			urPS.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
	
	
	// getter
	public Usage getFacilityUse (int uID) throws Exception {
		Statement st = DBHelper.getConnection().createStatement();
		
		// get Usage
		String st1 = "SELECT Usage_ID, UsageType, StartDate, EndDate FROM FacilityUsage "
				+ "WHERE Usage_ID = " + uID;
		ResultSet uRS = st.executeQuery(st1);
		Usage u = new Usage();
		while (uRS.next()) {
			u.setUsageID(uRS.getInt("Usage_ID"));
			u.setUsageType(uRS.getString("UsageType"));
			u.setStartDate(new java.util.Date(uRS.getDate("StartDate").getTime()));
			u.setEndDate(new java.util.Date(uRS.getDate("EndDate").getTime()));
		}
		uRS.close();
		
		// get User
		String st2 = "SELECT User_ID, FName, LName, Gender FROM User WHERE User_ID = " + uID;
		ResultSet urRS = st.executeQuery(st2);
		User ur = new User();
		while (urRS.next()) {
			ur.setUserID(urRS.getInt("User_ID"));
			ur.setFirstName(urRS.getString("FName"));
			ur.setLastName(urRS.getString("LName"));
			ur.setGender(urRS.getString("Gender"));
		}
		
		urRS.close();
		st.close();
		
		return u;
	}
}

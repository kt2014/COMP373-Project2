package dal;

import java.sql.*;

import model.facility.*;
import model.inspection.Inspection;

public class InspectionDAO {
	// setter
	public void setInspection(Inspection i) throws Exception {
		Connection connection = DBHelper.getConnection();
		
		String st = "INSERT INTO Inspection (Inspection_ID, InspectionType, InspectionDate, "
				+ "Inspector, OutCome) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement iPS = connection.prepareStatement(st);
		iPS.setInt(1, i.getInspectionID());
		iPS.setString(2, i.getInspectionType());
		iPS.setDate(3, new Date(i.getInspectionDate().getTime()));
		iPS.setString(4, i.getInspector());
		iPS.setString(5, i.getOutcome());
		
		iPS.executeUpdate();
		
		if (iPS != null) {
			iPS.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
	
	
	//getter
	public Inspection getInspcetion(int iID) throws Exception {
		Statement st = DBHelper.getConnection().createStatement();
		
		String st1 = "SELECT InspectionType, InspectionDate, Inspector, OutCome, "
				+ "FROM Inspection WHERE Inspection_ID = " + iID;
		
		ResultSet iRS = st.executeQuery(st1);
		Inspection i = new Inspection();
		while (iRS.next()) {
			i.setInspectionType(iRS.getString("InspectionType"));
			i.setInspectionDate(new java.util.Date(iRS.getDate("InspectionDate").getTime()));
			i.setInspector(iRS.getString("Inspector"));
			i.setOutcome(iRS.getString("Outcome"));

		}
		iRS.close();
		st.close();
		
		return i;
	}
}

package dal;

import java.sql.*;


public class DBHelper {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/db";
	
	static final String user = "root";
	static final String password = "pwd";
	
	public static Connection getConnection() throws Exception {
		Connection connection = DriverManager.getConnection(DB_URL+"?useSSL=false",user,password);
		
		return connection;
	}	
}


package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	
	private Connection conn = null;
	private String userName = "assignment1";;
	private String password = "password";
	private String dbConnection = "jdbc:mysql://localhost/assignment1?autoReconnect=true&useSSL=false";
	private PreparedStatement pSt;
	private ResultSet rS= null;
	
	public Database(){
	}
	
	public void connectDatabase(){
		try {
			conn = (Connection) DriverManager.getConnection(dbConnection, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean teacherExists(String firstName, String lastName){
		boolean exists = false;
		try {
			pSt = conn.prepareStatement("SELECT * FROM TEACHERS WHERE FIRSTNAME = ? AND LASTNAME = ? ");
			pSt.setString(1, firstName);
			pSt.setString(2, lastName);
			rS = pSt.executeQuery();
			
			exists = rS.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}
	
	


}

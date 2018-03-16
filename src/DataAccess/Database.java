package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import Domain.Student;
import Domain.User;

public class Database {
	
	private Connection conn = null;
	private String userName = "assignment1";;
	private String password = "password";
	private String dbConnection = "jdbc:mysql://localhost/assignment1?autoReconnect=true&useSSL=false";
	private PreparedStatement pSt;
	private ResultSet rS= null;
	private HashMap<String, String> userInfo;
	
	public Database(){
	}
	
	public void connectDatabase(){
		try {
			conn = (Connection) DriverManager.getConnection(dbConnection, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public boolean userIsAdmin(String userID) {
		
		boolean idPresent = false;
		
		try {
			pSt = conn.prepareStatement("");		//SQL query will go here to retrieve admin user's data if their userID is in the Admin table.
			pSt.setString(1, userID);
			rS = pSt.executeQuery();
			
			idPresent = rS.next();		
			
		}catch(SQLException e){
			
		}
			return idPresent;
	}

	public boolean userIsTeacher(String userID) {
		boolean idPresent = false;
		
		try {
			pSt = conn.prepareStatement("");		//SQL query will go here to retrieve teacher user's data if their userID is in the Teacher table.
			pSt.setString(1, userID);
			rS = pSt.executeQuery();
			
			idPresent = rS.next();		
			
		}catch(SQLException e){
			
		}
			return idPresent;
	}

	public boolean userIsStudent(String userID) {
		boolean idPresent = false;
		
		try {
			pSt = conn.prepareStatement("");		//SQL query will go here to retrieve student user's data if their userID is in the Student table..
			pSt.setString(1, userID);
			rS = pSt.executeQuery();
			
			idPresent = rS.next();		
			
		}catch(SQLException e){
			
		}
			return idPresent;
	}

	public HashMap<String, String> getStudentInfo(String userID) {
		return null;		
	}

	public HashMap<String, String> getTeacherInfo(String userID) {
		userInfo = new HashMap<String, String>();
		
		try {
			pSt = conn.prepareStatement("");		//SQL query will go here to retrieve the teacher's info and the classes they teach.
			pSt.setString(1, userID);
			rS = pSt.executeQuery();
			
			rS.next();
			userInfo.put("First Name", rS.getString("FirstName"));
			userInfo.put("Last Name", rS.getString("LastName"));
			userInfo.put("Email", rS.getString("EmailAddress"));
			
													//Add the rest of the data to the HashMap.
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userInfo;
	}

	public HashMap<String, String> getAdminInfo(String userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}

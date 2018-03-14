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

	public boolean userIsAdmin(String userID) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean userIsTeacher(String userID) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean userIsStudent(String userID) {
		// TODO Auto-generated method stub
		return false;
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

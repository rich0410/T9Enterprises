package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Domain.Student;
import Domain.TimeSlot;
import Domain.User;

public class Database {
	
	private Connection conn = null;
	private String userName = "assignment1";
	private String password = "password";
	private String dbConnection = "jdbc:mysql://localhost/assignment1";
	private PreparedStatement pSt;
	private ResultSet rS= null;
	private HashMap<String, String> userInfo;
	private HashMap<String, ArrayList<String>> schedInfo;
	
	public Database(){
	}
	
	/**
	 * Creates a connection to the database.
	 */
	public void connectDatabase(){
		try {
			conn = (Connection) DriverManager.getConnection(dbConnection, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Queries the database to determine the role of the user. If the user ID is stored in the Admin table the method will return a true, otherwise false.
	 * Note: at this point the table Admin has not been created in the database and will need to be for future implementations.
	 * @param userID
	 * @return
	 */
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

	/**
	 * Queries the database to determine the role of the user. If the user ID is stored in the Teacher table the method will return a true, otherwise false.
	 * @param userID
	 * @return
	 */
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

	/**
	 * Queries the database to determine the role of the user. If the user ID is stored in the Student table the method will return a true, otherwise false.
	 * @param userID
	 * @return
	 */
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

	/**
	 * This method will be used to retrieve the student's personal info(Name and email).
	 * @param userID
	 * @return
	 */
	public HashMap<String, String> getStudentInfo(String userID) {
		return null;		
	}

	/**
	 * This method will be used to retrieve the teacher's personal info(Name and email).
	 * @param userID
	 * @return
	 */
	public HashMap<String, String> getTeacherInfo(String userID) {
		userInfo = new HashMap<String, String>();
		
		try {
			pSt = conn.prepareStatement("SELECT * FROM TEACHER WHERE TEACHERID = ?");		
			pSt.setString(1, userID);
			rS = pSt.executeQuery();
			
			rS.next();
			userInfo.put("First Name", rS.getString("FirstName"));
			userInfo.put("Last Name", rS.getString("LastName"));
			userInfo.put("Email", rS.getString("EmailAddress"));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userInfo;
	}

	/**
	 * This method will be used to retrieve the Admin's personal info(Name and email).
	 * @param userID
	 * @return
	 */
	public HashMap<String, String> getAdminInfo(String userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This method will only be called on startup. This simply instantiates the HashMap used to store schedule information
	 * and initializes default values.
	 */
	public void setupHashMap(){
		schedInfo = new HashMap<String, ArrayList<String>>();
		
		schedInfo.put("Course", new ArrayList<String>());
		schedInfo.put("Day", new ArrayList<String>());
		schedInfo.put("Time", new ArrayList<String>());
		schedInfo.put("Duration", new ArrayList<String>());
		schedInfo.put("Room", new ArrayList<String>());
	}

	/**
	 * Retrieves schedule data for a teacher. Details pertaining to the room number, start time, duration, and course will be obtained from the database. 
	 * This data will be bundled in a HashMap object and returned to create a TimeTable object.
	 * @param userID
	 * @return
	 */
	public HashMap<String, ArrayList<String>> getScheduleData(String userID) {
		
		try {
			pSt = conn.prepareStatement("SELECT B.COURSECODE, B.COURSESECTION, C.TITLE, A.DAYOFTHEWEEK, A.STARTTIME, A.DURATION, B.ROOMNUMBER FROM TEACHERCLASSES A"
					+ "LEFT JOIN COURSESECTION B ON A.COURSESECTIONID = B.COURSESECTIONID LEFT JOIN COURSE C ON C.COURSECODE = B.COURSECODE WHERE"
					+ "A.TEACHERID = ?");		
			
			pSt.setString(1, userID);
			rS = pSt.executeQuery();
			
			while(rS.next()){
				
				String course = rS.getString("COURSECODE")+" - "+rS.getString("COURSESECTION")+" - "+rS.getString("TITLE");
				String day = rS.getString("DAYOFTHEWEEK");
				
				
				String dow = convertDay(day);
				
				
				ArrayList<String> classes = schedInfo.get("Course");
				classes.add(course);
				
				ArrayList<String> days = schedInfo.get("Day");
				days.add(dow);
				
				ArrayList<String> times = schedInfo.get("Time");
				times.add(rS.getString("STARTTIME"));
				
				ArrayList<String> durations = schedInfo.get("Duration");
				durations.add(rS.getString("DURATION"));
				
				ArrayList<String> rooms = schedInfo.get("Room");
				rooms.add(rS.getString("ROOMNUMBER"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return schedInfo;
	}
	
	/**
	 * Retrieves office time data for a teacher. Details pertaining to the room number, start time, and durationwill be obtained from the database. 
	 * This data will be bundled in a HashMap object and returned to create a TimeTable object.
	 * @param userID
	 * @return
	 */
	public HashMap<String, ArrayList<String>> getOfficeData(String userID) {
		
		try {
			pSt = conn.prepareStatement("SELECT * FROM TEACHEROFFICETIME WHERE TEACHERID = ?");		
			
			pSt.setString(1, userID);
			rS = pSt.executeQuery();
			
			while(rS.next()){
				
				String day = rS.getString("DAYOFTHEWEEK");
				
				
				String dow = convertDay(day);
				
				
				ArrayList<String> classes = schedInfo.get("Course");
				classes.add("Office");
				
				ArrayList<String> days = schedInfo.get("Day");
				days.add(dow);
				
				ArrayList<String> times = schedInfo.get("Time");
				times.add(rS.getString("STARTTIME"));
				
				ArrayList<String> durations = schedInfo.get("Duration");
				durations.add(rS.getString("DURATION"));
				
				ArrayList<String> rooms = schedInfo.get("Room");
				rooms.add(rS.getString("ROOMNUMBER"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return schedInfo;
	}
	
	/**
	 * Converts the three letter String representing a day to the full word.
	 * @param day
	 * @return
	 */
	private String convertDay(String day){
		
		String dow = "";
		
		switch (day){
		
		case "MON": dow = "MONDAY";
					break;
					
		case "TUE": dow = "TUESDAY";
					break;
		
		case "WED": dow = "WEDNESDAY";
					break;
		
		case "THU": dow = "THURSDAY";
					break;
		
		case "FRI": dow = "FRIDAY";
					break;
	}
		
		return dow;
	}
	
	/**
	 * to get all teachers
	 * */
	 public  HashMap<String, ArrayList<String>> getallTeachers() {
		
		 HashMap<String, ArrayList<String>> teachers =  new HashMap<String, ArrayList<String>>();
		
		try {
			pSt = conn.prepareStatement("SELECT * FROM TEACHEROFFICETIME Teacher;");
			rS = pSt.executeQuery();
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		return teachers;
		
		
		
		
		
	}

}

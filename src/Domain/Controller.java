package Domain;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import DataAccess.ScheduleReader;
import DataAccess.Database;
import UI.GUI;
import User.UserLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller{
	
	private static final Controller ctl = new Controller();
	private Student student;
	private Teacher teacher;
	private Admin admin;
	private Database dB;
	
	private String userID;
	public ScheduleReader dG;
	private HashMap<String, String> userData;


	private Controller(){
	}
	
	public static Controller getController(){
		return ctl;
	}
	
	public String getUserID(){
		return userID;
	}
	
	/**
	 * Load data and initiate the GUI object.
	 */
	public void startup(){
		dB = new Database();
		dB.connectDatabase();
	}
	
	
	
	/**
	 * Called by the GUI layer this method will pass the user ID and password to the UserLogin object to actually perform
	 * the authentication.
	 * @param userID
	 * @param password
	 * @return
	 */
	public boolean authenticateUser(String userID, String password){
		boolean verify = false;
		UserLogin uL = new UserLogin();
		if (uL.authenticate(userID, password)){
			
			loadUser(userID);
			
			verify = true;
			
			this.userID = userID;		//Store the userID for future operations.
		}
		return verify;
	}
	
	/**
	 * Once the user has been authenticated this method will call the database object to check the user's role. Once the user's
	 * role is determined the proper user object will be created to represent the user's data.
	 * @param userID
	 */
	private void loadUser(String userID) {

		if(dB.userIsAdmin(userID)){
			admin = new Admin(userID);
			userData = dB.getAdminInfo(userID);
			
			admin.setFirstName(userData.get("First Name"));
			admin.setLastName(userData.get("Larst Name"));
			admin.setEmailAddress(userData.get("Email"));
			
		}
		else if(dB.userIsTeacher(userID)){
			teacher = new Teacher(userID);
			userData = dB.getTeacherInfo(userID);
			
			teacher.setFirstName(userData.get("First Name"));
			teacher.setLastName(userData.get("Last Name"));
			teacher.setEmailAddress(userData.get("Email"));
			
			teacher.setClasses(dB.getTeacherClasses(userID));
			teacher.setOfficeHours(dB.getTeacherOffice(userID));

		}
		else if(dB.userIsStudent(userID)){
			student = new Student(userID);
			userData = dB.getStudentInfo(userID);
			
			student.setFirstName(userData.get("First Name"));
			student.setLastName(userData.get("Last Name"));
			student.setEmailAddress(userData.get("Email"));
			
			student.setClasses(dB.getStudentClasses(userID));
		}
	}
	
	public Student getStudent(){
		return student;
	}
	
	public Teacher getTeacher(){
		return teacher;
	}
	
	public Admin getAdmin(){
		return admin;
	}
	
	/**
	 * Instantiate the GUI object and invoke the method to display the interface.
	 */
	public void initGUI(String[] args) {

		Application.launch(GUI.class,args);

	}
	
	/**
	 * Returns a collection of resource bundles representing all of the office hours for a teacher.
	 * @return
	 */
	public ArrayList<HashMap<String,String>> getTeacherOfficeTimes(String id){
		
		return dB.getTeacherOffice(id);
	}

	
	/**
	 * Updates the database with the new meeting information and sends and email to the teacher. A resource bundle representing the office time
	 * for the teacher is passed back as a parameter from the presentation layer.
	 * @param office
	 */
	public void bookMeeting(HashMap<String, String> office){
		
		dB.bookMeeting(userID, office);
		
		//sendEmail(student.getEmailAddress(), office);		//This method call will need revision.
	}
	
	/**
	 * Returns an ArrayList of Strings containing the details for each meeting.
	 * @return
	 */
	public ArrayList<HashMap<String,String>> getAllStudentMeetings(){
		return dB.getAllStudentMeetings(student.getID());
	}
	
	/**
	 * Returns an ArrayList of Strings containing the details for each meeting.
	 * @return
	 */
	public ArrayList<HashMap<String,String>> getAllTeacherMeetings(){
		return dB.getAllTeacherMeetings(teacher.getID());
	}
	
	public String getStudentEmail(String sID){
		return dB.getStudetEmail(sID);
	}
	
	public String getTeacherEmail(String tID){
		return dB.getTeacherEmail(tID);
	}
	
	public ArrayList<String> getAllStudentEmails(String courseCode){
		return dB.getClassStudentsEmail(courseCode);
	}
	
	private void sendEmail(String emailAddress, HashMap<String, String> office) {
		
		
	}
	
	private void sendEmail(ArrayList<String> emailAddress) {
		
		
	}
	
	public void updateSchedule(File schedule){
		ScheduleReader sR = new ScheduleReader();
		dB.updateTeacherClasses(userID, sR.readFile(schedule));
		loadUser(userID);
	}

	
	
	public void cancelClass(HashMap<String, String> course){
		
		ArrayList<String> emails = dB.getClassStudentsEmail(course.get("Course"));
	}
	
	/**
	 * Reads the teacher timetable data to be passed to the presentation layer.
	 */
	/*protected void loadData(){
		dG = new DataGenerator();
		dG.readFile();
		names = dG.getNames();
		schedule =dG.getSched();
	}*/
	
	public void logout(){
		userID = null;
		student = null;
		teacher = null;
		admin = null;
		
	}

	public void resetMeetings(HashMap<String, String> office) {
		dB.resetMeetings(office);
	}


	
	
}

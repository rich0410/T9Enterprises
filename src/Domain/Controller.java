package Domain;

import java.util.ArrayList;
import java.util.HashMap;

import DataAccess.DataGenerator;
import DataAccess.Database;
import User.UserLogin;

public class Controller {
	
	private static final Controller ctl = new Controller();
	private User user;
	private Database dB;
	private HashMap<String, String> names = new HashMap<String, String>();
	private HashMap<String, ArrayList<String>> schedule = new HashMap<String, ArrayList<String>>();
	public DataGenerator dG;
	private HashMap<String, String> userData;
	//private GUI gui;

	private Controller(){
	}
	
	public static Controller getController(){
		return ctl;
	}
	
	/**
	 * Load data and initiate the GUI object.
	 */
	public void startup(){
		dB = new Database();
		dB.connectDatabase();
		loadData();
		//initGUI();
	}
	
	public boolean authenticateUser(String userID, String password){
		boolean verify = false;
		UserLogin uL = new UserLogin();
		if (uL.authenticate(userID, password)){
			
			loadUser(userID);
			
			invokeHomepage();
			verify =true;
		}
		return verify;
	}
	
	private void loadUser(String userID) {

		if(dB.userIsAdmin(userID)){
			user = new Admin(userID);
			userData = dB.getAdminInfo(userID);
			
		}
		else if(dB.userIsTeacher(userID)){
			user = new Teacher(userID);
			userData = dB.getTeacherInfo(userID);
			
			/*Set the data from HashMap userData to the attributes of the Teacher object*/

			
		}
		else if(dB.userIsStudent(userID)){
			user = new Student(userID);
			userData = dB.getStudentInfo(userID);
		}
	}

	private void invokeHomepage() {

		if (user instanceof Teacher){
			
			/*Invoke the teacher home page displaying the teacher's timetable*/
			
		}
	}

	/**
	 * Instantiate the GUI object and invoke the method to display the interface.
	 */
	/*private void initGUI() {
		GUI gui = new GUI();
		gui.init();
	}*/

	/**
	 * Creates a teacher object and sets it as the active user. If there is no record of the teacher in the database it will be updated.
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	/*public void createTeacher(){
		Teacher teacher = new Teacher(names.get("First Name"), names.get("Last Name"), "");
		user = teacher;
		
		//If the teacher doesn't exist in the database, insert the teacher as a new record.
		if(!dB.teacherExists())
			dB.commitTeacher(teacher);
	}*/
	
	/**
	 * Adds a new timetable to the teacher. The old timetable will be overwritten.
	 */
	/*public void createTimetable(){
		if(user.getClass().equals(Teacher.class)){
			Timetable tTable = new Timetable();
			user.setTimetable(tTable);
		}
	}*/
	
	
	public void addSlotToTimeTable(){
		
	}
	
	/**
	 * Reads the teacher timetable data to be passed to the presentation layer.
	 */
	protected void loadData(){
		dG = new DataGenerator();
		dG.readFile();
		names = dG.getNames();
		schedule =dG.getSched();
	}
	
	
}

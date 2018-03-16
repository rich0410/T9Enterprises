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
			
			verify =true;
		}
		return verify;
	}
	
	/**
	 * Once the user has been authenticated the=is method will call the database object to check the user's role. Once the user's
	 * role is determined the proper user object wil be created to represent the user's data.
	 * @param userID
	 */
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

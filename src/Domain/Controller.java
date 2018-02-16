package Domain;

import java.util.HashMap;

import DataAccess.DataGenerator;
import DataAccess.Database;

public class Controller {
	
	private static final Controller ctl = new Controller();
	private Person user;
	private Database dB;
	private HashMap<String, String> names = new HashMap<String, String>();
	private HashMap<String, String[]> schedule = new HashMap<String, String[]>();
	public DataGenerator dG;
	private GUI gui;

	private Controller(){
	}
	
	public static Controller getController(){
		return ctl;
	}
	
	/**
	 * Load data and initiate the GUI object.
	 */
	public void startup(){
		loadData();
		initGUI();
	}
	
	/**
	 * Instantiate the GUI object and invoke the method to display the interface.
	 */
	private void initGUI() {
		GUI gui = new GUI();
		gui.init();
	}

	/**
	 * Creates a teacher object and sets it as the active user. If there is no record of the teacher in the database it will be updated.
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public void createTeacher(){
		Teacher teacher = new Teacher(names.get("First Name"), names.get("Last Name"), "");
		user = teacher;
		
		//If the teacher doesn't exist in the database, insert the teacher as a new record.
		if(!dB.teacherExists())
			dB.commitTeacher(teacher);
	}
	
	/**
	 * Adds a new timetable to the teacher. The old timetable will be overwritten.
	 */
	public void createTimetable(){
		if(user.getClass().equals(Teacher.class)){
			Timetable tTable = new Timetable();
			user.setTimetable(tTable);
		}
	}
	
	
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

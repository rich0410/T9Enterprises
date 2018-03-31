package Domain;

import java.util.ArrayList;
import java.util.HashMap;

import DataAccess.DataGenerator;
import DataAccess.Database;
import UI.GUI;
import User.UserLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

    private static final Controller ctl = new Controller();
    private Student student;
    private Teacher teacher;
    private Admin admin;

    private Database dB;
    private HashMap<String, ArrayList<String>> schedule;
    private HashMap<String, ArrayList<String>> office;
    public DataGenerator dG;
    private HashMap<String, String> userData;
    private User user;


    private Controller() {
    }

    public static Controller getController() {
        return ctl;
    }

    /**
     * Load data and initiate the GUI object.
     */
    public void startup(String[] args) {
        dB = new Database();
        dB.connectDatabase();
        dB.setupHashMap();
        initGUI(args);
    }

    public User getTeacher() {
        return teacher;
    }

    /**
     * Called by the GUI layer this method will pass the user ID and password to the UserLogin object to actually perform
     * the authentication.
     *
     * @param userID
     * @param password
     * @return
     */
    public boolean authenticateUser(String userID, String password) {
//		boolean verify = false;
//		UserLogin uL = new UserLogin();
//		if (uL.authenticate(userID, password)){

        set_user(loadUser(userID));

        boolean verify = true;
//		}
        return verify;
    }

    /**
     * Once the user has been authenticated this method will call the database object to check the user's role. Once the user's
     * role is determined the proper user object will be created to represent the user's data.
     *
     * @param userID
     */
    private User loadUser(String userID) {
        User user = null;
        if (dB.userIsAdmin(userID)) {
            admin = new Admin(userID);
            userData = dB.getAdminInfo(userID);
            admin.setRole(1);
            user = admin;
        } else if (dB.userIsTeacher(userID)) {
            teacher = new Teacher(userID);
            userData = dB.getTeacherInfo(userID);
            teacher.setID(userData.get("ID"));
            teacher.setFirstName(userData.get("First Name"));
            teacher.setLastName(userData.get("Last Name"));
            teacher.setEmailAddress(userData.get("Email"));

            //createTimetable(userID);
            teacher.setRole(2);
            user = teacher;
        } else if (dB.userIsStudent(userID)) {
            student = new Student(userID);
            userData = dB.getStudentInfo(userID);
            student.setID(userData.get("ID"));
            student.setFirstName(userData.get("First Name"));
            student.setLastName(userData.get("Last Name"));
            student.setEmailAddress(userData.get("Email"));
            student.setRole(3);
            user = student;
        }

        return user;
    }


    public HashMap<String, ArrayList<String>> getTeacherTimetable(String userID){

        HashMap<String, ArrayList<String>>  data =   dB.getScheduleData(userID);
        return data;
    }

    private void invokeHomepage() {

		
			
			/*Invoke the teacher home page displaying the teacher's timetable*/

    }

    /**
     * Instantiate the GUI object and invoke the method to display the interface.
     */
    private void initGUI(String[] args) {

        Application.launch(GUI.class, args);

    }


    /**
     * Adds a new timetable to the teacher. The old timetable will be overwritten.
     */
    private void createTimetable(String userID) {
        schedule = dB.getScheduleData(userID);
        office = dB.getOfficeData(userID);

        Time_Table tTable = new Time_Table();
        tTable.addSlots(schedule);
        tTable.addSlots(office);

        teacher.setTimeTable(tTable);
    }


    public void addSlotToTimeTable() {


    }

    public void set_user(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
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


}

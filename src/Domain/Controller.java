package Domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import DataAccess.Database;
import UI.GUI;
import User.UserLogin;
import javafx.application.Application;
import User.Email;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * This is the Controller class which handles all the operation and used in different class
 *
 * @author Justin
 */
public class Controller {

    private static final Controller ctl = new Controller();
    private Student student;
    private Teacher teacher;
    private Admin admin;
    private Database dB;

    private String userID;

    private HashMap<String, String> userData;
    private User user;


    private Controller() {
    }

    public static Controller getController() {
        return ctl;
    }

    public String getUserID() {
        return userID;
    }

    /**
     * Load data and initiate the GUI object.
     */
    public void startup() {
        dB = new Database();
        dB.connectDatabase();
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
        boolean verify = false;
        UserLogin uL = new UserLogin();
        // if (uL.authenticate(userID, password)) {     // Currently user Url Verification functionality is not working
        if (loadUser(userID) != null && password.equals("Password123")) {
            set_user(loadUser(userID));
            verify = true;
            this.userID = userID;
        }//Store the userID for future operations.
        else {
            verify = false;
        }
        // }
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
            admin.setID(userData.get("ID"));
            admin.setFirstName(userData.get("First Name"));
            admin.setLastName(userData.get("Last Name"));
            admin.setEmailAddress(userData.get("Email"));
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

    public Student getStudent() {
        return student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Admin getAdmin() {
        return admin;
    }

    /**
     * Instantiate the GUI object and invoke the method to display the interface.
     */
    public void initGUI(String[] args) {

        Application.launch(GUI.class, args);

    }

    /**
     * Returns a collection of resource bundles representing all of the office hours for a teacher.
     *
     * @return
     */
    public ArrayList<HashMap<String, String>> getTeacherOfficeTimes(String id) {

        return dB.getTeacherOffice(id);
    }

    public ArrayList<HashMap<String, String>> getTeacherOfficeTimesOpen() {

        return dB.getTeacherOffice(teacher.getUserID());
    }


    /**
     * Updates the database with the new meeting information and sends and email to the teacher. A resource bundle representing the office time
     * for the teacher is passed back as a parameter from the presentation layer.
     *
     * @param office
     */
    public void bookMeeting(HashMap<String, String> office) {

        dB.bookMeeting(userID, office);
        sendEmail(student.getEmailAddress(), office);        //This method call will need revision.
    }

    public void ResetMeeting(HashMap<String, String> office) {

        dB.resetMeetings(office);
        sendEmail_Cancel(student.getEmailAddress(), office);        //This method call will need revision.
    }

    public void ReveOfficeHour(HashMap<String, String> office) {
        dB.resetOfficeTime(office);

    }

    public void ResetClassHour(HashMap<String, String> office) {
        dB.ResetClassHour(office);

    }

    /**
     * Returns an ArrayList of Strings containing the details for each meeting.
     *
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllStudentMeetings() {
        return dB.getAllStudentMeetings(student.getUserID());
    }

    /**
     * Returns an ArrayList of Strings containing the details for each meeting.
     *
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllTeacherMeetings() {
        return dB.getTeacherClasses(teacher.getUserID());
    }

    public ArrayList<HashMap<String, String>> getAllTeacherAppointments() {
        return dB.getAllTeacherMeetings(teacher.getUserID());
    }

    public ArrayList<HashMap<String, String>> getStudentData() {
        return dB.getStudentClasses(student.getUserID());
    }

    public void setUpdatedData(ArrayList<HashMap<String, String>> classInfo) throws SQLException {
        dB.updateTeacherClasses(teacher.getUserID(), classInfo);
    }

    public ArrayList<HashMap<String, String>> getAllTeachers() {
        return dB.get_all_teachers();

    }


    public ArrayList<HashMap<String, String>> getAllStudents() {
        return dB.get_all_Students();
    }


    public String getStudentEmail(String sID) {
        return dB.getStudetEmail(sID);
    }

    public String getTeacherEmail(String tID) {
        return dB.getTeacherEmail(tID);
    }

    public ArrayList<String> getAllStudentEmails(String courseCode) {
        return dB.getClassStudentsEmail(courseCode);
    }


    public void removeTeacher(String userID) {
        dB.removeTeacher(userID);
    }

    public void removeStudent(String userID) {
        dB.removeStudent(userID);
    }

    public void removeallstudents() {
        dB.removeAllstudents();
    }

    public void removeallTeachers() {
        dB.removeAllTeachers();
    }

    public void UpdateTeachers(ArrayList<HashMap<String, String>> TeacherInfo) throws SQLException {
        dB.updateTeachers(TeacherInfo);
    }

    public void UpdateStudents(ArrayList<HashMap<String, String>> StudentInfo) throws SQLException {
        dB.updateStudents(StudentInfo);
    }

    public void UpdateStudents_Schedule(ArrayList<HashMap<String, String>> StudentInfo) {
        dB.updateStudentschedule(StudentInfo);
    }

    private void sendEmail(String emailAddress, HashMap<String, String> office) {
        Email e = new Email();
        e.email_Thread(emailAddress);
        e.email_Thread(office.get("Email"));
        System.out.println(" An Email has been sent to" + office.get("Email"));

    }

    private void sendEmail_Cancel(String emailAddress, HashMap<String, String> office) {
        Email e = new Email();
        e.email_Thread(emailAddress);
        e.email_Thread_Cancel(office.get("Email"));
        System.out.println("An Email has been sent to" + office.get("Email"));

    }

    private void sendEmail(ArrayList<String> emailAddress) {


    }


    public void cancelClass(HashMap<String, String> course) {

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
    public void logout() {
        userID = null;
        student = null;
        teacher = null;
        admin = null;

    }


    public void set_user(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


}

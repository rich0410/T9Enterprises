package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Domain.Student;
import Domain.TimeSlot;
import Domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {

    private Connection conn = null;

    private String userName = "administrator";
    private String password = "t9-enterprise";
    private String dbConnection = "jdbc:mysql://localhost/Algonquin_Kiosk";
    private PreparedStatement pSt;
    private ResultSet rS = null;
    private HashMap<String, String> userInfo;
    private ArrayList<HashMap<String, String>> schedInfo;

    public Database() {
    }

    /**
     * Creates a connection to the database.
     */
    public void connectDatabase() {
        try {
            conn = DriverManager.getConnection(dbConnection, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Queries the database to determine the role of the user. If the user ID is stored in the Admin table the method will return a true, otherwise false.
     * Note: at this point the table Admin has not been created in the database and will need to be for future implementations.
     *
     * @param userID
     * @return
     */
    public boolean userIsAdmin(String userID) {

        boolean idPresent = false;

        try {
            pSt = conn.prepareStatement("SELECT * FROM ADMIN WHERE ADMINID = ?");
            pSt.setString(1, userID);
            rS = pSt.executeQuery();

            if (rS.next()) {
                idPresent = true;
            }

        } catch (SQLException e) {

        }
        return idPresent;
    }

    /**
     * Queries the database to determine the role of the user. If the user ID is stored in the Teacher table the method will return a true, otherwise false.
     *
     * @param userID
     * @return
     */
    public boolean userIsTeacher(String userID) {
        boolean idPresent = false;

        try {
            pSt = conn.prepareStatement("SELECT * FROM TEACHER WHERE TEACHERID = ?");
            pSt.setString(1, userID);
            rS = pSt.executeQuery();

            if (rS.next()) {
                idPresent = true;
            }

        } catch (SQLException e) {

        }
        return idPresent;
    }

    /**
     * Queries the database to determine the role of the user. If the user ID is stored in the Student table the method will return a true, otherwise false.
     *
     * @param userID
     * @return
     */
    public boolean userIsStudent(String userID) {
        boolean idPresent = false;

        try {
            pSt = conn.prepareStatement("SELECT * FROM STUDENT WHERE STUDENTID = ?");
            pSt.setString(1, userID);
            rS = pSt.executeQuery();

            if (rS.next()) {
                idPresent = true;
            }

        } catch (SQLException e) {

        }
        return idPresent;
    }

    /**
     * This method will be used to retrieve the student's personal info(Name and email).
     *
     * @param userID
     * @return
     */
    public HashMap<String, String> getStudentInfo(String userID) {
        userInfo = new HashMap<String, String>();
        try {
            pSt = conn.prepareStatement("SELECT * FROM STUDENT WHERE STUDENTID = ?");
            pSt.setString(1, userID);
            rS = pSt.executeQuery();

            rS.next();
            userInfo.put("ID", rS.getString("StudentID"));
            userInfo.put("First Name", rS.getString("FirstName"));
            userInfo.put("Last Name", rS.getString("LastName"));
            userInfo.put("Email", rS.getString("EmailAddress"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    /**
     * This method will be used to retrieve the teacher's personal info(Name and email).
     *
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
            userInfo.put("ID", rS.getString("TeacherID"));
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
     *
     * @param userID
     * @return
     */
    public HashMap<String, String> getAdminInfo(String userID) {
        userInfo = new HashMap<String, String>();

        try {
            pSt = conn.prepareStatement("SELECT * FROM ADMIN WHERE ADMINID = ?");
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
     * Returns a collection of resource bundles representing all the courses a given student is enrolled in.
     *
     * @param userID
     * @return
     */
    public ArrayList<HashMap<String, String>> getStudentClasses(String userID) {
        schedInfo = new ArrayList<HashMap<String, String>>();

        try {
            pSt = conn.prepareStatement("SELECT A.COURSECODE, B.LABLECTURE, B.TITLE, C.DURATION, C.DAYOFTHEWEEK, C.STARTTIME, C.ROOMNUMBER, "
                    + "D.TEACHERID, D.FIRSTNAME, D.LASTNAME, D.EMAILADDRESS FROM STUDENTCOURSES A INNER JOIN COURSES B ON A.COURSECODE = B.COURSECODE INNER JOIN "
                    + "SCHEDULE C ON B.COURSECODE = C.COURSECODE INNER JOIN TEACHER D ON C.TEACHERID = D.TEACHERID WHERE A.STUDENTID = ? ORDER BY"
                    + " A.COURSECODE DESC");

            pSt.setString(1, userID);
            rS = pSt.executeQuery();

            while (rS.next()) {

                HashMap<String, String> classes = new HashMap<String, String>();

                String course = rS.getString("COURSECODE") + " - " + rS.getString("TITLE") + " - " + ((rS.getInt("LABLECTURE") == 1) ? "Lab" : "Lecture");
                String day = rS.getString("DAYOFTHEWEEK");
                int duration = rS.getInt("DURATION");
                LocalTime time = rS.getTime("STARTTIME").toLocalTime();
                String timeString = time + " - " + time.plusHours(duration);
                String room = rS.getString("ROOMNUMBER");
                String teacher = rS.getString("FIRSTNAME") + " " + rS.getString("LASTNAME");
                String email = rS.getString("EMAILADDRESS");
                String teacherID = rS.getString("TEACHERID");

                classes.put("Course", course);
                classes.put("Day", day);
                classes.put("Time", timeString);
                classes.put("Room", room);
                classes.put("Teacher", teacher);
                classes.put("Email", email);
                classes.put("ID", teacherID);

                schedInfo.add(classes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedInfo;
    }

    /**
     * Returns a collection of resource bundles representing all the courses a given teacher is teaching.
     *
     * @param userID
     * @return
     */
    public ArrayList<HashMap<String, String>> getTeacherClasses(String userID) {
        schedInfo = new ArrayList<HashMap<String, String>>();

        try {
            pSt = conn.prepareStatement("SELECT A.COURSECODE, A.LABLECTURE, A.TITLE, B.ROOMNUMBER, B.DURATION, B.DAYOFTHEWEEK, B.STARTTIME"
                    + "  FROM COURSES A INNER JOIN SCHEDULE B ON A.COURSECODE = B.COURSECODE WHERE B.TEACHERID = ? ORDER BY A.COURSECODE DESC");

            pSt.setString(1, userID);
            rS = pSt.executeQuery();

            while (rS.next()) {

                HashMap<String, String> classes = new HashMap<String, String>();

                String course = rS.getString("COURSECODE") + " - " + rS.getString("TITLE") + " - " + ((rS.getInt("LABLECTURE") == 1) ? "Lab" : "Lecture");
                String day = rS.getString("DAYOFTHEWEEK");
                int duration = rS.getInt("DURATION");
                LocalTime time = rS.getTime("STARTTIME").toLocalTime();
                String timeString = time + " - " + time.plusHours(duration);
                String room = rS.getString("ROOMNUMBER");

                classes.put("Course", course);
                classes.put("Day", day);
                classes.put("Duration", Integer.toString(duration));
                classes.put("Time", timeString);
                classes.put("Room", room);


                schedInfo.add(classes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedInfo;
    }

    /**
     * Returns a collection of resource bundles representing all the office hours a given teacher has.
     *
     * @param userID
     * @return
     */
    public ArrayList<HashMap<String, String>> getTeacherOffice(String userID) {
        schedInfo = new ArrayList<HashMap<String, String>>();

        try {
            pSt = conn.prepareStatement("SELECT OFFICEID, ROOMNUMBER, DAYOFTHEWEEK, TIME, AVAILABLE FROM OFFICEHOURS WHERE TEACHERID = ? ");

            pSt.setString(1, userID);
            rS = pSt.executeQuery();

            while (rS.next()) {

                HashMap<String, String> hours = new HashMap<String, String>();

                String id = rS.getString("OFFICEID");
                String day = rS.getString("DAYOFTHEWEEK");
                String time = rS.getString("TIME").toString();
                String room = rS.getString("ROOMNUMBER");
                String booked = (rS.getInt("AVAILABLE") == 0) ? "Open" : "Booked";

                hours.put("ID", id);
                hours.put("Day", day);
                hours.put("Time", time);
                hours.put("Booked", booked);
                hours.put("Room", room);


                schedInfo.add(hours);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedInfo;
    }

    /**
     * This will return a collection of data representing all the meetings currently booked for a given teacher. Each meeting will be represented
     * as a HashMap - a resource bundle of Strings.
     *
     * @param id
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllTeacherMeetings(String id) {
        schedInfo = new ArrayList<HashMap<String, String>>();

        try {
            pSt = conn.prepareStatement("SELECT A.OFFICEID ,A.DAYOFTHEWEEK, A.TIME, A.ROOMNUMBER, C.FIRSTNAME, C.LASTNAME, C.EMAILADDRESS FROM OFFICEHOURS A "
                    + "INNER JOIN BOOKEDMEETINGS B ON A.OFFICEID = B.OFFICEID INNER JOIN STUDENT C ON B.STUDENTID = C.STUDENTID WHERE "
                    + "A.AVAILABLE = 1");

            rS = pSt.executeQuery();

            while (rS.next()) {

                HashMap<String, String> appts = new HashMap<String, String>();            //There will be one HashMap for each meeting
                String Officeid = rS.getString("OFFICEID");
                String day = rS.getString("DAYOFTHEWEEK");                                //These are the details for each meeting.
                String time = rS.getTime("TIME").toString();
                String room = rS.getString("ROOMNUMBER");
                String student = rS.getString("FIRSTNAME") + " " + rS.getString("LASTNAME");
                String email = rS.getString("EMAILADDRESS");

                appts.put("ID", Officeid);
                appts.put("Day", day);
                appts.put("Time", time);
                appts.put("Room", room);
                appts.put("Student", student);
                appts.put("Email", email);


                schedInfo.add(appts);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedInfo;
    }

    /**
     * This will return a collection of data representing all the meetings currently booked for a given student. Each meeting will be represented
     * as a HashMap - a resource bundle of Strings.
     *
     * @param userID
     * @return
     */
    public ArrayList<HashMap<String, String>> getAllStudentMeetings(String userID) {
        schedInfo = new ArrayList<HashMap<String, String>>();

        try {
            pSt = conn.prepareStatement("SELECT A.OFFICEID, B.DAYOFTHEWEEK, B.TIME, B.ROOMNUMBER, C.FIRSTNAME, C.LASTNAME, C.EMAILADDRESS FROM BOOKEDMEETINGS A"
                    + " INNER JOIN OFFICEHOURS B ON A.OFFICEID = B.OFFICEID INNER JOIN TEACHER C ON B.TEACHERID = C.TEACHERID WHERE "
                    + "A.STUDENTID = ?");

            pSt.setString(1, userID);
            rS = pSt.executeQuery();

            while (rS.next()) {

                HashMap<String, String> classes = new HashMap<String, String>();            //There will be one HashMap for each meeting

                String teacher = rS.getString("FIRSTNAME") + " " + rS.getString("LASTNAME");
                String Officeid = rS.getString("OFFICEID");
                String email = rS.getString("EMAILADDRESS");
                String day = rS.getString("DAYOFTHEWEEK");                                //These are the details for each meeting.
                String time = rS.getTime("TIME").toString();
                String room = rS.getString("ROOMNUMBER");

                classes.put("ID", Officeid);
                classes.put("Teacher", teacher);
                classes.put("Day", day);
                classes.put("Time", time);
                classes.put("Email", email);
                classes.put("Room", room);


                schedInfo.add(classes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedInfo;
    }


    /**
     * Provides the email address for a given teacher.
     *
     * @param userID
     * @return
     */
    public String getTeacherEmail(String userID) {
        String email = null;
        try {
            pSt = conn.prepareStatement("SELECT EMAILADDRESS FROM TEACHER WHERE TEACHERID = ? ");

            pSt.setString(1, userID);
            rS = pSt.executeQuery();

            if (rS.next() != false) {

                email = rS.getString("EMAILADDRESS");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return email;
    }

    /**
     * Provides the email address for a given student.
     *
     * @param userID
     * @return
     */
    public String getStudetEmail(String userID) {
        String email = null;
        try {
            pSt = conn.prepareStatement("SELECT EMAILADDRESS FROM STUDENT WHERE STUDENTID = ? ");

            pSt.setString(1, userID);
            rS = pSt.executeQuery();

            if (rS.next() != false) {

                email = rS.getString("EMAILADDRESS");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return email;
    }

    /**
     * Returns all email addresses for students enrolled in a given course.
     *
     * @param courseID
     * @return
     */
    public ArrayList<String> getClassStudentsEmail(String courseID) {
        ArrayList<String> emails = new ArrayList<String>();
        try {
            pSt = conn.prepareStatement("SELECT A.EMAILADDRESS FROM STUDENT A INNER JOIN STUDENTCOURSES B ON A.STUDENTID = B.STUDENTID "
                    + "WHERE CourseCode = ?");

            pSt.setString(1, courseID);
            rS = pSt.executeQuery();

            while (rS.next()) {

                emails.add(rS.getString("EMAILADDRESS"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emails;
    }

    /**
     * This will update the database with a new meeting between a student and teacher. The BOOKEDAPPOINTMENTS table and the TEACHEROFFICETIME
     * will be updated to reflect the new meeting.
     *
     * @param userID
     * @param office
     */
    public void bookMeeting(String userID, HashMap<String, String> office) {
        try {
            pSt = conn.prepareStatement("UPDATE OFFICEHOURS SET AVAILABLE = 1 WHERE OFFICEID = ?");
            pSt.setString(1, office.get("ID"));
            pSt.executeUpdate();

            pSt = conn.prepareStatement("INSERT INTO BookedMeetings (OfficeID, StudentID) VALUES(?, ?)");
            pSt.setString(1, office.get("ID"));
            pSt.setString(2, userID);
            pSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is only called from the test cases
     *
     * @param office
     */
    public void resetMeetings(HashMap<String, String> office) {
        try {
            pSt = conn.prepareStatement("DELETE FROM BOOKEDMEETINGS WHERE OFFICEID = ? ");
            pSt.setString(1, office.get("ID"));
            pSt.executeUpdate();

            pSt = conn.prepareStatement("UPDATE OFFICEHOURS SET AVAILABLE = 0 WHERE OFFICEID = ?");
            pSt.setString(1, office.get("ID"));
            pSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeachers(ArrayList<HashMap<String, String>> TeacherInfo) throws SQLException {

        for (HashMap<String, String> teachers : TeacherInfo) {

            pSt = conn.prepareStatement("INSERT INTO TEACHER(TeacherID, FirstName, LastName, EmailAddress) "
                    + "VALUES (?, ?, ?, ? )");


            pSt.setString(1, teachers.get("UserID"));
            pSt.setString(2, teachers.get("FirstName"));
            pSt.setString(3, teachers.get("LastName"));
            pSt.setString(4, teachers.get("EmailAddress"));

            pSt.executeUpdate();

        }

//            conn.commit();


    }


    public void updateStudents(ArrayList<HashMap<String, String>> StudentInfo) throws SQLException {

        for (HashMap<String, String> Students : StudentInfo) {

            pSt = conn.prepareStatement("INSERT INTO STUDENT(StudentID, FirstName, LastName, EmailAddress) "
                    + "VALUES (?, ?, ?, ?)");


            pSt.setString(1, Students.get("UserID"));
            pSt.setString(2, Students.get("FirstName"));
            pSt.setString(3, Students.get("LastName"));
            pSt.setString(4, Students.get("EmailAddress"));

            pSt.executeUpdate();

        }

//            conn.commit();


    }


    public void updateStudentschedule(ArrayList<HashMap<String, String>> StudentInfo) {

        try {
            for (HashMap<String, String> Students : StudentInfo) {

                pSt = conn.prepareStatement("INSERT INTO StudentCourses(StudentID, CourseCode) "
                        + "VALUES (?, ?)");

                pSt.setString(1, Students.get("StudentID"));
                pSt.setString(2, Students.get("CourseCode"));
                pSt.executeUpdate();

            }

//            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes all records from the Schedule table and the OfficeHours table for a given teacher. The tables are then updated to include the new
     * information passed to the method in the form of a HashMap.
     *
     * @param userID
     * @return
     */


    public void updateTeacherClasses(String userID, ArrayList<HashMap<String, String>> classInfo) throws SQLException {

        pSt = conn.prepareStatement("DELETE FROM OFFICEHOURS WHERE TEACHERID = ? ");
        pSt.setString(1, userID);
        pSt.executeUpdate();

        pSt = conn.prepareStatement("DELETE FROM SCHEDULE WHERE TEACHERID = ? ");
        pSt.setString(1, userID);
        pSt.executeUpdate();

        for (HashMap<String, String> classes : classInfo) {

            if (classes.get("Course").equals("Office")) {
                pSt = conn.prepareStatement("INSERT INTO OFFICEHOURS (TeacherID, DayOfTheWeek, Time, RoomNumber, Available) "
                        + "VALUES (?, ?, ?, ?, ?)");

                pSt.setString(1, userID);
                pSt.setString(2, classes.get("Day"));
                pSt.setString(3, classes.get("Time"));
                pSt.setString(4, classes.get("Room"));
                pSt.setInt(5, 0);

                pSt.executeUpdate();

            } else {

                pSt = conn.prepareStatement("INSERT INTO SCHEDULE (TeacherID, CourseCode, Duration, DayOfTheWeek, StartTime, RoomNumber) "
                        + "VALUES (?, ?, ?, ?, ?, ?)");

                pSt.setString(1, userID);
                pSt.setString(2, classes.get("Course"));
                pSt.setInt(3, Integer.parseInt(classes.get("Duration")));
                pSt.setString(4, classes.get("Day"));
                pSt.setString(5, classes.get("Time"));
                pSt.setString(6, classes.get("Room"));

                pSt.executeUpdate();

            }
        }

//            conn.commit();


    }


    public void removeTeacher(String userID) {
        try {
            pSt = conn.prepareStatement("DELETE FROM OFFICEHOURS WHERE TEACHERID = ? ");
            pSt.setString(1, userID);
            pSt.executeUpdate();

            pSt = conn.prepareStatement("DELETE FROM SCHEDULE WHERE TEACHERID = ? ");
            pSt.setString(1, userID);
            pSt.executeUpdate();

            pSt = conn.prepareStatement("DELETE FROM Teacher WHERE TEACHERID = ? ");
            pSt.setString(1, userID);
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeStudent(String userID) {
        try {

            pSt = conn.prepareStatement("DELETE FROM  StudentCourses WHERE StudentID = ? ");
            pSt.setString(1, userID);
            pSt.executeUpdate();

            pSt = conn.prepareStatement("DELETE FROM Student WHERE StudentID = ? ");
            pSt.setString(1, userID);
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeAllstudents() {
        try {
            pSt = conn.prepareStatement("DELETE FROM  StudentCourses");
            pSt.executeUpdate();
            pSt = conn.prepareStatement("DELETE FROM Student");
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAllTeachers() {
        try {
            pSt = conn.prepareStatement("DELETE FROM OFFICEHOURS ");
            pSt.executeUpdate();

            pSt = conn.prepareStatement("DELETE FROM SCHEDULE  ");
            pSt.executeUpdate();

            pSt = conn.prepareStatement("DELETE FROM Teacher");
            pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<HashMap<String, String>> get_all_teachers() {
        schedInfo = new ArrayList<HashMap<String, String>>();
        try {

            pSt = conn.prepareStatement("SELECT * FROM TEACHER");
            pSt.executeQuery();
            rS = pSt.executeQuery();

            while (rS.next()) {

                HashMap<String, String> teachers = new HashMap<String, String>();            //There will be one HashMap for each meeting

                String teacherid = rS.getString("TEACHERID");
                String FirstName = rS.getString("FIRSTNAME");
                String LastName = rS.getString("LASTNAME");
                String Email = rS.getString("EMAILADDRESS");

                teachers.put("ID", teacherid);
                teachers.put("FIRSTNAME", FirstName);
                teachers.put("LASTNAME", LastName);
                teachers.put("EMAILADDRESS", Email);


                schedInfo.add(teachers);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedInfo;
    }


    public ArrayList<HashMap<String, String>> get_all_Students() {
        schedInfo = new ArrayList<HashMap<String, String>>();
        try {

            pSt = conn.prepareStatement("SELECT * FROM STUDENT");
            pSt.executeQuery();
            rS = pSt.executeQuery();

            while (rS.next()) {

                HashMap<String, String> Students = new HashMap<String, String>();            //There will be one HashMap for each meeting

                String Studentid = rS.getString("STUDENTID");
                String FirstName = rS.getString("FIRSTNAME");
                String LastName = rS.getString("LASTNAME");
                String Email = rS.getString("EMAILADDRESS");

                Students.put("ID", Studentid);
                Students.put("FIRSTNAME", FirstName);
                Students.put("LASTNAME", LastName);
                Students.put("EMAILADDRESS", Email);


                schedInfo.add(Students);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedInfo;
    }

}

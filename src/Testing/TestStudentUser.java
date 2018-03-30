package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Domain.Controller;
import Domain.Student;

public class TestStudentUser {

	String userID = "jenk2002";
	String password = "password";
	static Controller ctl;
	Student student;
	
	@BeforeClass
	public static void start(){
		ctl = Controller.getController();
		ctl.startup();
	}
	
	@Before
	public void setup(){
		ctl.authenticateUser(userID, password);
	}
	
	@After
	public void teardown(){
		ctl.logout();
	}
	
	@Test
	public void testStudentLogin() {
				
		student = ctl.getStudent();
		
		assertFalse(student == null);
		
		assertEquals("jenk2002", student.getID());
		assertEquals("jenk2002", ctl.getUserID());
		
		assertEquals("John", student.getFirstName());
		assertEquals("Jenkins", student.getLastName());
		assertEquals("jenk2002@algonquinlive.com", student.getEmailAddress());
		
		assertTrue(ctl.getAdmin() == null);
		assertTrue(ctl.getTeacher() == null);
		
	}
	
	@Test
	public void testStudentClasses() {
		
		ArrayList<HashMap<String,String>> classes;
		
		student = ctl.getStudent();
		
		classes = student.getClasses();
		
		assertTrue(classes.get(0).get("Course").equals("CST9245-010 - Electro-Engineering - Lecture"));
		assertTrue(classes.get(1).get("Course").equals("CST4435-012 - Java Programming - Lab"));
		
		assertTrue(classes.get(0).get("Teacher").equals("Roland Biggs"));
		assertTrue(classes.get(1).get("Teacher").equals("Erin Snyder"));
		
		assertFalse(classes.get(0).get("Course").equals("CST1134-010 - Intro to Web - Lecture"));
		assertEquals(classes.size(), 2);

	}
	
	@Test
	public void testStudentMeeting(){
		
		ArrayList<HashMap<String,String>> classes;
		
		student = ctl.getStudent();
		
		classes = student.getClasses();
		
		ArrayList<HashMap<String,String>> office = ctl.getTeacherOfficeTimes(classes.get(0).get("ID"));
						
		ctl.bookMeeting(office.get(0));
		
		assertTrue(ctl.getAllStudentMeetings().get(0).get("Room").equals("T317"));
		
		assertTrue(ctl.getAllStudentMeetings().get(0).get("Time").equals("15:00:00"));
		
		assertTrue(ctl.getAllStudentMeetings().size() == 1);
		
		ctl.resetMeetings(office.get(0));
		
	}
	
	@Test
	public void testEmails(){
		
		ArrayList<HashMap<String,String>> classes;
		
		student = ctl.getStudent();
		
		classes = student.getClasses();
		
		String email = ctl.getTeacherEmail(classes.get(1).get("ID"));
		
		assertTrue(email.equals("snyd9954@algonquincollege.com"));
		
		email = ctl.getTeacherEmail(classes.get(0).get("ID"));
		
		assertFalse(email.equals("snyd9954@algonquincollege.com"));
		
		assertTrue(email.equals("bigg2212@algonquincollege.com"));
	}

}

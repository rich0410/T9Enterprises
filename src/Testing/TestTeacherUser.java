package Testing;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Domain.Controller;
import Domain.Teacher;

public class TestTeacherUser {

	String userID = "bigg2212";
	String password = "password";
	static Controller ctl;
	Teacher teacher;
	
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
	public void testTeacherLogin() {
				
		teacher = ctl.getTeacher();
		
		assertFalse(teacher == null);
		
		assertEquals("bigg2212", teacher.getID());
		assertEquals("bigg2212", ctl.getUserID());
		
		assertEquals("Roland", teacher.getFirstName());
		assertEquals("Biggs", teacher.getLastName());
		assertEquals("bigg2212@algonquincollege.com", teacher.getEmailAddress());
				
		assertTrue(ctl.getAdmin() == null);
		assertTrue(ctl.getStudent() == null);
		
	}
	
	@Test
	public void testTeacherClasses() {
		
		teacher = ctl.getTeacher();
		
		ArrayList<HashMap<String,String>> classes;
		
		classes = teacher.getClasses();
		
		assertTrue(classes.get(0).get("Course").equals("CST9245-010 - Electro-Engineering - Lecture"));
		assertTrue(classes.size() == 1);
		
		classes = teacher.getOfficeHours();
		
		assertTrue(classes.get(0).get("Day").equals("Thu"));
		assertFalse(classes.get(0).get("Room").equals("B343"));
		assertTrue(classes.get(0).get("Room").equals("T317"));
		
		assertTrue(classes.get(1).get("Room").equals("B343"));
		assertFalse(classes.get(1).get("Time").equals("15:00:00"));
		
		assertEquals(classes.size(), 2);
		
	}
	
	@Test
	public void testTeacherEmails(){
		
		teacher = ctl.getTeacher();
		
		ArrayList<HashMap<String,String>> classes;
		
		classes = teacher.getClasses();
		
		String course = classes.get(1).get("Course").substring(0, classes.get(0).get("Course").indexOf(" "));
		
		ArrayList<String> emails = ctl.getAllStudentEmails(course);
		
		assertTrue(emails.get(0) != null);
		
		assertTrue(emails.get(0).contains("@algonquinlive.com"));
	}
	
	@Test
	public void testTeacherUpdateSchedule(){
				
		File file = new File("schedule.csv");
		
		ctl.updateSchedule(file);
		
		ArrayList<HashMap<String,String>> classes;
		
		teacher = ctl.getTeacher();
		
		classes = teacher.getClasses();
		
		assertTrue(classes.get(2).get("Course").equals("CST1134-010 - Intro to Web - Lecture"));
		assertTrue(classes.size() == 3);
		assertFalse(classes.get(0).get("Course").equals("CST9245-010 - Electro-Engineering - Lecture"));
		
		classes = teacher.getOfficeHours();
		
		assertTrue(classes.get(0).get("Day").equals("Tue"));
		assertFalse(classes.get(0).get("Room").equals("B343"));
		assertFalse(classes.get(0).get("Day").equals("Thu"));
		assertTrue(classes.get(0).get("Room").equals("T309"));
		
		assertTrue(classes.size() == 6);
		assertFalse(classes.size() == 2);
				
	}

}
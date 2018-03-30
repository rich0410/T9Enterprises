package Domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This represents a teacher user. The user will have a collection of TimeTable objects (one for every week in the semester). 
 * @author Justin
 *
 */
public class Teacher extends User{
	
	private ArrayList<HashMap<String,String>> timetable = new ArrayList<HashMap<String,String>>();	
	private ArrayList<HashMap<String,String>> officehours = new ArrayList<HashMap<String,String>>();	

	public Teacher(String userID){
		super(userID);
	}
	
	public ArrayList<HashMap<String,String>> getClasses(){
		return timetable;
	}
	
	/**
	 * Set the timetable, creating an entry for every week in the semester.
	 * @param tTable
	 */
	public void setClasses(ArrayList<HashMap<String,String>> timetable){
		this.timetable = timetable;
	}

	public ArrayList<HashMap<String,String>> getOfficeHours() {
		return officehours;
	}

	public void setOfficeHours(ArrayList<HashMap<String,String>> officehours) {
		this.officehours = officehours;
	}
	
}

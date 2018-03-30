package Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
	
	private ArrayList<HashMap<String,String>> classes = new ArrayList<HashMap<String,String>>();

	public Student(String userID) {
		super(userID);
	}

	public void setClasses(ArrayList<HashMap<String, String>> classes){
		this.classes = classes;
	}
	
	public ArrayList<HashMap<String, String>> getClasses(){
		return classes;
	}
}

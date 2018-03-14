package Domain;

import java.util.ArrayList;

public class Student extends User {
	
	ArrayList<String> classes = new ArrayList<String>();

	public Student(String userID) {
		super(userID);
	}

	protected void setClasses(ArrayList<String> classes){
		this.classes = classes;
	}
	
	protected ArrayList<String> getClasses(){
		return classes;
	}
}

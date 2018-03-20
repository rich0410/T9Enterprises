package Domain;

import java.util.ArrayList;

public class User {
	
	private String firstName, lastName, email, userID, teacherid;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userID){
		this.userID = userID;
	}
	

	
	public String getFirstName(){
		return firstName;
	}

	public void setTeacherId(String teacherId){
		this.teacherid = teacherId;
	}

	public String getTeacherid(){
		return teacherid;
	}


	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	
	public void setEmailAddress(String email){
		this.email = email;
	}

	public  String getEmailAddress(){
		return email;
	}
	
	public String getID(){
		return userID;
	}
}

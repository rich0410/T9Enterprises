package Domain;

import java.util.ArrayList;

public class User {
	
	private String firstName, lastName, email, userID;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userID){
		this.userID = userID;
	}
	
	
	protected String getFirstName(){
		return firstName;
	}
	
	protected void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	protected String geLlastName(){
		return lastName;
	}
	
	protected void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	protected String getemail(){
		return email;
	}
	
	protected void setEmail(String email){
		this.email = email;
	}
	
	protected String getID(){
		return userID;
	}
}

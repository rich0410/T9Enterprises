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
	
	public User(String firstName,String lastName,String email,String userID){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userID = userID;
		
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String geLlastName(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getemail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getID(){
		return userID;
	}
}

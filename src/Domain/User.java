package Domain;

import java.util.ArrayList;

public class User {
	
	private String firstName, lastName, email, userID;
	private int role;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userID){
		this.userID = userID;
	}
	

	
	public String getFirstName(){
		return firstName;
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
	public void setID(String id){
		this.userID = id;
	}
	
	public String getID(){
		return userID;
	}

	public void setRole(int role){
		this.role = role;
	}

    public int getRole() {
        return role;
    }
}

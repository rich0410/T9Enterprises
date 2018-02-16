package Domain;

public class Person {
	
	private String firstName, lastName, email;
	
	public Person(String firstName, String lastName, String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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
	
	protected void setlastName(String lastName){
		this.lastName = lastName;
	}
	
	protected String getemail(){
		return email;
	}
	
	protected void setEmail(String email){
		this.email = email;
	}
}

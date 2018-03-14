package Domain;

import java.util.Date;

public class Teacher extends User{
	
	Time_Table timetable = new Time_Table();

	public Teacher(String userID){
		super(userID);
	}

	/**
	* This method is used to add or update the timeSlot.
	* */
	protected void updateSchedule(){

       //timetable.addSlot();
	}

	/**
	 *This method is used to cancel the Time slot
	 **/
	protected void cancelClass(){

		// timetable.removeSlot();
	}
	
}

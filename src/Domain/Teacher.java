package Domain;

import java.util.Date;

public class Teacher extends User{
	
	//A collection of 15 Timetable objects, one for every week in the semester.
	private Time_Table[] timetable = new Time_Table[15];

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
	
	public Time_Table[] getTimeTable(){
		return timetable;
	}
	
	public void setTimeTable(Time_Table tTable){
		
		for(int i=0; i < 15; i++){
			timetable[i] = tTable;
		}
		
	}
	
}

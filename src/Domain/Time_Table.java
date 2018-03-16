package Domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class Time_Table {
	
	//A HashMap representing a schedule. The key will be the day of the week and the value will be an ArrayList of TimeSlot objects.
    private HashMap<String, ArrayList<TimeSlot>> schedule;


    public Time_Table(){
    	initSchedule();
    }

    /**
     * This will initialize the schedule object with default values.
     */
    private void initSchedule() {
    	schedule = new HashMap<String, ArrayList<TimeSlot>>();
    	
		schedule.put("Monday", new ArrayList<TimeSlot>());						//There will be a collection of TimeSlots identified by
		schedule.put("Tuesday", new ArrayList<TimeSlot>());						//a day of the week. 
		schedule.put("Wednesday", new ArrayList<TimeSlot>());
		schedule.put("Thursday", new ArrayList<TimeSlot>());
		schedule.put("Friday", new ArrayList<TimeSlot>());
		
	}

	protected void addSlot(TimeSlot tSlot){    
    	ArrayList<TimeSlot> dailySched = schedule.get(tSlot.getDay());
    	
    	dailySched.add(tSlot); 	
    }

    protected void removeSlot(TimeSlot tSlot){ 
    	ArrayList<TimeSlot> dailySched = schedule.get(tSlot.getDay());
    	
    	if(dailySched.contains(tSlot)){
    		dailySched.remove(tSlot);     
    	}
	}

    protected TimeSlot getTimeSlot(String courseCode){
    	TimeSlot timeSlot = null;
    	
    	do{
    	//	schedule.get
    	}while(timeSlot != null);

        return  timeSlot;
    }

}

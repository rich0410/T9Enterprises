package Domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class Time_Table {
	
	//A HashMap representing a schedule. The key will be the day of the week and the value will be an ArrayList of TimeSlot objects.
    private HashMap<String, ArrayList<TimeSlot>> schedule;
    private TimeSlot tSlot;


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

	protected void addSlot(String day, TimeSlot tSlot){    
    	ArrayList<TimeSlot> dailySched = schedule.get(day);
    	
    	dailySched.add(tSlot); 	
    }

    protected void removeSlot(String day, TimeSlot tSlot){ 
    	ArrayList<TimeSlot> dailySched = schedule.get(day);
    	
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
    
	public void addSlots(HashMap<String, ArrayList<String>> slotInfo) {
		
		boolean moreSlots = true;
		int counter = 0;
		
		while(moreSlots){
			
			tSlot = new TimeSlot();

			tSlot.setCourse(slotInfo.get("Course").get(counter));
			tSlot.setStartTime(slotInfo.get("Time").get(counter));
			tSlot.setDuration(Integer.parseInt(slotInfo.get("Duration").get(counter)));
		
			schedule.get(slotInfo.get("Day")).add(tSlot);
			
			counter++; 
			
			if(counter >= slotInfo.get("Course").size()){
				moreSlots = false;
			}
		}
		
	}

}

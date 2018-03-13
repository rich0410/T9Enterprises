package Domain;

import java.sql.Time;

public class Time_Table {
    private TimeSlot timeSlot;


    public Time_Table(){}

    protected void addSlot(Time Start_time , Time end_Time, String room_number,String coursename){    // This method is used to add the slot the the time table to book an appointment.

       timeSlot = new TimeSlot(room_number,Start_time , end_Time);
       timeSlot.setCourse(coursename);



    }

    protected void removeSlot(Time Start_time , Time end_Time, String room_number){ // This method is used to remove the added timeslot from the database.

        timeSlot = new TimeSlot(room_number,Start_time , end_Time);

    }

    protected TimeSlot getTimeSlot(Time DateTime){


        return  timeSlot;
    }

}

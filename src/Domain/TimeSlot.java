package Domain;

import java.sql.Time;
import java.time.LocalTime;

public class TimeSlot {
    private LocalTime startTime;
    private String room_number;
    private int duration;
    private boolean cancelled = false;
    private String course;

    public TimeSlot(){}

    protected LocalTime getStartTime(){
        return startTime;
    }
    protected int getDuration(){
        return duration;
    }

    protected String getRoom_number(){
        return room_number;
    }

    protected  String getCourse(){
        return  course;
    }

    protected boolean isCancelled(){
        return cancelled;
    }

    protected void setStartTime(String startTime){
        this.startTime = LocalTime.parse(startTime);
    }

    protected  void setRoom_number(String room_number){
        this.room_number = room_number;
    }

    protected void setDuration(int duration){
        this.duration = duration;
    }
    
    protected void cancel(){
    	cancelled = true;
    }

    protected  void setCourse(String course){
        this.course = course;
    }
}

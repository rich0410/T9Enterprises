package Domain;

import java.sql.Time;
import java.time.LocalTime;

public class TimeSlot {
    private LocalTime startTime;
    private String room_number;
    private int duration;
    private boolean cancelled = false;
    private String course;
    private String day;

    public TimeSlot(){}

    public LocalTime getStartTime(){
        return startTime;
    }
    public int getDuration(){
        return duration;
    }

    public String getRoom_number(){
        return room_number;
    }

    public  String getCourse(){
        return  course;
    }

    public String getDay(){
        return day;
    }
    public boolean isCancelled(){
        return cancelled;
    }

    public void setStartTime(String startTime){
        this.startTime = LocalTime.parse(startTime);
    }

    public void setDay(String day){
        this.day = day;
    }

    public  void setRoom_number(String room_number){
        this.room_number = room_number;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }
    
    public void cancel(){
    	cancelled = true;
    }

    public  void setCourse(String course){
        this.course = course;
    }
}

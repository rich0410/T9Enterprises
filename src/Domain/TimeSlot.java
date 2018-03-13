package Domain;

import java.sql.Time;

public class TimeSlot {
    private Time startTime;
    private Time endTime;
    private String room_number;
    private Time day;
    private int duration;
    private boolean cancelled;
    private String course;

    public TimeSlot(){}

    public TimeSlot(String room_number,Time startTime,Time endTime){
        this.startTime = startTime;
        this.endTime = endTime;
        this.room_number = room_number;
    }

    protected Time getStartTime(){
        return startTime;
    }
    protected int getDuration(){
        return duration;
    }
    protected Time getEndTime(){
        return endTime;
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

    protected void setStartTime(Time startTime){
        this.startTime = startTime;
    }

    protected void setEndTime(Time endTime){
        this.endTime = endTime;
    }

    protected  void setRoom_number(){
        this.room_number = room_number;
    }

    protected void setDuration(int duration){
        this.duration = duration;
    }

    protected  void setCourse( String course){
        this.course = course;
    }
}

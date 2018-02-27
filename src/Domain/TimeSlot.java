package Domain;

import java.sql.Time;

public  abstract class TimeSlot {
    private Time startTime;
    private Time endTime;
    private String room_number;
    private boolean cancelled;

    public TimeSlot(){}

    public TimeSlot(Time startTime,Time endTime,String room_number){
        this.startTime = startTime;
        this.endTime = endTime;
        this.room_number = room_number;
    }

    protected Time getStartTime(){
        return startTime;
    }

    protected Time getEndTime(){
        return endTime;
    }

    protected String getRoom_number(){
        return room_number;
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
}

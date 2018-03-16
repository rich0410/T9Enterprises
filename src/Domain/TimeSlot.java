package Domain;

import java.sql.Time;

public class TimeSlot {
    private Time startTime;
    private String room_number;
    private String day;
    private int duration;
    private boolean cancelled;
    private String course;

    public TimeSlot(){}

    public TimeSlot(String room_number,Time startTime){
        this.startTime = startTime;
        this.room_number = room_number;
    }

    protected Time getStartTime(){
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

    protected void setStartTime(Time startTime){
        this.startTime = startTime;
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

	public String getDay() {
		// TODO Auto-generated method stub
		return null;
	}
}

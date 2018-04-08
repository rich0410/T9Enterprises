package Domain;

import javafx.scene.control.Button;

import java.sql.Time;
import java.time.LocalTime;

public class TimeSlot {
    private LocalTime startTime;
    private String room_number;
    private int duration;
    private boolean cancelled = false;
    private String course;
    private String day;
    private String Time;
    private String avalib;
    private String id;
    private Button button;

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

    public String getTime(){
        return Time;
    }

    public void setStartTime(String startTime){
        this.startTime = LocalTime.parse(startTime);
    }

    public void setTime(String startTime){
        this.Time = startTime;
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

    public void setAvalibility(String ava){
        this.avalib = ava;
    }
    public String getAvalibility(){
        return avalib;
    }

    public String getid(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public Button getButton(){
        return button;
    }
    public void setButton(Button button){
        this.button = button;
    }
}

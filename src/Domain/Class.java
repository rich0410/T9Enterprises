package Domain;

import java.sql.Date;
import java.sql.Time;

public class Class extends TimeSlot{

    private String schoolcode;
    private String coursename;
    public Class(String roomnumber, Time starttime , Time endtime){
        super();
    }

    protected void cancle(Date date){

    }

    protected String getCode(){
        return schoolcode;
    }

    protected String getName(){
        return coursename;
    }

}

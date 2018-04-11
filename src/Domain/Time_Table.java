package Domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This represents a teacher's timetable. A collection of TimeSlot objects will be bundled with a String representing a day of the week.
 * This class will received data from the database an use it to create TimeSlot objects and store them in an ArrayList identified by the day
 * of the week.
 *
 * @author Justin
 */
public class Time_Table {

    //A HashMap representing a schedule. The key will be the day of the week and the value will be an ArrayList of TimeSlot objects.
    private HashMap<String, ArrayList<TimeSlot>> schedule;
    private TimeSlot tSlot;


    public Time_Table() {
        initSchedule();
    }

    /**
     * This will initialize the schedule object with default values.
     */
    private void initSchedule() {
        schedule = new HashMap<String, ArrayList<TimeSlot>>();

        schedule.put("Monday", new ArrayList<TimeSlot>());                        //There will be a collection of TimeSlots identified by
        schedule.put("Tuesday", new ArrayList<TimeSlot>());                        //a day of the week.
        schedule.put("Wednesday", new ArrayList<TimeSlot>());
        schedule.put("Thursday", new ArrayList<TimeSlot>());
        schedule.put("Friday", new ArrayList<TimeSlot>());

    }

    /**
     * Adds a new time slot to the timetable. At this point the functionality to call this method has not been implemented. It's likely
     * the logic of this method will need to be revised.
     *
     * @param day
     * @param tSlot
     */
    protected void addSlot(String day, TimeSlot tSlot) {
        ArrayList<TimeSlot> dailySched = schedule.get(day);
        dailySched.add(tSlot);
    }

    /**
     * Removes a time slot from the timetable. At this point the functionality to call this method has not been implemented. It's likely
     * the logic of this method will need to be revised.
     *
     * @param day
     * @param tSlot
     */
    protected void removeSlot(String day, TimeSlot tSlot) {
        ArrayList<TimeSlot> dailySched = schedule.get(day);

        if (dailySched.contains(tSlot)) {
            dailySched.remove(tSlot);
        }
    }

    /**
     * Retrieves a time slot from the timetable. At this point the method needs to be revised.
     *
     * @param courseCode
     * @return
     */
    protected TimeSlot getTimeSlot(String courseCode) {
        TimeSlot timeSlot = null;

        do {
            //	schedule.get
        } while (timeSlot != null);

        return timeSlot;
    }

    /**
     * A resource bundle will be passed to this method from the database object. This method will use this data to create TimeSlot objects and insert them
     * into the appropriate collection identified by the day of the week.
     *
     * @param slotInfo
     */
    public void addSlots(HashMap<String, ArrayList<String>> slotInfo) {

        boolean moreSlots = true;
        int counter = 0;

        while (moreSlots) {

            tSlot = new TimeSlot();

            tSlot.setCourse(slotInfo.get("Course").get(counter));
            tSlot.setStartTime(slotInfo.get("Time").get(counter));
            tSlot.setDuration(Integer.parseInt(slotInfo.get("Duration").get(counter)));
            tSlot.setRoom_number(slotInfo.get("Room").get(counter));

            schedule.get(slotInfo.get("Day")).add(tSlot);

            counter++;

            if (counter >= slotInfo.get("Course").size()) {
                moreSlots = false;
            }
        }

    }

}

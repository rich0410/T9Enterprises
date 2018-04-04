package UI;

import DataAccess.Database;
import Domain.Controller;
import Domain.TimeSlot;
import Domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

public class TeacherTimeTable implements Initializable {

    @FXML
    private TableView<TimeSlot> tableView;
    @FXML
    private TableColumn<TimeSlot, String> DayOfTheWeek;

    @FXML
    private TableColumn<TimeSlot, String> CourseCodeID;

    @FXML
    private TableColumn<TimeSlot, String> Duration;
    @FXML
    private TableColumn<TimeSlot, String> StartTime;
    @FXML
    private TableColumn<TimeSlot, String> RoomNumber;

    @FXML
    private BorderPane root;

    private Controller c;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CourseCodeID.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Course"));
        Duration.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Duration"));
        DayOfTheWeek.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Day"));
        StartTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Time"));
        RoomNumber.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Room_number"));


        tableView.setItems(this.getdata());              //this is causing a null pointer.

    }

    public ObservableList<TimeSlot> getdata() {

        Controller c = Controller.getController();
        User u = RegistrationFormController.getUser();
        System.out.println(u.getUserID());
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();
        ArrayList<HashMap<String, String>> teacherslot = c.getAllTeacherMeetings();
        ArrayList<HashMap<String, String>> OfficeSlot = c.getTeacherOfficeTimesOpen();
        for (int i = 0; i < teacherslot.size(); i++) {
            Iterator<String> myVeryOwnIterator = teacherslot.get(i).keySet().iterator();
            TimeSlot t_slot = new TimeSlot();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = teacherslot.get(i).get(key);
                if (key.equals("Duration")) {
                    t_slot.setDuration(Integer.parseInt(value));
                }
                if (key.equals("Course")) {
                    t_slot.setCourse(value);
                }
                if (key.equals("Day")) {
                    t_slot.setDay(value);
                }
                if (key.equals("Time")) {
                    t_slot.setTime(value);
                }
                if (key.equals("Room")) {
                    t_slot.setRoom_number(value);
                }

            }
            list.add(t_slot);
        }


        for (int i = 0; i < OfficeSlot.size(); i++) {
            Iterator<String> myVeryOwnIterator = OfficeSlot.get(i).keySet().iterator();
            TimeSlot t_slot = new TimeSlot();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = OfficeSlot.get(i).get(key);
                if (key.equals("Duration")) {
                    t_slot.setDuration(Integer.parseInt(value));
                }
                if (key.equals("ID")) {
                    t_slot.setCourse("Office");
                }
                if (key.equals("Day")) {
                    t_slot.setDay(value);
                }
                if (key.equals("Time")) {
                    t_slot.setTime(value);
                }
                if (key.equals("Room")) {
                    t_slot.setRoom_number(value);
                }

            }
            list.add(t_slot);
        }

        return list;
    }
}

package UI;

import Domain.Controller;
import Domain.TimeSlot;
import Domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private TableView<TimeSlot> tableView1;

    @FXML
    private TableView<TimeSlot> tableView;


    @FXML
    private TableColumn<TimeSlot, String> CourseCodeID;


    @FXML
    private TableColumn<TimeSlot, String> Teacher;

    @FXML
    private TableColumn<TimeSlot, String> StartTime;

    @FXML
    private TableColumn<TimeSlot, String> EndTime;

    @FXML
    private TableColumn<TimeSlot, String> Email;

    @FXML
    private TableColumn<TimeSlot, String> RoomNumber;


    @FXML
    private TableColumn<TimeSlot, String> Time;

    @FXML
    private TableColumn<TimeSlot, String> Room;

    @FXML
    private TableColumn<TimeSlot, String> Professor;

    @FXML
    private TableColumn<TimeSlot, String> Day;

    private TimeSlot t;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CourseCodeID.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Course"));
        //LabLecture.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("firstName"));
        Time.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Time"));
        Room.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Room_number"));
        Professor.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Avalibility"));
        Day.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Day"));
        tableView1.setItems(this.gettimeSlots());              //this is causing a null pointer.

        Teacher.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Course"));
        StartTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Time"));
        EndTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Day"));
        Email.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Avalibility"));
        RoomNumber.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Room_number"));
        tableView.setItems(this.getAppointmentSlots());
        tableView.setOnMouseClicked(event -> {
            clickItem(event);
        });
    }


    public void clickItem(MouseEvent event) {
        if (event.getClickCount() == 1) //Checking double click
        {
            setTimeSlot(tableView.getSelectionModel().getSelectedItem());

        }
    }

    @FXML
    protected void handleDeleteAction(ActionEvent event) {
        Controller c = Controller.getController();
        HashMap<String, String> office = new HashMap<String, String>();
        office.put("ID",this.getTimeSlot().getid());
        c.ResetMeeting(office);
    }

    public ObservableList<TimeSlot> gettimeSlots() {

        Controller con = Controller.getController();
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();

        ArrayList<HashMap<String, String>> teacherslot =  con.getStudentData();

        for (int i = 0; i < teacherslot.size(); i++) {

            Iterator<String> myVeryOwnIterator = teacherslot.get(i).keySet().iterator();
            TimeSlot t = new TimeSlot();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = teacherslot.get(i).get(key);

                if (key.equals("Day")) {
                    t.setDay(value);
                }
                if (key.equals("Time")) {
                    t.setTime(value);
                }
                if (key.equals("Teacher")) {
                    t.setAvalibility(value);

                }
                if (key.equals("Room")) {
                    t.setRoom_number(value);
                }

                if (key.equals("Course")) {
                    t.setCourse(value);
                }
            }
            list.add(t);
        }


        return list;
    }

    public ObservableList<TimeSlot> getAppointmentSlots() {



        Controller con = Controller.getController();
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();

        ArrayList<HashMap<String, String>> teacherslot = con.getAllStudentMeetings();

        for (int i = 0; i < teacherslot.size(); i++) {

            Iterator<String> myVeryOwnIterator = teacherslot.get(i).keySet().iterator();
            TimeSlot t = new TimeSlot();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = teacherslot.get(i).get(key);

                if (key.equals("Teacher")) {
                    t.setCourse(value);
                }
                if (key.equals("Day")) {
                    t.setDay(value);
                }
                if (key.equals("Time")) {
                    t.setTime(value);

                }
                if (key.equals("Email")) {
                    t.setAvalibility(value);
                }

                if (key.equals("Room")) {
                    t.setRoom_number(value);
                }
            }
            list.add(t);
        }


        return list;
    }


    private void setTimeSlot(TimeSlot t){
        this.t = t;
    }

    private TimeSlot getTimeSlot(){
        return t;
    }
}

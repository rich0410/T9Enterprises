package UI;

import Domain.Controller;
import Domain.TimeSlot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

public class teacher_Appointments implements Initializable {

    @FXML
    private BorderPane root;


    @FXML
    private TableView<TimeSlot> tableView;


    @FXML
    private TableColumn<TimeSlot, String> Student;

    @FXML
    private TableColumn<TimeSlot, String> StartTime;

    @FXML
    private TableColumn<TimeSlot, String> EndTime;

    @FXML
    private TableColumn<TimeSlot, String> Email;

    @FXML
    private TableColumn<TimeSlot, String> RoomNumber;

    @FXML
    private TableColumn<TimeSlot, String> Options;

    @FXML
    private TimeSlot t;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Student.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Course"));
        StartTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Time"));
        EndTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Day"));
        Email.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Avalibility"));
        RoomNumber.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Room_number"));
        Options.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Button"));
        tableView.setItems(this.getAppointmentSlots());

    }


//    public void clickItem(MouseEvent event) {
//        if (event.getClickCount() == 1) //Checking double click
//        {
//            setTimeSlot(tableView.getSelectionModel().getSelectedItem());
//
//        }
//    }


    protected void handleDeleteAction(ActionEvent event, String id) {
        Controller c = Controller.getController();
        HashMap<String, String> office = new HashMap<String, String>();
        office.put("ID", id);
        c.ResetMeeting(office);
    }


    public ObservableList<TimeSlot> getAppointmentSlots() {


        Controller con = Controller.getController();
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();

        ArrayList<HashMap<String, String>> appintment = con.getAllTeacherAppointments();


        for (int i = 0; i < appintment.size(); i++) {

            Iterator<String> myVeryOwnIterator = appintment.get(i).keySet().iterator();
            TimeSlot t = new TimeSlot();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = appintment.get(i).get(key);
                if (key.equals("ID")) {
                    t.setId(value);
                }
                if (key.equals("Student")) {
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

                Button b = new Button("Remove");
                b.setOnAction(event -> {
                    handleDeleteAction(event, t.getid());
                });
                t.setButton(b);
            }

            list.add(t);
        }


        return list;
    }

//    private void setTimeSlot(TimeSlot t) {
//        this.t = t;
//    }
//
//    private TimeSlot getTimeSlot() {
//        return t;
//    }

}

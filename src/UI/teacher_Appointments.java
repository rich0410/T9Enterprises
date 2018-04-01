package UI;

import Domain.Controller;
import Domain.TimeSlot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class teacher_Appointments implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private TableView<TimeSlot> tableView1;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Student.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Course"));
        StartTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Time"));
        EndTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Day"));
        Email.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Avalibility"));
        RoomNumber.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Room_number"));
        tableView.setItems(this.getAppointmentSlots());
    }


    @FXML
    protected void handleDeleteAction(ActionEvent event) {

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
            }
            list.add(t);
        }


        return list;
    }

}

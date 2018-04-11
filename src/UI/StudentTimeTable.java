package UI;

import Domain.Controller;
import Domain.TimeSlot;
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
/**
 * This is the controller class used to get the students timetable
 * @author Prabdeep Singh Pannu
 * */

public class StudentTimeTable implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private TableView<TimeSlot> tableView1;


    @FXML
    private TableColumn<TimeSlot, String> CourseCodeID;


    @FXML
    private TableColumn<TimeSlot, String> Time;

    @FXML
    private TableColumn<TimeSlot, String> Room;

    @FXML
    private TableColumn<TimeSlot, String> Professor;

    @FXML
    private TableColumn<TimeSlot, String> Day;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CourseCodeID.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Course"));
        //LabLecture.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("firstName"));
        Time.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Time"));
        Room.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Room_number"));
        Professor.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Avalibility"));
        Day.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Day"));
        tableView1.setItems(this.gettimeSlots());              //this is causing a null pointer.
    }


    private ObservableList<TimeSlot> gettimeSlots() {

        Controller con = Controller.getController();
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();

        ArrayList<HashMap<String, String>> teacherslot = con.getStudentData();

        for (int i = 0; i < teacherslot.size(); i++) {

            Iterator<String> myVeryOwnIterator = teacherslot.get(i).keySet().iterator();
            TimeSlot t = new TimeSlot();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = teacherslot.get(i).get(key);
                if (key.equals("ID")) {
                    t.setId(value);
                }

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
}

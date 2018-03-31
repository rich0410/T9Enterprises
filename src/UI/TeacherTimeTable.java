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
import java.util.ResourceBundle;

public class TeacherTimeTable implements Initializable{

    @FXML
    private TableView<TimeSlot> tableView;
    @FXML
    private TableColumn<TimeSlot, String> DayOfTheWeek;

    @FXML
    private TableColumn<TimeSlot, String> CourseCodeID;

    @FXML
    private TableColumn<TimeSlot, String> Duration;
    @FXML
    private TableColumn<TimeSlot, String>  StartTime;
    @FXML
    private TableColumn<TimeSlot, String> RoomNumber;

    @FXML
    private BorderPane root;

    private Controller c;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CourseCodeID.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("CourseCodeID"));
        Duration.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Duration"));
        DayOfTheWeek.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("DayOfTheWeek"));
        StartTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("StartTime"));
        RoomNumber.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("RoomNumber"));


        tableView.setItems(this.getdata());              //this is causing a null pointer.

    }

    public ObservableList<TimeSlot> getdata() {

        Controller c = Controller.getController();
        User u = RegistrationFormController.getUser();
        System.out.println(u.getUserID());
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();
        HashMap<String, ArrayList<String>> teacherslot = c.getTeacherTimetable(u.getUserID());
        System.out.println(u.getFirstName());
        TimeSlot t_slot = new TimeSlot();

        return list;
    }
}

package UI;

import DataAccess.Database;
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

import java.time.LocalDate;
import java.util.*;

public class Availability implements Initializable {


    @FXML
    private BorderPane root;

    @FXML
    private TableView<TimeSlot> tableView;

    @FXML
    private TableColumn<TimeSlot, String> DayOfTheWeek;

    @FXML
    private TableColumn<TimeSlot, String> StartTime;

    @FXML
    private TableColumn<TimeSlot, String> Duration;

    @FXML
    private TableColumn<TimeSlot, String> RoomNumber;

    @FXML
    private TableColumn<TimeSlot, String> Location;

    private Database db;

    private String Userid;

    private TimeSlot t_slot;

    private User user;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DayOfTheWeek.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Day"));
        StartTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("StartTime"));
        Duration.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Duration"));
        RoomNumber.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Room_number"));
        Location.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Course"));
        user =ProfessorController.getUser();
        tableView.setItems(this.gettimeSlots());
        tableView.setOnMouseClicked(event -> {
            clickItem(event);
        });


    }

    public void clickItem(MouseEvent event) {
        if (event.getClickCount() == 1) //Checking double click
        {
            this.setTimeSlot(tableView.getSelectionModel().getSelectedItem());

        }
    }

    public ObservableList<TimeSlot> gettimeSlots() {

        db = new Database();
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();
        HashMap<String, ArrayList<String>> teacherslot = db.getOfficeData(user.getUserID());

        t_slot = new TimeSlot();
        for (Map.Entry<String, ArrayList<String>> entry : teacherslot.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();

            for (String aString : value) {
                if (key.equals("Duration")){
                    t_slot.setDuration(Integer.parseInt(aString));
                }
                if (key.equals("Course")){
                    t_slot.setCourse(aString);
                }
                if (key.equals("Day")){
                    t_slot.setDay(aString);
                }
                if (key.equals("Room")){
                    t_slot.setRoom_number(aString);
                }
                if (key.equals("Time")){
                    t_slot.setStartTime(aString);
                }

            }

        }
        list.add(t_slot);


        return list;
    }

    @FXML
    protected void handleUpdateAction(ActionEvent event) {
        BookAnAppointment.dateP.setValue(LocalDate.now());
        BookAnAppointment.starttimeP.setValue(getT_slot().getStartTime());
        BookAnAppointment.endtimeP.setValue(getT_slot().getStartTime().plusMinutes(15));
        BookAnAppointment.roomP.setText(getT_slot().getRoom_number());
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.close();

    }


    public TimeSlot getT_slot() {
        return t_slot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.t_slot = timeSlot;
    }
}

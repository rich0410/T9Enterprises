package UI;

import DataAccess.Database;
import Domain.Controller;
import Domain.TimeSlot;
import Domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        RoomNumber.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Room_number"));
        Location.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Avalibility"));
        user = ProfessorController.getUser();
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

        Controller con = Controller.getController();
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();

        ArrayList<HashMap<String, String>> teacherslot = con.getTeacherOfficeTimes(user.getUserID());

        for (int i = 0; i < teacherslot.size(); i++) {

            Iterator<String> myVeryOwnIterator = teacherslot.get(i).keySet().iterator();
            t_slot = new TimeSlot();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = teacherslot.get(i).get(key);
                if (key.equals("Day")) {
                    t_slot.setDay(value);
                }
                if (key.equals("Time")) {
                    t_slot.setStartTime(value);
                }
                if (key.equals("Booked")) {
                    t_slot.setAvalibility(value);

                }
                if (key.equals("Room")) {
                    t_slot.setRoom_number(value);
                }

            }
            list.add(t_slot);
        }



        return list;
    }

    @FXML
    protected void handleUpdateAction(ActionEvent event) {
        try {
            BookAnAppointment.dateP.setText(getT_slot().getDay());
            BookAnAppointment.starttimeP.setValue(getT_slot().getStartTime());
            BookAnAppointment.endtimeP.setValue(getT_slot().getStartTime().plusMinutes(15));
            BookAnAppointment.roomP.setText(getT_slot().getRoom_number());
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, null, "Error",
                    "No data Available!");
        }
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

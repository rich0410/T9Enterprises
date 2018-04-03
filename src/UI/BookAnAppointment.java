package UI;

import Domain.Controller;
import Domain.TimeSlot;
import Domain.Time_Table;
import User.Email;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookAnAppointment implements Initializable {
    @FXML
    private TextField professor;

    @FXML
    private TextField date;

    @FXML
    private JFXTimePicker starttime;

    @FXML
    private JFXTimePicker endtime;

    @FXML
    private TextField room;


    @FXML
    private GridPane root;

    private Time_Table time_table;

    private TimeSlot t_slot;

    private Availability a;

    public static TextField professorP;

    public static TextField dateP;
    public static JFXTimePicker starttimeP;
    public static JFXTimePicker endtimeP;
    public static TextField roomP;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        professorP = professor;
        dateP = date;
        starttimeP = starttime;
        endtimeP = endtime;
        roomP = room;


    }

    @FXML
    protected void handleappointmentButtonAction(ActionEvent event) {
        Controller c = Controller.getController();
        Email e = new Email();
        e.email_Thread(ProfessorController.getUser().getEmailAddress());
        HashMap<String, String> meeting = new HashMap<String, String>();
        meeting.put("ID", Availability.getT_slot().getid());
        meeting.put("Email",ProfessorController.getUser().getEmailAddress());
        c.bookMeeting(meeting);
//        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, null, "Confirmed",
//                "Your Appointment request is accepted");
        try {
            BorderPane Content = FXMLLoader.load(getClass().getResource(("../Layout/Appointment.fxml")));
            Welcome.fragementP.getChildren().setAll(Content);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @FXML
    protected void handlebackroomAction(ActionEvent event) {
        Roomfinder.getRoomFinder();
    }


    @FXML
    protected void handlesearchAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Layout/professor.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 800, 400);
            scene.getStylesheets().add(getClass().getResource("../Layout/demo.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Professors");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }


    @FXML
    protected void handlecheckAction(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Layout/Availability.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 800, 400);
            scene.getStylesheets().add(getClass().getResource("../Layout/demo.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Availability");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }


}
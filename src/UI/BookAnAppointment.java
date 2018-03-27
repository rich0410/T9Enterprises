package UI;

import User.Email;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookAnAppointment implements Initializable {
    @FXML
    private TextField professor;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTimePicker starttime;

    @FXML
    private JFXTimePicker endtime;

    @FXML
    private TextField room;


    @FXML
    private GridPane root;




    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    protected void handleappointmentButtonAction(ActionEvent event) {

        Email e = new Email();
        e.email_Thread(ProfessorController.getUser().getEmailAddress());

        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, null, "Confirmed",
                "Your Appointment request is accepted");

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
    protected void handleUpdateAction(ActionEvent event) {

        this.professor.setText(ProfessorController.getUser().getFirstName() + " "+ProfessorController.getUser().getLastName());

    }

    @FXML
    protected void handlecheckAction(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../Layout/Avalibility.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 800, 400);
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
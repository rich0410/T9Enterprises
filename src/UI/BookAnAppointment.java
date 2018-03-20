package UI;

import User.Email;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import java.io.IOException;


public class BookAnAppointment {
    @FXML
    private TextField professor;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTimePicker starttime;

    @FXML
    private JFXTimePicker  endtime;


    @FXML
    private GridPane root;

    @FXML
    protected void handleappointmentButtonAction(ActionEvent event) {

        Email e = new Email();
        e.email_Thread(professor.getText());

        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, null, "Confirmed",
                "Your Appointment request is accepted");

    }

    @FXML
    protected void handlebackButtonAction(ActionEvent event) throws IOException {

        AnchorPane Content = FXMLLoader.load(getClass().getResource(("../Layout/Welcome.fxml")));
        root.getChildren().setAll(Content);


    }
}
package UI;

import Domain.Controller;
import Domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegistrationFormController {

    public static Boolean isSplashLoaded = false;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    private static User user;



    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {

        Window owner = submitButton.getScene().getWindow();
        Controller conn = Controller.getController();
        if ((conn.authenticateUser(emailField.getText(), passwordField.getText()))) {
            try {
                User u = conn.getUser();
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../Layout/Welcome.fxml"));
                mainControler.rootp.getChildren().setAll(pane);
                Welcome.lebelP.setText("Hello " + u.getFirstName()+" "+u.getLastName()+ ",");
                setUser(u);

            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }


        } else {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Error",
                    "Wrong login and password!");
        }}

        private static void setUser(User user){
            RegistrationFormController.user = user;
        }

        public static User getUser(){
            return user;
        }


    }





package UI;

import User.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;



public class RegistrationFormController{

    public static Boolean isSplashLoaded = false;
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;


    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Window owner = submitButton.getScene().getWindow();

        if(emailField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        UserLogin login = new UserLogin();

        if(login.authenticate(emailField.getText(),passwordField.getText())){
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful",
                                                                                            "Welcome!");
        }else{
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Error",
                    "Wrong login and password!");
        }
        //        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful",
//                "Welcome!");
    }





}

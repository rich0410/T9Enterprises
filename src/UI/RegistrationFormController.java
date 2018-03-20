package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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


    private String user1 = "student";
    private String password1 = "student";



    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {

        Window owner = submitButton.getScene().getWindow();

        if ((emailField.getText().equals(user1) && passwordField.getText().equals(password1))) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../Layout/Welcome.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                Stage stage = new Stage();
                stage.setTitle("New Window");
                stage.setScene(scene);
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }

        } else {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Error",
                    "Wrong login and password!");
        }


    }

//    @FXML
//    protected void handleSubmitButtonAction(ActionEvent event) {
//        Window owner = submitButton.getScene().getWindow();
//
//        if(emailField.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//                    "Please enter your email id");
//            return;
//        }
//        if(passwordField.getText().isEmpty()) {
//            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
//                    "Please enter a password");
//            return;
//        }
//
//        //UserLogin login = new UserLogin();
//
//
//        if((emailField.getText().equals(user1)&&passwordField.getText().equals(password1))||(emailField.getText().equals(user2)&&passwordField.getText().equals(password2))||(emailField.getText().equals(user3)&&passwordField.getText().equals(password3))){
//           try{
//
//               AnchorPane  parentContent = FXMLLoader.load(getClass().getResource(("../Layout/Welcome.fxml")));
//               root.getChildren().setAll(parentContent);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        else{
//            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Error",
//                    "Wrong login and password!");
//        }
//
//    }


}
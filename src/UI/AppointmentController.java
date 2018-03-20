package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    @FXML private BorderPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) throws IOException {
        AnchorPane Content = FXMLLoader.load(getClass().getResource(("../Layout/Welcome.fxml")));
        root.getChildren().setAll(Content);


    }

}

package UI;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SlidePanelForTeacher implements Initializable{

    @FXML
    private JFXButton b1;


    @FXML
    private JFXButton b2;

    @FXML
    private JFXButton b3;

    @FXML
    private JFXButton b4;

    @FXML
    private JFXButton exit;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Functionality for teacher needs to be added*/
    @FXML
    private void changeUI(ActionEvent event) throws IOException {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            case "View TimeTable":
//                GridPane Content2 = FXMLLoader.load(getClass().getResource(("../Layout/Book_an_Appointment.fxml")));
//                Welcome.fragementP.getChildren().setAll(Content2);
                break;
            case "Add TimeTable":
//                BorderPane Content3 = FXMLLoader.load(getClass().getResource(("../Layout/Appointment.fxml")));
//                Welcome.fragementP.getChildren().setAll(Content3);
                break;
            case "Remove TimeTable":
//                BorderPane Content4 = FXMLLoader.load(getClass().getResource(("../Layout/Roomfinder.fxml")));
//                Welcome.fragementP.getChildren().setAll(Content4);
                break;
            case "EXIT":
                Platform.exit();
                break;
        }
    }
}

package UI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;



public class SidePanelContentController implements Initializable {

    @FXML
    private JFXButton b1;


    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void changeUI(ActionEvent event) throws IOException {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            case "Professors":
                BorderPane Content1 = FXMLLoader.load(getClass().getResource(("../Layout/professor.fxml")));
                Welcome.rootP.getChildren().setAll(Content1);
                break;
            case "Book an Appointment":
                GridPane Content2 = FXMLLoader.load(getClass().getResource(("../Layout/Book_an_Appointment.fxml")));
                Welcome.rootP.getChildren().setAll(Content2);
                break;
            case "View Appointments":
                BorderPane Content3 = FXMLLoader.load(getClass().getResource(("../Layout/Appointment.fxml")));
                Welcome.rootP.getChildren().setAll(Content3);
                break;
            case "Cancel Appointment":
                BorderPane Content4 = FXMLLoader.load(getClass().getResource(("../Layout/CancleAppointment.fxml")));
                Welcome.rootP.getChildren().setAll(Content4);
                break;

            case "EXIT":
                Platform.exit();
                break;
        }
    }

    
}

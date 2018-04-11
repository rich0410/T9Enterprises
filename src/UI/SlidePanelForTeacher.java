package UI;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This is a controller for the Slide Panel for the teacher to get other fragments
 * @author Prabdeep Singh Pannu, Bin
 * */
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
                Welcome.drawerP.close();
                BorderPane Content2 = FXMLLoader.load(getClass().getResource(("../Layout/teacherTimeTable.fxml")));
                Welcome.fragementP.getChildren().setAll(Content2);

                break;
            case "Add TimeTable":
                Welcome.drawerP.close();
                AnchorPane Content3 = FXMLLoader.load(getClass().getResource(("../Layout/Upload_TimeTable.fxml")));
                Welcome.fragementP.getChildren().setAll(Content3);

                break;

            case "Room Finder":
                Welcome.drawerP.close();
                StackPane Content5 = FXMLLoader.load(getClass().getResource(("../Layout/Roomfinder.fxml")));
                Welcome.fragementP.getChildren().setAll(Content5);
                break;

            case "View Appointments":
                Welcome.drawerP.close();
                BorderPane Content4 = FXMLLoader.load(getClass().getResource(("../Layout/teacher_Appointments.fxml")));
                Welcome.fragementP.getChildren().setAll(Content4);

                break;
            case "Log Out":
                mainControler m = new mainControler();
                m.LogOut(event);
                break;
        }
    }
}

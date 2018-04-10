package UI;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SlidePanelAdmin implements Initializable {

    @FXML
    private JFXButton b1;


    @FXML
    private JFXButton b2;

    @FXML
    private JFXButton b3;

    @FXML
    private JFXButton b4;

    @FXML
    private JFXButton b5;


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
            case "Edit Teachers":
                Welcome.drawerP.close();
                BorderPane Content2 = FXMLLoader.load(getClass().getResource(("../Layout/Get_all_teachers.fxml")));
                Welcome.fragementP.getChildren().setAll(Content2);

                break;
            case "Edit Students":
                Welcome.drawerP.close();
                BorderPane Content3 = FXMLLoader.load(getClass().getResource(("../Layout/Get_all_Students.fxml")));
                Welcome.fragementP.getChildren().setAll(Content3);

                break;

            case "Edit Schedule":
                Welcome.drawerP.close();
                AnchorPane Content4 = FXMLLoader.load(getClass().getResource(("../Layout/Upload_Student_Schedule.fxml")));
                Welcome.fragementP.getChildren().setAll(Content4);

                break;

            case "Room Finder":
                Welcome.drawerP.close();
                BorderPane Content5 = FXMLLoader.load(getClass().getResource(("../Layout/Roomfinder.fxml")));
                Welcome.fragementP.getChildren().setAll(Content5);
                break;

            case "Log Out":
                mainControler m = new mainControler();
                m.LogOut(event);
                break;
        }
    }
}

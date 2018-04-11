package UI;

import DataAccess.ScheduleReader;
import Domain.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This the controller class for uploading the timetable for the teacher using csv file
 * @author Prabdeep Singh pannu
 * */

public class UploadTimetable implements Initializable {


    @FXML
    private TextField fileinput;


    private File file;

    private ScheduleReader dG;
    private mainControler m = new mainControler();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    protected void handlecheckAction(ActionEvent event) {
      m.FileChooser();
      this.fileinput.setText(m.get_name());
    }




    @FXML
    protected void handleUploadAction(ActionEvent event) {
        try {
            Alert alert = AlertHelper.showAlert(Alert.AlertType.WARNING, null, "Warning", "Dis you remove all the appointments with the students!");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.YES) {
                Controller c = Controller.getController();
                dG = new ScheduleReader();
                ArrayList<HashMap<String, String>> data =dG.readFile_TimeTable(m.get_file());
                c.setUpdatedData(data);

                AlertHelper.showAlert_noButton(Alert.AlertType.INFORMATION, null, "Success",
                        "Data entered successfully!");
                try {
                    BorderPane Content = FXMLLoader.load(getClass().getResource(("../Layout/teacherTimeTable.fxml")));
                    Welcome.fragementP.getChildren().setAll(Content);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            } else {
                alert.close();
            }


        }catch (SQLException e) {
            e.printStackTrace();
            AlertHelper.showAlert_noButton(Alert.AlertType.CONFIRMATION, null, "Error",
                    "No duplicate data can be inserted!");


        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.showAlert_noButton(Alert.AlertType.CONFIRMATION, null, "Error",
                    "No data Available!");

        }



    }

}

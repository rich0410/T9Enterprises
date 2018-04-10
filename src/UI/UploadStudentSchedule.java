package UI;

import DataAccess.ScheduleReader;
import Domain.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UploadStudentSchedule implements Initializable {

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
            Controller c = Controller.getController();
            dG = new ScheduleReader();
            ArrayList<HashMap<String, String>> data = dG.readFile_StudentSchadule(m.get_file());

            c.UpdateStudents_Schedule(data);
            AlertHelper.showAlert_noButton(Alert.AlertType.CONFIRMATION, null, "SuccessFull",
                    "Data added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.showAlert_noButton(Alert.AlertType.CONFIRMATION, null, "Error",
                    "No data Available!");
        }


    }

}

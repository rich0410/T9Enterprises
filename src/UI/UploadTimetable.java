package UI;

import DataAccess.ScheduleReader;
import Domain.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UploadTimetable implements Initializable {


    @FXML
    private TextField fileinput;


    private File file;

    private ScheduleReader dG;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    @FXML
    protected void handlecheckAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            stage.setTitle("File Chooser");
            FileChooser fc = new FileChooser();
            fc.setTitle("Open CSV File");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Quiz Files", "*.csv"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fc.showOpenDialog(stage);
            this.fileinput.setText(file.getName());
            set_file(file);
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, null, "Error",
                    "No data Available!");
        }


    }


    public void set_file(File file) {
        this.file = file;
    }

    public File get_file() {
        return file;
    }


    @FXML
    protected void handleUploadAction(ActionEvent event) {
        try {
            Controller c = Controller.getController();
            dG = new ScheduleReader();
            ArrayList<HashMap<String, String>> data =dG.readFile(this.get_file());

            c.setUpdatedData(data);


        } catch (Exception e) {
            e.printStackTrace();
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, null, "Error",
                    "No data Available!");
        }


    }

}

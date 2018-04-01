package UI;

import DataAccess.DataGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UploadTimetable implements Initializable {


    @FXML
    private TextField fileinput;


    private File file;

    private DataGenerator dG;


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

            dG = new DataGenerator();
            dG.readFile(this.get_file());
            System.out.println(dG.getNames());
            System.out.println(dG.getSched());



        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, null, "Error",
                    "No data Available!");
        }


    }

}

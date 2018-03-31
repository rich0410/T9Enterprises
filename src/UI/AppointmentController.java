package UI;

import Domain.TimeSlot;
import Domain.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    @FXML private BorderPane root;

    @FXML
    private TableView<TimeSlot> tableView1;

    @FXML
    private TableColumn<TimeSlot, String> CourseCodeID;

    @FXML
    private TableColumn<TimeSlot, String> LabLecture;

    @FXML
    private TableColumn<TimeSlot, String> Title;

    @FXML
    private TableColumn<TimeSlot, String> Room;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CourseCodeID.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("teacherid"));
        LabLecture.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("firstName"));
        Title.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("lastName"));
        Room.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("emailAddress"));


    }



    @FXML
    protected void handleDeleteAction(ActionEvent event) {

    }



}

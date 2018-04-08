package UI;

import Domain.Controller;
import Domain.TimeSlot;
import Domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    @FXML
    private BorderPane root;


    @FXML
    private TableView<TimeSlot> tableView;


    @FXML
    private TableColumn<TimeSlot, String> Teacher;

    @FXML
    private TableColumn<TimeSlot, String> StartTime;

    @FXML
    private TableColumn<TimeSlot, String> EndTime;

    @FXML
    private TableColumn<TimeSlot, String> Email;

    @FXML
    private TableColumn<TimeSlot, String> RoomNumber;


    @FXML
    private TableColumn<TimeSlot, String> Options;

    private TimeSlot t;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Teacher.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Course"));
        StartTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Time"));
        EndTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Day"));
        Email.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Avalibility"));
        RoomNumber.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Room_number"));
        Options.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Button"));
        tableView.setItems(this.getAppointmentSlots());

    }


    private void handleDeleteAction(String id) throws IOException {
        Controller c = Controller.getController();
        HashMap<String, String> office = new HashMap<String, String>();
        office.put("ID", id);
        c.ResetMeeting(office);
        BorderPane Content4 = FXMLLoader.load(getClass().getResource(("../Layout/Appointment.fxml")));
        Welcome.fragementP.getChildren().setAll(Content4);
    }


    private ObservableList<TimeSlot> getAppointmentSlots() {


        Controller con = Controller.getController();
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();

        ArrayList<HashMap<String, String>> teacherslot = con.getAllStudentMeetings();

        for (int i = 0; i < teacherslot.size(); i++) {

            Iterator<String> myVeryOwnIterator = teacherslot.get(i).keySet().iterator();
            TimeSlot t = new TimeSlot();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = teacherslot.get(i).get(key);

                if (key.equals("ID")) {
                    t.setId(value);
                }

                if (key.equals("Teacher")) {
                    t.setCourse(value);
                }
                if (key.equals("Day")) {
                    t.setDay(value);
                }
                if (key.equals("Time")) {
                    t.setTime(value);

                }
                if (key.equals("Email")) {
                    t.setAvalibility(value);
                }

                if (key.equals("Room")) {
                    t.setRoom_number(value);
                }
                Button b = new Button("Remove");
                b.setOnAction(e -> {
                    try {
                        this.handleDeleteAction(t.getid());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                t.setButton(b);
            }
            list.add(t);
        }
        return list;
    }

}

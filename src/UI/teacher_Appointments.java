package UI;

import Domain.Controller;
import Domain.TimeSlot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import Domain.User;

public class teacher_Appointments implements Initializable {

    @FXML
    private BorderPane root;


    @FXML
    private TableView<TimeSlot> tableView;


    @FXML
    private TableColumn<TimeSlot, String> Student;

    @FXML
    private TableColumn<TimeSlot, String> StartTime;

    @FXML
    private TableColumn<TimeSlot, String> EndTime;

    @FXML
    private TableColumn<TimeSlot, String> Email;

    @FXML
    private TableColumn<TimeSlot, String> RoomNumber;


    @FXML
    private TimeSlot t;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Student.setCellValueFactory(new PropertyValueFactory<>("Course"));
        StartTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        EndTime.setCellValueFactory(new PropertyValueFactory<>("Day"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Avalibility"));
        RoomNumber.setCellValueFactory(new PropertyValueFactory<>("Room_number"));
        TableColumn<TimeSlot, Boolean> options = new TableColumn<>("Options");
        options.setCellValueFactory(new PropertyValueFactory<>("Button"));

        tableView.getColumns().add(options);
        tableView.setItems(this.getAppointmentSlots());

    }


    protected void handleDeleteAction( String id,String email, String name) throws IOException {

        Alert alert = AlertHelper.showAlert(Alert.AlertType.WARNING,null, "Warning","Do you want to remove appointment with "+name +" professor");
        Optional<ButtonType> result = alert.showAndWait();
        alert.show();
        if (result.get() == ButtonType.YES){
            Controller c = Controller.getController();
            HashMap<String, String> office = new HashMap<String, String>();
            office.put("ID", id);
            office.put("Email",email);
            c.ResetMeeting(office);
            BorderPane Content4 = FXMLLoader.load(getClass().getResource(("../Layout/teacher_Appointments.fxml")));
            Welcome.fragementP.getChildren().setAll(Content4);
        } else {
            alert.close();
        }

    }




    public ObservableList<TimeSlot> getAppointmentSlots() {


        Controller con = Controller.getController();
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();

        ArrayList<HashMap<String, String>> appintment = con.getAllTeacherAppointments();


        for (int i = 0; i < appintment.size(); i++) {

            Iterator<String> myVeryOwnIterator = appintment.get(i).keySet().iterator();
            TimeSlot t = new TimeSlot();
            User u = new User();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = appintment.get(i).get(key);
                if (key.equals("ID")) {
                    t.setId(value);
                }
                if (key.equals("Email")) {
                    u.setEmailAddress(value);
                }
                if (key.equals("Student")) {
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
                b.setOnAction(event -> {
                    try {
                        handleDeleteAction(t.getid(),u.getEmailAddress(),t.getCourse());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                t.setButton(b);
            }

            list.add(t);
        }


        return list;
    }



}

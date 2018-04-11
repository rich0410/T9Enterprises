package UI;

import Domain.Controller;
import Domain.TimeSlot;
import Domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;
/**
 * This class is used to load teacher time table from the csv file
 * @author Prabdeep Singh Pannu, Bin
 * */
public class TeacherTimeTable implements Initializable {

    @FXML
    private TableView<TimeSlot> tableView;
    @FXML
    private TableColumn<TimeSlot, String> DayOfTheWeek;

    @FXML
    private TableColumn<TimeSlot, String> CourseCodeID;

    @FXML
    private TableColumn<TimeSlot, String> Duration;
    @FXML
    private TableColumn<TimeSlot, String> StartTime;
    @FXML
    private TableColumn<TimeSlot, String> RoomNumber;

    @FXML
    private BorderPane root;

    private Controller c;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CourseCodeID.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Course"));
        Duration.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Duration"));
        DayOfTheWeek.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Day"));
        StartTime.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Time"));
        RoomNumber.setCellValueFactory(new PropertyValueFactory<TimeSlot, String>("Room_number"));
        TableColumn<TimeSlot, Boolean> options = new TableColumn<>("Options");
        options.setCellValueFactory(new PropertyValueFactory<>("Button"));

        tableView.getColumns().add(options);

        tableView.setItems(this.getdata());              //this is causing a null pointer.

    }

    public ObservableList<TimeSlot> getdata() {

        Controller c = Controller.getController();
        User u = RegistrationFormController.getUser();
        System.out.println(u.getUserID());
        ObservableList<TimeSlot> list = FXCollections.observableArrayList();
        ArrayList<HashMap<String, String>> teacherslot = c.getAllTeacherMeetings();
        ArrayList<HashMap<String, String>> OfficeSlot = c.getTeacherOfficeTimesOpen();

        for (int i = 0; i < teacherslot.size(); i++) {
            Iterator<String> myVeryOwnIterator = teacherslot.get(i).keySet().iterator();
            TimeSlot t_slot = new TimeSlot();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = teacherslot.get(i).get(key);

                if (key.equals("ID")) {
                    t_slot.setId(value);
                }
                if (key.equals("Duration")) {
                    t_slot.setDuration(Integer.parseInt(value));
                }
                if (key.equals("Course")) {
                    t_slot.setCourse(value);
                }
                if (key.equals("Day")) {
                    t_slot.setDay(value);
                }
                if (key.equals("Time")) {
                    t_slot.setTime(value);
                }
                if (key.equals("Room")) {
                    t_slot.setRoom_number(value);
                }

                Button b = new Button("Remove");

                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent ae) {
                        try {
                            handleDeleteTimeAction(t_slot.getid());
                            System.out.println("working");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });


                t_slot.setButton(b);
            }

            list.add(t_slot);
        }


        for (int i = 0; i < OfficeSlot.size(); i++) {
            Iterator<String> myVeryOwnIterator = OfficeSlot.get(i).keySet().iterator();
            TimeSlot t_slot = new TimeSlot();
            while (myVeryOwnIterator.hasNext()) {
                String key = (String) myVeryOwnIterator.next();
                String value = OfficeSlot.get(i).get(key);

                if (key.equals("ID")) {
                    t_slot.setCourse("Office");
                    t_slot.setId(value);
                }
                if (key.equals("Day")) {
                    t_slot.setDay(value);
                }
                if (key.equals("Time")) {
                    t_slot.setTime(value);
                }
                if (key.equals("Room")) {
                    t_slot.setRoom_number(value);
                }
                t_slot.setDuration(1);

                Button b = new Button("Remove");
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent ae) {
                        try {
                            handleDeleteAction(t_slot.getid());
                            System.out.println("working");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                t_slot.setButton(b);
            }

            list.add(t_slot);
        }

        return list;
    }

    protected void handleDeleteAction(String id) throws IOException {

        Alert alert = AlertHelper.showAlert(Alert.AlertType.WARNING, null, "Warning", "Do you want to remove this time Slot ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES) {
            Controller c = Controller.getController();
            HashMap<String, String> office = new HashMap<String, String>();
            office.put("ID", id);
            c.ReveOfficeHour(office);
            refresh();

        } else {
            alert.close();
        }

    }


    protected void handleDeleteTimeAction(String id) throws IOException {
        Alert alert = AlertHelper.showAlert(Alert.AlertType.WARNING, null, "Warning", "Do you want to remove this time Slot ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Controller c = Controller.getController();
            HashMap<String, String> office = new HashMap<String, String>();
            office.put("ID", id);
            c.ResetClassHour(office);
            refresh();
        } else {
            alert.close();
        }

    }

    public void refresh() {
        BorderPane Content4 = null;
        try {
            Content4 = FXMLLoader.load(getClass().getResource(("../Layout/teacherTimeTable.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Welcome.fragementP.getChildren().setAll(Content4);
    }
}

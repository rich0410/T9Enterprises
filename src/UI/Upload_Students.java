package UI;

import DataAccess.ScheduleReader;
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
import Domain.User;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import static java.util.logging.Level.WARNING;


public class Upload_Students implements Initializable {


    @FXML
    private TableView tableView;

    @FXML
    private TableColumn StudentID;
    @FXML
    private TableColumn FirstName;
    @FXML
    private TableColumn LastName;
    @FXML
    private TableColumn EmailAddress;
    @FXML
    private TableColumn Options;

    @FXML
    private TextField fileinput;


    private ScheduleReader dG;

    private mainControler m = new mainControler();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        StudentID.setCellValueFactory(new PropertyValueFactory<User, String>("ID"));
        FirstName.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
        EmailAddress.setCellValueFactory(new PropertyValueFactory<User, String>("EmailAddress"));
        Options.setCellValueFactory(new PropertyValueFactory<User, String>("Button"));

        Controller c = Controller.getController();
        tableView.setItems(getAllStudentsItt(c.getAllStudents()));

    }

    @FXML
    protected void handleRemoveAction(ActionEvent event) {


            Alert alert = AlertHelper.showAlert(Alert.AlertType.WARNING, null, "Warning", "Do you want to remove all students!");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.YES) {
                Controller c = Controller.getController();
                c.removeallstudents();
                refresh();
            } else {
                alert.close();
            }



        }

        @FXML
        protected void handleChooseFile(ActionEvent event){
            m.FileChooser();
            this.fileinput.setText(m.get_name());
        }

        @FXML
        protected void handleUpdateFile (ActionEvent event){
            try {
                Controller c = Controller.getController();
                dG = new ScheduleReader();
                ArrayList<HashMap<String, String>> data = dG.readFile_Users(m.get_file());
                c.UpdateStudents(data);
                refresh();

            } catch (SQLException e) {
                e.printStackTrace();
                AlertHelper.showAlert_noButton(Alert.AlertType.CONFIRMATION, null, "Error",
                        "No duplicate data can be inserted!");


            } catch (Exception e) {
                e.printStackTrace();
              AlertHelper.showAlert_noButton(Alert.AlertType.CONFIRMATION, null, "Error",
                        "No data Available!");

            }


        }


        private ObservableList<User> getAllStudentsItt (ArrayList < HashMap < String, String >> data){

            ObservableList<User> list = FXCollections.observableArrayList();

            ArrayList<HashMap<String, String>> teachers = data;


            for (int i = 0; i < teachers.size(); i++) {

                Iterator<String> myVeryOwnIterator = teachers.get(i).keySet().iterator();
                User user = new User();
                while (myVeryOwnIterator.hasNext()) {
                    String key = (String) myVeryOwnIterator.next();
                    String value = teachers.get(i).get(key);
                    if (key.equals("ID")) {
                        user.setID(value);
                    }
                    if (key.equals("FIRSTNAME")) {
                        user.setFirstName(value);
                    }
                    if (key.equals("LASTNAME")) {
                        user.setLastName(value);
                    }
                    if (key.equals("EMAILADDRESS")) {
                        user.setEmailAddress(value);

                    }
                    Button b = new Button("Remove");
                    b.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent ae) {
                            Alert alert = AlertHelper.showAlert(Alert.AlertType.WARNING, null, "Warning", "Do you want to remove " + user.getID() + " user");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.YES) {
                                Controller c = Controller.getController();
                                c.removeStudent(user.getID());
                                refresh();
                            } else {
                                alert.close();
                            }


                        }
                    });
                    user.setButton(b);

                }
                list.add(user);

            }
            return list;
        }


    private void refresh() {

        try {
            BorderPane Content3 = FXMLLoader.load(getClass().getResource(("../Layout/Get_all_Students.fxml")));
            Welcome.fragementP.getChildren().setAll(Content3);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

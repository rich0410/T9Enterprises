package UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

import DataAccess.Database;
import Domain.Controller;
import Domain.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Domain.User;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * This is the controller which is used to get all the professors and set them in the table
 * @team T9-Enterprise
 * @author Prabdeep Singh Pannu
 * */

public class ProfessorController implements Initializable {


    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> FirstName;
    @FXML
    private TableColumn<User, String> LastName;
    @FXML
    private TableColumn<User, String> Email;

    @FXML
    private BorderPane root;
    private static User user;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        Email.setCellValueFactory(new PropertyValueFactory<User, String>("emailAddress"));

        Controller c = Controller.getController();

        tableView.setItems(this.getAllTeachersItt(c.getAllTeachers()));              //this is causing a null pointer.
        tableView.setOnMouseClicked(event -> {
            clickItem(event);
        });


    }



    public void clickItem(MouseEvent event) {
        if (event.getClickCount() == 1) //Checking double click
        {
            setUser(tableView.getSelectionModel().getSelectedItem());

        }
    }

    public static void setUser(User u) {
        ProfessorController.user = u;
    }

    public static User getUser() {
        return user;
    }

    @FXML
    protected void handleUpdateAction(ActionEvent event) {
        BookAnAppointment.professorP.setText(ProfessorController.getUser().getFirstName() + " "+ProfessorController.getUser().getLastName());
        Stage stage = (Stage) tableView.getScene().getWindow();
        stage.close();
    }

    private ObservableList<User> getAllTeachersItt(ArrayList<HashMap<String, String>> data) {

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


            }
            list.add(user);

        }


        return list;

    }

}
    

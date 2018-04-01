package UI;

import java.net.URL;
import java.util.ResourceBundle;

import DataAccess.Database;
import Domain.Teacher;
import javafx.event.ActionEvent;
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

        Database db = new Database();

        tableView.setItems(db.parseUserList());              //this is causing a null pointer.
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

}
    

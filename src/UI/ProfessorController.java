package UI;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataAccess.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import Domain.User;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;



public class ProfessorController implements Initializable {
    
	
	@FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> TeacherID;
    @FXML private TableColumn<User, String> FirstName;
    @FXML private TableColumn<User, String> LastName;
    @FXML private TableColumn<User, String> Email;
    @FXML private BorderPane root;

   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TeacherID.setCellValueFactory(new PropertyValueFactory<User, String>("teacherid"));
        FirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        Email.setCellValueFactory(new PropertyValueFactory<User, String>("emailAddress"));

        Database db = new Database();

       //tableView.setItems(db.parseUserList());              //this is causing a null pointer.
        
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) throws IOException {
        AnchorPane Content = FXMLLoader.load(getClass().getResource(("../Layout/Welcome.fxml")));
        root.getChildren().setAll(Content);

    }



    }
    

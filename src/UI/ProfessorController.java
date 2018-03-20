package UI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DataAccess.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Domain.User;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ProfessorController implements Initializable {
    
	
	@FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> TeacherID;
    @FXML private TableColumn<User, String> FirstName;
    @FXML private TableColumn<User, String> LastName;
    @FXML private TableColumn<User, String> Email;
    @FXML private Button Back;

   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TeacherID.setCellValueFactory(new PropertyValueFactory<User, String>("TeacherID"));
        FirstName.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
        Email.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));

       //tableView.setItems(this.parseUserList());              //this is causing a null pointer.
        
    }
    private ObservableList<User> parseUserList(){
    	Database db = new Database();
        ObservableList<User> list = FXCollections.observableArrayList();
    	User u = new User();
        try {
			while(!db.getallTeachers().next()){
             				
				u.setFirstName(db.getallTeachers().getString("FirstName"));
				u.setLastName(db.getallTeachers().getString("LastName"));
				u.setEmail(db.getallTeachers().getString("EmailAddress"));
				
				
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
        
        return list;
    }



    
}
    

package UI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Domain.User;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ProfessorController implements Initializable {
    
	@FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> TeacherID;
    @FXML private TableColumn<User, String> FirstName;
    @FXML private TableColumn<User, String> LastName;
    @FXML private TableColumn<User, String> Email;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TeacherID.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        FirstName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        LastName.setCellValueFactory(new PropertyValueFactory<User, String>("active"));
        Email.setCellValueFactory(new PropertyValueFactory<User, String>("active"));

        tableView.getItems().setAll(parseUserList());
    }
    private List<User> parseUserList(){
    	
        List<User> l = new ArrayList<User>();
        User u = new User("prab");     //just a test weather it is loading data or not..
        l.add(u);
    	return l;
    }
    
}
    

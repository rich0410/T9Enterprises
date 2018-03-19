package UI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ToolbarController {
    
    @FXML
    private Button professorBtn;
  
    @FXML
    protected void professorButtonAction(ActionEvent event) {
       try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("../Layout/professor.fxml"));

			Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
			Stage stage = new Stage();
			stage.setTitle("New Window");
			stage.setScene(scene);
			stage.show();
			((Node) (event.getSource())).getScene().getWindow().hide();
       } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
       }
    }
    
}
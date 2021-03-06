package UI;

import com.jfoenix.controls.JFXDecorator;
import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.application.Application.launch;
/**
 * This is the main controller class on which all the layouts are set up
 * This works as a parent layout
 * @author Prabdeep Singh Pannu, Dii
 * */

public class mainControler implements Initializable {

    @FXML
    private StackPane root;

    public static StackPane rootp;

    public File file;

    public String name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootp = root;
        RegistrationFormController.isSplashLoaded = false;
        if (!RegistrationFormController.isSplashLoaded) {
            loadSplashScreen();
        }
    }

    /**
     * This includes the s[lash screen of the project
     * */
    private void loadSplashScreen() {
        try {

            RegistrationFormController.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource("../Layout/SplashScreen.fxml"));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    GridPane parentContent = FXMLLoader.load(getClass().getResource(("../Layout/registration_form.fxml")));
                    root.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    System.err.print(ex.getMessage());
                }
            });

        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public void LogOut(Event event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("../Layout/main.fxml"));
            Stage stage = new Stage();
            JFXDecorator decorator = new JFXDecorator(stage, root);
            decorator.setCustomMaximize(true);
            Scene scene = new Scene(decorator, 800, 600);
            scene.getStylesheets().add(getClass().getResource("../Layout/demo.css").toExternalForm());
            stage.setTitle("by T9-Enterprises");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }


    public void FileChooser() {
        try {
            Stage stage = new Stage();
            stage.setTitle("File Chooser");
            FileChooser fc = new FileChooser();
            fc.setTitle("Open CSV File");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Quiz Files", "*.csv"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fc.showOpenDialog(stage);
            set_name(file.getName());
            set_file(file);
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, null, "Error",
                    "No data Available!");
        }
    }

    private void set_file(File file) {
        this.file = file;
    }

    public File get_file() {
        return file;
    }

    private void set_name(String name) {
        this.name = name;
    }

    public String get_name() {
        return name;
    }


//    public static void getkeyboard(MouseEvent e) {
//        e.launch("osk.exe");
//
//    }
}

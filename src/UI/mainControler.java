package UI;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainControler implements Initializable {

    @FXML
    private StackPane root;

    public  static StackPane rootP;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (!RegistrationFormController.isSplashLoaded) {
            loadSplashScreen();
        }


        rootP = root;
    }

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

}

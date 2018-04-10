package UI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

import java.awt.event.ActionEvent;
import java.util.Optional;

public class AlertHelper {

    public static Alert showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType, "", ButtonType.YES, ButtonType.NO);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);

        return alert;
    }

    public static Alert showAlert_noButton(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType, "");
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
        return alert;
    }


}

package UI;

import Domain.Controller;
import Domain.User;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Welcome implements Initializable {


    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;



    public  static AnchorPane rootP;

    @FXML
    private AnchorPane fragment;


    public static AnchorPane fragementP;


    @FXML
    private Label lebel;

    public static Label lebelP;

    public static JFXDrawer drawerP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lebelP = lebel;
        rootP = root;
        fragementP = fragment;
        drawerP = drawer;
        Controller conn = Controller.getController();
        User u = conn.getUser();
            try {
                if (u.getRole() == 1) {
                    System.out.println("admin");
                } else if (u.getRole() == 2) {
                    VBox box = FXMLLoader.load(getClass().getResource("../Layout/Slide_panel_for_teacher.fxml"));
                    drawer.setSidePane(box);

                } else if (u.getRole() == 3) {
                    VBox box = FXMLLoader.load(getClass().getResource("../Layout/SidePanelContent.fxml"));
                    drawer.setSidePane(box);
                }


            } catch (IOException ex) {
                Logger.getLogger(mainControler.class.getName()).log(Level.SEVERE, null, ex);
            }

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);


            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        }



}
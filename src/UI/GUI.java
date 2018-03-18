package UI;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class helps to manage the UI part for the application
 * This extends Application class
 * some methods of the extended class is modified
 * */
public class GUI extends Application {




    @Override
    public void init()
    {
        System.out.printf("init() called on thread %s%n",
                Thread.currentThread());
    }


    /**
     * This method gets the layout and start with the login page
     * */
    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root= FXMLLoader.load(getClass().getResource("../Layout/main.fxml"));
        primaryStage.setTitle("by T9-Enterprises");
//        primaryStage.setScene(new Scene(root, 800, 500));
//        primaryStage.show();
        Scene scene = new Scene(root, 1200, 600);
        scene.getStylesheets().add(getClass().getResource("../Layout/demo.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop()
    {
        System.out.printf("stop() called on thread %s%n",
                Thread.currentThread());
    }




    public static void main(String[] args) {
        launch(args);
    }
}

package UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * This the controller class which is resposible to get the room finder on the front end
 * Web-view and web-engine java components are used to get the http page
 * @author Prabdeep Singh Pannu, Bin
 * */

public class Roomfinder implements Initializable {


    @FXML
    private StackPane stackPane;

    private static StackPane  s;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        s = stackPane;
        WebPage currentPage = new WebPage();
        WebView webView = currentPage.getWebView();
        WebEngine webEngine = currentPage.createWebEngine();
        webEngine.load("http://lyceum.algonquincollege.com/roomfinder/");
        BorderPane b = new BorderPane();
        b.setCenter(webView);
        s.getChildren().add(b);
    }


    public static void getRoomFinder() {
        WebPage currentPage = new WebPage();
        WebView webView = currentPage.getWebView();
        WebEngine webEngine = currentPage.createWebEngine();
        webEngine.load("http://lyceum.algonquincollege.com/roomfinder/");
        BorderPane b = new BorderPane();
        b.setCenter(webView);
        Stage s = new Stage();
        Scene scene = new Scene(b, 800, 500);
        s.setScene(scene);
        s.show();
    }


    static class WebPage {
        private WebView webview = new WebView();
        private static WebEngine e;

        public WebEngine createWebEngine() {
            WebView wv = getWebView();
            e = wv.getEngine();
            return e;
        }

        public WebView getWebView() {
            return webview;
        }

    }

}

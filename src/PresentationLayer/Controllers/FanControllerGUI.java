package PresentationLayer.Controllers;

import PresentationLayer.ScreenController;
import ServiceLayer.FootballAssosiationController;
import System.Users.Fan;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

//import java.awt.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;


import java.util.List;

public class FanControllerGUI {
    @FXML
    TextFlow textAlert;

    @FXML
    Button logOutBtn;

    @FXML
    public void initialize() {
        showAlert();
    }
    @FXML
    public void showAlert(){
        List<String> alerts= ScreenController.getInstance().getAlertsList();
        if(alerts!=null) {
            String text="";
            for (String a : alerts) {
               text= text+ a+'\n';
            }
            Text t = new Text(text);
            textAlert.getChildren().add(t);
        }
    }

    @FXML
    public void mouseInL(){
        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #a60000 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOutL(){
        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #A73A33 ; -fx-text-fill :  white ");
    }

    @FXML
    public void handleLogOut() throws Exception {
        ScreenController.getInstance().changeSenceLogOut();
    }
}

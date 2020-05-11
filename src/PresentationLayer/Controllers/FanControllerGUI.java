package PresentationLayer.Controllers;

import PresentationLayer.ScreenController;
import ServiceLayer.FootballAssosiationController;
import System.Users.Fan;
import javafx.fxml.FXML;

//import java.awt.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class FanControllerGUI {
    @FXML
    TextArea textAlert;


    @FXML
    public void initialize() {
        showAlert();
    }
    @FXML
    public void showAlert(){
        System.out.println("blabla");
        List<String> alerts= ScreenController.getInstance().getAlertsList();
        for (String a: alerts){
            textAlert.setText(a);
        }

    }
}

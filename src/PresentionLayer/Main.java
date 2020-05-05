package PresentionLayer;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;


public class Main extends Application {


    @FXML
    public void handleLogin(ActionEvent event){
        System.out.println("abc");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Football System");
        primaryStage.setScene(new Scene(root, 1440, 844));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

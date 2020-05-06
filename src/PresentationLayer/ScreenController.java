package PresentationLayer;

import System.Users.Referee;
import System.Users.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenController {

    Stage primaryStage;
    public String userName;

    private static ScreenController ourInstance = new ScreenController();

    public static ScreenController getInstance() {
        return ourInstance;
    }

    private ScreenController() {
    }

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;


    }

    public void changeSceneReferee(String referee) throws Exception{
        userName = referee;
        Parent root = FXMLLoader.load(getClass().getResource("MainRefereeMenu.fxml"));
        primaryStage.setScene(new Scene(root, 1440, 844) );
        primaryStage.show();
    }

    public void changeSenceLogOut() throws Exception{
        userName = "";
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root, 1440, 844) );
        primaryStage.show();
    }
}

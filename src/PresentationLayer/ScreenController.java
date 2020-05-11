package PresentationLayer;

import PresentationLayer.Controllers.RefereeControllerGui;
import ServiceLayer.RefereeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class ScreenController {

    Stage primaryStage;

    String gameInfo;

    public String userName;

    RefereeControllerGui refereeControllerGui;

    private static ScreenController ourInstance = new ScreenController();

    public static ScreenController getInstance() {
        return ourInstance;
    }

    private ScreenController() {
    }

    public RefereeControllerGui getRefereeControllerGui() {
        return refereeControllerGui;
    }

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;


    }

    public void changeSceneReferee(String referee) throws Exception{
        userName = referee;
        Parent root = FXMLLoader.load(getClass().getResource("MainRefereeMenu.fxml"));
        primaryStage.setScene(new Scene(root, 1440, 895) );
        primaryStage.show();
    }

    public void changeSenceLogOut() throws Exception{
        userName = "";
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root, 1440, 844) );
        primaryStage.show();
    }
    public void changeSceneFootballAssociation(String footballAssociation) throws Exception{
        userName = footballAssociation;
        Parent root = FXMLLoader.load(getClass().getResource("MainFootballAssociationMenu.fxml"));
        primaryStage.setScene(new Scene(root, 1440, 895) );
        primaryStage.show();
    }

    public void saveGameInfo(String homeTeam , String awayTeam , String gameID , RefereeControllerGui refereeControllerGui){
        gameInfo = gameID+","+homeTeam+","+awayTeam;
        this.refereeControllerGui = refereeControllerGui;
    }

    public String getGameIndfo(){
        return gameInfo;
    }

}

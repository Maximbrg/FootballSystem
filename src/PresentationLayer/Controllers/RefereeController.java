package PresentationLayer.Controllers;

import PresentationLayer.ScreenController;
import System.FootballObjects.Game;
import System.Users.Referee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.Date;


public class RefereeController {

    @FXML
    TableColumn <Game,String> team1;

    @FXML
    TableColumn <Game,String> team2;

    @FXML
    TableColumn <Game,String> score;

    @FXML
    TableColumn <Game, Date> date;

    @FXML
    TableView <Game> tableInfo;

    @FXML
    Button homeBtn;

    @FXML
    Button gamesBtn;

    @FXML
    Button stadiumsBtn;

    @FXML
    Button teamsBtn;

    @FXML
    Button playersBtn;

    @FXML
    Button refereeBtn;

    @FXML
    Text userInfo;

    @FXML
    Button logOutBtn;


//
//    @FXML
//    public void initialize() {
 //      eventComboBox.getItems().addAll("Red Card","Yellow Card","Offside","Goal","Injury","Offense");
 //  }

    private ObservableList<Game> games = FXCollections.observableArrayList();

    public void initialize() {
      //  games.add(((Referee)(ScreenController.getInstance().currentUser)).getGames().get(0));
     //   team1.setCellValueFactory(new PropertyValueFactory<>("Home1"));
      //  team2.setCellValueFactory(new PropertyValueFactory<>("Away"));
     //   score.setCellValueFactory(new PropertyValueFactory<>("Score"));
     //   date.setCellValueFactory(new PropertyValueFactory<>("Date"));
     //   tableInfo.setItems(games);
        userInfo.setText(ScreenController.getInstance().userName);
    }

    @FXML
    public void mouseInH(){
        homeBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOutH(){
        homeBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
    }

    @FXML
    public void mouseInG(){
        gamesBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOutG(){
        gamesBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
    }

    @FXML
    public void mouseInT(){
        teamsBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOutT(){
        teamsBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
    }

    @FXML
    public void mouseInP(){
        playersBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOutP(){
        playersBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
    }

    @FXML
    public void mouseInR(){
        refereeBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOutR(){
        refereeBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
    }

    @FXML
    public void mouseInS(){
        stadiumsBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOutS(){
        stadiumsBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
    }

    @FXML
    public void mouseInL(){
        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #A73A33 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOutL(){
        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
    }

    @FXML
    public void handleLogOut() throws Exception {
        ScreenController.getInstance().changeSenceLogOut();
    }

    }



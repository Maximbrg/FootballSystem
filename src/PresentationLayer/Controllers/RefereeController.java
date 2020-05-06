package PresentationLayer.Controllers;

import PresentationLayer.ScreenController;
import System.FootballObjects.Game;
import System.Users.Referee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
//
//    @FXML
//    public void initialize() {
 //      eventComboBox.getItems().addAll("Red Card","Yellow Card","Offside","Goal","Injury","Offense");
 //  }

    private ObservableList<Game> games = FXCollections.observableArrayList();

    public void initialize() {
        games.add(((Referee)(ScreenController.getInstance().currentUser)).getGames().get(0));
        team1.setCellValueFactory(new PropertyValueFactory<>("Home1"));
        team2.setCellValueFactory(new PropertyValueFactory<>("Away"));
     //   score.setCellValueFactory(new PropertyValueFactory<>("Score"));
     //   date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tableInfo.setItems(games);
    }

    }



package PresentationLayer.Controllers;

import PresentationLayer.ScreenController;
//import System.FootballObjects.Game;
import ServiceLayer.FootballAssosiationController;
import ServiceLayer.SystemManagerController;
import System.FootballObjects.League;
import System.FootballObjects.LeagueInformation;
import System.Users.Referee;
import System.Users.SystemManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Date;


public class FootballAssociationController {


    @FXML
    Pane footballAssociationMenuPane;
    @FXML
    Button currentButton;

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
    Button createTeamsBtn;

    @FXML
    Text userInfo;

    @FXML
    Button logOutBtn;
    @FXML
    Button postEvent;
//////////////////league policy\\\\\\\\\\\\\\\\\\\\\\
    @FXML
    ComboBox<String> leagueComboBox;
    @FXML
    ComboBox<String> seasonComboBox;
    @FXML
    ComboBox<String> scoreComboBox;
    @FXML
    ComboBox<String> schedulingPolicyComboBox;
    @FXML
    Button doneButton;
    @FXML
    Button cancelButton;
    @FXML
    Pane policyLeaguePane;
    @FXML
    Label schedulingPolicyValidate;
    @FXML
    Label scoreValidate;
    @FXML
    Label leagueValidate;
    @FXML
    Label seasonValidate;


//////////////////register team\\\\\\\\\\\\\\\\\\\\\\
    @FXML
    Pane teamRegistrationPane;
    @FXML
    ComboBox<String> teamOwnerComboBox;
    @FXML
    TextField teamNameTextField;
    @FXML
    Label teamValidate;
    @FXML
    Label teamOwnerValidate;
    @FXML
    Button cancelButtonRegister;
    @FXML
    Button registerTeam;

    public void initialize() {
        for(String l: FootballAssosiationController.getInstance().getAllLeagueString()){
            leagueComboBox.getItems().add(l);
        }
        //score policy
        for(String s :FootballAssosiationController.getInstance().getScorePolicyString() ){
            scoreComboBox.getItems().add(s);
        }
        //scheduling policy
        for(String s :FootballAssosiationController.getInstance().getAllocatePolicyString() ){
            schedulingPolicyComboBox.getItems().add(s);
        }
        for(String s : FootballAssosiationController.getInstance().getAllTeamOwnerString()){
            teamOwnerComboBox.getItems().add(s);
        }

    }
    @FXML
    private void leagueComboBoxOnAction(ActionEvent event) {
        String chosenLeague=leagueComboBox.getValue();
        for(String l: FootballAssosiationController.getInstance().getAllLeagueString()){
            if(chosenLeague.equals(l)){
                for(String li:FootballAssosiationController.getInstance().getLeagueInformationString(l)){
                    seasonComboBox.getItems().add(li);
                }
                break;
            }
        }
    }
    @FXML
    private void cancelButtonOnAction(ActionEvent a){
        policyLeaguePane.setVisible(false);
        leagueValidate.setVisible(false);
        seasonValidate.setVisible(false);
        scoreValidate.setVisible(false);
        schedulingPolicyValidate.setVisible(false);

    }
    @FXML
    private void teamRegistrationCancelButton(ActionEvent a){
        teamRegistrationPane.setVisible(false);
        teamValidate.setVisible(false);
        teamOwnerValidate.setVisible(false);
        footballAssociationMenuPane.setVisible(true);

    }
    @FXML
    private void registerButton(ActionEvent a){
        registerTeam();
    }
    @FXML
    private void setPolicyButton(){
        restoreValidate();
        teamRegistrationPane.setVisible(false);
        footballAssociationMenuPane.setVisible(true);
        policyLeaguePane.setVisible(true);
    }

    private void restoreValidate() {
        teamValidate.setVisible(false);
        schedulingPolicyValidate.setVisible(false);
        seasonValidate.setVisible(false);
        leagueValidate.setVisible(false);
        scoreValidate.setVisible(false);
        teamOwnerValidate.setVisible(false);
    }

    private void registerTeam() {
        boolean confirm = true;
        teamOwnerValidate.setVisible(false);
        teamValidate.setVisible(false);
        if(teamOwnerComboBox.getValue()==null){
            confirm=false;
            teamOwnerValidate.setVisible(true);
        }
        if(teamNameTextField.getText().equals("")){
            confirm=false;
            teamValidate.setVisible(true);
        }
        if(confirm) {
            FootballAssosiationController.getInstance().createTeam(teamNameTextField.getText(), teamOwnerComboBox.getValue());
            teamRegistrationPane.setVisible(false);
        }

    }
    @FXML
    public void createTeamButton(){
        restoreValidate();
        policyLeaguePane.setVisible(false);
        footballAssociationMenuPane.setVisible(true);
        teamRegistrationPane.setVisible(true);
    }
    @FXML
    public void setLeaguePolicy(){
        policyLeaguePane.setVisible(true);
        footballAssociationMenuPane.setVisible(true);
        teamRegistrationPane.setVisible(false);
    }

    @FXML
    private void doneButtonOnAction(ActionEvent a){
        leagueValidate.setVisible(false);
        seasonValidate.setVisible(false);
        scoreValidate.setVisible(false);
        schedulingPolicyValidate.setVisible(false);
        boolean confirm=true;
        String s= leagueComboBox.getValue();
        if(leagueComboBox.getValue()==null){
            confirm=false;
            leagueValidate.setVisible(true);
        }
        if(seasonComboBox.getValue()==null){
            confirm=false;
            seasonValidate.setVisible(true);
        }
        if(scoreComboBox.getValue()==null){
            confirm=false;
            scoreValidate.setVisible(true);
        }
        if(schedulingPolicyComboBox.getValue()==null){
            confirm=false;
            schedulingPolicyValidate.setVisible(true);
        }
        if(confirm){
            FootballAssosiationController.getInstance().editLeaguePolicy(leagueComboBox.getValue(),seasonComboBox.getValue(),scoreComboBox.getValue(),schedulingPolicyComboBox.getValue());
            policyLeaguePane.setVisible(false);
        }


    }
    @FXML
    public void mouseIn(Event e){
        Button btn = (Button) e.getSource();
        btn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");

    }
    @FXML
    public void mouseOut(Event e){
        Button btn = (Button) e.getSource();
        btn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
    }
//    @FXML
//    public void mouseIn(Button b){
//
//    }
//
//    @FXML
//    public void mouseInH(){
//        homeBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
//    }
//
//    @FXML
//    public void mouseOutH(){
//        homeBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
//    }
//
//    @FXML
//    public void mouseInG(){
//        gamesBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
//    }
//
//    @FXML
//    public void mouseOutG(){
//        gamesBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
//    }
//
//    @FXML
//    public void mouseInT(){
//        teamsBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
//    }
//
//    @FXML
//    public void mouseOutT(){
//        teamsBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
//    }
//
//    @FXML
//    public void mouseInP(){
//        playersBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
//    }
//
//    @FXML
//    public void mouseOutP(){
//        playersBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
//    }
//
//    @FXML
//    public void mouseInR(){
//        refereeBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
//    }
//
//    @FXML
//    public void mouseOutR(){
//        refereeBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
//    }
//
//    @FXML
//    public void mouseInS(){
//        stadiumsBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
//    }
//
//    @FXML
//    public void mouseOutS(){
//        stadiumsBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
//    }
//    @FXML
//    public void mouseInCT(){
//        createTeamsBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
//    }
//    @FXML
//    public void mouseOutCT(){
//        createTeamsBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
//    }
//
    @FXML
    public void mouseInL(){
        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  #A73A33 ; -fx-text-fill : white ");
    }
//
    @FXML
    public void mouseOutL(){
        logOutBtn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
    }
//
    @FXML
    public void handleLogOut() throws Exception {
        ScreenController.getInstance().changeSenceLogOut();
    }
//    @FXML
//    public void handleMouseClickedPass() {
//        postEvent.setStyle("-fx-background-color: #000F64 ; -fx-background-radius:10; -fx-text-fill: white");
//    }
//
//    @FXML
//    public void onHover() {
//        postEvent.setStyle("-fx-background-color: #4179F0 ; -fx-background-radius:10; -fx-text-fill: white");
//    }
//
//    @FXML
//    public void OnExit() {
//        postEvent.setStyle("-fx-background-color: #2060E4 ; -fx-background-radius:10; -fx-text-fill: white");
//    }

    }



package PresentationLayer.Controllers;

import ServiceLayer.*;
import PresentationLayer.ScreenController;
import System.Exeptions.NoRefereePermissions;
import System.Exeptions.NoSuchEventException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;
import java.util.HashMap;
import java.util.List;


public class RefereeControllerGui {



    @FXML
    Button homeBtn;

    Pane currPane;

    @FXML
    Text userInfo;

    @FXML
    Button logOutBtn;

    @FXML
    Text time1;

    @FXML
    Text date1;

    @FXML
    Text teamHomeName1;

    @FXML
    Text teamAwayName1;

    @FXML
    Text time2;

    @FXML
    Text date2;

    @FXML
    Text teamHomeName2;

    @FXML
    Text teamAwayName2;

    @FXML
    Text time3;

    @FXML
    Text date3;

    @FXML
    Text teamHomeName3;

    @FXML
    Text teamAwayName3;

    @FXML
    Text time4;

    @FXML
    Text date4;

    @FXML
    Text teamHomeName4;

    @FXML
    Text teamAwayName4;

    @FXML
    Text time5;

    @FXML
    Text date5;

    @FXML
    Text teamHomeName5;

    @FXML
    Text teamAwayName5;

    @FXML
    Pane info1;

    @FXML
    Pane info2;

    @FXML
    Pane info3;

    @FXML
    Pane info4;

    @FXML
    Pane info5;

    @FXML
    Text teamNameAway;

    @FXML
    Text teamNameHome;

    @FXML
    Pane rightPane;

    @FXML
    ComboBox <String> comboEventBox;

    @FXML
    ComboBox <String> teamChoice;

    @FXML
    HashMap<Pane,String> gameInfo;

    @FXML
    Text score;

    @FXML
    Pane liveInfo;

    @FXML
    Button postEvent;

    @FXML
    TextField playerNameText;

    @FXML
    TextField timeEvent;


    @FXML
    public void initialize() {
        gameInfo = new HashMap<>();
        comboEventBox.getItems().addAll("Red Card","Yellow Card","Offside","Goal","Injury","Offense");

        userInfo.setText(ScreenController.getInstance().userName);
        List<String> myGames = RefereeController.getInstance().getMyGames(ScreenController.getInstance().userName);
        if(myGames.size()>0) {
            String str = myGames.get(0);
            String[] arrayInfo = str.split(",");
            changeText(info1,teamHomeName1,teamAwayName1,time1,date1,arrayInfo);
            gameInfo.put(info1,arrayInfo[4]);
        }
        if(myGames.size()>1) {
            info2.setVisible(true);
            String str = myGames.get(1);
            String[] arrayInfo = str.split(",");
            changeText(info2,teamHomeName2,teamAwayName2,time2,date2,arrayInfo);
            gameInfo.put(info2,arrayInfo[4]);
        }

        if(myGames.size()>2) {
            String str = myGames.get(2);
            String[] arrayInfo = str.split(",");
            changeText(info3,teamHomeName3,teamAwayName3,time3,date3,arrayInfo);
            gameInfo.put(info3,arrayInfo[4]);
        }

        if(myGames.size()>3) {
            String str = myGames.get(3);
            String[] arrayInfo = str.split(",");
            changeText(info4,teamHomeName4,teamAwayName4,time4,date4,arrayInfo);
            gameInfo.put(info4,arrayInfo[4]);
        }

        if(myGames.size()>4) {
            String str = myGames.get(4);
            String[] arrayInfo = str.split(",");
            changeText(info5,teamHomeName5,teamAwayName5,time5,date5,arrayInfo);
            gameInfo.put(info5,arrayInfo[4]);
        }
    }

    private  void changeText(Pane info ,Text teamHomeName, Text teamAwayName, Text time, Text date, String[] arrayInfo){
        info.setVisible(true);
        teamHomeName.setText(arrayInfo[0]);
        teamAwayName.setText(arrayInfo[1]);
        time.setText(arrayInfo[2]);
        date.setText(arrayInfo[3]);
    }

    @FXML
    public void postEvent() {
        String str ="something goes wrong";
        try {
            String eventType = comboEventBox.getValue().replace(" ", "");
            String playerName = playerNameText.getText();
            if(!checkPlayerName(playerName)){
                str = "The player name should contain only letters";
                throw new Exception();
            }
            String time = timeEvent.getText();
            if(!(checkMin(time))) {
                str = "The min should be number between 1 to 120";
                throw new Exception();
            }
            int time2 = Integer.parseInt(time);

            String team = teamChoice.getValue();
            RefereeController.getInstance().addEventDuringGame(ScreenController.getInstance().userName, gameInfo.get(currPane), eventType, time2, playerName, team);
            if (eventType.equals("Goal")) {
                score.setText(RefereeController.getInstance().getScore(gameInfo.get(currPane), ScreenController.getInstance().userName));
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Event added successfully");
            alert.show();
        }
        catch (NoRefereePermissions refereePermissions){
            Alert alert = new Alert(Alert.AlertType.ERROR,"NoRefereePermissions");
            alert.show();
        }
        catch (NoSuchEventException refereePermissions){
            Alert alert = new Alert(Alert.AlertType.ERROR,"NoSuchEventException");
            alert.show();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR,str);
            alert.show();
        }
    }

    @FXML
    public void onEnteredPane(Event event){
        try {
            Pane info = ((Pane) event.getSource());
            if (info != currPane) {
                info.setStyle("-fx-background-radius:10 ; -fx-background-color: #2060E4");
                for (Node node : info.getChildren()) {
                    if (node instanceof Text) {
                        node.setStyle("-fx-fill:white");
                    }
                }
            }
        }
        catch (Exception e){

        }
    }

    @FXML
    public void onExitPane(Event event){
        try {
            Pane info = ((Pane) event.getSource());
            if (info != currPane) {
                info.setStyle("-fx-background-radius:10 ; -fx-background-color: #F6F6F4");
                for (Node node : info.getChildren()) {
                    if (node instanceof Text) {
                        node.setStyle("-fx-fill: #444444");
                    }
                }
            }
        }
        catch (Exception e){

        }
    }

    @FXML
    public void showRightMenu(Event event) {
        teamChoice.getItems().removeAll(teamChoice.getItems());
        if (currPane != null) {
            currPane.setStyle("-fx-background-radius:10 ; -fx-background-color: #F6F6F4");
            for (Node node : currPane.getChildren()) {
                if (node instanceof Text) {
                    if (node instanceof Text) {
                        node.setStyle("-fx-fill: #444444");
                    }
                }
            }
        }
            rightPane.setVisible(true);
            Pane info = ((Pane) event.getSource());
            currPane = info;
            int i = 0;
            info.setStyle("-fx-background-radius:10 ; -fx-background-color:#2060E4");
            for (Node node : info.getChildren()) {
                if (node instanceof Text) {
                    node.setStyle("-fx-fill:white");
                    if (i == 3) {
                        teamNameHome.setText(((Text) node).getText());
                    }
                    if (i == 4) {
                        teamNameAway.setText(((Text) node).getText());
                    }
                    i++;
                }
            }
            teamChoice.getItems().addAll(teamNameHome.getText(),teamNameAway.getText());

            String refereeName = ScreenController.getInstance().userName;
            String gameId = gameInfo.get(info);

            String result = RefereeController.getInstance().getScore(gameId,refereeName);
            score.setText(result);

            if(RefereeController.getInstance().isGameLive(gameId,refereeName)) {
                liveInfo.setVisible(true);
            }
            else{
                liveInfo.setVisible(false);
            }


        }

    @FXML
    public void mouseIn(Event event){
        Button btn = ((Button)event.getSource());
        event.getTarget();
        btn.setStyle("-fx-background-radius : 10;-fx-background-color :  #2060E4 ; -fx-text-fill : white ");
    }

    @FXML
    public void mouseOut(Event event){
        Button btn = ((Button)event.getSource());
        event.getTarget();
        btn.setStyle("-fx-background-radius : 10;-fx-background-color :  white ; -fx-text-fill :  #444444 ");
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

    @FXML
    public void handleMouseClickedPass() {
        postEvent.setStyle("-fx-background-color: #000F64 ; -fx-background-radius:10; -fx-text-fill: white");
    }

    @FXML
    public void onHover() {
        postEvent.setStyle("-fx-background-color: #4179F0 ; -fx-background-radius:10; -fx-text-fill: white");
    }

    @FXML
    public void OnExit() {
        postEvent.setStyle("-fx-background-color: #2060E4 ; -fx-background-radius:10; -fx-text-fill: white");
    }

    private boolean checkPlayerName(String str){
        char [] chars = str.toCharArray();
        for(char c : chars){
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')))
                return false;
        }
        return true;
    }

    private boolean checkMin(String str){
        char [] chars = str.toCharArray();
        if(chars.length > 3 || chars.length < 1)
            return false;
        for(char c : chars){
            if (!((c >= '0' && c <= '9')))
                return false;
        }
        if(Integer.parseInt(str) > 120 || Integer.parseInt(str) < 1){
            return false;
        }
        return true;
    }

    }





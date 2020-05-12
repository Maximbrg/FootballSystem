package PresentationLayer.Controllers;

import PresentationLayer.ScreenController;
import ServiceLayer.RefereeController;
import System.Exeptions.NoRefereePermissions;
import System.Exeptions.NoSuchEventException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class AddEventController {

    @FXML
    ComboBox<String> comboEventBox;

    @FXML
    TextField playerNameText;

    @FXML
    TextField timeEvent;

    @FXML
    Text teamNameHome;

    @FXML
    Text teamNameAway;

    @FXML
    RadioButton home;

    @FXML
    RadioButton away;



    String gameID;

    @FXML
    public void initialize() {

        comboEventBox.getItems().addAll("Red Card", "Yellow Card", "Offside", "Goal", "Injury", "Offense");
        String[] str = (ScreenController.getInstance().getGameIndfo()).split(",");
        teamNameHome.setText(str[0]);
        teamNameAway.setText(str[2]);
        gameID = str[1];
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
            String team = "";
            if(home.isSelected()){
                team = teamNameHome.getText();
            }
           else if(away.isSelected()){
               team = teamNameAway.getText();
            }
           else{
                str ="You have to choose a team";
                throw new Exception();
            }

            RefereeController.getInstance().addEventDuringGame(ScreenController.getInstance().userName, gameID, eventType, time2, playerName, team);
           if (eventType.equals("Goal")||eventType.equals("YellowCard")||eventType.equals("RedCard")) {
              ScreenController.getInstance().getRefereeControllerGui().updateEvent("Score",gameID);
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

package ServiceLayer;

import System.Exeptions.NoRefereePermissions;
import System.Exeptions.NoSuchEventException;
import System.FootballObjects.Event.AEvent;
import System.FootballObjects.Game;
import System.Users.Referee;
import System.Users.User;

import java.util.List;

public class RefereeController extends MainUserController  {

    private static RefereeController ourInstance = new RefereeController();

    public static RefereeController getInstance() {
        return ourInstance;
    }

    private RefereeController() {
    }

    public void editDetails(Referee referee, int id, String name, String password){
        FanController fanController = FanController.getInstance();
        fanController.editDetails(referee,id,name,password);
    }

    public List<Game> getMyGames(Referee referee){
        return referee.getGames();
    }

    public void addEventDuringGame(Referee referee, Game game, String type, int min) throws NoRefereePermissions, NoSuchEventException {
       referee.addEventMidGame(game,type,min);
    }

    public List<AEvent> getEventsOfGame(User user, Game game) throws NoRefereePermissions {
        if(!(user instanceof Referee)){
            throw new NoRefereePermissions();
        }
        return game.getEventLog().getaEventList();
    }

    public void editEventAfterGame(Referee referee, Game game, String type, AEvent oldEvent) throws NoRefereePermissions, NoSuchEventException {
        referee.editEventAfterGame(game, oldEvent, type);
    }

    public void addEventAfterGame(Referee referee, Game game, String type, int minute) throws NoRefereePermissions, NoSuchEventException {
        if(game.getMainReferee()!=referee){
            throw new NoRefereePermissions();
        }
        referee.addEventToLogEvent(game,type,minute);
    }

    public void createGameReport(Referee referee, Game game) throws NoRefereePermissions, NoSuchEventException {
        if(game.getMainReferee()!=referee){
            throw new NoRefereePermissions();
        }
        referee.createGameReport(game);
    }

}

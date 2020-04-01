package System.Users;

import System.FootballObjects.Event.AEvent;
import System.FootballObjects.Game;
import System.FootballObjects.Season;

import java.util.List;

public class Referee extends User {

    private String name;
    private String preparation;

    //Methods
    public void editPersonalDeatails(/* need to add the new details */){} //UC-38

    public List<Game> showGames(Season season){
        return null;
    } //UC-39

    public void editEventToGame(Game game , AEvent event){} //UC-40

    public void createGameReport(Game game , List<AEvent> aEventList){} //UC-41
}

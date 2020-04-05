package System.Users;

import System.FootballObjects.Event.AEvent;
import System.FootballObjects.Game;
import System.FootballObjects.Season;
import System.I_Observer.IObserverGame;
import System.I_Observer.ISubjectGame;

import java.util.List;

public class Referee extends User implements IObserverGame {

    private String preparation;
    private List<ISubjectGame> subjectGame;

    //Methods
    public void editPersonalDeatails(/* need to add the new details */){} //UC-38

    public List<Game> showGames(Season season){
        return null;
    } //UC-39

    public void editEventToGame(Game game , AEvent event){} //UC-40

    public void createGameReport(Game game , List<AEvent> aEventList){} //UC-41

    @Override
    public void update() {

    }

    /**
     * Add a game to get alert (adding to subjectGame list)
     * @param iSubjectGame
     */
    @Override
    public void registerAlert(ISubjectGame iSubjectGame) {
        this.subjectGame.add(iSubjectGame);

    }

    /**
     * Remove a game to get alert (adding to subjectGame list)
     * @param iSubjectGame
     */
    @Override
    public void removeAlert(ISubjectGame iSubjectGame) {
        this.subjectGame.remove(iSubjectGame);
    }

}

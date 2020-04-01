package System.FootballObjects;

import System.FootballObjects.Event.EventLog;
import System.I_Observer.IObserverGame;
import System.I_Observer.ISubjectGame;
import System.Users.Referee;
import System.Users.User;

import java.util.Date;
import java.util.List;

public class Game implements ISubjectGame {

    private Date date;
    private int hour;
    private String result;
    private List<IObserverGame> iObserverGameList;
    private Referee mainReferee;
    private Referee sideRefereeOne;
    private Referee getSideRefereeTwo;
    private EventLog eventLog;

    @Override
    public void notifyUsers(User user) {

    }

    @Override
    public void registerAlert(User user) { //UC-9

    } //UC-9

    @Override
    public void removeAlert(User user) { //UC-10

    } //UC-10

}

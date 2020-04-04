package System.FootballObjects;

import System.FootballObjects.Event.EventLog;
import System.I_Observer.IObserverGame;
import System.I_Observer.ISubjectGame;
import System.Users.Referee;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Game implements ISubjectGame {

    private Date date;
    private int hour;
    private String result;
    private List<IObserverGame> iObserverGameListForFans;
    private List<IObserverGame> iObserverGameListForReferee;
    private Referee mainReferee;
    private Referee sideRefereeOne;
    private Referee getSideRefereeTwo;
    private EventLog eventLog;

    public Game(Date date, int hour, String result, Referee mainReferee, Referee sideRefereeOne, Referee getSideRefereeTwo, EventLog eventLog) {
        this.date = date;
        this.hour = hour;
        this.result = result;
        this.mainReferee = mainReferee;
        this.sideRefereeOne = sideRefereeOne;
        this.getSideRefereeTwo = getSideRefereeTwo;
        this.eventLog = eventLog;
        this.iObserverGameListForFans= new LinkedList<>();
        this.iObserverGameListForReferee=new LinkedList<>();
    }


    /**
     * Adding a fan to alert list (iObserverGameListForFans)
     * @param fan
     */
    @Override
    public void registerFanToAlert(IObserverGame fan) { //UC-9
        this.iObserverGameListForFans.add(fan);
        fan.registerAlert(this);
    } //UC-9

    // @Override
    public void registerRefereeToAlert(IObserverGame referee) { //UC-9

    } //UC-9

    /**
     * Removing a fan from the alert list (iObserverGameListForFans)
     * @param fan
     */
    @Override
    public void removeAlertToFan(IObserverGame fan) {
        this.iObserverGameListForFans.remove(fan);
        fan.removeAlert(this);

    }

    @Override
    public void removeAlertToReferee(IObserverGame referee) {

    }

    /**
     * Send an alert to all fans in iObserverGameListForFans
     */
    @Override
    public void notifyFan () { //UC-10
        for (IObserverGame fan: iObserverGameListForFans) {
            fan.update();
        }
    }


    public void notifyReferee (){

    }

}

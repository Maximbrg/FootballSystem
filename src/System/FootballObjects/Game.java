package System.FootballObjects;

import System.FootballObjects.Event.AEvent;
import System.FootballObjects.Event.EventLog;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverGame;
import System.I_Observer.ISubjectGame;
import System.Users.Referee;
import System.Log;
import System.Users.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Game implements ISubjectGame {

    private static int ID=1;
    private int id;
    private Date date;
    private int hour;
    private String result;
    private Referee mainReferee;
    private Referee sideRefereeOne;
    private Referee getSideRefereeTwo;
    private Team away;
    private Team home;
    private EventLog eventLog;
    private List<IObserverGame> iObserverGameListForFans;
    private List<IObserverGame> iObserverGameListForReferees;

    //<editor-fold desc="constructor">
    public Game(Date date, int hour, Referee mainReferee, Referee sideRefereeOne, Referee getSideRefereeTwo, Team away, Team home) {
        this.id= ID;
        ID++;
        this.date = date;
        this.hour = hour;
        this.mainReferee = mainReferee;
        this.sideRefereeOne = sideRefereeOne;
        this.getSideRefereeTwo = getSideRefereeTwo;
        this.away=away;
        this.home=home;
        this.eventLog = new EventLog();
        this.iObserverGameListForFans= new LinkedList<>();
        this.iObserverGameListForReferees =new LinkedList<>();
    }
    //</editor-fold>

    //<editor-fold desc="getter">
    public int getId(){
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public String getResult() {
        return result;
    }

    public Referee getMainReferee() {
        return mainReferee;
    }

    public Referee getSideRefereeOne() {
        return sideRefereeOne;
    }

    public Referee getGetSideRefereeTwo() {
        return getSideRefereeTwo;
    }

    public Team getAway() {
        return away;
    }

    public Team getHome() {
        return home;
    }

    public EventLog getEventLog() {
        return eventLog;
    }

    public List<IObserverGame> getiObserverGameListForFans() {
        return iObserverGameListForFans;
    }

    public List<IObserverGame> getiObserverGameListForReferees() {
        return iObserverGameListForReferees;
    }
    //</editor-fold>

    //<editor-fold desc="setter">
    public void setDate(Date date) {
        this.date = date;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMainReferee(Referee mainReferee) {
        this.mainReferee = mainReferee;
    }

    public void setSideRefereeOne(Referee sideRefereeOne) {
        this.sideRefereeOne = sideRefereeOne;
    }

    public void setGetSideRefereeTwo(Referee getSideRefereeTwo) {
        this.getSideRefereeTwo = getSideRefereeTwo;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public void setEventLog(EventLog eventLog) {
        this.eventLog = eventLog;
    }

    public void setiObserverGameListForFans(List<IObserverGame> iObserverGameListForFans) {
        this.iObserverGameListForFans = iObserverGameListForFans;
    }

    public void setiObserverGameListForReferees(List<IObserverGame> iObserverGameListForReferees) {
        this.iObserverGameListForReferees = iObserverGameListForReferees;
    }
    //</editor-fold>

    /**
     * Adding event to log event
     * @param event
     */
    public void addEventToLogEvent(AEvent event){
        eventLog.addEventToLog(event);
        notifyFan();
    }

    /**
     * Adding a fan to alert list (iObserverGameListForFans)
     * @param fan
     */
    @Override
    public void registerFanToAlert(IObserverGame fan) { //UC-9
        this.iObserverGameListForFans.add(fan);
        fan.registerAlert(this);
        Log.getInstance().writeToLog("Fan "+((User)fan).getName() +" (id: "+ ((User)fan).getId()+") register to receive a game (id: " + id +") alert");

    } //UC-9

    /**
     * Adding a referee to alert list (iObserverGameListForReferees)
     * @param referee
     */
    @Override
    public void registerRefereeToAlert(IObserverGame referee) {
        this.iObserverGameListForReferees.add(referee);
        referee.registerAlert(this);
        Log.getInstance().writeToLog("Referee "+((User)referee).getName() +" (id: "+ ((User)referee).getId()+") register to receive a game (id: " + id +") alert");

    } //UC-9

    /**
     * Removing a fan from the alert list (iObserverGameListForFans)
     * @param fan
     */
    @Override
    public void removeAlertToFan(IObserverGame fan) {
        this.iObserverGameListForFans.remove(fan);
        fan.removeAlert(this);
        Log.getInstance().writeToLog("Fan "+((User)fan).getName() +" (id: "+ ((User)fan).getId()+") removed alert to game (id: " + id +") alert");

    }

    /**
     * Removing a referee from the alert list (iObserverGameListForReferees)
     * @param referee
     */
    @Override
    public void removeAlertToReferee(IObserverGame referee) {
        this.iObserverGameListForReferees.remove(referee);
        referee.removeAlert(this);
        Log.getInstance().writeToLog("Referee "+((User)referee).getName() +" (id: "+ ((User)referee).getId()+") removed alert to game (id: " + id +") alert");

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

    /**
     * Send an alert to all referee in iObserverGameListForReferees
     */
    @Override
    public void notifyReferee (){
        for (IObserverGame referee: iObserverGameListForReferees) {
            referee.update();
        }
    }

}

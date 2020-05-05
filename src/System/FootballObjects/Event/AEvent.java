package System.FootballObjects.Event;

import System.SystemEventLog;

import java.util.Date;

public abstract class AEvent {

    //<editor-fold desc="Fields">
    private static int ID=1;
    private int id;
    private Date date;
    private int minute;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    /**
     * Abstract constructor for all the events that can occur in game
     * @param minuteInTheGame
     */
    public AEvent(int minuteInTheGame) {
        this.minute = minuteInTheGame;
        id=ID++;
        date=new Date(System.currentTimeMillis());
        SystemEventLog.getInstance().writeToLog("New Event was created. ("+id+")");
    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public int getId() {
        return id;
    }

    public int getMinute(){
        return minute;
    }

    public Date getDate() {
        return date;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public static void setID(int ID) {
        AEvent.ID = ID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
    //</editor-fold>

}

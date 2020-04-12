package System.FootballObjects.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import System.Log;

public abstract class AEvent {
    private static int ID=1;
    private int id;
    private Date date;
    private int minute;

    //<editor-fold desc="Constructor">
    public AEvent(int minuteInTheGame) {
        this.minute = minuteInTheGame;
        id=ID++;
        date=new Date(System.currentTimeMillis());
        Log.getInstance().writeToLog("New Event was created. ("+id+")");
    }
    //</editor-fold>

    //<editor-fold desc="getters">
    public int getId() {
        return id;
    }
    public int getMinute(){
        return minute;
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

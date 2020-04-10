package System.FootballObjects.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import System.Log;

public abstract class AEvent {
    private static int ID=1;
    private int id;
    private Date date;
    //private int hour;
    private int minute;
//Constructor

    public AEvent(int minuteInTheGame) {
        this.minute = minuteInTheGame;
        id=ID++;
        date=new Date(System.currentTimeMillis());
        Log.getInstance().writeToLog("New Event was created. ("+id+")");
    }

    public int getId() {
        return id;
    }
    public int getMinute(){
        return minute;
    }


}

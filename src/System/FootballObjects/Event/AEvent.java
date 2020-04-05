package System.FootballObjects.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    }

    public int getId() {
        return id;
    }
    public int getMinute(){
        return minute;
    }


}
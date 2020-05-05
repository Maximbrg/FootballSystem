package System.FootballObjects.Event;
import System.SystemEventLog;
import System.FootballObjects.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventLog {

    //<editor-fold desc="Fields">
    private List<AEvent> aEventList;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public EventLog(){
        aEventList=new ArrayList<AEvent>();
    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public List<AEvent> getEventList(){
        return aEventList;
    }
    //</editor-fold>

    //<editor-fold desc="Methods">
    /**
     * Adding event to log event
     * @param event
     */
    public void addEventToLog(AEvent event){
        aEventList.add(event);
        sortEventLog();
        SystemEventLog.getInstance().writeToLog("Event was added to eventLog. Id:"+event.getId());
    }

    /**
     * Removes event from log event
     * @param event
     */
    public void removeEvent(AEvent event){
        aEventList.remove(event);
        sortEventLog();
        SystemEventLog.getInstance().writeToLog("Event was removed from eventLog. Id:"+event.getId());

    }

    /**
     * Sorting event log by time
     */
    public void sortEventLog(){
        Collections.sort(this.aEventList, new Comparator<AEvent>() {
            public int compare(AEvent o1, AEvent o2) {
                if(o1.getMinute()==o2.getMinute()){
                    return 0;
                }
                if(o1.getMinute()>o2.getMinute() ){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
    }
    //</editor-fold>

}

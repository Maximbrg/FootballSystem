package System.FootballObjects.Event;
import System.Log;
import System.FootballObjects.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventLog {

    private List<AEvent> aEventList;
    public EventLog(){
        aEventList=new ArrayList<AEvent>();
    }
    public List<AEvent> getaEventList(){
        return aEventList;
    }
    public void addEventToLog(AEvent event){
        aEventList.add(event);
        sortEventLog();
        Log.getInstance().writeToLog("Event was added to eventLog. Id:"+event.getId());
    }
    public void removeEvent(AEvent event){
        aEventList.remove(event);
        sortEventLog();
        Log.getInstance().writeToLog("Event was removed from eventLog. Id:"+event.getId());

    }

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
}

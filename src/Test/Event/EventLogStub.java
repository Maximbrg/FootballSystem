

import System.FootballObjects.Event.*;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class EventLogStub {
    EventLog eventLog ;
    Goal g2;
    Offense g1;
    RedCard g0;
    @Before
    public void init(){
        g0=new RedCard(10);
        g1=new Offense(20);
        g2=new Goal(30);
        eventLog=new EventLog();
    }

    @Test
    public void addEventToLogTest(){
        eventLog.addEventToLog(g0);
        assertEquals(g0.getId(),eventLog.getaEventList().get(0).getId());
    }
    @Test
    public void removeEventTest(){
        eventLog.addEventToLog(g0);
        eventLog.removeEvent(g0);
        assertEquals(0,eventLog.getaEventList().size());
    }

    @Test
    public void sortEventLogTest(){
        eventLog.addEventToLog(g2);
        eventLog.addEventToLog(g0);
        eventLog.addEventToLog(g1);
        int j=0;
        for (int i = 10; i <31; i+=10) {
            assertEquals(i,eventLog.getaEventList().get(j).getMinute());
            j++;
        }
    }


}

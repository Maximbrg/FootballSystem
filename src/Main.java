package System;

import System.FootballObjects.Event.EventLog;
import System.FootballObjects.Event.Goal;
import System.FootballObjects.Game;
import System.FootballObjects.Season;
import System.FootballObjects.Team.Team;
import System.Users.Referee;
import System.Users.RefereeType;
import System.Users.SystemManager;

import java.io.IOException;
import java.sql.Ref;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    public static void main(String[] args) throws IOException {
//        Log l = Log.getInstance();
//        l.writeToLog("Shiran is now following after Alon Mizrachi.");
//        SystemManager s= new SystemManager(123);
//        //System.out.print(s.showLog());
        EventLog e=new EventLog();
        Goal g1=new Goal(10);
        Goal g2=new Goal(20);
        Goal g3=new Goal(30);
        e.addEventToLog(g3);
        e.addEventToLog(g2);
        e.addEventToLog(g1);
        System.out.println(e.getaEventList().get(0).getClass().toString().substring(35,e.getaEventList().get(0).getClass().toString().length()));
        Referee r = new Referee("avi",RefereeType.MainReferee,204,"ss","ssss");
        Game g=new Game(new Date(System.currentTimeMillis()),10,r,new Referee("avi2",RefereeType.MainReferee,204,"ss","ssss"),new Referee("avi3", RefereeType.MainReferee,204,"ss","ssss"),new Team(),new Team());
        g.addEventToLogEvent(e.getaEventList().get(0));
        g.addEventToLogEvent(e.getaEventList().get(1));
        g.addEventToLogEvent(e.getaEventList().get(2));
        System.out.print(r.createGameReport(g));

    }
}

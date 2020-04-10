import System.Asset.Asset;
import System.Enum.RefereeType;
import System.Enum.TeamStatus;
import System.FootballObjects.Event.Goal;
import System.FootballObjects.Event.YellowCard;
import System.FootballObjects.Game;
import System.FootballObjects.Season;
import System.FootballObjects.Team.Team;
import System.PersonalPages.PersonalPage;
import System.Users.Fan;
import System.Users.Referee;
import System.Users.TeamManager;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

/*

public class RefereeTest {
    Referee rTest2,rTest3,rTest0,rTest1;
    Game g1,g0;
    Goal goal0,goal1;
    Date d1,d2;
    Team t,t2;
    @Before
    public void init(){
        rTest1=new Referee("Hen", RefereeType.MainReferee,204,"abc","KillerReferee");
        rTest0=new Referee("Max", RefereeType.AssistantReferee,205,"abc","KillerReferee");
        rTest2=new Referee("Dana", RefereeType.AssistantReferee,206,"abc","KillerReferee");
        rTest3=new Referee("Dana", RefereeType.AssistantReferee,207,"abc","KillerReferee");
        //<editor-fold desc="setters for dates">
        double hours =(new Date(System.currentTimeMillis()).getHours()-6.51);
        int castHours=(int)hours;
         d1=new Date(System.currentTimeMillis());
        d1.setHours(castHours);
         d2=new Date(System.currentTimeMillis());
        d2.setHours(castHours+3);
        //</editor-fold>
         t=new Team("Hap",TeamStatus.Active,null,null,null,null,null,100,2000,null);
         t2=new Team("Hap",TeamStatus.Active,null,null,null,null,null,100,2000,null);
         g0 = new Game(d1,10,rTest1,rTest0,rTest2,t,t2);
         g1 = new Game(d2,10,rTest1,rTest0,rTest2,t,t2);
         goal0= new Goal(22);
         rTest0.addEventToLogEvent(g0,"Goal",22);
         //goal1= new Goal(50);
    }

    @Test
    public void addGameTest(){
        rTest0.addGame(g0);
        assertEquals(g0.getId(),rTest0.getGames().get(0).getId());
    }
    @Test
    public void editEventAfterGameTest(){
        // too late for changing in the event log
        rTest1.editEventAfterGame(g0,g0.getEventLog().getaEventList().get(0),"RedCard");
        assertEquals(g0.getEventLog().getaEventList().get(0).getClass().toString().substring(35),"Goal");
        //in couple hours after game (not more than 5)
        g1.addEventToLogEvent(goal0);
        rTest1.editEventAfterGame(g1,g1.getEventLog().getaEventList().get(0),"RedCard");
        assertEquals(g1.getEventLog().getaEventList().get(0).getClass().toString().substring(35),"RedCard");// too late for changing in the event log
        //assistant referee not allow to edit
        rTest0.editEventAfterGame(g1,g1.getEventLog().getaEventList().get(0),"Offense");
        assertNotEquals(g1.getEventLog().getaEventList().get(0).getClass().toString().substring(35),"Offense");// too late for changing in the event log
        //referee that not belong to the game
        rTest3.editEventAfterGame(g1,g1.getEventLog().getaEventList().get(0),"Offense");
        assertNotEquals(g1.getEventLog().getaEventList().get(0).getClass().toString().substring(35),"Offense");// too late for changing in the event log
    }
    @Test
    public void addEventMidGameTest(){
        //in middle of the game
        int hours =new Date(System.currentTimeMillis()).getHours()-1;
        d2.setHours(hours);
        rTest0.addEventMidGame(g1,"YellowCard",15);
        assertEquals(g1.getEventLog().getaEventList().get(0).getClass().toString().substring(35), "YellowCard");
        //after the game
        d2.setHours(hours-8);
        rTest0.addEventMidGame(g1,"Offense",90);
        assertNotEquals(g1.getEventLog().getaEventList().get(g1.getEventLog().getaEventList().size()-1).getClass().toString().substring(35), "Offense");
        //referee that not belong to the game
        d2.setHours(hours);
        rTest3.addEventMidGame(g1,"Offense",90);
        assertNotEquals(g1.getEventLog().getaEventList().get(g1.getEventLog().getaEventList().size()-1).getClass().toString().substring(35), "Offense");
    }
    @Test
    public void getFutureGamesTest(){
        Date d11=new Date(2021-1900,6,15,10,10);
        Date d22=new Date(2010-1900,6,15,10,10);
        Date d3=new Date(2020-1900,7,15,10,10);
        Date d4=new Date(2022-1900,8,15,10,10);
        Game g00 = new Game(d11,10,rTest1,rTest0,rTest2,t,t2);
        Game g11 = new Game(d22,10,rTest1,rTest0,rTest2,t,t2);
        Game g2 = new Game(d3,10,rTest1,rTest0,rTest2,t,t2);
        Game g3 = new Game(d4,10,rTest1,rTest0,rTest2,t,t2);
        rTest1.addGame(g00);
        rTest1.addGame(g3);
        rTest1.addGame(g11);
        rTest1.addGame(g2);
        for (int i = 1; i <rTest1.getFutureGames().size()-1 ; i++) {
            assertTrue(rTest1.getFutureGames().get(i-1).getDate().before(rTest1.getFutureGames().get(i).getDate()));
            assertTrue(rTest1.getFutureGames().get(i).getDate().before(rTest1.getFutureGames().get(i+1).getDate()));
        }

    }
    @Test
    public void getGamesForSeasonTest(){
        Date d11=new Date(2021-1900,6,15,10,10);
        Date d22=new Date(2010-1900,6,15,10,10);
        Date d3=new Date(2020-1900,7,15,10,10);
        Date d4=new Date(2022-1900,8,15,10,10);
        Game g00 = new Game(d11,10,rTest1,rTest0,rTest2,t,t2);
        Game g11 = new Game(d22,10,rTest1,rTest0,rTest2,t,t2);
        Game g2 = new Game(d3,10,rTest1,rTest0,rTest2,t,t2);
        Game g3 = new Game(d4,10,rTest1,rTest0,rTest2,t,t2);
        ArrayList<Team> teamList=  new ArrayList<>();
        teamList.add(t);
        teamList.add(t2);
        Season s=new Season(2019,teamList);
        s.setGameToGames(g00);
        s.setGameToGames(g3);
        s.setGameToGames(g11);
        s.setGameToGames(g2);
        assertEquals(g11.getId(),rTest1.getGamesForSeason(s).get(0).getId());
        assertEquals(g2.getId(),rTest1.getGamesForSeason(s).get(1).getId());
        assertEquals(g00.getId(),rTest1.getGamesForSeason(s).get(2).getId());
        assertEquals(g3.getId(),rTest1.getGamesForSeason(s).get(3).getId());
    }
}
*/
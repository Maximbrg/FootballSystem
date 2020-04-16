

import System.Enum.RefereeType;
import System.Exeptions.NoRefereePermissions;
import System.Exeptions.NoSuchEventException;
import System.FootballObjects.Event.Goal;
import System.FootballObjects.Game;
import System.FootballObjects.League;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.FootballObjects.Team.Team;
import System.Users.FootballAssociation;
import System.Users.Referee;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class RefereeTest {
    static Referee rTest2,rTest3,rTest0,rTest1;
    static  Game g1,g0;
    static Goal goal0,goal1;
    static Date d1,d2;
    static Team t,t2;
    static ArrayList<Referee> rList;
    @BeforeClass
    public static void init() throws NoSuchEventException {
        rTest1=new Referee("Hen", RefereeType.MAIN,204,"abc","KillerReferee");
        rTest0=new Referee("Max", RefereeType.ASSISTANT,205,"abc","KillerReferee");
        rTest2=new Referee("Dana", RefereeType.ASSISTANT,206,"abc","KillerReferee");
        rTest3=new Referee("Dana", RefereeType.ASSISTANT,207,"abc","KillerReferee");
        //<editor-fold desc="setters for dates">
        double hours =(new Date(System.currentTimeMillis()).getHours()-6.51);
        int castHours=(int)hours;
         d1=new Date(System.currentTimeMillis());
        d1.setHours(castHours);
         d2=new Date(System.currentTimeMillis());
        d2.setHours(castHours+3);
        //</editor-fold>
         t=new Team("Hap",null);
         t2=new Team("Hap",null);
         g0 = new Game(d1,10,rTest1,rTest0,rTest2,t,t2);
         g1 = new Game(d2,10,rTest1,rTest0,rTest2,t,t2);
         goal0= new Goal(22);
         rTest0.addEventToLogEvent(g0,"Goal",22);
         rList=new ArrayList<>();
         rList.add(rTest1);
         rList.add(rTest3);
         rList.add(rTest0);
         rList.add(rTest2);
         //goal1= new Goal(50);
    }
    @Test
    public void addGameTest(){
        rTest0.addGame(g0);
        assertEquals(g0.getId(),rTest0.getGames().get(0).getId());
    }
    @Test
    public void editEventAfterGameTest() throws NoRefereePermissions, NoSuchEventException {
        // too late for changing in the event log
        try{
            rTest1.editEventAfterGame(g0,g0.getEventLog().getEventList().get(0),"RedCard");
        }catch (NoRefereePermissions e){
            assert (true);
        }
        assertEquals(g0.getEventLog().getEventList().get(0).getClass().toString().substring(35),"Goal");
        //in couple hours after game (not more than 5)
        g1.addEventToLogEvent(goal0);
        rTest1.editEventAfterGame(g1,g1.getEventLog().getEventList().get(0),"RedCard");
        assertEquals(g1.getEventLog().getEventList().get(0).getClass().toString().substring(35),"RedCard");// too late for changing in the event log
        //assistant referee not allow to edit
        try{
            rTest0.editEventAfterGame(g1,g1.getEventLog().getEventList().get(0),"Offense");
        }catch (NoRefereePermissions e){
            assert (true);
        }
        assertNotEquals(g1.getEventLog().getEventList().get(0).getClass().toString().substring(35),"Offense");// too late for changing in the event log
        //referee that not belong to the game
        try{
            rTest3.editEventAfterGame(g1,g1.getEventLog().getEventList().get(0),"Offense");
        }catch (NoRefereePermissions e){
            assert (true);
        }
        assertNotEquals(g1.getEventLog().getEventList().get(0).getClass().toString().substring(35),"Offense");// too late for changing in the event log
    }
    @Test
    public void addEventMidGameTest() throws NoRefereePermissions, NoSuchEventException {
        //in middle of the game
        int hours =new Date(System.currentTimeMillis()).getHours()-1;
        d2.setHours(hours);
        rTest0.addEventMidGame(g1,"YellowCard",15);
        assertEquals(g1.getEventLog().getEventList().get(0).getClass().toString().substring(35), "YellowCard");
        //after the game
        d2.setHours(hours-8);
        try{
            rTest0.addEventMidGame(g1,"Offense",90);
        }catch (NoRefereePermissions e){
            assert (true);
        }
        assertNotEquals(g1.getEventLog().getEventList().get(g1.getEventLog().getEventList().size()-1).getClass().toString().substring(35), "Offense");
        //referee that not belong to the game
        d2.setHours(hours);
        try {
            rTest3.addEventMidGame(g1, "Offense", 90);
       }catch (NoRefereePermissions e){
            assert (true);

       }
        assertNotEquals(g1.getEventLog().getEventList().get(g1.getEventLog().getEventList().size()-1).getClass().toString().substring(35), "Offense");
    }

    @Test
    public void createGameReportTest(){
        try {
            rTest0.createGameReport(g1);
        } catch (NoRefereePermissions e){
            assert (true);
        }
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
        ArrayList<Team> teamList=  new ArrayList<>();
        teamList.add(t);
        teamList.add(t2);
        Season s=new Season(2019);
        League leagueTest=new League("champions",teamList);
        LeagueInformation lTest=new LeagueInformation(leagueTest,s,new FootballAssociation(123,"avile","345345","avileHaGadol"));
        s.addLeagueInformation(lTest);
        lTest.initLeagueInformation();
        lTest.schedulingReferee(rList);
        List<Game> gamesForRef=rList.get(0).getGamesForSeason(s);
        assertEquals(rList.get(0).getId(),gamesForRef.get(0).getMainReferee().getId());
        assertEquals(rList.get(0).getId(),gamesForRef.get(1).getMainReferee().getId());

    }
}

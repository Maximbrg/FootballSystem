import System.Controller;
import System.Enum.RefereeType;
import System.Exeptions.HasTeamAlreadyException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.League;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.FootballObjects.Team.*;
import System.Users.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSuit5 {

    League PremierLeague;
    Season season;
    LeagueInformation leagueInformation;

    TeamOwner teamOwner=new TeamOwner(1,"Inbar","123","Inbar",100);
    Team Arsenal= new Team("Arsenal", teamOwner);
    Team Liverpool = new Team("Liverpool", teamOwner);
    Team Chelsea= new Team("Chelsea",  teamOwner);

    @Before
    public void setUp() throws Exception {

        List<Team> teams= new ArrayList<>();
        teams.add(Arsenal);
        teams.add(Liverpool);
        teams.add(Chelsea);
        PremierLeague= new League("PremierLeague",teams);
        season= new Season(2000);
        FootballAssociation footballAssociation = new FootballAssociation(1, "Shachar", "111", "Sha");
        leagueInformation= new LeagueInformation(PremierLeague,season, footballAssociation);
        leagueInformation.initLeagueInformation();

    }

    @Test
    public void Test1() throws HasTeamAlreadyException, UserNameAlreadyExistException { //Test ID: 11#  -- Init league and check update results of games
        SystemManager systemManager = new SystemManager(1,"a","a","a");
        TeamOwner teamOwner = systemManager.createNewTeamOwner(1,"Gabe","Gabe","GabeFTW",0);
        systemManager.createTeam("Arsenal" ,teamOwner);
        systemManager.createTeam("Liverpool" ,teamOwner);
        FootballAssociation footballAssociation =  new FootballAssociation(1,"shachar","123","ss");
        Season season=new Season(2000);
        League  PremierLeague= new League("PremierLeague",Controller.getInstance().getAllTeams());
        LeagueInformation leagueInformation=new LeagueInformation(PremierLeague,season,(FootballAssociation) Controller.getInstance().getUsers().get(0));
        leagueInformation.initLeagueInformation();
        footballAssociation.addNewReferee("r1", RefereeType.MAIN,1,"123","r1");
        footballAssociation.addNewReferee("r2", RefereeType.ASSISTANT,1,"123","r2");
        footballAssociation.addNewReferee("r3", RefereeType.ASSISTANT,1,"123","r3");
        leagueInformation.schedulingReferee(Controller.getInstance().getAllReferee());
        List<Team>teams=Controller.getInstance().getAllTeams();
        Team t1=teams.get(0);
        Team t2=teams.get(1);
        leagueInformation.updateScoreTeamInLeagueTable(t1,"WIN");
        leagueInformation.updateScoreTeamInLeagueTable(t2,"LOSS");
        Team [] teams1= new Team[3];
        int i=0;
        HashMap<Team,Integer> temp= leagueInformation.getLeagueTable();
        for (HashMap.Entry me : temp.entrySet()) {
            teams1[i]=(Team) me.getKey();
            i++;
        }
        assertEquals(teams1[0],t1);
        assertEquals(teams1[1],t2);
        leagueInformation.updateScoreTeamInLeagueTable(t2,"WIN");
        leagueInformation.updateScoreTeamInLeagueTable(t2,"WIN");
        i=0;
        HashMap<Team,Integer> temp2= leagueInformation.getLeagueTable();
        for (HashMap.Entry me : temp2.entrySet()) {
            teams1[i]=(Team) me.getKey();
            i++;
        }
        assertEquals(teams1[1],t1);
        assertEquals(teams1[0],t2);

    }

    @Test
    public void Test2() throws UserNameAlreadyExistException { //Test IDl 12# -- Checks whether the game embedding in the complete system was performed properly
        SystemManager systemManager = new SystemManager(1,"a","a","a");
        Season season=new Season(2000);
        League  PremierLeague= new League("PremierLeague",Controller.getInstance().getAllTeams());
        LeagueInformation leagueInformation=new LeagueInformation(PremierLeague,season,(FootballAssociation) Controller.getInstance().getUsers().get(0));
        leagueInformation.initLeagueInformation();
        //leagueInformation.schedulingReferee(Controller.getInstance().getAllReferee());

        assertEquals(leagueInformation.getGames().size(),2);
        assertEquals(Controller.getInstance().getAllReferee().get(0).getGames().size(),2);
        assertEquals(Controller.getInstance().getAllReferee().get(1).getGames().size(),2);
        assertEquals(Controller.getInstance().getAllReferee().get(2).getGames().size(),2);
        assertEquals(leagueInformation.getLeague().getTeams(),Controller.getInstance().getAllTeams());
    }

    @Test
    public void Test3() throws UserNameAlreadyExistException { //Test IDl 13# -- Checks update Team Allocate Policy with init league information.
        SystemManager systemManager = new SystemManager(3,"c","c","ccc");
        Season season=new Season(2000);
        League  PremierLeague= new League("PremierLeague",Controller.getInstance().getAllTeams());
        LeagueInformation leagueInformation2=new LeagueInformation(PremierLeague,season,(FootballAssociation) Controller.getInstance().getUsers().get(0));
        ITeamAllocatePolicy iTeamAllocatePolicy=new OneGameAllocatePolicy();
        leagueInformation2.editGameSchedulingPolicy(iTeamAllocatePolicy);

        leagueInformation2.initLeagueInformation();
        assertEquals(leagueInformation2.getGames().size(),1);

        LeagueInformation leagueInformation3=new LeagueInformation(PremierLeague,season,(FootballAssociation) Controller.getInstance().getUsers().get(0));
        leagueInformation3.initLeagueInformation();
        assertEquals(leagueInformation3.getGames().size(),2);
    }

    @Test
    public void Test4() throws UserNameAlreadyExistException { //Test IDl 14# -- Checks update Team Score Policy with init league information.
        SystemManager systemManager = new SystemManager(1,"a","a","a");
        TeamOwner teamOwner = systemManager.createNewTeamOwner(4,"d","d","ddd",0);
        systemManager.createTeam("Arsenal" ,teamOwner);
        systemManager.createTeam("Liverpool" ,teamOwner);
        systemManager.createTeam("Chelsea" ,teamOwner);
        FootballAssociation footballAssociation =  new FootballAssociation(1,"shachar","123","ss");
        Season season=new Season(2000);
        League  PremierLeague= new League("PremierLeague",Controller.getInstance().getAllTeams());
        LeagueInformation leagueInformation=new LeagueInformation(PremierLeague,season,(FootballAssociation) Controller.getInstance().getUsers().get(0));
        IScoreMethodPolicy iScoreMethodPolicy=new DefaultMethod();
        leagueInformation.editScoreSchedulingPolicy(iScoreMethodPolicy);

        leagueInformation.initLeagueInformation();

        assertEquals(leagueInformation.getWIN(),3);
        assertEquals(leagueInformation.getTIE(),1);
        assertEquals(leagueInformation.getLOSS(),0);

    }

    @Test
    public void Test5() throws UserNameAlreadyExistException { //Test IDl 15# --
        SystemManager systemManager = new SystemManager(1,"a","a","a");
        TeamOwner teamOwner = systemManager.createNewTeamOwner(10,"f","ff","sdfs",0);
        systemManager.createTeam("Arsenal" ,teamOwner);
        systemManager.createTeam("Liverpool" ,teamOwner);
        systemManager.createTeam("Chelsea" ,teamOwner);
        FootballAssociation footballAssociation =  new FootballAssociation(1,"shachar","123","ss");
        Season season=new Season(2000);
        League  PremierLeague= new League("PremierLeague",Controller.getInstance().getAllTeams());
        LeagueInformation leagueInformation=new LeagueInformation(PremierLeague,season,(FootballAssociation) Controller.getInstance().getUsers().get(0));
        IScoreMethodPolicy iScoreMethodPolicy=new DefaultMethod();
        leagueInformation.editScoreSchedulingPolicy(iScoreMethodPolicy);


    }

}
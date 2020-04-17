import System.Controller;
import System.Exeptions.HasTeamAlreadyException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.League;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.FootballObjects.Team.Team;
import System.Report;
import System.Users.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestSuit1 {

    @Test
    public void Test1() throws HasTeamAlreadyException, UserNameAlreadyExistException { //Test ID: 1#  -- Checks if player can join to a new team
        SystemManager systemManager = new SystemManager(1,"a","a","a");
        Player player = systemManager.createNewPlayer(1,"Pudge","Pudge","PudgeFTW",new Date(),"Strength",100,0);
        TeamOwner teamOwner = systemManager.createNewTeamOwner(1,"Gabe","Gabe","GabeFTW",0);
        systemManager.createTeam("DOTA" ,teamOwner);
        Team team = Controller.getInstance().getAllTeams().get(0);
        team.addAsset(player);
        assertEquals(player,team.getAssets().get(0));
    }

    @Test
    public void Test2() throws UserNameAlreadyExistException { //Test IDl 2# -- Checks if teams added successfully to league
        SystemManager systemManager = new SystemManager(1,"a","a","a");
        TeamOwner teamOwner = systemManager.createNewTeamOwner(1,"Gabe","Gabe","GabeFTW",0);
        systemManager.createTeam("DOTA1" ,teamOwner);
        systemManager.createTeam("DOTA2" ,teamOwner);
        FootballAssociation footballAssociation =  systemManager.createNewFootballAssociation(1,"Lina","Lina","LineFTW");
        LeagueInformation leagueInformation = footballAssociation.initLeague(new Season(2020),new League("Dota",Controller.getInstance().getAllTeams()));
        assertEquals(leagueInformation.getLeague().getTeams(),Controller.getInstance().getAllTeams());
    }

    @Test
    public void Test3() throws UserNameAlreadyExistException { //Test IDl 3# -- Checks if system manager see all reports
        SystemManager systemManager = new SystemManager(1,"a","a","a");
        Fan fan = Controller.getInstance().signUp(1,"Invoker","Invoker","InvokerFTW");
        fan.submitReport("hi");
        HashMap<Integer,Report> info = systemManager.getOpenReports();
        Assert.assertTrue(info.get(1).getReport().equals("hi"));
    }

    @Test
    public void Test4() throws UserNameAlreadyExistException { //Test IDl 4# -- Checks if user can see answer on his report
        SystemManager systemManager = new SystemManager(1,"a","a","a");
        Fan fan = Controller.getInstance().signUp(1,"Invoker","Invoker","InvokerFTW");
        fan.submitReport("hi");
        HashMap<Integer,Report> info = systemManager.getOpenReports();
        systemManager.getOpenReports().get(1).answer("bye");
        Assert.assertTrue(fan.getMyReports().get(1).getAnswer().equals("bye"));
    }

    @Test
    public void Test5() throws UserNameAlreadyExistException { //Test IDl 4# -- Checks if Controller have all the users in the system
        SystemManager systemManager = new SystemManager(1,"a","a","a");
        Controller.getInstance().addUser("a",systemManager);
        systemManager.createNewFan(1,"EarthShaker","EarthShaker","EarthShakerFTW");
        systemManager.createNewFan(1,"Puck","Puck","PuckFTW");
        Assert.assertTrue(Controller.getInstance().getUsers().size()==3);
    }

}

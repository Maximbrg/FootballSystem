import ServiceLayer.Exceptions.TeamHasAFutureGame;
import ServiceLayer.SystemManagerController;
import System.Controller;
import System.Enum.RefereeType;
import System.Enum.UserStatus;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.League;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.FootballObjects.Team.Team;
import System.Report;
import System.Users.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SystemManagerControllerTest {
    SystemManagerController sTest;
    SystemManager sMangerTest;
    FootballAssosiation fTest;
    Season seasonTest;
    League leagueTest;
    Team t,t2;
    List<Team> teams;
    LeagueInformation lInfo;
    Fan fanTest,fanTest1,fanTest2,fanTest3;

    @Before
    public void init() throws UserNameAlreadyExistException {
        sTest=new SystemManagerController();
        sMangerTest=new SystemManager(124,"aviko","123","ninja");
        Controller.getInstance().addUser(sMangerTest.getUserName(),sMangerTest);
        //fTest=sTest.createNewFootballAssociation(sMangerTest,123,"kikos","123","kikos45");
        sMangerTest.createTeam("hapoelb7",null);
        sMangerTest.createTeam("hapoelb72",null);
         t=Controller.getInstance().getAllTeams().get(0);
         t2=Controller.getInstance().getAllTeams().get(1);

         teams=new LinkedList<>();
         teams.add(t);
         teams.add(t2);
        seasonTest=new Season(1999);
        leagueTest=new League("champions",teams);
         lInfo=new LeagueInformation(leagueTest,seasonTest,fTest);

    }

    /**
     *
     * close the team permanently
     * 8.1.1 -uc 25
     * @throws UserNameAlreadyExistException
     */
    @Test
    public void permanentlyCloseTeam() throws UserNameAlreadyExistException {
        fTest=sTest.createNewFootballAssociation(sMangerTest,123,"kikos","123","kikos45");
        fTest.initLeague(seasonTest,leagueTest);
        fTest.initLeagueInformation(lInfo);
        try {
            sTest.permanentlyCloseTeam(sMangerTest,t);
        } catch (TeamHasAFutureGame teamHasAFutureGame) {
            assert (true);
        }
    }

    /**
     *
     * restart a removed user
     * 8.2.2 - uc 25
     */
    @Test
    public void restartUser() throws UserNameAlreadyExistException {
        try {
            fanTest=sTest.createNewFan(sMangerTest,10,"roni","a","debik");
            fanTest1=sTest.createNewFan(sMangerTest,10,"roni","a","debiq");

            sTest.removeUser(sMangerTest,fanTest1);
            assertEquals(UserStatus.REMOVED,fanTest1.getStatus());
            sTest.restartUser(sMangerTest,fanTest1);
            assertEquals(UserStatus.INACTIVE,fanTest1.getStatus());
        } catch (NoSuchAUserNamedException e) {
            assert (false);
        }



    };

    /**
     * remove user from the system
     * 8.2.1 - uc 25
     */
    @Test
    public void removeUser() throws UserNameAlreadyExistException {
        fanTest=sTest.createNewFan(sMangerTest,10,"roni","a","debiky");
        fanTest1=sTest.createNewFan(sMangerTest,10,"roni","a","debiqy");
        try {
            sTest.removeUser(sMangerTest,fanTest);
            assertEquals(UserStatus.REMOVED,fanTest.getStatus());
        } catch (NoSuchAUserNamedException e) {
            assert (false);
        }
        try {//username is not in the system
            sTest.removeUser(sMangerTest,new Fan(123,"s","s","cohen"));
        } catch (NoSuchAUserNamedException e) {
            assert (true);
        }
    }

    /**
     * show the report of a fan
     * 8.3.1 uc-27
     * @throws UserNameAlreadyExistException
     */
    @Test
    public void showReport() throws UserNameAlreadyExistException {
        fanTest2=sTest.createNewFan(sMangerTest,10,"roni","a","debiky123");
        Report r=fanTest2.submitReport("CoronaTime");
        assert (sTest.showReport(fanTest2.getMyReports().get(r.getId())).contains("CoronaTime"));
    };

    /**
     * answer the report of a fan
     * 8.3.2 uc-27
     * @throws UserNameAlreadyExistException
     */
    @Test
    public void answerReport() throws UserNameAlreadyExistException {
        fanTest3=sTest.createNewFan(sMangerTest,10,"roni","a","debiky1233");
        Report r=fanTest3.submitReport("CoronaTime");
        sTest.answerReport(sMangerTest,r,"goodVibesOnly");
        assert (sTest.showReport(fanTest3.getMyReports().get(r.getId())).contains("Answer: goodVibesOnly"));
    }

    /**
     * system manager introduce by the Log file
     * 8.4.1 uc-28
     * @throws UserNameAlreadyExistException
     */
    @Test
    public void showLog(){
        try {
            assert(sTest.showLog(sMangerTest).contains("Log introduced by the System Manager. id:"+sMangerTest.getId()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * create newPlayer Test

     */
    @Test
    public void createNewPlayer(){
        try {
            sTest.createNewPlayer(sMangerTest,123,"maorMel","11","meli",null,"for",10,20);
            sTest.createNewPlayer(sMangerTest,123,"maorMel","11","meli",null,"for",10,20);
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        assertEquals("meli",Controller.getInstance().getAllPlayers().get(0).getUserName());
    }
    /**
     * create new Coach Test

     */
    @Test
    public void createNewCoach(){
        try{
        sTest.createNewCoach(sMangerTest,123,"maorMel","11","melik",null,"for",10,20);
        sTest.createNewCoach(sMangerTest,123,"maorMel","11","melik",null,"for",10,20);
    } catch (UserNameAlreadyExistException e) {
        assert(true);
    }
    assertEquals("melik",Controller.getInstance().getAllCoach().get(0).getUserName());

    }
    @Test
    public void createNewFan(){
        boolean check=false;
        try{
            sTest.createNewFan(sMangerTest,123,"maorMel","11","melik2");
            sTest.createNewFan(sMangerTest,123,"maorMel","11","melik2");
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        for(Fan f:Controller.getInstance().getAllFan()){
            if(f.getUserName().equals("melik2")){
                check=true;
                break;

            }
        }
        assert (check);
    }

    @Test
    public void createNewReferee(){

        boolean check=false;
        try{
            sTest.createNewReferee(sMangerTest,123,"maorMel","11","melik21", RefereeType.MainReferee);
            sTest.createNewFan(sMangerTest,123,"maorMel","11","melik2");
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        for(Referee f:Controller.getInstance().getAllReferee()){
            if(f.getUserName().equals("melik21")){
                check=true;
                break;

            }
        }
        assert (check);
    }

    @Test
    public void createNewTeamManager(){

        boolean check=false;
        try{
            sTest.createNewTeamManager(sMangerTest,123,"maorMel","11","meliki21",1,1);
            sTest.createNewTeamManager(sMangerTest,123,"maorMel","11","meliki21",1,1);
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        for(TeamManager f:Controller.getInstance().getAllTeamManager()){
            if(f.getUserName().equals("meliki21")){
                check=true;
                break;

            }
        }
        assert (check);
    }
    @Test
    public void createNewTeamOwner(){

        boolean check=false;
        try{
            sTest.createNewTeamOwner(sMangerTest,123,"maorMel","11","meliki212",1,1);
            sTest.createNewTeamOwner(sMangerTest,123,"maorMel","11","meliki212",1,1);
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        for(TeamOwner f:Controller.getInstance().getAllTeamOwner()){
            if(f.getUserName().equals("meliki212")){
                check=true;
                break;

            }
        }
        assert (check);

    }
    @Test
    public void createNewSystemManager(){

        boolean check=false;
        try{
            sTest.createNewSystemManager(sMangerTest,123,"maorMel","11","sys");
            sTest.createNewSystemManager(sMangerTest,123,"maorMel","11","sys");
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        for(SystemManager f:Controller.getInstance().getAllSystemManager()){
            if(f.getUserName().equals("sys")){
                check=true;
                break;

            }
        }
        assert (check);




    }



}

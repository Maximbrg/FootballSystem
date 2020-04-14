
import System.Controller;
import System.Enum.TeamStatus;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.Team.Team;
import System.Report;
import System.Users.Fan;
import System.Users.SystemManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class SystemManagerTest {

    SystemManager systemMTest;
    Team tTest;
    Report reportTest;


    @Before
    public void init(){
        systemMTest=new SystemManager(111,"Sys","123","bestSystemManager");
        tTest=new Team("HBS",null);
         reportTest=new Report(new Fan(1234,"Avi","abc1234","theBigAvi"),"You are the best SystemManger in the world");

    }

    /**
     * close team and set status to "PermantlyClose"
     */
    @Test
    public void closeTeamTest(){
        systemMTest.closeTeam(tTest);
        assertEquals(TeamStatus.PermantlyClose,tTest.getTeamStatus());
    }

    @Test
    public void addReportTest(){
        systemMTest.addReport(reportTest);
        assertEquals(reportTest.getId(),systemMTest.getOpenReports().get(reportTest.getId()).getId());
    }


    @Test
    public void answerTheReportTest(){
        systemMTest.answerTheReport(reportTest,"You are the best report EVER!");
        assertEquals("You are the best report EVER!",reportTest.getAnswer());
    }

    @Test
    public void createTeamTest(){
        systemMTest.createTeam("Hap123",null);
        boolean ans=false;
        for(Team t:Controller.getInstance().getAllTeams()){
            if(t.getName().equals("Hap123")){
                assert(true);
            }
        }
    }
    @Test
    public void createNewPlayerTest(){
        try {
            systemMTest.createNewPlayer(10,"eilam","11","eilami",null,"",12,12);
            systemMTest.createNewPlayer(10,"eilam","11","eilami",null,"",12,12);
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        assertEquals(10,Controller.getInstance().getUsers().get("eilami").getId());
    }

    @Test
    public void createNewCoachTest(){
        try {
            systemMTest.createNewCoach(10,"eilam","11","eilami123",null,"",12,12);
            systemMTest.createNewCoach(10,"eilam","11","eilami123",null,"",12,12);
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        assertEquals(10,Controller.getInstance().getUsers().get("eilami123").getId());

    }

    @Test
    public void createNewFanTest(){
        try {
            systemMTest.createNewFan(10,"eilam","11","eilami123k");
            systemMTest.createNewFan(10,"eilam","11","eilami123k");
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        assertEquals(10,Controller.getInstance().getUsers().get("eilami123k").getId());

    }


    @Test
    public void createNewTeamManagerTest(){
        try {
            systemMTest.createNewTeamManager(10,"eilam","11","eilami123ke",23,23);
            systemMTest.createNewTeamManager(10,"eilam","11","eilami123ke",23,23);
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        assertEquals(10,Controller.getInstance().getUsers().get("eilami123ke").getId());

    }


    @Test
    public void createNewTeamOwnerTest(){
        try {
            systemMTest.createNewTeamOwner(10,"eilam","11","eilami123ker",23);
            systemMTest.createNewTeamOwner(10,"eilam","11","eilami123ker",23);
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        assertEquals(10,Controller.getInstance().getUsers().get("eilami123ker").getId());

    }

    @Test
    public void createNewSystemManagerTest(){
        try {
            systemMTest.createNewSystemManager(10,"eilam","11","eilami123kere");
            systemMTest.createNewSystemManager(10,"eilam","11","eilami123kere");
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        assertEquals(10,Controller.getInstance().getUsers().get("eilami123kere").getId());

    }



    @Test
    public void removeUserTest(){
        Fan fTest=new Fan(1234,"Avi","abc1234","theBigAvi123");
        Controller.getInstance().addUser(fTest.getUserName(),fTest);
        SystemManager systemMTest1=new SystemManager(111,"Sys","123","bestSystemManager");

        //user not found , username not exist in the system
        try {
            systemMTest1.removeUser("theBigAvi1234");
        } catch (Exception e) {
            assert(true);
        }
        //user found
        try {
            systemMTest1.removeUser("theBigAvi123");
            assertNull(Controller.getInstance().getUsers().get("theBigAvi123"));
        } catch (NoSuchAUserNamedException e) {
            assert(false);
        }
    }
    @Test
    public void restartRemovedUserTest(){
        Fan fTest=new Fan(1234,"Avi","abc1234","theBigAvi123");
        Controller.getInstance().addUser(fTest.getUserName(),fTest);
        //not found user in this userName
        try {
            Controller.getInstance().removeUser(fTest.getUserName()+"1");
        } catch (NoSuchAUserNamedException e) {
            assert(true);
        }
        //legal and exist user name
        try {
            Controller.getInstance().removeUser(fTest.getUserName());
            Controller.getInstance().restartRemvoeUser(fTest.getUserName());
            assertNull(Controller.getInstance().getRemovedUsers().get(fTest.getUserName()));
        } catch (NoSuchAUserNamedException e) {
            assert(false);
        }
    }

}

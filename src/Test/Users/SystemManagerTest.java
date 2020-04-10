

import System.Enum.TeamStatus;
import System.Controller;
import System.Exeptions.NoSuchAUserNamedException;
import System.FootballObjects.Team.Team;
import System.Users.Fan;
import System.Users.SystemManager;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import System.Report;
import java.awt.*;
import java.util.LinkedList;



import java.awt.*;

public class SystemManagerTest {

    SystemManager systemMTest;
    Team tTest;
    Report reportTest;


    @Before
    public void init(){
        systemMTest=new SystemManager(111,"Sys","123","bestSystemManager");
        tTest=new Team("HBS", TeamStatus.Active,null,new LinkedList<>(),null,null,null,100,2000,null);
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
        systemMTest.createTeam("Hap123",TeamStatus.Active,null,null,null,null,null,100,2000,null);
        assertEquals("Hap123",Controller.getInstance().getAllTeams().get(0).getName());
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

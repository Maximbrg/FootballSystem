import System.Enum.TeamStatus;
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

public class SystemManagerStub {

    SystemManager systemMTest;
    Team tTest;
    Report reportTest;


    @Before
    public void init(){
        systemMTest=SystemManager.getInstance();
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
        assertEquals(reportTest.getId(),systemMTest.getReportsBox().get(reportTest.getId()).getId());
    }


    @Test
    public void answerTheReportTest(){
        systemMTest.answerTheReport(reportTest,"You are the best report EVER!");
        assertEquals("You are the best report EVER!",reportTest.getAnswer());
    }

}

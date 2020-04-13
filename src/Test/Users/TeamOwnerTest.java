import System.Enum.TeamStatus;
import System.Exeptions.StillNoAppointedOwner;
import System.FootballObjects.Field;
import System.FootballObjects.Team.Team;
import System.Users.TeamOwner;
import org.junit.Before;
import org.junit.Test;
import  System.FinancialReport;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamOwnerTest {

    TeamOwner owner1,owner2,owner3,owner4;
    Team team1,team2;
    List<TeamOwner> teamOwnersList;
    HashMap<Team,List<TeamOwner>> teamOwnersWhichIappointed;
    FinancialReport fReport;
    Field field;

    @Before
    public void init(){

        teamOwnersWhichIappointed=new HashMap<>();
        owner1=new TeamOwner(123,"dana","1254","danosh",100);
        List owner1ListOfTeams=owner1.getTeamList();
        owner1ListOfTeams.add(team1);
        owner1.setTeamList(owner1ListOfTeams);
        owner2=new TeamOwner(123,"harel","4542","harelush",200);
        owner3=new TeamOwner(123,"shiran","4542","shiran",200);
        owner4=new TeamOwner(123,"hen","4542","hen",200);

        team1=new Team("Hapoel Beer Sheva",owner1);
        team2=new Team("Hapoel Beer Sheva",owner2);

        teamOwnersList=new LinkedList<>();
        teamOwnersList.add(owner2);

        field=new Field(123,"tedi",15000,1000);
        team1.setField(field);
    }

    @Test
    public void addTeamOwnerTest(){
        owner1.addTeamOwner(team1,owner2);
        owner1.addTeamOwner(team1,owner3);
        assertTrue(team1.getTeamOwnersWhichappointedByAteamOwner(owner1).contains(owner2));
        assertTrue(owner2.getTeamList().contains(team1));
        assertTrue(team1.getTeamOwnersWhichappointedByAteamOwner(owner1).contains(owner3));
        assertTrue(owner3.getTeamList().contains(team1));

    }//---UC 18

    @Test
    public void removeTeamOwnerTest() throws StillNoAppointedOwner {
        try {
            owner1.removeTeamOwner(team1, owner2);
            assertTrue(team1.getTeamOwnersWhichappointedByAteamOwner(owner1).contains(owner2) == false);
            assertTrue(owner2.getTeamList().contains(team1) == false);
        }catch (StillNoAppointedOwner e){
            assert(true);
        }
        try {
            //owner3 cant remove owner2 because he didnt appointed him
            owner3.removeTeamOwner(team1, owner2);
            assertTrue(team1.getTeamOwnersWhichappointedByAteamOwner(owner1).contains(owner2) == true);
            assertTrue(owner2.getTeamList().contains(team1) == true);
        }catch (StillNoAppointedOwner e){
            assert(true);
        }
    }//---UC 19


    @Test
    public void RestartTeamTest(){
        team2.setTeamStatus(TeamStatus.Active);
        assertTrue(team2.getTeamStatus()==TeamStatus.Active);
    } //UC-23


    @Test
    public void getFinancialReportTest(){
        fReport= owner1.addFinancialReport(team1,new Date());
        assertTrue(fReport!=null);
    }

}

import System.Enum.TeamStatus;
import System.Exeptions.HasTeamAlreadyException;
import System.FootballObjects.Team.Team;
import System.Users.TeamManager;
import System.Users.TeamOwner;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeamManagerTest {
    TeamManager t1;
    Team team1,team2;
    Date d;
    TeamOwner owner1,owner2,owner3;


    @Before
    public void init(){
        owner1=new TeamOwner(123,"dana","1254","danosh",100);
        owner2=new TeamOwner(123,"harel","4542","harelush",200);
        owner3=new TeamOwner(123,"shiran","4542","shiran",200);

        team1=new Team("Hapoel Beer Sheva",owner1);
        team2=new Team("Hapoel Beer Sheva",owner2);
        d=new Date(System.currentTimeMillis());
        t1=new TeamManager(456, "Harel sror", "acb345", "harelush",700,17000);
    }

    @Test
    public void editAssetValueTest() {
        t1.setAssetValue(555);
        assertTrue(t1.getAssetValue()==555);
    }

    @Test
    public void resetMyTeamTest() {
        t1.resetMyTeam();
        assertTrue(t1.getMyTeam()==null);
    }

    @Test
    public void addMyTeamTest() throws HasTeamAlreadyException {
        t1.addMyTeam(team2);
        assertTrue(t1.getMyTeam()==team2);
    }

    @Test
    public void getSalaryTest() {
        assertTrue(t1.getSalary() == 17000);
    }

}

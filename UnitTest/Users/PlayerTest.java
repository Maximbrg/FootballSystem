import System.Enum.TeamStatus;
import System.Exeptions.HasTeamAlreadyException;
import System.Exeptions.PersonalPageAlreadyExist;
import System.FootballObjects.Team.Team;
import System.PersonalPages.PersonalPage;
import System.Users.Player;
import System.Users.TeamOwner;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    Player p1;
    Team team1,team2;
    Date d;
    TeamOwner owner1,owner2;

    @Before
    public void init() throws HasTeamAlreadyException {
        owner1=new TeamOwner(123,"dana","1254","danosh",100);
        owner2=new TeamOwner(123,"harel","1254","danosh",100);

        team1=new Team("Hapoel Beer Sheva",owner1);
        team2=new Team("Hapoel Beer Sheva",owner2);
        d=new Date(System.currentTimeMillis());
        p1=new Player(123,"Ben Saar","abc123","benzi",d,"goalkeeper",0,100);
    }

    @Test
    public void editAssetValueTest() {
        p1.editAssetValue(555);
        assertTrue(p1.getAssetValue()==555);
    }

    @Test
    public void resetMyTeamTest() {
        p1.resetMyTeam();
        assertTrue(p1.getMyTeam()==null);
    }

    @Test
    public void addMyTeamTest() throws HasTeamAlreadyException {
        p1.addMyTeam(team2);
        assertTrue(p1.getMyTeam()==team2);
    }

    @Test
    public void getSalaryTest() {
        assertTrue(p1.getSalary() == 100);
    }


     @Test
     public void createPersonalPageTest() throws PersonalPageAlreadyExist {
        PersonalPage personalPage;
     try {
          personalPage = p1.createPersonalPage();
          assertTrue(personalPage!=null);
     }catch (Exception e){
         System.out.println("Personal Page Already Exist");
     }
     }

}

import System.Enum.TeamStatus;
import System.Exeptions.HasTeamAlreadyException;
import System.Exeptions.PersonalPageAlreadyExist;
import System.FootballObjects.Team.Team;
import System.PersonalPages.PersonalPage;
import System.Users.Coach;
import System.Users.TeamOwner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoachTest {

    Coach c1,c2;
    Team team1;
    PersonalPage newPersonalPage;
    TeamOwner owner1;


    @Before
    public void init(){
        owner1=new TeamOwner(123,"dana","1254","danosh",100);

        c1=new Coach(367, "harel",  "password", "harelush", null , null, 100, 17000);
        c2=new Coach(367, "harel",  "password", "harelush", null , null, 100, 17000);
        team1=new Team("Hapoel Beer Sheva",owner1);
        newPersonalPage= new PersonalPage(c1);
    }

    @Test
    public void editAssetValueTest() {
        c1.setAssetValue(555);
        assertTrue(c1.getAssetValue()==555);
    }

    @Test
    public void resetMyTeamTest() {
        c1.resetMyTeam();
        assertTrue(c1.getMyTeam()==null);
    }

    @Test
    public void addMyTeamTest() throws HasTeamAlreadyException {
        c2.addMyTeam(team1);
        assertTrue(c2.getMyTeam()==team1);
    }

    @Test
    public void getSalaryTest() {
        assertTrue(c2.getSalary() == 17000);
    }

    @Test
    public void createPersonalPageTest() throws PersonalPageAlreadyExist {
        PersonalPage personalPage;
        try {
            personalPage = c1.createPersonalPage();
            assertTrue(personalPage!=null);
        }catch (Exception e){
            System.out.println("Personal Page Already Exist");
        }
    }

}

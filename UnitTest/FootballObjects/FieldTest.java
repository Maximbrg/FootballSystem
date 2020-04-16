import System.Enum.TeamStatus;
import System.FootballObjects.Field;
import System.FootballObjects.Team.Team;
import System.Users.TeamOwner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    Field f1,f2,f3;
    Team team1;
    TeamOwner owner1;

    @Before
    public void init(){
        owner1=new TeamOwner(123,"dana","1254","danosh",100);

        team1=new Team("Hapoel Beer Sheva",owner1);
        f1=new Field(2000, "tedi", 0, 150000);
        f2=new Field(2000, "tedi", 0, 150000);
        f3=new Field(2000, "tedi", 0, 150000);
        //    public Field(int id, String name, int assetValue,int maintCost) {
    }


    @Test
    public void editAssetValueTest() {
        f1.editAssetValue(555);
        assertTrue(f1.getAssetValue()==555);
    }

    @Test
    public void resetMyTeamTest() {
        f1.resetMyTeam();
        assertTrue(f1.getMyTeam()==null);
    }

    @Test
    public void addMyTeamTest() {
        f2.addMyTeam(team1);
        assertTrue(f2.getHomeTeams().contains(team1));
    }

    @Test
    public void getSalaryTest() {
        assertTrue(f1.getSalary()==0);
    }

}

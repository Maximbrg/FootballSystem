import System.Asset.Asset;
import System.Enum.TeamStatus;
import System.Exeptions.HasTeamAlreadyException;
import System.Exeptions.PersonalPageAlreadyExist;
import System.FootballObjects.Team.Team;
import System.PersonalPages.PersonalPage;
import System.Users.Player;
import System.Users.TeamManager;
import System.Users.TeamOwner;
import  org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    List<Asset> assets1,assets2,assets3,assets4;
    List<TeamManager> teamManagersList1,teamManagersList2;
    TeamManager manager1,manager2,manager3;
    HashMap<TeamOwner, List<TeamOwner>> teamOwnersWhichappointed;
    Team team1,team2,team3,team4;
    Date d1,d2;
    Asset playerAsset1,playerAsset2,playerAsset3;
    TeamOwner owner1,owner2,owner3;


    @Before
    public void init() throws HasTeamAlreadyException {
        assets1=new LinkedList<>();
        assets2=new LinkedList<>();
        assets3=new LinkedList<>();
        assets4=new LinkedList<>();

        d1=new Date(System.currentTimeMillis());
        d2=new Date(System.currentTimeMillis());

        playerAsset1=new Player(123,"Ben Saar","abc123","benzi",d1,"goalkeeper",0,100);
        playerAsset2=new Player(123,"Ben Saar","abc123","benzi",d1,"goalkeeper",0,100);
        playerAsset3=new Player(123,"Ben Saar","abc123","benzi",d1,"goalkeeper",0,100);
        assets2.add(playerAsset2);

        manager1=new TeamManager(456, "Harel sror", "acb345", "harelush",700, 500);
        manager2=new TeamManager(456, "Harel sror", "acb345", "harelush",700,0);
        manager2.addMyTeam(team1);
        manager3=new TeamManager(456, "Harel sror", "acb345", "harelush",700,0);
        teamManagersList1=new LinkedList<>();
        teamManagersList2=new LinkedList<>();
        teamManagersList2.add(manager2);

        owner1=new TeamOwner(123,"dana","1254","danosh",100);
        owner2=new TeamOwner(123,"harel","4542","harelush",200);
        owner3=new TeamOwner(123,"shiran","4542","shiran",200);

        team1=new Team("Hapoel Beer Sheva",owner1);
        team2=new Team("Hapoel Tel Aviv",owner2);
        team3=new Team("Hapoel Jerusalem",owner3);
        team4=new Team("Maccabi Tel Aviv",owner1);

        team4.addAsset(playerAsset1);
        team4.addAsset(playerAsset2);
        team4.addAsset(manager1);


    }

    @Test
    public void addAssetTest() throws HasTeamAlreadyException {
        team1.addAsset(playerAsset3);
        assertTrue(team1.getAssets().contains(playerAsset3));
    }

    @Test
    public void removeAsset() {
        team4.removeAsset(playerAsset2);
        assertFalse(team4.getAssets().contains(playerAsset2));

        assertTrue(playerAsset2.getMyTeam()==null);
    }

    @Test
    public void editAssetTest(){
        playerAsset1.editAssetValue(500);
        assertTrue(playerAsset1.getAssetValue()==500);
    }

    @Test
    public void addTeamManagerTest(){
        team1.addTeamManager(manager1);
        assertTrue(team1.getTeamManagersList().contains(manager1));
        assertTrue(manager1.getMyTeam()==team1);
    }

    @Test
    public void removeTeamManager(){
        team2.removeTeamManager(manager2);
        assertTrue(team2.getTeamManagersList().contains(manager2)==false);
        assertTrue(manager1.getMyTeam()!=team1);
    }

    @Test
    public void closeTeamTest(){
        team3.closeTeam();
        assertTrue(team3.getTeamStatus()==TeamStatus.Close);

    }

    @Test
    public void PermanentlyCloseTeamTest(){
        assets4.add(manager3);
        team4.PermanentlyCloseTeam();
        assertTrue(team4.getTeamStatus()==TeamStatus.PermanentlyClose);
        for (Asset a:team4.getAssets()) {
            assertTrue(a.getMyTeam()==null);
        }
    }

    @Test
    public void getSalaryTest(){
       assertTrue(team4.getPaymentSalary()==700);
    }

    @Test
    public void createPersonalPageTest() throws PersonalPageAlreadyExist {
        PersonalPage personalPage;
        try {
            personalPage = team1.createPersonalPage();
            assertTrue(personalPage!=null);
        }catch (Exception e){
            System.out.println("Personal Page Already Exist");
        }
    }
}

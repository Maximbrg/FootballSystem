import ServiceLayer.Exceptions.TeamIsClosedException;
import ServiceLayer.TeamOwnerController;
import System.*;
import System.Exeptions.HasTeamAlreadyException;
import System.FootballObjects.Field;
import System.FootballObjects.Team.Team;
import System.Users.*;
import org.junit.*;
import java.util.Date;
import ServiceLayer.*;
import System.Exeptions.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSuit4 {

    static PlayerController playerControllerController;
    static TeamOwnerController teamOwnerController;

    @Before
    public void init(){
        playerControllerController=PlayerController.getInstance();
        teamOwnerController=TeamOwnerController.getInstance();
    }

    @Test
    public void Test1(){
        TeamOwner tOwner=new TeamOwner(123,"Harel Sror","abc123","harelush",10000);
        Team team1=new Team("Hapoel Beer Sheva",tOwner);
        Team team2=new Team("macabi",tOwner);
        Field field=new Field(2000, "tedi", 0, 150000);
        team1.setField(field);
        team2.setField(field);
        assertTrue(field.getHomeTeams().contains(team1));
        assertTrue(field.getHomeTeams().contains(team2));
    }

    @Test
    public void addingTeamOwnerWhichIsAlsoPlayerTest(){
        Date d=new Date(System.currentTimeMillis());
        TeamOwner tOwner=new TeamOwner(123,"Harel Sror","abc123","harelush",10000);
        tOwner.setSelfPlayer(new Player(123,"Harel Sror","abc123","harelush",d,"goalkeeper",0,100));
        Team team1=new Team("Hapoel Beer Sheva",tOwner);
        try {
            team1.addAsset(tOwner.getSelfPlayer());
        }   catch (HasTeamAlreadyException e){fail();}
        assertEquals(tOwner.getName(),tOwner.getSelfPlayer().getName());
        assertTrue(team1.getAssets().contains(tOwner.getSelfPlayer()));
        assertEquals(tOwner.getSelfPlayer().getMyTeam(),team1);
    }

    @Test
    public void addingTeamOwnerWhichIsAlsoCoachTest(){
        TeamOwner tOwner=new TeamOwner(123,"Harel Sror","abc123","harelush",10000);
        tOwner.setSelfCoach(new Coach(123,"Harel Sror","abc123","harelush","assistant coach","assistant coach",15000,10000));
        Team team1=new Team("Hapoel Beer Sheva",tOwner);
        try {
            team1.addAsset(tOwner.getSelfCoach());
        }   catch (HasTeamAlreadyException e){fail();}
        assertEquals(tOwner.getName(),tOwner.getSelfCoach().getName());
        assertTrue(team1.getAssets().contains(tOwner.getSelfCoach()));
        assertEquals(tOwner.getSelfCoach().getMyTeam(),team1);
    }

    @Test
    public void addingTeamOwnerWhichIsAlsoTeamManagerTest(){
        TeamOwner tOwner=new TeamOwner(123,"Harel Sror","abc123","harelush",10000);
        tOwner.setSelfTeamManager(new TeamManager(123, "Harel Sror", "abc123", "harelush",700,17000));
        Team team1=new Team("Hapoel Beer Sheva",tOwner);
        try {
            team1.addAsset(tOwner.getSelfTeamManager());
        }   catch (HasTeamAlreadyException e){fail();}
        assertEquals(tOwner.getName(),tOwner.getSelfTeamManager().getName());
        assertTrue(team1.getAssets().contains(tOwner.getSelfTeamManager()));
        assertEquals(tOwner.getSelfTeamManager().getMyTeam(),team1);
    }

    @Test
    public void Test5(){
        TeamOwner tOwner=new TeamOwner(123,"Harel Sror","abc123","harelush",10000);
        Team team1=new Team("Hapoel Beer Sheva",tOwner);
        Date d=new Date(System.currentTimeMillis());
        Player p= new Player(123,"Harel Sror","abc123","harelush",d,"goalkeeper",0,100);
        try {
            teamOwnerController.addAssetToTeam(tOwner, team1, p);
            playerControllerController.setDetails(p,124,"Harel Sror","abc123",d,"goalkeeper");
            teamOwnerController.editAssetOfTeam(tOwner,team1,p,500);
            assertEquals(p.getId(),124);
            assertEquals(p.getAssetValue(),500);
        }catch (TeamIsClosedException e){  fail(); }
        catch (NotHisTeamException e){ fail(); }
        catch (HasTeamAlreadyException e){ fail(); }
        catch (NotOwnerOfThisTeamException e){fail();}
    }
}

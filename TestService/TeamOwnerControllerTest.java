import ServiceLayer.Exceptions.TeamIsClosedException;
import ServiceLayer.TeamOwnerController;
import System.Enum.TeamStatus;
import System.Exeptions.AlreadyHasTeamException;
import System.Exeptions.HasTeamAlreadyException;
import System.Exeptions.NotHisTeamException;
import System.FootballObjects.Field;
import System.FootballObjects.Team.Team;
import System.Users.Coach;
import System.Users.Player;
import System.Users.TeamManager;
import System.Users.TeamOwner;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class TeamOwnerControllerTest {
    TeamOwnerController teamOwnerController;
    TeamOwner tOwner1,tOwner2,tOwner3,tOwner4;
    Player player;
    Coach coach;
    TeamManager teamManager;
    Field field;
    Date d;
    Team team1,team2,team3,team4;

    @Before
    public void init() throws HasTeamAlreadyException {

        teamOwnerController = TeamOwnerController.getInstance();
        tOwner1=new TeamOwner(123,"Harel Sror","abc123","harelush",10000);
        tOwner2=new TeamOwner(123,"Maxim ","abc123","maxx",10000);
        tOwner3=new TeamOwner(123,"Shiran ","abc123","shirannn",10000);
        tOwner4=new TeamOwner(123,"Dana ","abc123","danaaa",10000);


        d=new Date(System.currentTimeMillis());
        player=new Player(123,"Ben Saar","abc123","benzi",d,"goalkeeper",0,100);
        coach=new Coach(123,"Hen","abc123","henss","assistant coach","assistant coach",15000,10000);
        teamManager=new TeamManager(456, "Shachar", "acb345", "shachar",700,17000);
        field=new Field(2000, "tedi", 0, 150000);

        team1=new Team("Hapoel Beer Sheva",tOwner1);
        team2=new Team("Hapoel Tel Aviv",tOwner2);
        team2.addAsset(player);
        team2.addAsset(coach);
        team2.addAsset(teamManager);
        team2.addAsset(field);



        //team3=new Team("Hapoel Jerusalem",null);
        //team4=new Team("Maccabi Tel Aviv",null);
    }

    @Test
    public void createFieldTest(){
    Field newField= teamOwnerController.createField(2001,"vasermil");
    assertTrue(newField!=null);
    assertTrue(newField instanceof Field);
    assertTrue(newField.getId()==2001);
    assertTrue(newField.getName()=="vasermil");
    }//Test ID:    #6.1

    //<editor-fold desc="Add asset to team tests - #6.1">
    @Test
    public void addAssetToTeamTest1() throws TeamIsClosedException, NotHisTeamException, HasTeamAlreadyException {
    try {
        teamOwnerController.addAssetToTeam(tOwner1,team1,player);
        assertTrue(team1.getAssets().contains(player));
        assertTrue(player.getMyTeam()==team1);
        assertTrue(tOwner1.getTeamList().contains(team1));
    }catch (Exception e){

         }
    }//Test ID:    #6.1 -- checks if player added successfully to the team

    @Test
    public void addAssetToTeamTest2() throws TeamIsClosedException, NotHisTeamException, HasTeamAlreadyException {
        try {
            teamOwnerController.addAssetToTeam(tOwner1,team1,coach);
            assertTrue(team1.getAssets().contains(coach));
            assertTrue(coach.getMyTeam()==team1);
            assertTrue(tOwner1.getTeamList().contains(team1));
        }catch (Exception e){

        }
    }//Test ID:    #6.1 -- checks if coach added successfully to the team

    @Test
    public void addAssetToTeamTest3() throws TeamIsClosedException, NotHisTeamException, HasTeamAlreadyException{
        try {
            teamOwnerController.addAssetToTeam(tOwner1,team1,teamManager);
            assertTrue(team1.getAssets().contains(teamManager));
            assertTrue(teamManager.getMyTeam()==team1);
            assertTrue(tOwner1.getTeamList().contains(team1));
        }catch (Exception e){

        }
    }//Test ID:    #6.1 -- checks if teamManager added successfully to the team

    @Test
    public void addAssetToTeamTest4() throws TeamIsClosedException, NotHisTeamException, HasTeamAlreadyException {
        try {
            teamOwnerController.addAssetToTeam(tOwner1,team1,field);
            assertTrue(team1.getAssets().contains(field));
            assertTrue(field.getMyTeam()==team1);
            assertTrue(tOwner1.getTeamList().contains(team1));
        }catch (Exception e){

        }
    }//Test ID:    #6.1 -- checks if field added successfully to the team
    //</editor-fold>

    //<editor-fold desc="Remove asset from team tests - #6.1">
    @Test
    public void removeAssetFromTeamTest1(){
        try {
            teamOwnerController.removeAssetFromTeam(tOwner1,team1,player);
            assertTrue(!team1.getAssets().contains(player));
            assertTrue(player.getMyTeam()!=team1);
            assertTrue(!tOwner1.getTeamList().contains(team1));
        }catch (Exception e){

        }
    }//Test ID:    #6.1 -- checks if player removed successfully from the team

    @Test
    public void removeAssetFromTeamTest2(){
        try {
            teamOwnerController.removeAssetFromTeam(tOwner1,team1,coach);
            assertTrue(!team1.getAssets().contains(coach));
            assertTrue(coach.getMyTeam()!=team1);
            assertTrue(!tOwner1.getTeamList().contains(team1));
        }catch (Exception e){

        }
    }//Test ID:    #6.1 -- checks if coach removed successfully from the team

    @Test
    public void removeAssetFromTeamTest3(){
        try {
            teamOwnerController.removeAssetFromTeam(tOwner1,team1,teamManager);
            assertTrue(!team1.getAssets().contains(teamManager));
            assertTrue(teamManager.getMyTeam()!=team1);
            assertTrue(!tOwner1.getTeamList().contains(team1));
        }catch (Exception e){

        }
    }//Test ID:    #6.1 -- checks if team manager removed successfully from the team

    @Test
    public void removeAssetFromTeamTest4(){
        try {
            teamOwnerController.removeAssetFromTeam(tOwner1,team1,field);
            assertTrue(!team1.getAssets().contains(field));
            assertTrue(field.getMyTeam()!=team1);
            assertTrue(!tOwner1.getTeamList().contains(team1));
        }catch (Exception e){

        }
    }//Test ID:    #6.1 -- checks if field removed successfully from the team
    //</editor-fold>

    @Test
    public void editAssetOfTeamTest(){
        try {
            teamOwnerController.editAssetOfTeam(tOwner1,team2,player,1700);
            teamOwnerController.editAssetOfTeam(tOwner1,team2,coach,1700);
            teamOwnerController.editAssetOfTeam(tOwner1,team2,teamManager,1700);
            teamOwnerController.editAssetOfTeam(tOwner1,team2,field,1700);

            assertTrue(player.getAssetValue()==1700);
            assertTrue(coach.getAssetValue()==1700);
            assertTrue(teamManager.getAssetValue()==1700);
            assertTrue(field.getAssetValue()==1700);
        }catch (Exception e){

        }
    }//Test ID:    #6.1 -- checks if assets valu update successfully


    @Test
    public void addTeamOwnerTest() throws TeamIsClosedException, NotHisTeamException, AlreadyHasTeamException {
        try {
            teamOwnerController.addTeamOwner(tOwner1,team1,tOwner2);
            assertTrue(team1.getTeamOwnerListOfThisOwner(tOwner1).contains(tOwner1));
            assertTrue(tOwner2.getTeamList().contains(team1));
        }catch (Exception e){}
    }//Test ID:    #6.2



    @Test
    public void removeTeamOwnerTest(){
        try {
            tOwner1.addTeamOwner(team1,tOwner3);// harel adding shiran as owner
            tOwner1.addTeamOwner(team1,tOwner4);//harel adding dana as owner
            // -- standard check whether the owner can remove other owners he has appointed
            teamOwnerController.removeTeamOwner(tOwner1,tOwner3,team1);
            assertFalse(team1.getAllTeamOwners().contains(tOwner3));
            assertFalse(team1.getTeamOwnerListOfThisOwner(tOwner1).contains(tOwner3));
            assertFalse(tOwner3.getTeamList().contains(team1));
            // -- Edge case - check whether owner which appointed by other owner ,can remove other owners that he didnt appointed
            teamOwnerController.removeTeamOwner(tOwner3,tOwner1,team1);
            assertTrue(tOwner1.getTeamList().contains(team1));
            assertTrue(team1.getAllTeamOwners().contains(tOwner1));

        }catch (Exception e){}
    }//Test ID:    #6.3

    @Test
    public void addTeamMenegarTest(){
    try {
        teamOwnerController.addTeamManager(tOwner1,team1,teamManager);
        assertTrue(team1.getTeamManagersList().contains(teamManager));
        assertTrue(teamManager.getMyTeam()==team1);
    }catch (Exception e){}
    }//Test ID:    #6.4

    @Test
    public void removeTeamMenegarTest(){
        //removeTeamMenegar(TeamOwner teamOwner, Team team, TeamManager teamManager)
        try {
            teamOwnerController.addTeamOwner(tOwner1,team1,tOwner2);

            //in case that the owner which appoint the manager is the same who's remove him
            teamOwnerController.addTeamManager(tOwner1,team1,teamManager);
            teamOwnerController.removeTeamMenegar(tOwner1,team1,teamManager);
            assertFalse(team1.getTeamManagersList().contains(teamManager));
            assertNull(teamManager.getMyTeam());
            assertNull(teamManager.getMyTeamOwner());

            //in case that owner which didnt appoint the manager try to remove him
            teamOwnerController.addTeamManager(tOwner1,team1,teamManager);
            teamOwnerController.removeTeamMenegar(tOwner3,team1,teamManager);
            assertTrue(team1.getTeamManagersList().contains(teamManager));
            assertTrue(teamManager.getMyTeam()==team1);
        }catch (Exception e){}

    }//Test ID:    #6.5

    @Test
    public void closeTeamTest(){
        try {
            teamOwnerController.addTeamManager(tOwner1,team1,teamManager);
            teamOwnerController.addTeamOwner(tOwner1,team1,tOwner2);

            teamOwnerController.closeTeam(tOwner1,team1);
            assertTrue(team1.getTeamStatus()== TeamStatus.Close);
            assertNull(teamManager.getMyTeam());
            assertFalse(tOwner2.getTeamList().contains(team1));
        }catch (Exception e){}

    }//Test ID:    #6.6

    @Test
    public void openTeamTest(){
        //openTeam(TeamOwner teamOwner, Team team)
    try {
        //precondition for the test :
        teamOwnerController.addTeamManager(tOwner1,team1,teamManager);
        teamOwnerController.addTeamOwner(tOwner1,team1,tOwner2);
        teamOwnerController.closeTeam(tOwner1,team1);
        //--------------------------
        teamOwnerController.openTeam(tOwner1,team1);
        assertTrue(team1.getTeamStatus()== TeamStatus.Active);
        assertTrue(teamManager.getMyTeam()==team1);
        assertTrue(tOwner2.getTeamList().contains(team1));
    }catch (Exception e){}
    }//Test ID:    #6.6

    @Test
    public void sumbitReportTest(){
        //sumbitReport(TeamOwner teamOwner, Team team)

    }//Test ID:    #6.7


}

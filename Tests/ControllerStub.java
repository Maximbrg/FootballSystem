import System.Enum.RefereeType;
import System.Enum.TeamStatus;
import System.Enum.UserStatus;
import System.Exeptions.UserNameAlreadyExistException;
import System.Exeptions.noSuchAUserNamedException;
import System.Exeptions.wrongPasswordException;
import System.FootballObjects.Season;
import System.FootballObjects.Team.Team;
import System.Users.*;
import org.junit.Before;
import org.junit.Test;
import System.*;
import static org.junit.jupiter.api.Assertions.*;


import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class ControllerStub {
    Controller cTest;
//    Fan fanLogOut= new Fan(201,"shmuel","h124","shmuelSG");



    @Before
    public void init() throws UserNameAlreadyExistException {
        cTest= Controller.getInstance();
        //Controller.getInstance().signUp(205,"Itzik","h123","ItzikHaGadol8989");
        //fTest= new Fan(201,"Itzik","h124","ItzikHaGadol");
        //cTest.signUp(10,"Hen Debi","123123","xyl");

    }
    @Test
    public void initSystemTest(){

    }

    @Test
    public void loginTest() throws UserNameAlreadyExistException {
        Controller.getInstance().signUp(205,"Itzik","h123","ItzikHaGadol");
        //try to login with wrong password
        try {
            cTest.login("ItzikHaGadol","h124");
        } catch (wrongPasswordException e) {
            assert(true);
        } catch (noSuchAUserNamedException e) {
        }

        //login with wrong username
        try {
            cTest.login("ItzikHaGadol123","h123");
        } catch (wrongPasswordException e) {
        } catch (noSuchAUserNamedException e) {
            assert(true);
        }
        //successful login
        try {
            cTest.login("ItzikHaGadol","h123");
        } catch (wrongPasswordException e) {
            assert(false);
        } catch (noSuchAUserNamedException e) {
            assert(false);
        }
        //get the right user
        Fan f=null;
        try {
            f=(Fan)cTest.login("ItzikHaGadol","h123");
        } catch (wrongPasswordException e) {
            e.printStackTrace();
        } catch (noSuchAUserNamedException e) {
            e.printStackTrace();
        }
        assertEquals(f.getId(),cTest.getUsers().get("ItzikHaGadol").getId());
    }
    @Test
    public void logoutTest(){
        Fan fanLogOut=null;
        try {
             fanLogOut =(Fan) Controller.getInstance().signUp(205,"shmulik","h124","ShmuelSG");

            cTest.login("ShmuelSG","h124");
        } catch (wrongPasswordException e) {
            assert(false);
        } catch (noSuchAUserNamedException e) {
            assert(false);
        } catch (UserNameAlreadyExistException e) {
            assert (false);
        }

        cTest.logOut(fanLogOut);
        assertEquals("INACTIVE",cTest.getUsers().get("ShmuelSG").getUserStatus().toString());

    }


    @Test
    public void signUpTest(){
        try {
            //username is already in use
            cTest.signUp(10,"Hen Debi","123123","xyl");
            //legal username
            cTest.signUp(10,"Idan Debi","123123","idani");
            assertEquals(10,cTest.getUsers().get("idani").getId());
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }

    }
    @Test
    public void getAllRefereeTest(){
        Referee rTest1=new Referee("Hen", RefereeType.MainReferee,204,"abc","KillerReferee");
        Referee rTest0=new Referee("Max", RefereeType.AssistantReferee,205,"abc","KillerReferee123");
        Fan fTest0= new Fan(201,"Itzik","h124","ItzikHaGadol1");
        Fan fTest1= new Fan(203,"avi","h124","aviHaGadol1");
        int count=0;

        cTest.addUser(rTest0.getUserName(),rTest0);
        cTest.addUser(rTest1.getUserName(),rTest1);

        cTest.addUser(fTest1.getUserName(),fTest1);
        cTest.addUser(fTest0.getUserName(),fTest0);
         for(Referee r:cTest.getAllReferee()){
             count++;
             assertEquals("Referee",r.getClass().toString().substring(r.getClass().toString().length()-7));
         }
        assertEquals(2,count);
    }
    @Test
    public void getAllCoachTest(){
        Couch couchTest= new Couch(208,"MaorMelichson","aa","maori1","maorit","CHampionLEague","Forward",null,150,null,10);
        cTest.addUser(couchTest.getUserName(),couchTest);
        int count =0;
        for(Couch r:cTest.getAllCoach()){
            count++;
            assertEquals("Couch",r.getClass().toString().substring(r.getClass().toString().length()-5));
        }
        assertEquals(1,cTest.getAllCoach().size());
    }
    @Test
    public void getAllPlayersTest(){
        Player playerTest= new Player(208,"MaorMelichson","aa","maori1","maorit",new Date(System.currentTimeMillis()),"Forward",null,150,null,10);
        Player playerTest1= new Player(207,"ElyanivBarda","aa","maorit54","maorit",new Date(System.currentTimeMillis()),"Forward",null,150,null,10);
        Referee rTest1=new Referee("Hen", RefereeType.MainReferee,204,"abc","KillerReferee123");
        Referee rTest0=new Referee("Max", RefereeType.AssistantReferee,205,"abc","KillerReferee1234");
        Fan fTest0= new Fan(201,"Itzik","h124","ItzikHaGadol54");
        Fan fTest1= new Fan(203,"avi","h124","aviHaGadol34");
        int count=0;

        cTest.addUser(rTest0.getUserName(),rTest0);
        cTest.addUser(rTest1.getUserName(),rTest1);

        cTest.addUser(fTest1.getUserName(),fTest1);
        cTest.addUser(fTest0.getUserName(),fTest0);
        cTest.addUser(playerTest.getUserName(),playerTest);
        cTest.addUser(playerTest1.getUserName(),playerTest1);

        for(Player r:cTest.getAllPlayers()){
            count++;
            assertEquals("Player",r.getClass().toString().substring(r.getClass().toString().length()-6));
        }
        assertEquals(3,count);
        //the third one is from the addUserTest() that locate below
        //|
        //|
        //V
    }

    @Test
    public void addUserTest(){
        Player playerTest= new Player(208,"MaorMelichson","aa","shoshana","maorit",new Date(System.currentTimeMillis()),"Forward",null,150,null,10);
        cTest.addUser(playerTest.getUserName(),playerTest);
        assertEquals(playerTest.getId(),cTest.getUsers().get("shoshana").getId());
    }
    @Test
    public void getSeasonTest(){
        cTest.addSeason(new Season(2019,null));
        assertEquals(2019,cTest.getSeason("2019").getYear());
    }


}

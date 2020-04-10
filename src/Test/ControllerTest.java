
import System.Enum.RefereeType;
import System.Enum.TeamStatus;
import System.Enum.UserStatus;
import System.Exeptions.UserNameAlreadyExistException;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.WrongPasswordException;
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

public class ControllerTest {
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
        } catch (WrongPasswordException e) {
            assert(true);
        } catch (NoSuchAUserNamedException e) {
        }

        //login with wrong username
        try {
            cTest.login("ItzikHaGadol123","h123");
        } catch (WrongPasswordException e) {
        } catch (NoSuchAUserNamedException e) {
            assert(true);
        }
        //successful login
        try {
            cTest.login("ItzikHaGadol","h123");
        } catch (WrongPasswordException e) {
            assert(false);
        } catch (NoSuchAUserNamedException e) {
            assert(false);
        }
        //get the right user
        Fan f=null;
        try {
            f=(Fan)cTest.login("ItzikHaGadol","h123");
        } catch (WrongPasswordException e) {
            e.printStackTrace();
        } catch (NoSuchAUserNamedException e) {
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
        } catch (WrongPasswordException e) {
            assert(false);
        } catch (NoSuchAUserNamedException e) {
            assert(false);
        } catch (UserNameAlreadyExistException e) {
            assert (false);
        }

        cTest.logOut(fanLogOut);
        assertEquals("INACTIVE",cTest.getUsers().get("ShmuelSG").getStatus().toString());

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
        Coach couchTest= new Coach(208,"MaorMelichson","aa","maori1","maorit","cc",10,10);
        cTest.addUser(couchTest.getUserName(),couchTest);
        int count =0;
        for(Coach r:cTest.getAllCoach()){
            count++;
            assertEquals("Coach",r.getClass().toString().substring(r.getClass().toString().length()-5));
        }
        assertEquals(1,cTest.getAllCoach().size());
    }
    @Test
    public void getAllPlayersTest(){
        Team t=new Team("Hap",TeamStatus.Active,null,null,null,null,null,100,2000,null);
        Player playerTest= new Player(208,"MaorMelichson","aa","maori1",new Date(System.currentTimeMillis()),"Forward",100,10);
        Player playerTest1= new Player(207,"ElyanivBarda","aa","maorit54",new Date(System.currentTimeMillis()),"Forward",10,100);
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
        assertEquals(2,count);
        //the third one is from the addUserTest() that locate below
        //|
        //|
        //V
    }

    @Test
    public void addUserTest(){
        Team t=new Team("Hap",TeamStatus.Active,null,null,null,null,null,100,2000,null);
        Player playerTest= new Player(208,"MaorMelichson","aa","maori1",new Date(System.currentTimeMillis()),"Forward",100,10);
        cTest.addUser(playerTest.getUserName(),playerTest);
        assertEquals(playerTest.getId(),cTest.getUsers().get("maori1").getId());
    }
    @Test
    public void getSeasonTest(){
        Season s= new Season(2019);
        cTest.addSeason(s);
        assertEquals("2019",cTest.getSeason("2019").getYear());
    }

    @Test
    public void removeUserTest(){
        Fan fTest= new Fan(201,"Itzik","h124","ItzikHaGadol");
        cTest.addUser(fTest.getUserName(),fTest);
        //remove user that not exist
        try {
            cTest.removeUser(fTest.getUserName()+"blabla");
        } catch (NoSuchAUserNamedException e) {
            assert(true);
        }
        //remove exist user
        try {
            cTest.removeUser(fTest.getUserName());
            assertNull(cTest.getUsers().get(fTest.getUserName()));
            assertNotNull(cTest.getRemovedUsers().get(fTest.getUserName()));
        } catch (NoSuchAUserNamedException e) {
            assert(false);
        }

    }
    @Test
    public void restartRemvoedUserTest(){
        Fan fTest= new Fan(201,"Itzik","h124","ItzikHaGadol");

        cTest.addUser(fTest.getUserName(),fTest);
        //user name not found
        try {
            cTest.removeUser(fTest.getUserName() +"blabla");
        } catch (NoSuchAUserNamedException e) {
            assert(true);
        }
        try {
            cTest.removeUser(fTest.getUserName());
            assertNotNull(cTest.getRemovedUsers().get(fTest.getUserName()));
            assertNull(cTest.getUsers().get(fTest.getUserName()));
        } catch (NoSuchAUserNamedException e) {
            assert(false);
        }


    }

}

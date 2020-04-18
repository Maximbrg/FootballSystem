import ServiceLayer.FanController;
import ServiceLayer.GuestController;
import ServiceLayer.PlayerController;
import ServiceLayer.SystemManagerController;
import System.Controller;
import System.Enum.RefereeType;
import System.Enum.SearchCategory;
import System.Enum.UserStatus;
import System.Exeptions.AlreadyHasPageException;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.UserNameAlreadyExistException;
import System.Exeptions.WrongPasswordException;
import System.FootballObjects.Team.Team;
import System.IShowable;
import System.PersonalPages.PersonalPage;
import System.Searcher.ASearchStrategy;
import System.Searcher.SearchByCategory;
import System.Users.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuestFanControllerTest {

    @Test
    public void signUpTest1()  {
        String name = "Maxim";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW1022";
        GuestController guestController = new GuestController();
        try {
            Fan newFan = guestController.signUp(id, name, password, username);
            Controller controller = Controller.getInstance();
            HashMap<String, User> dic = controller.getUsers();
            Fan sameFan = (Fan) (dic.get("MaxFTW1022"));
            assertEquals(username,sameFan.getUserName());
        }
        catch (Exception e){
            fail();
        }
    } //Test ID:    #2.2.1 -- checks if signup in is working

    @Test
    public void signUpTest2()  { //Test ID: #1.2
        String name = "Maxim";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW55";
        GuestController gustController = new GuestController();
        try {
            Fan newFan = gustController.signUp(id, name, password, username);
            Fan sameFan = gustController.signUp(id, name, password, username);
        }
        catch (UserNameAlreadyExistException e){
            assert (true);
        }
    } //Test ID:    #2.2.2 -- checks if can't signup with two same userName

    @Test
    public void loginTest1(){
        String name = "Maxim";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW44";
        GuestController gustController = new GuestController();
        try {
           gustController.signUp(id, name, password, username);
        }
        catch (Exception e){
            fail();
        }
        try {
            User user = gustController.login(username, password);
            assertEquals(UserStatus.ACTIVE,Controller.getInstance().getUsers().get(username).getStatus());
        }
        catch (Exception e){
            fail();
        }
    } //Test ID:    #2.3.1 -- checks if can login after signup

    @Test
    public void loginTest2(){
        String name = "Maxim1";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW4433";
        GuestController gustController = new GuestController();
        try {
            gustController.signUp(id, name, password, username);
        }
        catch (Exception e){
            fail();
        }
        try {
            User user = gustController.login("****", password);
        }
        catch (NoSuchAUserNamedException e){
            assert (true);
        }
        catch (WrongPasswordException e){
            fail();
        }
    } //Test ID:    #2.3.2 -- checks if can login with wrong user name

    @Test
    public void loginTest3(){
        String name = "Maxim";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW4356";
        GuestController gustController = new GuestController();
        try {
            gustController.signUp(id, name, password, username);
        }
        catch (Exception e){
            fail();
        }
        try {
            User user = gustController.login(username, "12");
        }
        catch (NoSuchAUserNamedException e){
            fail();
        }
        catch (WrongPasswordException e){
            assert (true);
        }
    } //Test ID:    #2.3.3 -- checks if can login with wrong password

    /**
     * log out from system
     * reminder : guest cannot log out .
     */
    @Test
    public void logOutTest1(){
        String name = "Maxim";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW44785";
        GuestController gustController = new GuestController();
        Fan f=null;
        try {
             f = (Fan)gustController.signUp(id, name, password, username);
            assertEquals(UserStatus.INACTIVE.toString(),f.getStatus().toString());
        }
        catch (Exception e){
            fail();
        }
        try {
            gustController.logOut(f);
        }
        catch (UnsupportedOperationException u){
            assert (true);
        }
    } //Test ID:    #3.1.1

    @Test
    public void getInfoToShow() throws UserNameAlreadyExistException{
        FootballAssociation assosiation = new FootballAssociation(205695612,"Max","123","MaxFTW");
        Team t=new Team("Hap",null);
        Controller.getInstance().addTeam(t);
        assosiation.addNewReferee("Invoker", RefereeType.MAIN,1,"123","Invoker");
        GuestController gustController = new GuestController();
        List<IShowable> iShowables =  gustController.getInfoToShow("Referee");
        for(IShowable is:iShowables){
            String string = is.getName()+is.getType();
            if(string.equals("InvokerReferee")){
                assert(true);
                break;
            }
        }
        iShowables =  gustController.getInfoToShow("Team");
        for(IShowable is:iShowables){
            String string = is.getName()+is.getType();
            if(string.equals("HapTeam")){
                assert(true);
                break;
            }
        }
    } //Test ID:    #2.4.2

    @Test
    public void searchShowablesTest(){
        GuestController gustController = new GuestController();
        Player p = new Player(12,"hende","abcd","xyl",null,"",100,150);
        Controller.getInstance().addUser(p.getUserName(),p);
        ASearchStrategy a=new SearchByCategory();
        Fan f=new Fan(123,"aviki","","aviviki");
        List<IShowable> list =gustController.searchShowables(f,a,SearchCategory.PLAYER,"xy");
        assertEquals("hende",list.get(0).getName());
        assertEquals("xy",f.getSearchHistory().get(0));
    } //Test ID:    #2.5.1

    /**
     * filter the search by category
     */
    @Test
    public void filterResultsTest(){
        GuestController gustController = new GuestController();
        Player p = new Player(12,"hende","abcd","poco",null,"",100,150);
        Coach p2 = new Coach(12,"hendebi","abcd","shoco","","",10,20);
        Controller.getInstance().addUser(p.getUserName(),p);
        Controller.getInstance().addUser(p2.getUserName(),p2);
        ASearchStrategy a=new SearchByCategory();
        List<IShowable> listTest=new ArrayList<IShowable>();
        listTest.add(p);
        listTest.add(p2);
        List<IShowable> list =gustController.filterResults(a,SearchCategory.PLAYER,listTest);
        for(IShowable l:list){
            if(l.getType()!="Player"){
                assert(false);
            }
        }

    } //Test ID:    #2.4.1

    @Test
    public void submitReport() throws UserNameAlreadyExistException { //Test ID:    3.4
        Fan fan = Controller.getInstance().signUp(1,"a","a","b");
        fan.submitReport("report");
        assertTrue(fan.getMyReports().size()==1);
    } //Test ID:    #3.4.1

    @Test
    public void unFollowPersonalPage() {
        SystemManager systemManager=new SystemManager(1,"shiran","111","shiran");
        try {
            Fan fan= SystemManagerController.getInstance().createNewFan(systemManager,4,"fan","111","fan1");
            Player player= SystemManagerController.getInstance().createNewPlayer(systemManager,3,"plyer","111","player1",null,"",1,1);
            PlayerController.getInstance().createPersonalPage(player);
            PersonalPage personalPage=player.getPersonalPage();
            FanController.getInstance().followPersonalPage(fan,personalPage);
            List<PersonalPage> personalPagesList=FanController.getInstance().getAllpersonalPages();
            FanController.getInstance().unfollowPersonalPage(fan,personalPagesList.get(0));
            //fan was removed from personal page followers
            assertEquals(personalPagesList.get(0).getFollowers().size(),0);
            //personal page was removed from fan
            assertEquals(fan.getFollowPages().size(),0);
        } catch (UserNameAlreadyExistException e) {
            e.printStackTrace();
        } catch (AlreadyHasPageException e) {
            e.printStackTrace();
        }
    } //Test ID :  3.2.1

    @Test
    public void followPersonalPage() {
        SystemManager systemManager=new SystemManager(1,"shiran","111","shiran");
        try {
            Fan fan= SystemManagerController.getInstance().createNewFan(systemManager,4,"fan","111","fan");
            Player player= SystemManagerController.getInstance().createNewPlayer(systemManager,3,"plyer","111","player",null,"",1,1);
            PlayerController.getInstance().createPersonalPage(player);
            PersonalPage personalPage=player.getPersonalPage();
            FanController.getInstance().followPersonalPage(fan,personalPage);
            //personal page was added to fan
            for(PersonalPage page: fan.getFollowPages()){
                assertEquals(page,personalPage);
            }
            //player was added to personal page
            assertEquals(personalPage.getPageAvailable(),player);
            //personal was page added to player
            assertEquals(personalPage,player.getPersonalPage());
            //fan was added to personal page
            for (Fan fan1:personalPage.getFollowers()){
                assertEquals(fan,fan1);
            }
        } catch (UserNameAlreadyExistException e) {
            e.printStackTrace();
        } catch (AlreadyHasPageException e) {
            e.printStackTrace();
        }
    } //Test ID :  3.2.2
}
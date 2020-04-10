import ServiceLayer.GustController;
import System.Enum.RefereeType;
import System.Exeptions.UserNameAlreadyExistException;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.WrongPasswordException;
import System.Users.Fan;
import System.Users.FootballAssosiation;
import System.Users.User;
import org.junit.*;
import System.*;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GustControllerTest {

    @Test
    public void signupTest1()  {
        String name = "Maxim";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW";
        GustController gustController = new GustController();
        try {
            Fan newFan = gustController.signup(id, name, password, username);
            Controller controller = Controller.getInstance();
            HashMap<String, User> dic = controller.getUsers();
            Fan sameFan = (Fan) (dic.get("MaxFTW"));
            assertEquals(newFan.getUserName(),sameFan.getUserName());
        }
        catch (Exception e){
            fail();
        }
    } //Test ID:    #2.2.1 -- checks if signup in is working

    @Test
    public void signupTest2()  { //Test ID: #1.2
        String name = "Maxim";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW";
        GustController gustController = new GustController();
        try {
            Fan newFan = gustController.signup(id, name, password, username);
            Fan sameFan = gustController.signup(id, name, password, username);
            fail();
        }
        catch (UserNameAlreadyExistException e){
        }
    } //Test ID:    #2.2.2 -- checks if can't signup with two same userName

    @Test
    public void loginTest1(){
        String name = "Maxim";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW";
        GustController gustController = new GustController();
        try {
           gustController.signup(id, name, password, username);
        }
        catch (Exception e){
            fail();
        }
        try {
            User user = gustController.login(username, password);
            assertEquals(user.getUserName(),username);
        }
        catch (Exception e){
            fail();
        }
    } //Test ID:    #2.3.1 -- checks if can login after signup

    @Test
    public void loginTest2(){
        String name = "Maxim";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW";
        GustController gustController = new GustController();
        try {
            gustController.signup(id, name, password, username);
        }
        catch (Exception e){
            fail();
        }
        try {
            User user = gustController.login("****", password);
            fail();
        }
        catch (NoSuchAUserNamedException e){

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
        String username = "MaxFTW";
        GustController gustController = new GustController();
        try {
            gustController.signup(id, name, password, username);
        }
        catch (Exception e){
            fail();
        }
        try {
            User user = gustController.login(username, "12");
            fail();
        }
        catch (NoSuchAUserNamedException e){
            fail();
        }
        catch (WrongPasswordException e){

        }
    } //Test ID:    #2.3.3 -- checks if can login with wrong password

    @Test
    public void getInfoToShow() throws UserNameAlreadyExistException{
        FootballAssosiation association = new FootballAssosiation(205695612,"Max","123","MaxFTW");
        association.addNewReferee("Invoker", RefereeType.MainReferee,1,"123","Invoker");
        GustController gustController = new GustController();
        List<IShowable> iShowables =  gustController.getInfoToShow("Referee");
        String string = iShowables.get(0).getName()+iShowables.get(0).getType();
        assertEquals("InvokerReferee",string);
    } //Test ID:    #3.1
}
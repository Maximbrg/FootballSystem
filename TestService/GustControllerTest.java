import ServiceLayer.GustController;
import System.Enum.RefereeType;
import System.Exeptions.UserNameAlreadyExistException;
import System.Users.Fan;
import System.Users.FootballAssosiation;
import System.Users.User;
import org.junit.*;
import System.*;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GustControllerTest {

    @Test
    public void signup() throws UserNameAlreadyExistException { //Test ID: #1
        String name = "Maxim";
        int id = 205695612;
        String password = "1234";
        String username = "MaxFTW";
        GustController gustController = new GustController();
        Fan newFan = gustController.signup(id,name,password,username);
        Controller controller = Controller.getInstance();
        HashMap<String , User> dic = controller.getUsers();
        Fan sameFan = (Fan)(dic.get("MaxFTW"));
        System.out.println("aaa");
    }

    @Test
    public void getInfoToShow() throws UserNameAlreadyExistException{
        FootballAssosiation assosiation = new FootballAssosiation(205695612,"Max","123","MaxFTW");
        assosiation.addNewReferee("Invoker", RefereeType.MainReferee,1,"123","Invoker");
        GustController gustController = new GustController();
        List<IShowable> iShowables =  gustController.getInfoToShow("Referee");
        String string = iShowables.get(0).getName()+iShowables.get(0).getType();
        assertEquals("InvokerReferee",string);
    } //Test ID:    #3.1
}
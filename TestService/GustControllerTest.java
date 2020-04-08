import ServiceLayer.GustController;
import System.Controller;
import System.Exeptions.UserNameAlreadyExistException;
import System.Users.Fan;
import System.Users.User;
import org.junit.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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
}
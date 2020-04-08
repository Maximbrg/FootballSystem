package ServiceLayer;

import System.*;
import System.Exeptions.UserNameAlreadyExistException;
import System.Users.Fan;

public class GustController {

    public Fan signup(int id, String name, String password, String userName) throws UserNameAlreadyExistException {
        Controller controller = Controller.getInstance();
        Fan newFan = controller.signup(id,name,password,userName);
        return newFan;
    }

}

package ServiceLayer;

import System.*;
import System.Users.User;

public abstract class MainUserController {

    public void logOut(User user) {
        Controller controller = Controller.getInstance();
        controller.logOut(user);
    }


}

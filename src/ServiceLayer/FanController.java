package ServiceLayer;

import System.PersonalPages.PersonalPage;
import System.Users.Fan;
import System.Users.User;
import System.*;

public class FanController extends GustController {

    public void follow(Fan fan, PersonalPage personalPage){
        personalPage.follow(fan);
    }

    @Override
    public Fan signup(int id, String name, String password, String userName)  {
        throw new UnsupportedOperationException();
    }

    @Override
    public void logOut(User user){
        Controller controller = Controller.getInstance();
        controller.logOut(user);
    }
}
/*
    public void unfollow(Fan fan,PersonalPage personalPage);
    public void follow(Fan fan, Game game);
    public void unfollow(Fan fan, Game game);
    public void sumbitReport(Fan fan,String report);
    public List<String> searchHistory(Fan fan);
    public List<String> showDetails(User user);

    */
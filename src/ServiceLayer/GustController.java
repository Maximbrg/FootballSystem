package ServiceLayer;

import System.*;
import System.Exeptions.UserNameAlreadyExistException;
import System.Exeptions.noSuchAUserNamedException;
import System.Exeptions.wrongPasswordException;
import System.Users.Fan;
import System.Users.Referee;
import System.Users.User;

import java.util.LinkedList;
import java.util.List;

public class GustController {

    public Fan signup(int id, String name, String password, String userName) throws UserNameAlreadyExistException {
        Controller controller = Controller.getInstance();
        Fan newFan = controller.signup(id,name,password,userName);
        return newFan;
    }

    public User login(String userName , String password) throws wrongPasswordException, noSuchAUserNamedException {
        Controller controller = Controller.getInstance();
        User existUser = controller.login(userName,password);
        return existUser;
    }

    public List<IShowable> getInfoToShow(String name){
        List<IShowable> result = new LinkedList<IShowable>();
        switch (name){
            case "Player":{
                result.add((IShowable)(Controller.getInstance().getAllPlayers()));
            }
            case "Coach":{
                result.add((IShowable)(Controller.getInstance().getAllCoach()));
            }
            case "Team":{
                result.add((IShowable)(Controller.getInstance().getAllTeams()));
            }
            case "League":{
                result.add((IShowable)(Controller.getInstance().getAllLeagues()));
            }
            case "Season":{
                result.add((IShowable)(Controller.getInstance().getAllSeasons()));
            }
            case "Referee":{
                for(Referee referee : Controller.getInstance().getAllReferee()){
                    result.add((IShowable)referee);
                }

            }
        }
        return result;
    }


}

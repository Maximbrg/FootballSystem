package ServiceLayer;

import System.*;
import System.Enum.SearchCategory;
import System.Exeptions.UserNameAlreadyExistException;
import System.Exeptions.noSuchAUserNamedException;
import System.Exeptions.wrongPasswordException;
import System.FootballObjects.League;
import System.FootballObjects.Season;
import System.FootballObjects.Team.Team;
import System.Searcher.ASearchStrategy;
import System.Users.*;

import java.util.LinkedList;
import java.util.List;

public class GustController {

    public User login(String userName , String password) throws wrongPasswordException, noSuchAUserNamedException {
        Controller controller = Controller.getInstance();
        User existUser = controller.login(userName,password);
        return existUser;
    }

    public Fan signup(int id, String name, String password, String userName) throws UserNameAlreadyExistException {
        Controller controller = Controller.getInstance();
        Fan newFan = controller.signup(id,name,password,userName);
        return newFan;
    }

    public List<IShowable> getInfoToShow(String name){
        List<IShowable> result = new LinkedList<IShowable>();
        switch (name){
            case "Player":{
                for(Player player : Controller.getInstance().getAllPlayers()){
                    result.add(player);
                }
            }
            case "Coach":{
                for(Coach coach : Controller.getInstance().getAllCoach()){
                    result.add(coach);
                }
            }
            case "Team":{
                for(Team team : Controller.getInstance().getAllTeams()){
                    result.add(team);
                }
            }
            case "League":{
                for(League league : Controller.getInstance().getAllLeagues()){
                    result.add(league);
                }
            }
            case "Season":{
                for(Season season : Controller.getInstance().getAllSeasons()){
                    result.add(season);
                }
            }
            case "Referee":{
               for(Referee referee : Controller.getInstance().getAllReferee()){
                    result.add(referee);
                }
            }
        }
        return result;
    }

    public List<IShowable> searchShowables(User user, ASearchStrategy aSearchStrategy, SearchCategory searchCategory, String query){
        List<IShowable> results = aSearchStrategy.search(searchCategory,query);
        user.addSearchHistory(query);
        return results;
    }

    public List<IShowable> filterResults(ASearchStrategy aSearchStrategy, SearchCategory searchCategory, List<IShowable> iShowableList){
        List<IShowable> results = aSearchStrategy.filter(iShowableList, searchCategory);
        return results;
    }

}

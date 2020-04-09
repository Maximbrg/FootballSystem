package ServiceLayer;

import System.Enum.RefereeType;
import System.Exeptions.IllegalInputException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.League;
import System.Controller;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.Users.FootballAssosiation;
import System.Users.Referee;

public class FootballAssosiationController {

    public League initEmptyLeague(String name){
        League league=new League(name);
        Controller controller= Controller.getInstance();
        controller.addLeague(league);
        return league;
    }

    public void initLeague(FootballAssosiation footballAssosiation,League league){
        Controller controller=Controller.getInstance();
        //////---add to function get league!!!!
        footballAssosiation.initLeague(controller.getAllTeams(),controller.getAllReferee());
    }

    public void removeReferee(FootballAssosiation footballAssosiation, Referee referee) throws IllegalInputException {
        footballAssosiation.removeReferee(referee);
    }

    public Referee addReferee(FootballAssosiation footballAssosiation,String name, RefereeType type, int id, String pass, String userName) throws UserNameAlreadyExistException {
        Referee referee=footballAssosiation.addNewReferee(name,type,id,pass,userName);
        return referee;
    }


}

package System;

import System.FootballObjects.League;
import System.FootballObjects.Season;
import System.FootballObjects.Team.IScoreMethodPolicy;
import System.FootballObjects.Team.ITeamAllocatePolicy;
import System.Users.Fan;
import System.Users.SystemManager;
import System.Users.User;

import java.util.List;

public class Controller {

    private static int nextFreeID; // id that we give to a new user.
    private List<User> users;
    private List<League> leagues;
    private SystemManager systemManager;
    private ITeamAllocatePolicy iTeamAllocatePolicy; // the default method for allocate team for a season.
    private IScoreMethodPolicy iScoreMethodPolicy; //the default method for calculate the score of league.
    private Season currentSeason;


    //Constructor

    private static Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {
    }

    //Methods

    public boolean initSystem(){ //UC-1
        return false;
    } //UC-1

    public User login(String userName , String password){ //UC-3
        return null;
    } //UC-3

    public Fan signUp(/*Need to add to the function all relevant arguments for build new fan object*/){ //UC-2
       return null;
    } //UC-2

    public String showMenu(User user){ //UC-4
        return null;
    } //UC-4 /* instead of return String need to return an objects that have all elements for building a menu.

    public String search(){ //UC-5
        return null;
    } //UC-5 /* same as UC-4 but also need to add to the arguments the object the we want to  search in the system.

    public boolean logOut(){ //UC-6
        return false;
    } //UC-6

}

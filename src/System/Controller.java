package System;

import System.Exeptions.IllegalInputExeption;
import System.Exeptions.noSuchAUserNamedException;
import System.Exeptions.wrongPasswordException;
import System.FootballObjects.League;
import System.FootballObjects.Season;
import System.FootballObjects.Team.DefualtAllocte;
import System.FootballObjects.Team.DefualtMethod;
import System.FootballObjects.Team.IScoreMethodPolicy;
import System.FootballObjects.Team.ITeamAllocatePolicy;
import System.Users.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    private static int nextFreeID; // id that we give to a new user.
    private HashMap<String,User> users;
    private List<League> leagues;
    private SystemManager systemManager;
    private ITeamAllocatePolicy iTeamAllocatePolicy; // the default method for allocate team for a season.
    private IScoreMethodPolicy iScoreMethodPolicy; //the default method for calculate the score of league.
    private Season currentSeason; //need to update it every year


    //Constructor

    private static Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {
        nextFreeID = 0;
        users = new HashMap<>();
        leagues =  new LinkedList<>();
        systemManager = null;
        iTeamAllocatePolicy = new DefualtAllocte();
        iScoreMethodPolicy = new DefualtMethod();
        currentSeason = new Season();
    }

    //Methods

    public SystemManager initSystem(){ //UC-1
        System.out.println("log file : successful connection to accounting system.");
        System.out.println("log file : successful connection to tax system.");
        SystemManager systemManager = new SystemManager();
        return systemManager;
    } //UC-1

    public User login(String userName , String password) throws wrongPasswordException , noSuchAUserNamedException { //UC-3
        System.out.println("log file : successful login.");
        User user = users.get(userName);
        if(user == null) {
            throw new noSuchAUserNamedException();
        }
        if(user.getPassword().equals(password)) {
            user.setStatus(Status.ACTIVE);
            return user;
        }
        throw new wrongPasswordException();
    } //UC-3

    public Fan signup(/*Need to add to the function all relevant arguments for build new fan object*/){ //UC-2
        System.out.println("log file : successful signup .");
        Fan fan = new Fan();
        users.put(fan.getUserName(),fan);
        return fan;
    } //UC-2

    public Object showMenu(String name) throws IllegalInputExeption{ //UC-4
        switch (name){
            case "League": {
                return leagues;
            }
            case "Player": {
                List<Player> players = new LinkedList<>();
                for(User user : users.values()){
                    if(user instanceof Player) {
                        players.add((Player) user);
                    }
                }
                return players;
            }
            case "Couch": {
                List<Couch> couches = new LinkedList<>();
                for(User user : users.values()){
                    if(user instanceof Couch) {
                        couches.add((Couch) user);
                    }
                }
                return couches;
            }
        }
        throw new IllegalInputExeption();
    } //UC-4

    public String search(User user){ //UC-5
        return null;
    } //UC-5 /* same as UC-4 but also need to add to the arguments the object the we want to  search in the system.

    public void logOut(User user){ //UC-6
        user.setStatus(Status.INACTIVE);
    } //UC-6

}

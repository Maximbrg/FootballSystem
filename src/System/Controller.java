package System;

import System.Exeptions.UserNameAlreadyExistException;
import System.Exeptions.noSuchAUserNamedException;
import System.Exeptions.wrongPasswordException;
import System.FootballObjects.League;
import System.FootballObjects.Season;
import System.FootballObjects.Team.*;
import System.Users.*;
import System.Enum.UserStatus;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    private HashMap<String,User> users;
    private List<League> leagues;
   // private Season currentSeason; //need to update it every year
    private List<Team> teams;
    private List<Season> seasons;

    //Constructor

    private static Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {
        users = new HashMap<>();
        leagues =  new LinkedList<>();
    }

    //Methods

    public void initSystem(){ //UC-1
        System.out.println("log file : successful connection to accounting system.");
        System.out.println("log file : successful connection to tax system.");
    } //UC-1

    public User login(String userName , String password) throws wrongPasswordException , noSuchAUserNamedException { //UC-3
        System.out.println("log file : successful login.");
        User user = users.get(userName);
        if(user == null) {
            throw new noSuchAUserNamedException();
        }
        if(user.getPassword().equals(password)) {
            user.setStatus(UserStatus.ACTIVE);
            Log.getInstance().writeToLog("User log in to the system. id("+user.getId()+").");
            return user;
        }
        throw new wrongPasswordException();
    } //UC-3

    public Fan signup(int id, String name, String password, String userName) throws UserNameAlreadyExistException{ //UC-2
        System.out.println("log file : successful sign up.");
        User user = users.get(userName);
        if(user != null) {
            throw new UserNameAlreadyExistException();
        }//more details
        Fan fan = new Fan(id,name, password,userName);
        users.put(fan.getUserName(),fan);
        return fan;
    } //UC-2

    public List<League> getAllLeagues(){ //UC-4
        return leagues;
    } //UC-4

    public List<Referee> getAllReferee(){
        List <Referee> referees = new LinkedList<>();
        for(User user : users.values()){
            if(user instanceof Referee){
                referees.add((Referee)user);
            }
        }
        return referees;
    } //UC-4

    public List<Coach> getAllCoach(){
        List <Coach> Coachs = new LinkedList<>();
        for(User user : users.values()){
            if(user instanceof Coach){
                Coachs.add((Coach)user);
            }
        }
        return Coachs;
    } //UC-4

    public List<Player> getAllPlayers(){
        List <Player> players = new LinkedList<>();
        for(User user : users.values()){
            if(user instanceof Player){
                players.add((Player)user);
            }
        }
        return players;
    } //UC-4

    public List<Team> getAllTeams(){
        return teams;
    } //UC-4

    public List<Season> getAllSeasons(){
        return seasons;
    } //UC-4

    public void logOut(User user){ //UC-6
        user.setStatus(UserStatus.INACTIVE);
        Log.getInstance().writeToLog("User log out from the system. id("+user.getUserName()+").");
    } //UC-6

    public  HashMap<String,User> getUsers(){
        return users;
    }

    public void addUser(String s,User u){
        users.put(s,u);
    }

    public void addSeason(Season season){
        seasons.add(season);
    }

    public void addLeague(League league){leagues.add(league);}

    public Season getSeason(String year){
        for(Season s : seasons){
            if(s.getYear().equals(year))
                return s;
        }
        return null;
    }

    public void addTeam(Team team){
        teams.add(team);
    }

}

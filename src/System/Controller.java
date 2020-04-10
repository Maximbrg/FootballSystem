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


    private HashMap<String,User> removedUser;

    //Constructor

    private static Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {
        users = new HashMap<>();
        leagues =  new LinkedList<>();
        removedUser =  new HashMap<>();
        teams=new LinkedList<>();
        seasons=new LinkedList<>();
    }

    //Methods

    public void initSystem(){ //UC-1
        System.out.println("log file : successful connection to accounting system.");
        System.out.println("log file : successful connection to tax system.");
    } //UC-1

    public User login(String userName , String password) throws wrongPasswordException , noSuchAUserNamedException { //UC-3
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

    public Fan signUp(int id, String name, String password, String userName) throws UserNameAlreadyExistException{ //UC-2
        User user = users.get(userName);
        User user1 = removedUser.get(userName);
        if(user != null || user != null) {
            throw new UserNameAlreadyExistException();
        }//more details
        Fan fan = new Fan(id,name, password,userName);
        users.put(fan.getUserName(),fan);
        Log.getInstance().writeToLog("A new user signUp to the system. ("+fan.getId()+","+fan.getUserName()+").");
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

    public Season getSeason(String year){
        for(Season s : seasons){
            if(s.getYear().equals(year))
                return s;
        }
        return null;
    }
    public HashMap<String, User> getRemovedUsers() {
        return removedUser;
    }


    public void addTeam(Team team){
        teams.add(team);
    }

    /**
     * add the removed users to a list of users
     * @param userName unique nickname
     */
    public void removeUser(String userName) throws noSuchAUserNamedException {
        if(users.get(userName)==null){
            throw new noSuchAUserNamedException();
        }
        removedUser.put(userName,users.get(userName));
        users.remove(userName);
        Log.getInstance().writeToLog("User removed from the system. userName("+userName+").");
    }

    /**
     * restart a removed user to the system
     * @param userName
     */
    public void restartRemvoeUser(String userName) throws noSuchAUserNamedException {
        if(removedUser.get(userName)==null){
            throw new noSuchAUserNamedException();
        }
        users.put(userName,removedUser.get(userName));
        removedUser.remove(userName);
        Log.getInstance().writeToLog("Removed user restart to the system. userName("+userName+").");

    }
}

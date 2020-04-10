package System;
import System.Exeptions.IllegalInputException;
import System.Exeptions.UserNameAlreadyExistException;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.WrongPasswordException;
import System.FootballObjects.Field;
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
    private List<Field> fields;
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
        teams = new LinkedList<>();
        seasons = new LinkedList<>();
        fields = new LinkedList<>();
    }

    //Methods

    public void initSystem(){ //UC-1
        System.out.println("log file : successful connection to accounting system.");
        System.out.println("log file : successful connection to tax system.");
    } //UC-1

    public User login(String userName , String password) throws WrongPasswordException , NoSuchAUserNamedException { //UC-3
        User user = users.get(userName);
        if(user == null) {
            throw new NoSuchAUserNamedException();
        }
        if(user.getPassword().equals(password)) {
            user.setStatus(UserStatus.ACTIVE);
            Log.getInstance().writeToLog("User log in to the system. id("+user.getId()+").");
            return user;
        }
        throw new WrongPasswordException();
    } //UC-3

    public Fan signUp(int id, String name, String password, String userName) throws UserNameAlreadyExistException{ //UC-2
        User user = users.get(userName);
        User user1 = removedUser.get(userName);
        if(user != null || user1 != null) {
            throw new UserNameAlreadyExistException();
        }//more details
        Fan fan = new Fan(id,name, password,userName);
        users.put(fan.getUserName(),fan);
        Log.getInstance().writeToLog("A new user signUp to the system. ("+fan.getId()+","+fan.getUserName()+").");
        return fan;
    } //UC-2

    /**
     * Checks if user name exist in the system
     * @param userName
     * @return
     */
    public boolean isUserNameExist(String userName){
        User user = users.get(userName);
        User user1 = removedUser.get(userName);
        if(user != null || user1 != null) {
            return true;
        }
        return false;
    }

    /**
     * Checks if league name already exist
     * @param name
     * @return
     */
    public boolean isLeagueExist(String name){
        for(League l: this.leagues){
            if(name.equals(l.getName())){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if Season exist by compering years
     * @param year
     * @return
     */
    public boolean isSeasonExsit(int year){
        for(Season season:seasons){
            if(season.getIntYear()==year){
                return true;
            }
        }
        return false;
    }

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
    public HashMap<String, User> getRemovedUsers() {
        return removedUser;
    }

    public void addTeam(Team team){
        teams.add(team);
    } //fff

    /**
     * add the removed users to a list of users
     * @param userName unique nickname
     */
    public void removeUser(String userName) throws NoSuchAUserNamedException {
        if(users.get(userName)==null){
            throw new NoSuchAUserNamedException();
        }
        users.get(userName).setStatus(UserStatus.REMOVED);
        removedUser.put(userName,users.get(userName));
        users.remove(userName);
        Log.getInstance().writeToLog("User removed from the system. userName("+userName+").");
    }

    /**
     * restart a removed user to the system
     * @param userName
     */
    public void restartRemvoeUser(String userName) throws NoSuchAUserNamedException {
        if(removedUser.get(userName)==null){
            throw new NoSuchAUserNamedException();
        }
        users.put(userName,removedUser.get(userName));
        removedUser.remove(userName);
        Log.getInstance().writeToLog("Removed user restart to the system. userName("+userName+").");

    }

    public void removeTeam(Team team){ teams.remove(team); }

    public void addField(Field field){
        fields.add(field);
    }

    public void removeField(Field field){
        fields.remove(field);
    }



}

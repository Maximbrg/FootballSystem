package System;

import System.Enum.RefereeType;
import System.Enum.UserStatus;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.UserNameAlreadyExistException;
import System.Exeptions.WrongPasswordException;
import System.FootballObjects.Field;
import System.FootballObjects.League;
import System.FootballObjects.Season;
import System.FootballObjects.Team.*;
import System.Users.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    //<editor-fold desc="Fields">
    private HashMap<String,User> users;
    private List<League> leagues;
    private List<Team> teams;
    private List<Season> seasons;
    private List<Field> fields;
    private HashMap<String,User> removedUser;

    private HashMap<String, IScoreMethodPolicy> scorePolicies;
    private HashMap<String, ITeamAllocatePolicy> methodAllocatePolicies;
    //</editor-fold>

    //<editor-fold desc="Constructor">
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
        scorePolicies=new HashMap<>();
        methodAllocatePolicies=new HashMap<>();

    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public List<League> getAllLeagues(){ //UC-4
        return leagues;
    } //UC-4
    public List<Team> getAllTeams(){
        return teams;
    } //UC-4
    public List<Season> getAllSeasons(){
        return seasons;
    } //UC-4
    public  HashMap<String,User> getUsers(){
        return users;
    }
    public void addUser(String s,User u){
        users.put(s,u);
    }
    public void addSeason(Season season){
        seasons.add(season);
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public void setRemovedUser(HashMap<String, User> removedUser) {
        this.removedUser = removedUser;
    }
    //</editor-fold>

    //<editor-fold desc="Methods">
    public void initSystem(){ //UC-1
        //Object TaxServer;
        //Object AccountingServer;
        //In the future we will use this objects for getting/sending information from/to tax and accounting servers
        SystemEventLog.getInstance().writeToLog("Successfully connected to Tax System");
        SystemEventLog.getInstance().writeToLog("Successfully connected to Accounting System");
        SystemManager systemManager = new SystemManager(0,"Administrator","2&^4BcE#@6","Admin");
        this.addUser("Admin",systemManager);
        IScoreMethodPolicy iScoreMethod=new DefaultMethod();
        ITeamAllocatePolicy iScoreAllocate=new DefaultAllocate();
        scorePolicies.put(iScoreMethod.getClass().getSimpleName(),iScoreMethod);
        methodAllocatePolicies.put(iScoreAllocate.getClass().getSimpleName(),iScoreAllocate);
    } //UC-1

    public User login(String userName , String password) throws WrongPasswordException , NoSuchAUserNamedException { //UC-3
        User user = users.get(userName);
        if(user == null) {
            throw new NoSuchAUserNamedException();
        }
        if(user.getPassword().equals(password)) {
            user.setStatus(UserStatus.ACTIVE);
            SystemEventLog.getInstance().writeToLog("User log in to the system. id("+user.getId()+").");
            return user;
        }
        throw new WrongPasswordException();
    } //UC-3

    public void logOut(User user){ //UC-6
        user.setStatus(UserStatus.INACTIVE);
        SystemEventLog.getInstance().writeToLog("User log out from the system. id("+user.getUserName()+").");
    } //UC-6

    public Fan signUp(int id, String name, String password, String userName) throws UserNameAlreadyExistException{ //UC-2
        User user = users.get(userName);
        User user1 = removedUser.get(userName);
        if(user != null || user1 != null) {
            throw new UserNameAlreadyExistException();
        }//more details
        Fan fan = new Fan(id,name, password,userName);
        users.put(fan.getUserName(),fan);
        SystemEventLog.getInstance().writeToLog("A new user signUp to the system. ("+fan.getId()+","+fan.getUserName()+").");
        return fan;
    } //UC-2

    /**
     * add field to the system fields
     * @param field
     */
    public void addField(Field field){
        fields.add(field);
    }
    /**
     * remove field from the system fields
     * @param field
     */
    public void removeField(Field field){
        fields.remove(field);
    }
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

    public List<Fan> getAllFan() {
        List <Fan> fanList = new LinkedList<>();
        for(User user : users.values()){
            if(user instanceof Fan){
                fanList.add((Fan) user);
            }
        }
        return fanList;
    }

    public List<TeamManager> getAllTeamManager() {
        List <TeamManager> teamMangerList = new LinkedList<>();
        for(User user : users.values()){
            if(user instanceof TeamManager){
                teamMangerList.add((TeamManager) user);
            }
        }
        return teamMangerList;
    }

    public List<TeamOwner> getAllTeamOwner() {
        List <TeamOwner> teamOwnerList = new LinkedList<>();
        for(User user : users.values()){
            if(user instanceof TeamOwner){
                teamOwnerList.add((TeamOwner) user);
            }
        }
        return teamOwnerList;
    }
    /**
     * get string of the policies
     * @return
     */
    public List<String> getScorePoliciesString() {
        List<String> list=new ArrayList<>();
        for (int i = 0; i <scorePolicies.size() ; i++) {
            list.add(scorePolicies.keySet().toArray()[i].toString());
        }
        return list;
    }
    public List<Player> getAllPlayers(){
        List <Player> players = new LinkedList<>();
        for(User user : users.values()){
            if(user instanceof Player){
                players.add((Player)user);
            }
        }
        return players;
    } //UC-4

    public List<SystemManager> getAllSystemManager() {
        List <SystemManager> sysList = new LinkedList<>();
        for(User user : users.values()){
            if(user instanceof SystemManager){
                sysList.add((SystemManager) user);
            }
        }
        return sysList;
    }

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

    /**
     * add league to the system
     * @param league
     */
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

    /**
     * add team to the system teams
     * @param team
     */
    public void addTeam(Team team){
        teams.add(team);
    }

    /**
     * add the removed users to a list of users
     * @param userName unique nickname
     */
    public void removeUser(String userName) throws NoSuchAUserNamedException {
        if(users.get(userName)==null){
            throw new NoSuchAUserNamedException();
        }
        users.get(userName).removeUser();
        users.get(userName).setStatus(UserStatus.REMOVED);
        removedUser.put(userName,users.get(userName));
        users.remove(userName);
        SystemEventLog.getInstance().writeToLog("User removed from the system. userName("+userName+").");
    }

    /**
     * restart a removed user to the system
     * @param userName
     */
    public void restartRemoveUser(String userName) throws NoSuchAUserNamedException {
        if(removedUser.get(userName)==null){
            throw new NoSuchAUserNamedException();
        }
        users.put(userName,removedUser.get(userName));
        removedUser.remove(userName);
        users.get(userName).setStatus(UserStatus.INACTIVE);
        SystemEventLog.getInstance().writeToLog("Removed user restart to the system. userName("+userName+").");
    }

    public void removeTeam(Team team){ teams.remove(team); }

    /**
     * get string of the policies
     * @return
     */
    public List<String> getMethodAllocatePoliciesString() {
        List<String> list=new ArrayList<>();
        for (int i = 0; i <methodAllocatePolicies.size() ; i++) {
            list.add(methodAllocatePolicies.keySet().toArray()[i].toString());
        }
        return list;
    }

    public HashMap<String, ITeamAllocatePolicy> getMethodAllocatePolicies() {
        return methodAllocatePolicies;
    }
    public HashMap<String, IScoreMethodPolicy> getScorePolicies() {
        return scorePolicies;
    }
    //</editor-fold>

}

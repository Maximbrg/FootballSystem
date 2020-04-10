package System.Users;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import System.Asset.Asset;
import System.Enum.TeamStatus;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.Field;
import System.FootballObjects.Team.Team;
import System.PersonalPages.PersonalPage;
import System.Report;
import System.Log;
import System.FinancialReport;
import System.Controller;

public class SystemManager extends User {

    private static HashMap<Integer,Report> openReportsBox = new HashMap<Integer, Report>();
    private static HashMap<Integer,Report> closeReportsBox = new HashMap<Integer, Report>();

    //<editor-fold desc="constructor">
    public SystemManager(int id, String name, String password, String userName){
        super(id,name,password,userName);
    }
    //</editor-fold>

    //<editor-fold desc="getters">
    public HashMap<Integer, Report> getOpenReports() {
        return openReportsBox;
    }
    public HashMap<Integer, Report> getCloseReports() { return closeReportsBox;}

    /**
     * present the log file
     * @return
     */
    public String getLog() throws IOException {
        Log.getInstance().writeToLog("Log introduced by the System Manager.");
        return Log.getInstance().getLog();

    } //UC-28
    //</editor-fold>

    //<editor-fold desc="Methods">
    /**
     * close team
     * @param team
     */
    public void closeTeam(Team team){
        team.PermanentlyCloseTeam();
        Log.getInstance().writeToLog("The team: "+team.getName()+",Id: " +team.getId()+" closed successfully.");
    } //UC-25

    /**
     * System manager command to remove system by the conroller
     * @param userName to remove
     * @throws NoSuchAUserNamedException
     */
    public void removeUser(String userName) throws NoSuchAUserNamedException {
        Controller.getInstance().removeUser(userName);
    } //UC-26//

    /**
     * Restart a user
     * @param userName
     * @throws NoSuchAUserNamedException
     */
    public void restartRemovedUser(String userName) throws NoSuchAUserNamedException{
        Controller.getInstance().restartRemvoeUser(userName);
    }

    /**
     * Adding a new report to reportsHash
     * @param report
     */
    public static void addReport(Report report) {
        openReportsBox.put(report.getId(),report);
        Log.getInstance().writeToLog("A new report added to the System. id("+report.getId()+").");
    }

    /**
     * Answer to report
     * @param report
     */
    public void answerTheReport(Report report, String answer){
        report.answer(answer);
        closeReportsBox.put(report.getId(),report);
        openReportsBox.remove(report.getId());
        Log.getInstance().writeToLog("A answer to report set. ("+report.getId()+").");

    }

    public void createTeam(String name, TeamStatus teamStatus, PersonalPage personalPage, List<Asset> assets, List<TeamManager> teamManagersList, HashMap<TeamOwner, List<TeamOwner>> teamOwnersWhichappointed, Field field, int income, int expense, FinancialReport financialReport ){
        Team newTeam = new Team(name,teamStatus,personalPage,assets,teamManagersList,teamOwnersWhichappointed,field,income,expense,financialReport);
        Controller.getInstance().addTeam(newTeam);
        Log.getInstance().writeToLog("New team added to the system. ("+newTeam.getId()+", "+newTeam.getName()+")");
    }

    //<editor-fold desc="Create Users">

    /**
     * Create a new Player
     * @param id
     * @param name
     * @param password
     * @param userName
     * @param birthDate
     * @param role
     * @param assetValue
     * @param salary
     * @return
     * @throws UserNameAlreadyExistException
     */
    public Player createNewPlayer(int id, String name, String password, String userName, Date birthDate, String role, int assetValue, int salary) throws UserNameAlreadyExistException {
        if(Controller.getInstance().isUserNameExist(userName)){
            throw new UserNameAlreadyExistException();
        }
        Player user=new Player(id,name,password,userName,birthDate,role,assetValue,salary);
        Controller.getInstance().addUser(userName,user);
        Log.getInstance().writeToLog("Add a new user : " + user.getClass() +" "+ user.getId());
        return user;

    }

    /**
     * Create a new coach
     * @param id
     * @param name
     * @param password
     * @param userName
     * @param preparation
     * @param role
     * @param assetValue
     * @param salary
     * @return
     * @throws UserNameAlreadyExistException
     */
    public Coach createNewCoach(int id, String name, String password, String userName, String preparation, String role, int assetValue, int salary) throws UserNameAlreadyExistException {
        if(Controller.getInstance().isUserNameExist(userName)){
            throw new UserNameAlreadyExistException();
        }
        Coach user=new Coach(id,name,password,userName,preparation,role,assetValue,salary);
        Controller.getInstance().addUser(userName,user);
        Log.getInstance().writeToLog("Add a new user : " + user.getClass() +" "+ user.getId());

        return user;
    }

    /**
     * Create a new fan
     * @param id
     * @param name
     * @param password
     * @param userName
     * @return
     * @throws UserNameAlreadyExistException
     */
    public Fan createNewFan(int id, String name, String password, String userName) throws UserNameAlreadyExistException {
        return Controller.getInstance().signUp(id,name,password,userName);
    }
    ///need????????????
    public Referee createNewReferee(){return null;}

    /**
     * Create a new team manager
     * @param id
     * @param name
     * @param password
     * @param userName
     * @param assetValue
     * @param salary
     * @return
     * @throws UserNameAlreadyExistException
     */
    public TeamManager createNewTeamManager(int id, String name, String password, String userName, int assetValue, int salary) throws UserNameAlreadyExistException {
        if(Controller.getInstance().isUserNameExist(userName)){
            throw new UserNameAlreadyExistException();
        }
        TeamManager user=new TeamManager(id,name,password,userName,assetValue,salary);
        Controller.getInstance().addUser(userName,user);
        Log.getInstance().writeToLog("Add a new user : " + user.getClass() +" "+ user.getId());

        return user;
    }

    //needs to chang team owner constructor
    public TeamOwner createNewTeamOwner(){return null;}

    /**
     * Create a new system manager
     * @param id
     * @param name
     * @param password
     * @param userName
     * @return
     * @throws UserNameAlreadyExistException
     */
    public SystemManager createNewSystemManager(int id, String name, String password, String userName) throws UserNameAlreadyExistException {
        if (Controller.getInstance().isUserNameExist(userName)) {
            throw new UserNameAlreadyExistException();
        }
        SystemManager user = new SystemManager(id, name, password, userName);
        Controller.getInstance().addUser(userName, user);
        Log.getInstance().writeToLog("Add a new user : " + user.getClass() + " " + user.getId());
        return user;
    }

    //</editor-fold>

    //</editor-fold>

}

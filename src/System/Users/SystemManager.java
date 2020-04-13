package System.Users;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import System.Report;
import System.Log;
import System.Controller;

public class SystemManager extends User implements IObserverTeam {

    private static HashMap<Integer,Report> openReportsBox = new HashMap<Integer, Report>();
    private static HashMap<Integer,Report> closeReportsBox = new HashMap<Integer, Report>();
    private static List<ISubjectTeam> observerTeams = new LinkedList<>();

    //<editor-fold desc="constructor">
    public SystemManager(int id, String name, String password, String userName){
        super(id,name,password,userName);
    }
    //</editor-fold>

    //<editor-fold desc="setters">

    public static void setOpenReportsBox(HashMap<Integer, Report> openReportsBox) {
        SystemManager.openReportsBox = openReportsBox;
    }

    public static void setCloseReportsBox(HashMap<Integer, Report> closeReportsBox) {
        SystemManager.closeReportsBox = closeReportsBox;
    }
    //</editor-fold>

    //Methods
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
        Log.getInstance().writeToLog("Log introduced by the System Manager. id:"+getId());
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

    /**
     * create new team and add it to the user's system. register all the system managers by this new team
     * @param name
     */
    public void createTeam(String name,TeamOwner teamOwner){
        Team newTeam = new Team(name,teamOwner);
        for(SystemManager s:Controller.getInstance().getAllSystemManager()){
            newTeam.registerSystemManagerToAlert(s);
        }
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

    /**
     * create new team owner
     * @param id
     * @param name
     * @param password
     * @param userName
     * @param salary
     * @return
     * @throws UserNameAlreadyExistException
     */
    public TeamOwner createNewTeamOwner(int id, String name, String password, String userName,int salary) throws UserNameAlreadyExistException {
        if(Controller.getInstance().isUserNameExist(userName)){
            throw new UserNameAlreadyExistException();
        }
        TeamOwner user=new TeamOwner(id,name,password,userName,salary);
        Controller.getInstance().addUser(userName,user);
        Log.getInstance().writeToLog("Add a new user : " + user.getClass() +" "+ user.getId());
        return user;
        }

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
    @Override
    public void update(String msg) {
        Log.getInstance().writeToLog("System Manager was updated by a team. about the messge:"+msg+" id's SystemManager:"+getId());

    }
    @Override
    public void registerAlert(ISubjectTeam iSubjectTeam) {
        observerTeams.add(iSubjectTeam);

    }
    @Override
    public void removeAlert(ISubjectTeam iSubjectTeam) {
        observerTeams.remove(iSubjectTeam);

    }

    //</editor-fold>

    //</editor-fold>

}

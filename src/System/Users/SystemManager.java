package System.Users;

import java.io.IOException;
import java.util.HashMap;

import System.Enum.TeamStatus;
import System.FootballObjects.Team.Team;
import System.Report;
import System.Log;

public class SystemManager extends User {

    private HashMap<Integer,Report> reportsHash;


    //<editor-fold desc="constructor- singleton">
    private static SystemManager ourInstance = new SystemManager(111,"","111","systemM");

    public static SystemManager getInstance() {
        return ourInstance;
    }

    private SystemManager(int id, String name, String password, String userName){
        super(id,name,password,userName);
        reportsHash= new HashMap<>();
    }

    public static HashMap<Integer,Report> getReports() {
        return null;
    }
    //</editor-fold>

    //Methods

    /**
     * close team
     * @param team
     */
    public void closeTeam(Team team){
        team.setTeamStatus(TeamStatus.PermantlyClose);
        team.PermanentlyCloseTeam();
        Log.getInstance().writeToLog("The team: "+team.getName()+",Id: " +team.getId()+" closed successfully.");
    } //UC-25

    public void removeUser(User user){

    } //UC-26//

    /**
     * present the log file
     * @return
     */
    public String getLog() throws IOException {
        Log.getInstance().writeToLog("Log introduced by the System Manager.");
        return Log.getInstance().getLog();

    } //UC-28


    /**
     * Adding a new report to reportsHash
     * @param report
     */
    public void addReport(Report report) {
        reportsHash.put(report.getId(),report);
    }

    /**
     * Answer to report
     * @param report
     */
    public void answerTheReport(Report report, String answer){
        report.answer(answer);
    }
}

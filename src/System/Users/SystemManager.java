package System.Users;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import System.Asset.Asset;
import System.Enum.TeamStatus;
import System.Exeptions.NoSuchAUserNamedException;
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

    //<editor-fold desc="constructor- singleton">
    public SystemManager(int id, String name, String password, String userName){
        super(id,name,password,userName);
    }
    //</editor-fold>

    //Methods

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

    public void restartRemovedUser(String userName) throws NoSuchAUserNamedException{
        Controller.getInstance().restartRemvoeUser(userName);
    }

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


    //</editor-fold>


    //<editor-fold desc="getters">
    public HashMap<Integer, Report> getOpenReports() {
        return openReportsBox;
    }
    //</editor-fold>

}

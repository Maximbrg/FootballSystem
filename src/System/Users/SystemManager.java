package System.Users;

import java.util.HashMap;
import java.util.List;

import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.Report;
import System.Controller;
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
    //</editor-fold>

    //Methods

    public void closeTeam(Team team){} //UC-25

    public void removeUser(User user){} //UC-26

    public String showLog(){
        return null;
    } //UC-28

    /**
     * Adding a new report to reportsHash
     * @param report
     */
    public void addReport(Report report) {
        reportsHash.put(report.getId(),report);
    }

    public void answerTheReport(Report report, String answer){
        report.answer(answer);
    }
}

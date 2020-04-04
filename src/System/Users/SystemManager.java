package System.Users;

import java.util.HashMap;
import java.util.List;

import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.Report;
import System.Controller;
import System.Log;

public class SystemManager extends User implements IObserverTeam {

    private String name;
    private HashMap<Integer,Report> reportsHash;
    private Controller  controller;
    private Log log;


    //Methods
    public void closeTeam(Team team){} //UC-25

    public void removeUser(User user){} //UC-26

    public String showLog(){
        return null;
    } //UC-28

    @Override
    public void update() {

    }

    /**
     * Adding a new report to reportsHash
     * @param report
     */
    public void addReport(Report report) {
        reportsHash.put(report.getId(),report);
    }
}

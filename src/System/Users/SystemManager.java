package System.Users;

import java.util.List;

import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.Report;
import System.Controller;
import System.Log;

public class SystemManager extends User implements IObserverTeam {

    private String name;
    private List<Report> list;
    private Controller  controller;
    private Log log;

    public SystemManager() {
    }

    //Methods
    public void closeTeam(Team team){} //UC-25

    public void removeUser(User user){} //UC-26

    public String showLog(){
        return null;
    } //UC-28

    @Override
    public void update() {

    }
}

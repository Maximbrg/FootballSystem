package ServiceLayer;

import ServiceLayer.Exceptions.TeamHasAFutureGame;
import System.FootballObjects.Team.Team;
import System.Users.SystemManager;
import System.Controller;
import System.Users.User;
import System.Report;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SystemManagerController {

    /**
     * Get all teams in the system
     * @return
     */
    public List<Team> getAllTeams(){
        Controller controller= Controller.getInstance();
        return  controller.getAllTeams();
    }

    /**
     * Gets all users in the system
     * @return
     */
    public List<User> getAllUsers(){
        Controller controller=Controller.getInstance();
        List<User> users= new LinkedList<>();
        for(Map.Entry user: controller.getUsers().entrySet()){
            users.add((User)user.getValue());
        }
        return users;
    }

    /**
     * Gets all open reports in system
     * @return
     */
    public HashMap<Integer,Report> getAllReports(){
        return SystemManager.getReports();
    }

    /**
     * Permanently close a team
     * @param systemManager
     * @param team
     */
    public void permanentlyCloseTeam(SystemManager systemManager, Team team) throws TeamHasAFutureGame {
        if(isAFutureGame(team)){
            throw new TeamHasAFutureGame();
        }
        team.PermanentlyCloseTeam();
    }

    /**
     * Removes user from the system
     * @param systemManager
     * @param user
     */
    private void removeUser(SystemManager systemManager,User user){
        systemManager.removeUser(user);
        ///????????
    }

    /**
     * Watch an open report details
     * @param report
     * @return
     */
    public String showReport(Report report){
        return report.getReportDetails();
    }

    /**
     * Answer report
     * @param systemManager
     * @param report
     * @param answer
     */
    public void answerReport(SystemManager systemManager,Report report, String answer){
        systemManager.answerTheReport(report,answer);
    }

    /**
     * Watch system log details
     * @param systemManager
     * @return
     * @throws IOException
     */
    public String showLog(SystemManager systemManager) throws IOException {
        return systemManager.getLog();
    }

    public void activeRecommendationSystem(){
        //?????????????
    }

    /**
     * Checks if has a future game to team
     * @param team
     * @return
     */
    private boolean isAFutureGame(Team team){
        //?????????????
        return true;
    }


}

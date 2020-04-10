package System.FootballObjects.Team;

import ServiceLayer.Exceptions.TeamHasAFutureGame;
import System.Exeptions.NoSuchAUserNamedException;
import System.FootballObjects.Game;
import System.Users.SystemManager;
import System.Users.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface ITeamAllocatePolicy {

    void setTeamPolicy(List<Team> teams, List<Game> games);

    class SystemManagerController {

        //<editor-fold desc="Getters">
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
            Controller controller= Controller.getInstance();
            List<User> users= new LinkedList<>();
            for(Map.Entry user: controller.getUsers().entrySet()){
                users.add((User)user.getValue());
            }
            return users;
        }

        /**
         * Get all inactive users in the system
         * @return
         */
        public List<User> getAllInactiveUsers(){
            List<User> users= new LinkedList<>();
            for(Map.Entry user: Controller.getInstance().getRemovedUsers().entrySet()){
                users.add((User)user.getValue());
            }
            return users;
        }

        /**
         * Gets all open reports in system
         * @return
         */
        public HashMap<Integer, Report> getAllOpenReports(SystemManager systemManager){

            return systemManager.getOpenReports();
        }

        //</editor-fold>


        /**
         * Permanently close a team
         * @param systemManager
         * @param team
         */
        public void permanentlyCloseTeam(SystemManager systemManager, Team team) throws TeamHasAFutureGame {
            if(!isAFutureGame(team)){
                throw new TeamHasAFutureGame();
            }
            systemManager.closeTeam(team);
        }

        public void restartUser(SystemManager systemManager, User user) throws NoSuchAUserNamedException {
            systemManager.restartRemovedUser(user.getUserName());
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
        public void answerReport(SystemManager systemManager, Report report, String answer){
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
            return team.getFutureGames().isEmpty();
        }


    }
}

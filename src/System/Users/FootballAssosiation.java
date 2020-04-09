package System.Users;

import System.Enum.RefereeType;
import System.FootballObjects.League;
import System.FootballObjects.Season;
import System.FootballObjects.Team.IScoreMethodPolicy;
import System.FootballObjects.Team.ITeamAllocatePolicy;
import System.FootballObjects.Team.Team;
import System.Controller;
import System.BudgetRules;
import System.FootballObjects.Game;
import System.FootballObjects.LeagueInformation;
import System.Log;
import System.Exeptions.*;
import System.Enum.UserStatus;

import java.util.*;

public class FootballAssosiation extends User {

    private String name;
    private Controller controller;
    private BudgetRules budgetRules;


    //<editor-fold desc="contractur">
    public FootballAssosiation(int id, String name, String password, String userName) {
        super(id,name,password,userName);
    }
    //</editor-fold>


    //Methods

    /**
     * Init new League
     * @param teams
     * @param referees
     */
    //UC-29
    public void initLeague(List<Team> teams, List<Referee> referees, Season season) {
        //1-5
        String name="";
        League league= new League(name);
        for (Team t : teams) {
            league.addTeam(t);
        }
        //6
        //Initialize your Date however you like it.
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        LeagueInformation leagueInformation= new LeagueInformation(league,season);
        leagueInformation.schedulingReferee(referees);

        Log.getInstance().writeToLog("Init new League. Name:"+ league.getName());
    } //UC-29

    public void addSeason(int year, List<Game> games){
        Season season=new Season(year, games);
        controller.addSeason(season);
    }
    public void getSeasonFromController(String year){
        controller.getSeason(year);
    }

    /**
     *  Add New Referee
     * @param name
     * @param type
     * @param id
     * @param pass
     * @param userName
     * @throws UserNameAlreadyExistException
     */
    //UC-30
    public void addNewReferee(String name, RefereeType type, int id, String pass, String userName)  throws UserNameAlreadyExistException {
        Referee referee= new Referee(name,type,id,pass,userName);
        Controller controller= Controller.getInstance();
        for (HashMap.Entry me : controller.getUsers().entrySet()) {
            if(me.getKey().equals(userName)){
                throw new UserNameAlreadyExistException();
            }
        }
        controller.addUser(userName,referee);
        Log.getInstance().writeToLog("Add new referee. id:"+referee.getId());

    }   //UC-30

    /**
     * Remove referee- Before deleting,we check the referee has some games to judge.
     * If so we will use the function: 'manuallChangingReferee' to manuall Changing Referee.
     * @param referee
     * @throws IllegalInputException
     */
    //UC-31
    public void removeReferee(Referee referee)  throws IllegalInputException  {
        Controller controller = Controller.getInstance();
        String userName = referee.getUserName();

        if (referee.getFutureGames().size() > 0) {
            throw new IllegalInputException();
        }

        //search specific referee with list from controller;
        HashMap<String, User> users = controller.getUsers();
        for (HashMap.Entry user : users.entrySet()) {
            if (user.getKey().equals(userName) && user.getValue() instanceof Referee) {
                ((Referee) user.getValue()).setStatus(UserStatus.INACTIVE);
            }
        }
    }

    /**
     * Manually swapping all games that the referee we want to delete should be judged in the future
     * @param listOfGame
     * @param referees
     * @param referee
     */
    public void manuallChangingReferee(List <Game> listOfGame, List<Referee> referees, Referee referee){
        int i=0;
        for(Game game:listOfGame){
            for(Referee newReferee:referees){
                if(newReferee.getType()==referee.getType()){
                    if(referee.getRefereeType()==RefereeType.MainReferee){
                        game.setMainReferee(newReferee);
                    }
                    else{
                        if(referee.getId()==game.getAssistantRefereeOne().getId()) {
                            game.setAssistantRefereeOne(newReferee);
                        }
                        else{
                            game.setAssistantRefereeTwo(newReferee);
                        }
                    }
                }
            }
        }

    }
    //UC-31


    //public void addBudgetRule(String rule){} //UC-33

    //public void editGameSchedulingPolicy(ITeamAllocatePolicy iTeamAllocatePolicy){} //UC-34

    //public void initialScoreSchedulingPlicy(League league , Season season , IScoreMethodPolicy iScoreMethodPolicy){}; //UC-36

    //public void editScoreSchedulingPolicy(League league , Season season , IScoreMethodPolicy iScoreMethodPolicy){} //UC-37

}
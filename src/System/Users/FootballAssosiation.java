package System.Users;

import System.FootballObjects.League;
import System.FootballObjects.Season;
import System.Controller;
import System.BudgetRules;
import System.FootballObjects.Game;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Team.Team;
import System.Log;
import System.Exeptions.*;
import System.Enum.RefereeType;
import System.FinancialReport;

import java.util.*;

public class FootballAssosiation extends User {

    private String name;
    private HashMap<Integer,LeagueInformation> leagueInformations;
    private Controller controller;
    private BudgetRules budgetRules;


    //<editor-fold desc="contractur">
    public FootballAssosiation(int id, String name, String password, String userName) {
        super(id,name,password,userName);
        leagueInformations=new HashMap<>();
    }
    //</editor-fold>

    //Methods

    /**
     * Init new League
     * @param season
     * @param league
     */
    //UC-29
    public LeagueInformation initLeague(Season season, League league) {
        //init League Information with league, season from service Layer
        LeagueInformation leagueInformation= new LeagueInformation(league,season, this);
        //add new leagueInformation to list
        leagueInformations.put(leagueInformation.getId(), leagueInformation);

        //update pointers
        season.addLeagueInformation(leagueInformation);
        league.addLeagueInformation(leagueInformation);

        //add to log
        Log.getInstance().writeToLog("Init new League. Name:"+ league.getName());

        //return to service Layer
        return leagueInformation;
    } //UC-29

    /**
     * init leagueInformation policy-  Team Allocate Policy AND Score Method Policy.
     * @param leagueInformation
     */
    public void initLeagueInformation(LeagueInformation leagueInformation){
        leagueInformation.initLeagueInformation();
    }

    /**
     * init scheduling Referee for league Information.
     * MUST USE THIS FUNCTION AFTER  initLeagueInformation!!! (schedulingReferee need that list of game dont be empty)
     * @param leagueInformation
     * @param referees
     */
    public void schedulingReferee(LeagueInformation leagueInformation, List<Referee> referees){
        leagueInformation.schedulingReferee(referees);
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
    public Referee addNewReferee(String name, RefereeType type, int id, String pass, String userName)  throws UserNameAlreadyExistException {
        Referee referee= new Referee(name,type,id,pass,userName);
        Controller controller= Controller.getInstance();
        for (HashMap.Entry me : controller.getUsers().entrySet()) {
            if(me.getKey().equals(userName)){
                throw new UserNameAlreadyExistException();
            }
        }
        controller.addUser(userName,referee);
        Log.getInstance().writeToLog("Add new referee. id:"+referee.getId());
        return referee;

    }   //UC-30

    /**
     * Remove referee- Before deleting,we check the referee has some games to judge.
     * If so we will use the function: 'manuallChangingReferee' to manuall Changing Referee.
     * @param referee
     * @throws IllegalInputException
     */
    //UC-31
    public void removeReferee(Referee referee) throws IllegalInputException, NoSuchAUserNamedException {
        Controller controller = Controller.getInstance();
        String userName = referee.getUserName();

        for(Game g:referee.getGames()) {
            referee.removeAlert(g);
        }

        if (referee.getFutureGames().size() > 0) {
            throw new IllegalInputException();
        }
        controller.removeUser(userName);
    }

    /**
     * Manually swapping all games that the referee we want to delete should be judged in the future
     * @param leagueInformation
     * @param referees
     * @param referee
     */
    public void manualChangingReferee(LeagueInformation leagueInformation, List<Referee> referees, Referee referee){
        for(Game game:leagueInformation.getGames()){
            for(Referee newReferee:referees){
                if(newReferee.equals(referee)){//skip the old referee!
                    continue;
                }
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

    public void addSeason(int year){
        Season season=new Season(year);
        controller.addSeason(season);
    }
    public void getSeasonFromController(String year){
        controller.getSeason(year);
    }

    /**
     * financial report by the order of the association football
     * @param team to get financial report about it
     * @return
     */
    public FinancialReport getFinancialReport(Team team){
        Log.getInstance().writeToLog("The football association representative got financial report about the team:"+team.getName()+" id's representative:"+getId());
        return team.getFinancialReport();
    }




    //public void addBudgetRule(String rule){} //UC-33

    //public void editGameSchedulingPolicy(ITeamAllocatePolicy iTeamAllocatePolicy){} //UC-34

    //public void initialScoreSchedulingPlicy(League league , Season season , IScoreMethodPolicy iScoreMethodPolicy){}; //UC-36

    //public void editScoreSchedulingPolicy(League league , Season season , IScoreMethodPolicy iScoreMethodPolicy){} //UC-37


    public HashMap<Integer, LeagueInformation> getLeagueInformations() {
        return leagueInformations;
    }
}
package ServiceLayer;

import ServiceLayer.Exceptions.CantSchedulingRefereeWithoutGames;
import ServiceLayer.Exceptions.IsNotStartOFSeason;
import ServiceLayer.Exceptions.LeagueNameAlreadyExist;
import System.Enum.RefereeType;
import System.Exeptions.IllegalInputException;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.League;
import System.Controller;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.FootballObjects.Team.IScoreMethodPolicy;
import System.FootballObjects.Team.ITeamAllocatePolicy;
import System.FootballObjects.Team.Team;
import System.Users.FootballAssosiation;
import System.Users.Referee;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FootballAssosiationController {

    //<editor-fold desc="Getters">
    /**
     * Get all teams in the system
     * @return
     */
    public List<Team> getAllTeams(){
        return Controller.getInstance().getAllTeams();
    }
    /**
     * Get all leagues in the system
     * @return
     */
    public List<League> getAllLeague(){
        return Controller.getInstance().getAllLeagues();
    }

    /**
     * Get all referee in the system
     * @return
     */
    public List<Referee> getAllReferee(){
        return Controller.getInstance().getAllReferee();
    }

    /**
     * Get all LeagueInformation of FootballAssosiation
     * @param footballAssosiation
     * @return
     */
    public List<LeagueInformation> getMyLeagueInformation(FootballAssosiation footballAssosiation){
        List<LeagueInformation> leagueInformations=new LinkedList<>();
        for(Map.Entry l: footballAssosiation.getLeagueInformations().entrySet()){
            leagueInformations.add((LeagueInformation)l.getValue());
        }
        return leagueInformations;
    }
    //</editor-fold>

    //<editor-fold desc="Methods">
    /**
     * Create a new league in the system and place teams there
     * @param name
     * @param teams
     * @return
     * @throws LeagueNameAlreadyExist
     */
    public League initEmptyLeague(String name, List<Team> teams) throws LeagueNameAlreadyExist {
        Controller controller= Controller.getInstance();
        if(controller.isLeagueExist(name)){
            throw new LeagueNameAlreadyExist();
        }
        League league=new League(name, teams);
        controller.addLeague(league);
        return league;
    }

    /**
     * The function init empty LeagueInformation by linking the season and league
     * after the object is created the football association can allocate games and referees
     * @param footballAssosiation
     * @param league
     * @param year
     * @return
     */
    public LeagueInformation initLeague(FootballAssosiation footballAssosiation,League league, String year){
        //Check if season already exist in the system
        Controller controller=Controller.getInstance();
        Season season= controller.getSeason(year);
        if(season==null){
            season=new Season(Integer.valueOf(year));
        }

        //create empty LeagueInformation
        return footballAssosiation.initLeague(season,league);
    }

    /**
     * Add a new referee to system
     * @param footballAssosiation
     * @param name
     * @param type
     * @param id
     * @param pass
     * @param userName
     * @return
     * @throws UserNameAlreadyExistException
     */
    public Referee addReferee(FootballAssosiation footballAssosiation,String name, RefereeType type, int id, String pass, String userName) throws UserNameAlreadyExistException {
        Referee referee=footballAssosiation.addNewReferee(name,type,id,pass,userName);
        return referee;
    }

    /**
     * Remove exist referee from the system
     * if there are games that are related to the referee it cannot be deleted
     * @param footballAssosiation
     * @param referee
     * @throws IllegalInputException
     */
    public void removeReferee(FootballAssosiation footballAssosiation, Referee referee) throws IllegalInputException, NoSuchAUserNamedException {
        footballAssosiation.removeReferee(referee);
    }

    /**
     * Scheduling referee for LeagueInformation
     * only if exist game to LeagueInformation
     * @param footballAssosiation
     * @param leagueInformation
     * @param referees
     * @throws CantSchedulingRefereeWithoutGames
     */
    public void schedulingReferee(FootballAssosiation footballAssosiation,LeagueInformation leagueInformation, List<Referee> referees) throws CantSchedulingRefereeWithoutGames {
        if(!leagueInformation.getGames().isEmpty()){
            throw new CantSchedulingRefereeWithoutGames();
        }
        footballAssosiation.schedulingReferee(leagueInformation,referees);
    }

    /**
     * Edit score policy at the beginning of the season
     * @param leagueInformation
     * @param policy
     * @throws IsNotStartOFSeason
     */
    public void editScorePolicy(LeagueInformation leagueInformation, IScoreMethodPolicy policy) throws IsNotStartOFSeason {
        if(isStartOfSeason(leagueInformation)){
            leagueInformation.editScoreSchedulingPolicy(policy);
        }
        throw new IsNotStartOFSeason();
    }

    /**
     * Edit team allocate policy at the beginning of the season
     * @param leagueInformation
     * @param policy
     * @throws IsNotStartOFSeason
     */
    public void editTeamAllocatePolicy(LeagueInformation leagueInformation, ITeamAllocatePolicy policy) throws IsNotStartOFSeason {
        if(isStartOfSeason(leagueInformation)){
            leagueInformation.editGameSchedulingPolicy(policy);
        }
        throw new IsNotStartOFSeason();
    }

    /**
     * Scheduling games for LeagueInformation
     * @param footballAssosiation
     * @param leagueInformation
     */
    public void schedulingGames(FootballAssosiation footballAssosiation, LeagueInformation leagueInformation){
        footballAssosiation.initLeagueInformation(leagueInformation);
    }

    /**
     * Replace referee from exist and delete referee user
     * @param footballAssosiation
     * @param leagueInformation
     * @param referees
     * @param referee
     * @throws IllegalInputException
     * @throws NoSuchAUserNamedException
     */
    public void replaceReferee(FootballAssosiation footballAssosiation, LeagueInformation leagueInformation, List<Referee> referees, Referee referee) throws IllegalInputException, NoSuchAUserNamedException {
        footballAssosiation.manualChangingReferee(leagueInformation,referees,referee);
        footballAssosiation.removeReferee(referee);//remove referee's user after chang
    }
    //</editor-fold>

    //<editor-fold desc="Private Methods">
    private boolean isStartOfSeason(LeagueInformation leagueInformation){
        if(leagueInformation.getGames().isEmpty()){
            return true;
        }
        return false;
    }
    //</editor-fold>


}
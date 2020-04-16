package ServiceLayer;
import ServiceLayer.Exceptions.*;
import System.Enum.RefereeType;
import System.Exeptions.IllegalInputException;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.Game;
import System.FootballObjects.League;
import System.Controller;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.FootballObjects.Team.IScoreMethodPolicy;
import System.FootballObjects.Team.ITeamAllocatePolicy;
import System.FootballObjects.Team.Team;
import System.Users.FootballAssociation;
import System.Users.Referee;
import System.Users.TeamOwner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FootballAssosiationController {

    private static FootballAssosiationController ourInstance = new FootballAssosiationController();

    //<editor-fold desc="Constructor">
    private FootballAssosiationController(){};
    //</editor-fold>

    //<editor-fold desc="Getters">
    public static FootballAssosiationController getInstance() {
        return ourInstance;
    }

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
     * Get all team owner in the system
     * @return
     */
    public List<TeamOwner> getAllTeamOwner(){
        return Controller.getInstance().getAllTeamOwner();
    }

    /**
     * Get all LeagueInformation of FootballAssociation
     * @param footballAssociation
     * @return
     */
    public List<LeagueInformation> getMyLeagueInformation(FootballAssociation footballAssociation){
        List<LeagueInformation> leagueInformations=new LinkedList<>();
        for(Map.Entry l: footballAssociation.getLeagueInformations().entrySet()){
            leagueInformations.add((LeagueInformation)l.getValue());
        }
        return leagueInformations;
    }

    /**
     * Get all games for League information
     * @param leagueInformation
     * @return
     */
    public List<Game> getAllGames(LeagueInformation leagueInformation){
        return leagueInformation.getGames();
    }

    /**
     * Get League table
     * @param leagueInfo
     * @return
     */
    public HashMap<Team,Integer> getLeagueTable(LeagueInformation leagueInfo){
        return leagueInfo.getLeagueTable();
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
     * @param footballAssociation
     * @param league
     * @param year
     * @return
     */
    public LeagueInformation initLeague(FootballAssociation footballAssociation, League league, String year){
        //Check if season already exist in the system
        Controller controller=Controller.getInstance();
        Season season= footballAssociation.getSeasonFromController(year);
        if(season==null){
            season=new Season(Integer.valueOf(year));
            controller.addSeason(season);
        }

        //create empty LeagueInformation
        return footballAssociation.initLeague(season,league);
    }

    /**
     * Add a new referee to system
     * @param footballAssociation
     * @param name
     * @param type
     * @param id
     * @param pass
     * @param userName
     * @return
     * @throws UserNameAlreadyExistException
     */
    public Referee addReferee(FootballAssociation footballAssociation, String name, RefereeType type, int id, String pass, String userName) throws UserNameAlreadyExistException {
        Referee referee= footballAssociation.addNewReferee(name,type,id,pass,userName);
        return referee;
    }

    /**
     * Remove exist referee from the system
     * if there are games that are related to the referee it cannot be deleted
     * @param footballAssociation
     * @param referee
     * @throws IllegalInputException
     */
    public void removeReferee(FootballAssociation footballAssociation, Referee referee) throws IllegalInputException, NoSuchAUserNamedException {
        footballAssociation.removeReferee(referee);
    }

    /**
     * Scheduling referee for LeagueInformation
     * only if exist game to LeagueInformation
     * @param footballAssociation
     * @param leagueInformation
     * @param referees
     * @throws CantSchedulingRefereeWithoutGames
     */
    public void schedulingReferee(FootballAssociation footballAssociation, LeagueInformation leagueInformation, List<Referee> referees) throws CantSchedulingRefereeWithoutGames, MustHaveLeastOneMainReferee, MustHaveLeastTwoSideReferee {
        if(leagueInformation.getGames().isEmpty()){
            throw new CantSchedulingRefereeWithoutGames();
        }
        int mainNum=0;
        int sideNum=0;
        for(Referee ref:referees){
            if(ref.getRefereeType()==RefereeType.MAIN){
                mainNum++;
            }else if(ref.getRefereeType()== RefereeType.ASSISTANT){
                sideNum++;
            }
        }

        if(mainNum==0){
            throw new MustHaveLeastOneMainReferee();
        }
        if(sideNum<2){
            throw new MustHaveLeastTwoSideReferee();
        }

        footballAssociation.schedulingReferee(leagueInformation,referees);
    }

    /**
     * Edit score policy at the beginning of the season
     * @param leagueInformation
     * @param policy
     * @throws IsNotStartOFSeason
     */
    public void editScorePolicy(LeagueInformation leagueInformation,IScoreMethodPolicy policy) throws IsNotStartOFSeason {
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
            return;
        }
        throw new IsNotStartOFSeason();
    }

    /**
     * Scheduling games for LeagueInformation
     * @param footballAssociation
     * @param leagueInformation
     */
    public void schedulingGames(FootballAssociation footballAssociation, LeagueInformation leagueInformation) throws MustHaveLeastTwoTeams {
        if(leagueInformation.getLeague().getTeams().size()<2){
            throw new MustHaveLeastTwoTeams();
        }
        footballAssociation.initLeagueInformation(leagueInformation);
    }

    /**
     * Replace referee from exist and delete referee user
     * @param footballAssociation
     * @param leagueInformation
     * @param referees
     * @param referee
     * @throws IllegalInputException
     * @throws NoSuchAUserNamedException
     */
    public void replaceReferee(FootballAssociation footballAssociation, LeagueInformation leagueInformation, List<Referee> referees, Referee referee) throws IllegalInputException, NoSuchAUserNamedException {
        footballAssociation.manualChangingReferee(leagueInformation,referees,referee);
        footballAssociation.removeReferee(referee);//remove referee's user after chang
    }

    /**
     * Create a new team to system
     * @param teamName
     * @param teamOwner
     * @return
     */
    public Team createTeam(String teamName, TeamOwner teamOwner){
        Team team= new Team(teamName,teamOwner);
        Controller.getInstance().addTeam(team);
        teamOwner.addTeamToMyTeamList(team);
        return team;
    }

    /**
     * Update game result and score in league table
     * @param game
     * @param homeScore
     * @param awayScore
     */
    public void updateResultToGame(Game game, int homeScore, int awayScore) {
        game.setResult(homeScore,awayScore);
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

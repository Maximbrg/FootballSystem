package System.Users;

import System.FootballObjects.League;
import System.FootballObjects.Season;
import System.FootballObjects.Team.IScoreMethodPolicy;
import System.FootballObjects.Team.ITeamAllocatePolicy;
import System.FootballObjects.Team.Team;
import System.Controller;
import System.BudgetRules;

import java.util.List;

public class FootballAssosiation extends User {

    private String name;
    private Controller controller;
    private BudgetRules budgetRules;

    //Methods
    public void initLeague(List<Team> team, List<Referee> referees) {
    } //UC-29

    public void addNewReferee() {
    }   //UC-30

    public void removeReferee(Referee referee) {
    } //UC-31

    public void addBudgetRule(String rule){} //UC-33

    public void editGameSchedulingPolicy(ITeamAllocatePolicy iTeamAllocatePolicy){} //UC-34

    public void initialScoreSchedulingPlicy(League league , Season season , IScoreMethodPolicy iScoreMethodPolicy){}; //UC-36

    public void editScoreSchedulingPolicy(League league , Season season , IScoreMethodPolicy iScoreMethodPolicy){} //UC-37

}
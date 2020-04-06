package System.Users;
import System.FinancialReport;
import System.Asset.Asset;
import System.Enum.TeamStatus;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;

import java.util.List;

public class TeamOwner extends User implements Asset, IObserverTeam {

    private Couch selfCouch; // if he also Couch otherwise null
    private TeamManager selfTeamManager; // if he also TeamManager otherwise null
    private Player selfPlayer; // if he also Player otherwise null
    private List<Team> teamList;
    private int salary;


    //Methods

    /**
     * restart a team status - open a team
     * @param team - to restart
     */
    public void RestartTeam(Team team){
        team.setTeamStatus(TeamStatus.Active);
    } //UC-23

    /**
     * team owner create financial report
     * @param team to produce financial report
     */
    public void getFinancialReport(Team team){
        FinancialReport fReport = new FinancialReport(team);
        team.setFinancialReport(fReport);
    } //UC-24 /* need to open a class of financial reports and store their the financial reports and that all football association can view it */

    @Override
    public void edit() {

    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void update() {


    }
}

package System.Users;

import System.Asset.Asset;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;

import java.util.List;

public class TeamOwner extends User implements Asset, IObserverTeam {

    private String name;
    private Couch selfCouch; // if he also Couch otherwise null
    private TeamManager selfTeamManager; // if he also TeamManager otherwise null
    private Player selfPlayer; // if he also Player otherwise null
    private List<Team> teamList;

    //Methods
    public void openTeam(Team team){} //UC-23

    public void sendFinancialReport(Team team){
    } //UC-24 /* need to open a class of financial reports and store their the financial reports and that all football association can vie it */

    @Override
    public void edit() {

    }

    @Override
    public void update() {

    }
}

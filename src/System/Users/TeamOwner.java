package System.Users;
//<editor-fold desc="imports">
import System.FinancialReport;
import System.Asset.Asset;
import System.Enum.TeamStatus;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//</editor-fold>

public class TeamOwner extends User implements IObserverTeam {

    private String name;
    private Couch selfCouch; // if he also Couch otherwise null
    private TeamManager selfTeamManager; // if he also TeamManager otherwise null
    private Player selfPlayer; // if he also Player otherwise null
    private int salary;
    private List<ISubjectTeam> subjectTeam;
    private List<Team> teamList;
    private HashMap<Team,List<TeamOwner>> teamOwnersWhichIappointed;

    //<editor-fold desc="Constructor">
    public TeamOwner(int id, String password, String userName, TeamStatus status, String name, Couch selfCouch, TeamManager selfTeamManager, Player selfPlayer, int salary, List<Team> teamList, HashMap<Team, List<TeamOwner>> teamOwnersWhichIappointed) {
        super(id, password, userName, status);
        this.name = name;
        this.selfCouch = selfCouch;
        this.selfTeamManager = selfTeamManager;
        this.selfPlayer = selfPlayer;
        this.salary = salary;
        this.subjectTeam=new LinkedList<>();
        this.teamList = teamList;
        this.teamOwnersWhichIappointed = teamOwnersWhichIappointed;
    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public String getName() {
        return name;
    }

    public Couch getSelfCouch() {
        return selfCouch;
    }

    public TeamManager getSelfTeamManager() {
        return selfTeamManager;
    }

    public Player getSelfPlayer() {
        return selfPlayer;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public int getSalary() {
        return salary;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setSelfCouch(Couch selfCouch) {
        this.selfCouch = selfCouch;
    }

    public void setSelfTeamManager(TeamManager selfTeamManager) {
        this.selfTeamManager = selfTeamManager;
    }

    public void setSelfPlayer(Player selfPlayer) {
        this.selfPlayer = selfPlayer;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }
    //</editor-fold>

    //<editor-fold desc="Methods">
    /**
     *
     * @param team
     * @param newTeamOwner
     */
    public void addTeamOwner(Team team, TeamOwner newTeamOwner){
        List<TeamOwner> teamOwnersList=team.getTeamOwnerList(this);
        teamOwnersList.add(newTeamOwner);
        team.setListOfOwnersWhichIappoint(this,teamOwnersList);
        this.teamOwnersWhichIappointed.remove(team);
        this.teamOwnersWhichIappointed.put(team,teamOwnersList);
    }//---UC 18

    /**
     *
     * @param team
     * @param teamOwnerToRemove
     */
    public void removeTeamOwner(Team team, TeamOwner teamOwnerToRemove){
        List<TeamOwner> teamOwnersList=team.getTeamOwnerList(this);
        teamOwnersList.remove(teamOwnerToRemove);
        team.setListOfOwnersWhichIappoint(this,teamOwnersList);
        this.teamOwnersWhichIappointed.remove(team);
        this.teamOwnersWhichIappointed.put(team,teamOwnersList);
    }//---UC 19


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
    //</editor-fold>

    //<editor-fold desc="Override Methods">
    @Override
    public void update() {

    }

    /**
     * Add a team to get alert (adding to subjectGame list)
     * @param iSubjectTeam
     */
    @Override
    public void registerAlert(ISubjectTeam iSubjectTeam){
        this.subjectTeam.add(iSubjectTeam);
    }

    /**
     * Remove a team to get alert (adding to subjectGame list)
     * @param iSubjectTeam
     */
    @Override
    public void removeAlert(ISubjectTeam iSubjectTeam) {
        this.subjectTeam.remove(iSubjectTeam);
    }
    //</editor-fold>
}

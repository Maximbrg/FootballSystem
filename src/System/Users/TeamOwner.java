package System.Users;
//<editor-fold desc="imports">
import System.Exeptions.StillNoAppointedOwner;
import System.Exeptions.TeamStatusException;
import System.FinancialReport;
import System.Enum.TeamStatus;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import System.Log;
//</editor-fold>

public class TeamOwner extends User implements IObserverTeam {

    private Coach selfCoach; // if he also Coach otherwise null
    private TeamManager selfTeamManager; // if he also TeamManager otherwise null
    private Player selfPlayer; // if he also Player otherwise null
    private int salary;
    private List<ISubjectTeam> subjectTeam;
    private List<Team> teamList;
    private HashMap<Team,LinkedList<TeamOwner>> teamOwnersWhichIappointed;

    /**
     * Initialize variables
     * @param id
     * @param password
     * @param userName
     * @param name
     * @param salary
     */
    //<editor-fold desc="Constructor">
    public TeamOwner(int id, String name, String password, String userName,int salary) {
        super(id,name, password, userName);
        this.selfCoach = null;
        this.selfTeamManager = null;
        this.selfPlayer = null;
        this.salary = salary;
        this.subjectTeam=new LinkedList<>();
        this.teamList = new LinkedList<>();
        this.teamOwnersWhichIappointed = new HashMap<>();
    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public String getName() {
        return name;
    }

    public Coach getSelfCoach() {
        return selfCoach;
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

    public void setSelfCoach(Coach selfCoach) {
        this.selfCoach = selfCoach;
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
     * This function allows the owner to appoint a group of other owners according to UC 18.
     * @param team
     * @param newTeamOwner
     */
    public void addTeamOwner(Team team, TeamOwner newTeamOwner){
        newTeamOwner.addTeamToMyTeamList(team);
        LinkedList<TeamOwner> teamOwnersList=this.teamOwnersWhichIappointed.get(team);
        if(teamOwnersList!=null){
            teamOwnersList.add(newTeamOwner);
        }
        else {
            teamOwnersList = new LinkedList<TeamOwner>();
            teamOwnersList.add(newTeamOwner);
        }
        this.teamOwnersWhichIappointed.put(team,teamOwnersList);
        team.setListOfOwnersWhichIappoint(this,teamOwnersWhichIappointed.get(team));
        team.addOwnerToTeamOwnersList(newTeamOwner);
        Log.getInstance().writeToLog("Team owner : "+getName()+", id : "+getId() +"was added a new team owner to his team.  "
        +"team name : " + team.getName()+" , team id :"+team.getId()+". The owner name which was added : "+ newTeamOwner.getName()+
                " , owner id : " + newTeamOwner.getId()+" .");
    }//---UC 18

    /**
     * This function allows the owner to remove team owner which his appointed - according to UC 19.
     * @param team
     * @param teamOwnerToRemove
     */
    public void removeTeamOwner(Team team, TeamOwner teamOwnerToRemove) throws StillNoAppointedOwner {
        LinkedList<TeamOwner> teamOwnersList=team.getTeamOwnerListOfThisOwner(this);
        if(teamOwnersList!=null && teamOwnersList.size()!=0){
        teamOwnersList.remove(teamOwnerToRemove);
        team.setListOfOwnersWhichIappoint(this,teamOwnersList);
        this.teamOwnersWhichIappointed.remove(team);
        this.teamOwnersWhichIappointed.put(team,teamOwnersList);
        team.removeOwnerFromTeamOwnersList(teamOwnerToRemove);
        Log.getInstance().writeToLog("Team owner : "+getName()+", id : "+getId() +"was removed team owner from his team.  "
                    +"team name : " + team.getName()+" , team id :"+team.getId()+". The owner name which was removed : "+ teamOwnerToRemove.getName()+
                    " , owner id : " + teamOwnerToRemove.getId()+" .");
        }
        else
            throw new StillNoAppointedOwner();
    }//---UC 19


    /**
     * Restart team status - reopen team
     * @param team - to restart
     */
    public void restartTeam(Team team) throws TeamStatusException {
        if(team.getTeamStatus()==TeamStatus.Close) {
            team.setTeamStatus(TeamStatus.Active);
            team.notifyTeamOwnersAndManager(getName() + " was open again.");
            Log.getInstance().getInstance().writeToLog(getName() + " was open again");
        }else{
            Log.getInstance().getInstance().writeToLog(getName() + " try to restart a open/permanently team");
            throw new TeamStatusException();
        }
    } //UC-23

    /**
     * Team owner create financial report
     * @param team to produce financial report
     */
    public FinancialReport addFinancialReport(Team team){
        FinancialReport fReport = new FinancialReport(team, new Date());
        team.addFinancialReport(fReport);
        Log.getInstance().writeToLog("A new financial report was set to team : "+ team.getName()+" , id :"+ team.getId());
        return fReport;
    } //UC-24

    public void addTeamToMyTeamList(Team t){
        this.teamList.add(t);
        Log.getInstance().writeToLog("Team : "+ t.getName()+" , id :"+ t.getId()+ "was added to the teams list of : "+ this.getName()+
                " , id :"+ this.getId());
    }
    //</editor-fold>

    //<editor-fold desc="Override Methods">
    @Override
    public void update(String msg) {
        Log.getInstance().writeToLog("TeamOwner was updated about :"+msg+". id's TeamOwner:"+getId());

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

package System.FootballObjects.Team;

import System.Asset.Asset;
import System.Enum.TeamStatus;
import System.FootballObjects.Field;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;
import System.Users.TeamManager;
import System.Users.TeamOwner;
import System.Users.User;
import System.FinancialReport;

import java.util.HashMap;
import java.util.List;

public class Team implements IPageAvailable, ISubjectTeam {

    private static int id;
    private String name;
    private TeamStatus teamStatus;
    private PersonalPage personalPage;
    private List<Asset> assets;
    private List<IObserverTeam> iObserverTeams;
    private HashMap <TeamOwner , List<TeamOwner >> teamOwnerListHashMap;
    private HashMap<TeamManager , List<TeamManager>> teamManagers;
    private Field field;
    private int income;
    private int expense;
    private FinancialReport financialReport;

    //Constructor
    public Team(){}

    public static void setId(int id) {
        Team.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeamStatus(TeamStatus teamStatus) {
        this.teamStatus = teamStatus;
    }

    public void setPersonalPage(PersonalPage personalPage) {
        this.personalPage = personalPage;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public void setiObserverTeams(List<IObserverTeam> iObserverTeams) {
        this.iObserverTeams = iObserverTeams;
    }

    public void setTeamOwnerListHashMap(HashMap<TeamOwner, List<TeamOwner>> teamOwnerListHashMap) {
        this.teamOwnerListHashMap = teamOwnerListHashMap;
    }
    public void setFinancialReport(FinancialReport financialReport) {
        this.financialReport=financialReport;
    }

    public void setTeamManagers(HashMap<TeamManager, List<TeamManager>> teamManagers) {
        this.teamManagers = teamManagers;
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Field getField() {
        return field;
    }

    public int getIncome() {
        return income;
    }
    public int getExpense() {
        return expense;
    }

    public int getPaymentSalary() {
        int sum=0;
        for(Asset a : assets){
            sum+=a.getSalary();
        }
        return sum;
    }

    public TeamStatus getTeamStatus() {
        return teamStatus;
    }

    public PersonalPage getPersonalPage() {
        return personalPage;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public List<IObserverTeam> getiObserverTeams() {
        return iObserverTeams;
    }

    public HashMap<TeamOwner, List<TeamOwner>> getTeamOwnerListHashMap() {
        return teamOwnerListHashMap;
    }

    public HashMap<TeamManager, List<TeamManager>> getTeamManagers() {
        return teamManagers;
    }

    //Methods
    public void addAsset(Asset asset){} //UC-15

    public void removeAsset(Asset asset){} //UC-16

    public void editAsset(Asset asset /* need to add argument of the new Details */ ){} //UC-17

    public void addTeamOwner(TeamOwner teamOwner , TeamOwner teamOwnerNew){} //UC-18

    public void removeTeamOwner(TeamOwner teamOwner){} //UC-19

    public void addTeamManager(TeamManager teamManager){} //UC-20

    public void removeTeamManager(TeamManager teamManager){} //UC-21

    public void closeTeam(){} //UC-22


    @Override
    public String showDetails() {
        return null;
    }

    @Override
    public void removeAlert(User user) {

    }

    @Override
    public void registerAlert(User user) {

    }

    @Override
    public void notifyUsers(User user) {

    }

    public void PermanentlyCloseTeam() {
    }



}

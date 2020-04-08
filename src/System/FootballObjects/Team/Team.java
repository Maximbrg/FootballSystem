//<editor-fold desc="imports">
package System.FootballObjects.Team;
import System.Asset.Asset;
import System.Enum.TeamStatus;
import System.FootballObjects.Field;
import System.FootballObjects.Game;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;
import System.Users.*;
import System.FinancialReport;
import System.IShowable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//</editor-fold>

public class Team implements IPageAvailable, ISubjectTeam, IShowable {

    private static int id;
    private String name;
    private TeamStatus teamStatus;
    private PersonalPage personalPage;
    private List<Asset> assets;

    private List<Game> gamesOfTeams;

    private List<IObserverTeam> iObserverTeamListForPlayers;
    private List<IObserverTeam> iObserverTeamListForFields;
    private List<IObserverTeam> iObserverTeamListForTeamManagers;
    private List<IObserverTeam> iObserverTeamListForFanCouches;

    private List<TeamManager> teamManagersList;
    private HashMap<TeamOwner,List<TeamOwner>> teamOwnersWhichappointed;


    private Field field;
    private int income;
    private int expense;
    private FinancialReport financialReport;


    //<editor-fold desc="Constructor">
    public Team(String name, TeamStatus teamStatus, PersonalPage personalPage, List<Asset> assets, List<TeamManager> teamManagersList, HashMap<TeamOwner, List<TeamOwner>> teamOwnersWhichappointed, Field field, int income, int expense, FinancialReport financialReport) {
        this.name = name;
        this.teamStatus = teamStatus;
        this.personalPage = personalPage;
        this.assets = assets;
        this.teamManagersList = teamManagersList;
        this.teamOwnersWhichappointed = teamOwnersWhichappointed;
        this.field = field;
        this.income = income;
        this.expense = expense;
        this.financialReport = financialReport;

        this.iObserverTeamListForPlayers=new LinkedList<>();
        this.iObserverTeamListForFields=new LinkedList<>();
        this.iObserverTeamListForTeamManagers=new LinkedList<>();
        this.iObserverTeamListForFanCouches=new LinkedList<>();

        this.gamesOfTeams= new ArrayList<>();

    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public static int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Team";
    }

    @Override
    public String getDetails() {
        String str = "@name:"+name;
        return str;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public List<IObserverTeam> getiObserverTeamListForPlayers() {
        return iObserverTeamListForPlayers;
    }

    public List<IObserverTeam> getiObserverTeamListForFields() {
        return iObserverTeamListForFields;
    }

    public List<IObserverTeam> getiObserverTeamListForTeamManagers() {
        return iObserverTeamListForTeamManagers;
    }

    public List<IObserverTeam> getiObserverTeamListForFanCouches() {
        return iObserverTeamListForFanCouches;
    }

    public List<TeamManager> getTeamManagersList() {
        return teamManagersList;
    }

    public HashMap<TeamOwner, List<TeamOwner>> getTeamOwnersWhichappointed() {
        return teamOwnersWhichappointed;
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

    public FinancialReport getFinancialReport() {
        return financialReport;
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

    public List<TeamOwner> getTeamOwnerList(TeamOwner appointedTeamOwner){
        return this.teamOwnersWhichappointed.get(appointedTeamOwner);
    }

    public List<Game> getGamesOfTeams(){
        return gamesOfTeams;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
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

    public void setiObserverTeamListForPlayers(List<IObserverTeam> iObserverTeamListForPlayers) {
        this.iObserverTeamListForPlayers = iObserverTeamListForPlayers;
    }

    public void setiObserverTeamListForFields(List<IObserverTeam> iObserverTeamListForFields) {
        this.iObserverTeamListForFields = iObserverTeamListForFields;
    }

    public void setiObserverTeamListForTeamManagers(List<IObserverTeam> iObserverTeamListForTeamManagers) {
        this.iObserverTeamListForTeamManagers = iObserverTeamListForTeamManagers;
    }

    public void setiObserverTeamListForFanCouches(List<IObserverTeam> iObserverTeamListForFanCouches) {
        this.iObserverTeamListForFanCouches = iObserverTeamListForFanCouches;
    }

    public void setTeamManagersList(List<TeamManager> teamManagersList) {
        this.teamManagersList = teamManagersList;
    }

    public void setTeamOwnersWhichappointed(HashMap<TeamOwner, List<TeamOwner>> teamOwnersWhichappointed) {
        this.teamOwnersWhichappointed = teamOwnersWhichappointed;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public void setFinancialReport(FinancialReport financialReport) {
        this.financialReport = financialReport;
    }

    public void setListOfOwnersWhichIappoint(TeamOwner teamOwner,List<TeamOwner>newList){
        this.teamOwnersWhichappointed.replace(teamOwner,this.teamOwnersWhichappointed.get(teamOwner),newList);
    }
    //</editor-fold>

    //<editor-fold desc="Team Methods">
    /**
     *
     * @param asset
     * add asset to the team assets
     * according to UC-15
     *
     */
    public void addAsset(Asset asset){
        if(asset!=null) {
            this.assets.add(asset);
            asset.addMyTeam(this);//connect this team to the asset
        }
    } //UC-15

    /**
     *
     * @param asset
     * remove asset from the team assets
     * according to UC-16
     *
     */
    public void removeAsset(Asset asset){
        if(asset!=null){
            this.assets.remove(asset);
            asset.resetMyTeam();//delete this team from the asset
        }
    } //UC-16


    public void editAsset(Asset asset ,int val){
        asset.editAssetValue(val);
    } //UC-17


    /**
     *
     * @param teamManagerNew
     * according to UC-20
     */
    public void addTeamManager(TeamManager teamManagerNew){
        this.teamManagersList.add(teamManagerNew);
    } //UC-20

    /**
     *
     * @param teamManager
     * according to UC-21
     */
    public void removeTeamManager(TeamManager teamManager){
        this.teamManagersList.remove(teamManager);
    } //UC-21

    /**
     * Close team
     * according to UC-22
     */
    public void closeTeam(){
        this.setTeamStatus(TeamStatus.Close);
    } //UC-22

    /**
     * Close team permanently
     */
    public void PermanentlyCloseTeam(){
        this.setTeamStatus(TeamStatus.PermantlyClose);
        for (Asset a:assets) {
            a.resetMyTeam();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Override Methods">
    @Override
    public String showDetails() {
        return null;
    }


    @Override
    public void registerPlayerToAlert(IObserverTeam player) {
        this.iObserverTeamListForPlayers.add(player);
        player.registerAlert(this);
    }

    @Override
    public void registerFieldToAlert(IObserverTeam field) {
        this.iObserverTeamListForFields.add(field);
        field.registerAlert(this);
    }

    @Override
    public void registerTeamManagerToAlert(IObserverTeam teamManager) {
        this.iObserverTeamListForTeamManagers.add(teamManager);
        teamManager.registerAlert(this);
    }

    @Override
    public void registerCouchToAlert(IObserverTeam couch) {
        this.iObserverTeamListForFanCouches.add(couch);
        couch.registerAlert(this);
    }

    @Override
    public void removeAlertToPlayer(IObserverTeam player) {
        this.iObserverTeamListForPlayers.remove(player);
        player.removeAlert(this);
    }

    @Override
    public void removeAlertToField(IObserverTeam field) {
        this.iObserverTeamListForFields.remove(field);
        field.removeAlert(this);
    }

    @Override
    public void removeAlertToTeamManager(IObserverTeam teamManager) {
        this.iObserverTeamListForTeamManagers.remove(teamManager);
        teamManager.removeAlert(this);
    }

    @Override
    public void removeAlertToCouch(IObserverTeam couch) {
        this.iObserverTeamListForFanCouches.remove(couch);
        couch.removeAlert(this);
    }

    @Override
    public void notifyPlayer() {
        for (IObserverTeam player: this.iObserverTeamListForPlayers)
            player.update();
    }

    @Override
    public void notifyField() {
        for (IObserverTeam field: this.iObserverTeamListForFields)
            field.update();
    }

    @Override
    public void notifyTeamManager() {
        for (IObserverTeam teamManager: this.iObserverTeamListForTeamManagers)
            teamManager.update();
    }

    @Override
    public void notifyCouch() {
        for (IObserverTeam couch: this.iObserverTeamListForFanCouches)
            couch.update();
    }
    //</editor-fold>


}

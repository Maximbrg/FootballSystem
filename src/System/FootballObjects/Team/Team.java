//<editor-fold desc="imports">
package System.FootballObjects.Team;
import System.Asset.Asset;
import System.Enum.TeamStatus;
import System.Exeptions.HasTeamAlreadyException;
import System.Exeptions.PersonalPageAlreadyExist;
import System.FootballObjects.Field;
import System.FootballObjects.Game;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;
import System.Users.*;
import System.FinancialReport;
import System.IShowable;
import System.Log;

import java.util.*;
//</editor-fold>

public class Team implements IPageAvailable, ISubjectTeam, IShowable {

    private static int id;
    private String name;
    private TeamStatus teamStatus;
    private PersonalPage personalPage;
    private List<Asset> assets;
    private List<Player> players;
    private List<Game> gamesOfTeams;

    private List<IObserverTeam> iObserverTeamListForSystemManagers;
    private List<IObserverTeam> iObserverTeamListForTeamOwnersAndManagers;


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
        this.iObserverTeamListForSystemManagers=new LinkedList<>();
        this.iObserverTeamListForTeamOwnersAndManagers=new LinkedList<>();
        this.gamesOfTeams= new ArrayList<>();
        this.players = new LinkedList<>();

    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public int getId() {
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

    public List<Game> getFutureGames(){
        List<Game> futureGames = new ArrayList<>();
        for(Game game:gamesOfTeams){
            long diffHours =  (new Date(System.currentTimeMillis()).getTime()-game.getDate().getTime() ) / (60 * 60 * 1000);
            if(diffHours<=0){
                futureGames.add(game);
            }
        }
        Collections.sort(futureGames, new Comparator<Game>() {
            public int compare(Game o1, Game o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        Log.writeToLog("The Team "+getName()+"id: "+getId() +" pull his future games.");
        return futureGames;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setId(int id) {
        this.id = id;
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
    public void addAsset(Asset asset) throws HasTeamAlreadyException {
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
        notifySystemManager();
        notifyTeamOwnersAndManager();//needs another notify for this

    } //UC-22

    /**
     * Close team permanently
     */
    public void PermanentlyCloseTeam(){
        this.setTeamStatus(TeamStatus.PermantlyClose);
        for (Asset a:assets) {
            a.resetMyTeam();
        }
        notifyTeamOwnersAndManager();

    }
    //</editor-fold>

    //<editor-fold desc="Override Methods">
    @Override
    public String showDetails() {
        return null;
    }

    @Override
    public void registerSystemManagerToAlert(IObserverTeam systemManager) {
        this.iObserverTeamListForSystemManagers.add(systemManager);
        systemManager.registerAlert(this);
    }

    @Override
    public void registerAlert(IObserverTeam obs) {
        this.iObserverTeamListForTeamOwnersAndManagers.add(obs);
        obs.registerAlert(this);
    }

    @Override
    public void removeAlertToSystemManager(IObserverTeam systemManager) {
        this.iObserverTeamListForSystemManagers.remove(systemManager);
        systemManager.registerAlert(this);
    }

    @Override
    public void removeAlert(IObserverTeam obs) {
        this.iObserverTeamListForTeamOwnersAndManagers.remove(obs);
        obs.removeAlert(this);
    }

    @Override
    public void notifySystemManager() {
        for(IObserverTeam observerTeam:this.iObserverTeamListForSystemManagers){
            observerTeam.update();
        }
    }

    @Override
    public void notifyTeamOwnersAndManager() {
        for (IObserverTeam observerTeam:this.iObserverTeamListForTeamOwnersAndManagers){
            observerTeam.update();
        }
    }

    @Override
    public PersonalPage createPersonalPage() throws PersonalPageAlreadyExist {
        if(personalPage!= null){
            PersonalPage newPersonalPage= new PersonalPage(this);
            this.personalPage=newPersonalPage;
        }
        throw new PersonalPageAlreadyExist();
    }

    //</editor-fold>

    public void addPlayerToTeam(Player player) {
        players.add(player);
    }

    public void removePlayerFromTeam(Player player){
        players.remove(player);
    }


}

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
    private HashMap<TeamOwner,LinkedList<TeamOwner>> teamOwners;

    private Field field;
    private int income;
    private int expense;
    private FinancialReport financialReport;

    /**
     * Initialize variables
     * @param name

     */
    //<editor-fold desc="Constructor">
    public Team(String name,TeamOwner teamOwner) {
        this.name = name;
        this.field = null;
        this.teamStatus = TeamStatus.Active;
        this.personalPage = null;
        this.assets = new LinkedList<>();
        this.teamManagersList = new LinkedList<>();
        this.teamOwners = new HashMap<>();
        teamOwners.put(teamOwner,new LinkedList<TeamOwner>());
        this.financialReport = null;
        this.iObserverTeamListForSystemManagers=new LinkedList<>();
        this.iObserverTeamListForTeamOwnersAndManagers=new LinkedList<>();
        this.gamesOfTeams= new ArrayList<>();
        this.players = new LinkedList<>();
    }

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

    /**
     * @return the team details
     */
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

    public HashMap<TeamOwner, LinkedList<TeamOwner>> getListTeamOwnersWhichappointed() {
        return teamOwners;
    }

    public LinkedList<TeamOwner> getTeamOwnersWhichappointedByAteamOwner(TeamOwner t) {
        return teamOwners.get(t);
    }

    public Field getField() {
        return field;
    }

    public int getIncome() {
        return income;
    }

    public int getExpense() {
        int sum =0;
        sum+=getPaymentSalary();
        sum+=field.getMaintenanceCost();


        return expense;
    }

    public FinancialReport getFinancialReport() {
        return financialReport;
    }

    /**
     * This function summarize all the payments for each asset it's owns
     * @return the total amount the team should pay
     */
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

    public LinkedList<TeamOwner> getTeamOwnerList(TeamOwner appointedTeamOwner){
        LinkedList<TeamOwner> res=this.teamOwners.get(appointedTeamOwner);
        if(res!=null)
          return res;

           return res = new LinkedList<TeamOwner>();
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
        Log.getInstance().writeToLog("The Team "+getName()+"id: "+getId() +" pull his future games.");
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

    public void setTeamOwnersWhichappointed(HashMap<TeamOwner, LinkedList<TeamOwner>> teamOwnersWhichappointed) {
        this.teamOwners = teamOwnersWhichappointed;
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

    public void setListOfOwnersWhichIappoint(TeamOwner teamOwner,LinkedList<TeamOwner>newList){
        this.teamOwners.put(teamOwner,newList);
    }



    //</editor-fold>

    //<editor-fold desc="Team Methods">
    /**
     *
     * @param asset
     * add a new asset to the team assets
     * according to UC-15
     *
     */
    public void addAsset(Asset asset) throws HasTeamAlreadyException {
        if(asset!=null) {
            this.assets.add(asset);
            asset.addMyTeam(this);//connect this team to the asset
            Log.getInstance().writeToLog("New asset wae added to team :"+this.getName()+" , id :"+this.getId()+"Asset details : "+ asset.getDetails());
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
            Log.getInstance().writeToLog("Asset details : "+ asset.getDetails()+"was remove from team :"+this.getName()+" , id :"+this.getId());

        }
    } //UC-16

    /**
     * This function edit the value of the asset which we get as a parameter
     * @param asset
     * @param val
     */
    public void editAsset(Asset asset ,int val){
        asset.editAssetValue(val);
        Log.getInstance().writeToLog("Asset details : "+ asset.getDetails()+"was update value.");

    } //UC-17

    public void addIncome(int income){
        this.income+=income;
        Log.getInstance().writeToLog("A new income added to the incomes of the team id: "+ getId());

    }

    /**
     *Team can have more then one manager, this function add a new manager to the list of the managers
     * @param teamManagerNew
     * according to UC-20
     */
    public void addTeamManager(TeamManager teamManagerNew){
        this.teamManagersList.add(teamManagerNew);
        teamManagerNew.setMyTeam(this);
        Log.getInstance().writeToLog("Team manager : "+teamManagerNew.getName() +", id :"+ teamManagerNew.getId());

    } //UC-20

    /**
     *Team can have more then one manager, this function remove manager from the list of the managers
     * @param teamManager
     * according to UC-21
     */
    public void removeTeamManager(TeamManager teamManager){
        this.teamManagersList.remove(teamManager);
    } //UC-21

    /**
     * Close team - this function update ths team's status to "close" and notify about the closing to the stakeholder
     * according to UC-22
     */
    public void closeTeam(){
        this.setTeamStatus(TeamStatus.Close);
        notifySystemManager("Team Closed :"+ getName());
        notifyTeamOwnersAndManager("Team Closed :"+ getName());//needs another notify for this

    } //UC-22

    /**
     * Close team permanently
     * This function update ths team's status to "Permanently Close" and notify about the closing to the stakeholder
     */
    public void PermanentlyCloseTeam(){
        this.setTeamStatus(TeamStatus.PermantlyClose);
        for (Asset a:assets) {
            a.resetMyTeam();
        }
        notifyTeamOwnersAndManager("Team Closed permanently:"+ getName());
        for(IObserverTeam ioT:iObserverTeamListForSystemManagers){
            ioT.removeAlert(this);
        }
        iObserverTeamListForSystemManagers=new LinkedList<>();
    }

    /**
     * This function addd a new player to the team
     * @param player
     */
    public void addPlayerToTeam(Player player) {
        players.add(player);
    }

    /**
     * This function remove player from the team
     * @param player
     */
    public void removePlayerFromTeam(Player player){
        players.remove(player);
    }
    //</editor-fold>

    //<editor-fold desc="Override Methods">

    /**
     *
     * @return the details of this team
     */
    @Override
    public String showDetails() {
        return this.getDetails();
    }

    /**
     * This function register the system manager to this team alert according to changes in this team
     * @param systemManager
     */
    @Override
    public void registerSystemManagerToAlert(IObserverTeam systemManager) {
        this.iObserverTeamListForSystemManagers.add(systemManager);
        systemManager.registerAlert(this);
    }

    /**
     * This function add observer "obs" to the observers list of this team
     * @param obs
     */
    @Override
    public void registerAlert(IObserverTeam obs) {
        this.iObserverTeamListForTeamOwnersAndManagers.add(obs);
        obs.registerAlert(this);
    }

    /**
     * This function cancel the alert that the system manager receive from this team
     * @param systemManager
     */
    @Override
    public void removeAlertToSystemManager(IObserverTeam systemManager) {
        this.iObserverTeamListForSystemManagers.remove(systemManager);
        systemManager.registerAlert(this);
    }

    /**
     * This function remove observer "obs" from the observers list of this team
     * @param obs
     */
    @Override
    public void removeAlert(IObserverTeam obs) {
        this.iObserverTeamListForTeamOwnersAndManagers.remove(obs);
        obs.removeAlert(this);
    }

    /**
     * This function update each observer of this team observers
     */
    @Override
    public void notifySystemManager(String msg) {
        for(IObserverTeam observerTeam:this.iObserverTeamListForSystemManagers){
            observerTeam.update(msg);
        }
    }

    /**
     * This function update the teamOwner and the system manager in changes of this team
     */
    @Override
    public void notifyTeamOwnersAndManager(String msg) {
        for (IObserverTeam observerTeam:this.iObserverTeamListForTeamOwnersAndManagers){
            observerTeam.update(msg);
        }
    }

    /**
     * This function create a new personal page to the team
     * @return
     * @throws PersonalPageAlreadyExist
     */
    @Override
    public PersonalPage createPersonalPage() throws PersonalPageAlreadyExist {
        if(personalPage== null){
            PersonalPage newPersonalPage= new PersonalPage(this);
            this.personalPage=newPersonalPage;
            Log.getInstance().writeToLog("The PersonalPage for team : "+getName()+" id : "+getId() +"was created.");
            return this.personalPage;
        }
        throw new PersonalPageAlreadyExist();
    }

    //</editor-fold>




}

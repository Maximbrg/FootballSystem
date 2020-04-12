package System.Users;

//<editor-fold desc="imports">
import System.Asset.Asset;
import System.Exeptions.HasTeamAlreadyException;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import java.util.LinkedList;
import java.util.List;
import System.Log;
//</editor-fold>

public class TeamManager extends User implements Asset, IObserverTeam {

    private  int assetValue;
    private Team myTeam;
    private List<ISubjectTeam> subjectTeam;
    private int salary;

    /**
     * Initialize variables
      * @param id
     * @param name
     * @param password
     * @param userName
     * @param assetValue
     * @param salary
     */
    //<editor-fold desc="Constructor">
    public TeamManager(int id, String name, String password, String userName, int assetValue, int salary) {
        super(id, name, password, userName);
        this.assetValue = assetValue;
        this.salary = salary;
        this.subjectTeam=new LinkedList<>();

    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public String getName() {
        return name;
    }

    @Override
    public int getAssetValue() {
        return assetValue;
    }

    @Override
    public String getDetails() {
        String str = "@name:"+name+"@team:";
        if(this.myTeam!=null)
            str=str+myTeam.getName()+"";
        return str;
    }


    @Override
    public Team getMyTeam() {
        return myTeam;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setName(String name) {
        this.name = name;
    }

    protected void setAssetValue(int assetValue) {
        this.assetValue = assetValue;
    }

    public void setMyTeam(Team myTeam) {
        this.myTeam = myTeam;
    }
    //</editor-fold>

    //<editor-fold desc="Override Methods">
    /**
     * Edit the asset value with a new value
     * @param newVal
     */
    @Override
    public void editAssetValue(int newVal) {
        this.setAssetValue(newVal);
        Log.writeToLog("The asset value for team manager : "+getName()+" id : "+getId() +"was edit.");

    }

    /**
     * Every asset connect to team , when this function call the team of the asset restart
     */
    @Override
    public void resetMyTeam() {
        this.myTeam=null;
        Log.writeToLog("The team for team manager : "+getName()+" id : "+getId() +"was restart.");

    }

    @Override
    public void resetMyTeam(Team team) {
        this.myTeam=null;
        Log.writeToLog("The team for team manager : "+getName()+" id : "+getId() +"was restart.");

    }

    /**
     * Every asset should be connect to team , when this function call the team which we get as parameter set as the asset team
     * @param team
     * @throws HasTeamAlreadyException
     */
    @Override
    public void addMyTeam(Team team) throws HasTeamAlreadyException{
        if(this.myTeam != null) {
            throw new HasTeamAlreadyException();
        }
        else{
            this.myTeam = team;
            Log.writeToLog("The team for team manager : "+getName()+" id : "+getId() +"was added.");

        }
    }

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

    /**
     * This function return the asset salary
     * @return
     */
    @Override
    public int getSalary() {
        return salary;
    }
    //</editor-fold>

}

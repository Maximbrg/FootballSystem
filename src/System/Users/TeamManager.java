package System.Users;

//<editor-fold desc="imports">
import System.Asset.Asset;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import java.util.LinkedList;
import java.util.List;
//</editor-fold>

public class TeamManager extends User implements Asset, IObserverTeam {

    private String name;
    private  int assetValue;
    private Team myTeam;
    private List<ISubjectTeam> subjectTeam;
    private int salary;

    //<editor-fold desc="Constructor">
    public TeamManager(int id, String name, String password, String userName, String name1, int assetValue, Team myTeam, int salary) {
        super(id, name, password, userName);
        this.name = name1;
        this.assetValue = assetValue;
        this.myTeam = myTeam;
        this.salary = salary;
        this.subjectTeam=new LinkedList<>();

    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public String getName() {
        return name;
    }

    public int getAssetValue() {
        return assetValue;
    }

    public Team getMyTeam() {
        return myTeam;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setAssetValue(int assetValue) {
        this.assetValue = assetValue;
    }

    public void setMyTeam(Team myTeam) {
        this.myTeam = myTeam;
    }
    //</editor-fold>

    //<editor-fold desc="Override Methods">
    @Override
    public void editAssetValue(int newVal) {
        this.setAssetValue(newVal);
    }

    @Override
    public void resetMyTeam() {
        this.myTeam=null;
    }

    @Override
    public void addMyTeam(Team team) {
        this.myTeam=team;
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

    @Override
    public int getSalary() {
        return salary;
    }
    //</editor-fold>

}

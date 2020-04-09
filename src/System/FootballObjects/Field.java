package System.FootballObjects;

//<editor-fold desc="imports">
import System.Asset.Asset;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import java.util.LinkedList;
import java.util.List;
//</editor-fold>

public class Field implements Asset {

    private int id;
    private String name;
    private List<Team> homeTeams;
    private  int assetValue;
    private Team myTeam;
    private List<ISubjectTeam> subjectTeam;
    private int maintenanceCost;

    //<editor-fold desc="Constructor">
    public Field(int id, String name, List<Team> homeTeams, int assetValue, Team myTeam,int maintCost) {
        this.id = id;
        this.name = name;
        this.homeTeams = homeTeams;
        this.assetValue = assetValue;
        this.myTeam = myTeam;
        this.subjectTeam=new LinkedList<>();
        this.maintenanceCost=maintCost;
    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Team> getHomeTeams() {
        return homeTeams;
    }

    public int getAssetValue() {
        return assetValue;
    }

    public Team getMyTeam() {
        return myTeam;
    }

    public int getMaintenanceCost() {  return maintenanceCost; }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHomeTeams(List<Team> homeTeams) {
        this.homeTeams = homeTeams;
    }

    public void setAssetValue(int assetValue) {
        this.assetValue = assetValue;
    }

    public void setMyTeam(Team myTeam) {
        this.myTeam = myTeam;
    }

    public void setMaintenanceCost(int maintenanceCost) {  this.maintenanceCost = maintenanceCost;  }

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
    public int getSalary() {
        return 0;
    }

    //</editor-fold>

}

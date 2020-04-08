package System.Users;

//<editor-fold desc="imports">
import System.Asset.Asset;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;
import System.IShowable;

import java.util.LinkedList;
import java.util.List;

//</editor-fold>

public class Coach extends User implements Asset, IPageAvailable, IObserverTeam, IShowable {

    private String name;
    private String preparation;
    private String role;
    private PersonalPage personalPage;
    private  int assetValue;
    private Team myTeam;
    private int salary;
    private List<ISubjectTeam> subjectTeam;

    //<editor-fold desc="Constructor">
    public Coach(int id, String name, String password, String userName, String name1, String preparation, String role, PersonalPage personalPage, int assetValue, Team myTeam, int salary) {
        super(id, name, password, userName);
        this.name = name1;
        this.preparation = preparation;
        this.role = role;
        this.personalPage = personalPage;
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

    @Override
    public String getType() {
        return "Coach";
    }

    @Override
    public String getDetails() {
        String str = "@name:"+name+"@preparation:"+preparation+"@role:"+role+"@team:"+myTeam.getName()+"";
        return str;
    }

    public String getPreparation() {
        return preparation;
    }

    public String getRole() {
        return role;
    }

    public PersonalPage getPersonalPage() {
        return personalPage;
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

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPersonalPage(PersonalPage personalPage) {
        this.personalPage = personalPage;
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

    @Override
    public String showDetails() {
        return null;
    }
    //</editor-fold>
}

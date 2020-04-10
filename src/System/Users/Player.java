package System.Users;

import System.Asset.Asset;
import System.Exeptions.PersonalPageAlreadyExist;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;
import System.IShowable;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Player extends User implements Asset, IPageAvailable, IShowable {


    private String name;
    private Date birthDate;
    private String role;
    private PersonalPage personalPage;
    private  int assetValue;
    private Team myTeam;
    private List<ISubjectTeam> subjectTeam;
    private int salary;

    //<editor-fold desc="Constructor">
    public Player(int id, String name, String password, String userName, String name1, Date birthDate, String role, int assetValue, Team myTeam, int salary) {
        super(id, name, password, userName);
        this.name = name1;
        this.birthDate = birthDate;
        this.role = role;
        this.personalPage = null;
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
    public int getId(){
        return id;
    }

    @Override
    public String getType() {
        return "Player";
    }

    @Override
    public String getDetails() {
        String str = "@name:"+name+"@birthday:"+birthDate.toString()+"@role:"+role+"@team:"+myTeam.getName()+"";
        return str;
    }

    public Date getBirthDate() {
        return birthDate;
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

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
    public String showDetails() {
        return null;
    }

    @Override
    public int getSalary() {
        return salary;
    }

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
    public PersonalPage createPersonalPage() throws PersonalPageAlreadyExist {
        if(personalPage!= null){
            PersonalPage newPersonalPage= new PersonalPage(this);
            this.personalPage=newPersonalPage;
        }
        throw new PersonalPageAlreadyExist();
    }

    //</editor-fold>
}

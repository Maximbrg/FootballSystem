package System.Users;

//<editor-fold desc="imports">
import System.Asset.Asset;
import System.Exeptions.HasTeamAlreadyException;
import System.Exeptions.PersonalPageAlreadyExist;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;
import System.IShowable;

import java.util.LinkedList;
import java.util.List;

//</editor-fold>

public class Coach extends User implements Asset, IPageAvailable, IShowable {

    private String preparation;
    private String role;
    private PersonalPage personalPage;
    private  int assetValue;
    private Team myTeam;
    private int salary;
    private List<ISubjectTeam> subjectTeam;

    //<editor-fold desc="Constructor">
    public Coach(int id, String name, String password, String userName, String preparation, String role, int assetValue, int salary) {
        super(id, name, password, userName);
        this.preparation = preparation;
        this.role = role;
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
    public void addMyTeam(Team team) throws HasTeamAlreadyException {
       if(this.myTeam != null) {
           throw new HasTeamAlreadyException();
       }
       else{
           this.myTeam = team;
       }
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public String showDetails() {
        return null;
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
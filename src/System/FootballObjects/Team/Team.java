package System.FootballObjects.Team;

import System.Asset.Asset;
import System.I_Observer.IObserverTeam;
import System.I_Observer.ISubjectTeam;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;
import System.Users.TeamManager;
import System.Users.TeamOwner;
import System.Users.User;

import java.util.HashMap;
import java.util.List;

public class Team implements IPageAvailable, ISubjectTeam {

    private static int id;
    private String name;
    private Status status;
    private PersonalPage personalPage;
    private List<Asset> assets;
    private List<IObserverTeam> iObserverTeams;
    private HashMap <TeamOwner , List<TeamOwner >> teamOwnerListHashMap;
    private HashMap<TeamManager , List<TeamManager>> teamManagers;

    //Constructor
    public Team(){}

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
}

package ServiceLayer;
import ServiceLayer.Exceptions.NotHisManagerException;
import ServiceLayer.Exceptions.TeamIsClosedException;
import System.Asset.Asset;
import System.Enum.TeamStatus;
import System.Exeptions.*;
import System.FootballObjects.Field;
import System.FootballObjects.Team.Team;
import System.Users.*;
import System.*;

public class TeamOwnerController extends MainUserController implements Observable {

    //<editor-fold desc="Singleton Constructor">

    private static TeamOwnerController ourInstance = new TeamOwnerController();

    public static TeamOwnerController getInstance() {
        return ourInstance;
    }

    private TeamOwnerController() {
    }

    //</editor-fold>

    //<editor-fold desc="Methods">

    public Field createField(int id, String name){
        Field field = new Field(id, name, 0, 0);
        Controller.getInstance().addField(field);
        return field;
    }

    public void addAssetToTeam(TeamOwner teamOwner, Team team, Asset asset) throws NotHisTeamException, TeamIsClosedException, HasTeamAlreadyException  {
        this.checkInputs(teamOwner,team);
        team.addAsset(asset);
        if(asset instanceof Player){
           team.addPlayerToTeam((Player)asset);
        }
    }

    public void removeAssetFromTeam(TeamOwner teamOwner, Team team, Asset asset) throws NotHisTeamException, TeamIsClosedException {
        this.checkInputs(teamOwner,team);
        team.removeAsset(asset);
        if(asset instanceof Player){
            team.removePlayerFromTeam((Player)asset);
        }
    }

    public void editAssetOfTeam(TeamOwner teamOwner, Team team,  Asset asset, int value) throws NotHisTeamException, TeamIsClosedException {
        this.checkInputs(teamOwner,team);
        asset.editAssetValue(value);
    }

    public void addTeamOwner(TeamOwner teamOwner, Team team, TeamOwner newTeamOwner) throws NotHisTeamException, TeamIsClosedException, AlreadyHasTeamException {
        this.checkInputs(teamOwner,team);
        if(newTeamOwner.getTeamList().size()>0){
            throw new AlreadyHasTeamException();
        }
        teamOwner.addTeamOwner(team,newTeamOwner);
    }

    public void removeTeamOwner(TeamOwner teamOwner, TeamOwner teamOwnerToRemove, Team team) throws NotHisTeamException, TeamIsClosedException, StillNoAppointedOwner {
        this.checkInputs(teamOwner,team);
        teamOwner.removeTeamOwner(team,teamOwnerToRemove);
    }

    public void addTeamMenegar(TeamOwner teamOwner, Team team, TeamManager teamManager) throws NotHisTeamException, TeamIsClosedException, AlreadyHasTeamException {
        this.checkInputs(teamOwner,team);
        if(teamManager.getMyTeam()!=null){
            throw new AlreadyHasTeamException();
        }
        team.addTeamManager(teamManager);
        teamManager.setMyTeamOwner(teamOwner);
    }

    public void removeTeamMenegar(TeamOwner teamOwner, Team team, TeamManager teamManager) throws NotHisTeamException, NotHisManagerException, TeamIsClosedException {
        this.checkInputs(teamOwner,team);
        if(teamManager.getMyTeamOwner()!=teamOwner){
            throw new NotHisManagerException();
        }
        team.removeTeamManager(teamManager);
        teamManager.setMyTeamOwner(null);
    }

    public void closeTeam(TeamOwner teamOwner, Team team) throws NotHisTeamException, TeamIsClosedException {
        this.checkInputs(teamOwner,team);
        team.closeTeam();
        notifyUI();
    }

    public void openTeam(TeamOwner teamOwner, Team team) throws NotHisTeamException, TeamStatusException, TeamIsClosedException {
        this.checkInputs(teamOwner,team);
        teamOwner.restartTeam(team);
        notifyUI();
    }

    public FinancialReport sumbitReport(TeamOwner teamOwner, Team team) throws NotHisTeamException, TeamIsClosedException {
        checkInputs(teamOwner,team);
       return teamOwner.addFinancialReport(team);
    }

    //</editor-fold>

    //<editor-fold desc="Override">

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyUI() {

    }

    private void checkInputs(TeamOwner teamOwner, Team team) throws NotHisTeamException, TeamIsClosedException{
        if(!teamOwner.getTeamList().contains(team)){
            throw new NotHisTeamException();
        }
        if(team.getTeamStatus()== TeamStatus.Close){
            throw new TeamIsClosedException();
        }
    }

    //</editor-fold>
}


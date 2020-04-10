package ServiceLayer;

import System.Asset.Asset;
import System.Exeptions.HasTeamAlreadyException;
import System.Exeptions.NotHisTeamException;
import System.FootballObjects.Field;
import System.FootballObjects.Team.Team;
import System.Users.Coach;
import System.Users.Player;
import System.*;
import System.Users.TeamManager;
import System.Users.TeamOwner;

import java.util.Date;

public class TeamOwnerController extends MainUserController  {

    private static TeamOwnerController ourInstance = new TeamOwnerController();

    public static TeamOwnerController getInstance() {
        return ourInstance;
    }

    private TeamOwnerController() {
    }

    public Player signup(int id, String name, String password, String userName, Date birthDate, String role, int assetValue, int salary){
        Player player = new Player(id, name, password, userName, birthDate,  role,  assetValue,  salary) ;
        Controller.getInstance().addUser(userName,player);
        return player;
    }

    public Coach signup(int id, String name, String password, String userName, String preparation, String role){
        Coach coach = new Coach(id, name, password, userName, preparation, role, 0,0 );
        Controller.getInstance().addUser(userName,coach);
        return coach;
    }

    public TeamManager signup(int id, String name, String password, String userName, String name1, Team myTeam){
        TeamManager teamManager = new TeamManager(id, name, password, userName, 0,0 );
        Controller.getInstance().addUser(userName,teamManager);
        return teamManager;
    }

    public Field createField(int id, String name){
        Field field = new Field(id, name, 0, 0);
        Controller.getInstance().addField(field);
        return field;
    }

    public void addAssetToTeam(TeamOwner teamOwner, Team team, Asset asset) throws NotHisTeamException, HasTeamAlreadyException {
        if(!teamOwner.getTeamList().contains(team)){
            throw new NotHisTeamException();
        }
        team.addAsset(asset);
        if(asset instanceof Player){
           team.addPlayerToTeam((Player)asset);
        }
    }

    public void removeAssetFromTeam(TeamOwner teamOwner, Team team, Asset asset) throws NotHisTeamException, HasTeamAlreadyException {
        if(!teamOwner.getTeamList().contains(team)){
            throw new NotHisTeamException();
        }
        team.removeAsset(asset);
        if(asset instanceof Player){
            team.removePlayerFromTeam((Player)asset);
        }
    }

   /* public void editAssetOfTeam(TeamOwner teamOwner, Asset asset, ) */

}

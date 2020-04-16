import ServiceLayer.FanController;
import ServiceLayer.FootballAssosiationController;
import ServiceLayer.PlayerController;
import ServiceLayer.SystemManagerController;
import System.Exeptions.AlreadyHasPageException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.Game;
import System.FootballObjects.Team.Team;
import System.PersonalPages.PersonalPage;
import System.Users.Fan;
import System.Users.Player;
import System.Users.SystemManager;
import System.Users.TeamOwner;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSuit3 {
    @Test
    /**
     * Add a new team to the system
     */
    public void addNewTeam(){
        SystemManager systemManager=new SystemManager(1,"shiran","111","shiran");
//        Controller.getInstance().addUser(systemManager.getUserName(),systemManager);
        try {
            TeamOwner teamOwner= SystemManagerController.getInstance().createNewTeamOwner(systemManager,2,"teamOwner","111","teamOwner",100,10000);
            Team team= FootballAssosiationController.getInstance().createTeam("team1",teamOwner);
            List<Team> teamList= FootballAssosiationController.getInstance().getAllTeams();
            //team added to controller
            for(Team t: teamList){
                assertEquals(t,team);
            }
            //team owner added to team
            for(TeamOwner owner: team.getAllTeamOwners()){
                assertEquals(owner,teamOwner);
            }
            //team added to teams of team owner
            for (Team t: teamOwner.getTeamList()){
                assertEquals(t,team);
            }
            //team owner added to controller
            for (TeamOwner t: FootballAssosiationController.getInstance().getAllTeamOwner()){
                assertEquals(t,teamOwner);
            }
        } catch (UserNameAlreadyExistException e) {
            assert(false);
        }
    }

    @Test
    /**
     * Fan registered to follow of personal page
     */
    public void followPersonalPage() {
        SystemManager systemManager=new SystemManager(1,"shiran","111","shiran");
        try {
            Fan fan= SystemManagerController.getInstance().createNewFan(systemManager,4,"fan","111","fan");
            Player player= SystemManagerController.getInstance().createNewPlayer(systemManager,3,"plyer","111","player",null,"",1,1);
            PlayerController.getInstance().createPersonalPage(player);
            PersonalPage personalPage=player.getPersonalPage();
            FanController.getInstance().followPersonalPage(fan,personalPage);
            //personal page was added to fan
            for(PersonalPage page: fan.getFollowPages()){
                assertEquals(page,personalPage);
            }
            //player was added to personal page
            assertEquals(personalPage.getPageAvailable(),player);
            //personal was page added to player
            assertEquals(personalPage,player.getPersonalPage());
            //fan was added to personal page
            for (Fan fan1:personalPage.getFollowers()){
                assertEquals(fan,fan1);
            }
        } catch (UserNameAlreadyExistException e) {
            e.printStackTrace();
        } catch (AlreadyHasPageException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * Fan do unfollow to personal page
     */
    public void unFollowPersonalPage() {
        SystemManager systemManager=new SystemManager(1,"shiran","111","shiran");
        try {
            Fan fan= SystemManagerController.getInstance().createNewFan(systemManager,4,"fan","111","fan1");
            Player player= SystemManagerController.getInstance().createNewPlayer(systemManager,3,"plyer","111","player1",null,"",1,1);
            PlayerController.getInstance().createPersonalPage(player);
            PersonalPage personalPage=player.getPersonalPage();
            FanController.getInstance().followPersonalPage(fan,personalPage);
            List<PersonalPage> personalPagesList=FanController.getInstance().getAllpersonalPages();
            FanController.getInstance().unfollowPersonalPage(fan,personalPagesList.get(0));
            //fan was removed from personal page followers
            assertEquals(personalPagesList.get(0).getFollowers().size(),0);
            //personal page was removed from fan
            assertEquals(fan.getFollowPages().size(),0);
        } catch (UserNameAlreadyExistException e) {
            e.printStackTrace();
        } catch (AlreadyHasPageException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * fan register to game alert
     */
    public void registerToGameAlert(){
        Game game= new Game(null,9,null,null,null,null,null);
        Fan fan= new Fan(4,"fan","111","fan1");
        game.registerFanToAlert(fan);
        assertEquals(game.getiObserverGameListForFans().get(0),fan);
        assertEquals(fan.getSubjectGame().get(0),game);
    }

    @Test
    /**
     * fan cancel game alert
     */
    public void moveAlertToGame(){
        Game game= new Game(null,9,null,null,null,null,null);
        Fan fan= new Fan(4,"fan","111","fan1");
        game.registerFanToAlert(fan);
        game.removeAlertToFan(fan);
        assertEquals(game.getiObserverGameListForFans().size(),0);
        assertEquals(fan.getSubjectGame().size(),0);
    }

}

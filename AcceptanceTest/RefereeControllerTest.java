import ServiceLayer.RefereeController;
import ServiceLayer.SystemManagerController;
import System.Controller;
import System.Enum.RefereeType;
import System.Exeptions.NoRefereePermissions;
import System.Exeptions.NoSuchEventException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.Event.AEvent;
import System.FootballObjects.Game;
import System.FootballObjects.League;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.FootballObjects.Team.Team;
import System.Users.FootballAssociation;
import System.Users.Referee;
import System.Users.SystemManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class RefereeControllerTest {
    static RefereeController rCTest;
    static SystemManagerController systemManagerController;
    static SystemManager sManager;
    static Referee rMain,rAssist,rAssist1;
    static FootballAssociation fATest;
    static Team t1,t2;
    static Game g1,g2;
    static Season s;
    static League l;
    static LeagueInformation leagueInformation;
    static List<Team> teams;
    static List<Referee> referees;

    public RefereeControllerTest() throws UserNameAlreadyExistException {

    }



    @BeforeClass
    public static void init() throws UserNameAlreadyExistException {
        rCTest= RefereeController.getInstance();
        systemManagerController=SystemManagerController.getInstance();
        sManager=new SystemManager(123,"abc","abv","sys");
        rAssist =systemManagerController.createNewReferee(sManager,441,"avi","1","EilamHagadol", RefereeType.ASSISTANT);
        rAssist1 =systemManagerController.createNewReferee(sManager,441,"avi","1","EilamHagadol3", RefereeType.ASSISTANT);
        rMain =systemManagerController.createNewReferee(sManager,444,"avi","1","aviHagadol", RefereeType.MAIN);
        referees=new LinkedList<>();
        referees.add(rMain);
        referees.add(rAssist);
        referees.add(rAssist1);
        fATest=systemManagerController.createNewFootballAssociation(sManager,112,"eli","11","loli");
        sManager.createTeam("hapoelb7",null);
        sManager.createTeam("hapoelb72",null);
        t1= Controller.getInstance().getAllTeams().get(0);
        t2= Controller.getInstance().getAllTeams().get(1);
        teams=new LinkedList<>();
        teams.add(t1);
        teams.add(t2);
        s=new Season(1999);
        l=new League("champions",teams);
        leagueInformation=new LeagueInformation(l,s,fATest);
        s.addLeagueInformation(leagueInformation);
        fATest.initLeagueInformation(leagueInformation);
        fATest.schedulingReferee(leagueInformation,referees);

    }

    /**
     * edit details
     * uc-38
     * 10.1.1
     */
    @Test
    public void editDetails() throws UserNameAlreadyExistException {
        //rMain =systemManagerController.createNewReferee(sManager,444,"avi","1","aviHagadol28", RefereeType.MAIN);
        //edit id
        rCTest.editDetails(rMain,1111,"","");
        assertEquals(1111,rMain.getId());
        //edit name
        rCTest.editDetails(rMain,-1,"fufi","");
        assertEquals("fufi",rMain.getName());
        //edit pass
        rCTest.editDetails(rMain,-1,"","aa");
        assertEquals("aa",rMain.getPassword());
        //edit everything
        rCTest.editDetails(rMain,48,"koko","bb");
        assertEquals("bb",rMain.getPassword());
        assertEquals("koko",rMain.getName());
        assertEquals(48,rMain.getId());
    }

    /**
     * get all the games ever to the referee
     *
     * uc- 39
     * 10.2.1
     */
    @Test
    public void getMyGames()  {

        for(Game g:rCTest.getMyGames(rMain)){
            if(!g.getMainReferee().getUserName().equals("aviHagadol") ){
                assert(false);
            }
        }

    }

    /**
     * get future games
     *  uc- 39
     * 10.2.2
     */

    @Test
    public void getMyFutureGames()  {

        leagueInformation.getGames().get(0).getDate().setYear(2021-1900);
        leagueInformation.getGames().get(1).getDate().setMonth(0);
        for(Game g:rCTest.getMyFutureGames(rMain)){
            System.out.println(g.getDate().toString());
            if(g.getDate().before(new Date(System.currentTimeMillis()))){
                assert(false);
            }
        }

        assert (true);

    }

    /**
     * get the games to the concrete season
     *  uc- 39
     * 10.2.3
     */
    @Test
    public void getMySeasonGames(){
                int count =0;
                List<Game> l=s.getLeaguesInformation().get(0).getGames();
                for(Game g:rCTest.getMySeasonGames(rMain,s) ){
                    if(!l.contains(g)){
                        assert(false);
                    }
                }
    }

    /**
     * add event during the game
     * uc 40
     * 10.3.1
     */
    @Test
    public void addEventDuringGame(){
        Game g=rMain.getGames().get(0);
        Date d1=g.getDate();
        d1.setTime(System.currentTimeMillis());
        //try before game start
        try {
            d1.setHours(new Date(System.currentTimeMillis()).getHours()-2);
            rCTest.addEventDuringGame(rMain,g,"Offense",10,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(true);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        d1.setHours(new Date(System.currentTimeMillis()).getHours()+1);
        //referee not allow to addEventDuringGame
        try {
            rCTest.addEventDuringGame(new Referee("a",RefereeType.MAIN,59,"o","abra"),g,"Offense",10,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(true);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        //no such Event like this kind
        try {
            rCTest.addEventDuringGame(rMain,g,"blueCard",10,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(false);
        } catch (NoSuchEventException e) {
            assert (true);
        }
        //legal addEventDuringGame
        try {
            rCTest.addEventDuringGame(rMain,g,"RedCard",10,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(false);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        List<AEvent> a=g.getEventLog().getEventList();
        assertEquals("RedCard",g.getEventLog().getEventList().get(0).getClass().toString().substring(35));


    }


    /**
     * uc-40
     * 10.3.2
     */
    @Test
    public void editEventAfterGame(){
        Game g=rMain.getGames().get(0);
        Date d1=g.getDate();
        d1.setTime(System.currentTimeMillis());
        //initialize
        try {
            rCTest.addEventDuringGame(rMain,g,"RedCard",1,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            noRefereePermissions.printStackTrace();
        } catch (NoSuchEventException e) {
            e.printStackTrace();
        }
        //try before game start
        try {
            d1.setHours(new Date(System.currentTimeMillis()).getHours()-2);
            rCTest.editEventAfterGame(rMain,g,"Offense",g.getEventLog().getEventList().get(0),"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(true);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        //referee not allow to addEventAfterGame -assistant referee
        try {
            rCTest.editEventAfterGame(rAssist1,g,"Offense",g.getEventLog().getEventList().get(0),"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(true);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        //no such Event like this kind
        try {
            rCTest.editEventAfterGame(rMain,g,"blueCard",g.getEventLog().getEventList().get(0),"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(false);
        } catch (NoSuchEventException e) {
            assert (true);
        }
        //to late to add event to game
        try {
            d1.setHours(new Date(System.currentTimeMillis()).getHours()+10);
            rCTest.editEventAfterGame(rMain,g,"RedCard",g.getEventLog().getEventList().get(0),"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(true);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        //to late to add event to game
        try {
            d1.setHours(new Date(System.currentTimeMillis()).getHours()+4);
            rCTest.editEventAfterGame(rMain,g,"RedCard",g.getEventLog().getEventList().get(0),"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(false);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        List<AEvent> a=g.getEventLog().getEventList();
        assertEquals("RedCard",g.getEventLog().getEventList().get(0).getClass().toString().substring(35));
        assertEquals(1,g.getEventLog().getEventList().get(0).getMinute());

    }

    /**
     *uc 40
     * 10.4.1
     */
    @Test
    public void addEventAfterGame(){
        Game g=rMain.getGames().get(0);
        Date d1=g.getDate();
        d1.setTime(System.currentTimeMillis());
        //initialize
        try {
            rCTest.addEventAfterGame(rMain,g,"RedCard",1,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            noRefereePermissions.printStackTrace();
        } catch (NoSuchEventException e) {
            e.printStackTrace();
        }
        //try before game start
        try {
            d1.setHours(new Date(System.currentTimeMillis()).getHours()-2);
            rCTest.addEventAfterGame(rMain,g,"Offense",10,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(true);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        //referee not allow to addEventAfterGame -assistant referee
        try {
            rCTest.addEventAfterGame(rAssist1,g,"Offense",10,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(true);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        //no such Event like this kind
        try {
            rCTest.addEventAfterGame(rMain,g,"blueCard",10,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(false);
        } catch (NoSuchEventException e) {
            assert (true);
        }
        //to late to add event to game
        try {
            d1.setHours(new Date(System.currentTimeMillis()).getHours()+10);
            rCTest.addEventAfterGame(rMain,g,"RedCard",10,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(true);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        //legal add Event afterGame
        try {
            d1.setHours(new Date(System.currentTimeMillis()).getHours()+4);
            rCTest.addEventAfterGame(rMain,g,"YellowCard",90,"","");
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(false);
        } catch (NoSuchEventException e) {
            assert (false);
        }
        List<AEvent> a=g.getEventLog().getEventList();
        assertEquals("YellowCard",g.getEventLog().getEventList().get(g.getEventLog().getEventList().size()-1).getClass().toString().substring(35));
        assertEquals(90,g.getEventLog().getEventList().get(g.getEventLog().getEventList().size()-1).getMinute());


    }
    /**
     *uc 41
     * 10.4.2
     */
    @Test
    public void createGameReport(){
        Game g=rMain.getGames().get(0);
        Date d1=g.getDate();
        d1.setTime(System.currentTimeMillis());
        d1.setHours(new Date(System.currentTimeMillis()).getHours()+1);
        //create game report before the game end
        try {
            rCTest.createGameReport(rMain,g);
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(true);
        }
        //create game report with assistant referee - wrong
        try {
            rCTest.createGameReport(rAssist1,g);
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(true);
        }
        //legal creating of a game report
        d1.setHours(new Date(System.currentTimeMillis()).getHours()+10);
        try {
            rCTest.createGameReport(rMain,g);
        } catch (NoRefereePermissions noRefereePermissions) {
            assert(false);
        }
    }







}
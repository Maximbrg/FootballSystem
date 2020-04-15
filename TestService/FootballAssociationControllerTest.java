import ServiceLayer.Exceptions.*;
import ServiceLayer.FootballAssosiationController;
import System.Enum.RefereeType;
import System.Enum.TeamStatus;
import System.Exeptions.IllegalInputException;
import System.Exeptions.NoSuchAUserNamedException;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.Game;
import System.FootballObjects.League;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.FootballObjects.Team.OneGameAllocatePolicy;
import System.FootballObjects.Team.Team;
import System.Users.Fan;
import System.Users.FootballAssociation;
import System.Users.Referee;
import System.Users.TeamOwner;
import org.junit.Before;
import org.junit.Test;
import System.*;
import java.util.List;

import static org.junit.Assert.*;

public class FootballAssociationControllerTest {

    FootballAssosiationController faController;
    Controller controller= Controller.getInstance();


    @Before
    public void initTest(){
        faController= new FootballAssosiationController();
        Team t1= new Team("team1",null);
        Team t2= new Team("team2",null);
        Team t3= new Team("team3",null);
        controller.addTeam(t1);
        controller.addTeam(t2);
        controller.addTeam(t3);
    }

    @Test
    //Test ID:    #9.1.1 -- checks if initEmptyLeague in is working
    public void  initEmptyLeagueTest1(){
        List<Team> teams=faController.getAllTeams();
        try {
            League league=faController.initEmptyLeague("league1", teams);
            assertEquals(league.getName(),"league1");
        }catch (LeagueNameAlreadyExist e){
            assert(false);

        }

    }
    @Test
    /*Test ID:    #9.1.2 -- checks if initEmptyLeague throw exception
                            if exists same league in the system
    */
    public void  initEmptyLeagueTest(){
        List<Team> teams=faController.getAllTeams();
        try {
            League league1=new League("league1", teams);
            controller.addLeague(league1);
            League league2=faController.initEmptyLeague("league1", teams);
        }catch (LeagueNameAlreadyExist e){
            assert(true);
            assertEquals(faController.getAllLeague().size(),1);
        }
    }

    @Test
    /*Test ID:    #9.2.1 -- checks if initLeague working with a new season
     */
    public void initLeagueTest1(){
        List<Team> teams=faController.getAllTeams();
        FootballAssociation fa= new FootballAssociation(123,"fa1", "123","123");
        League league= new League("leagueTest",teams);
        LeagueInformation leagueInfoTest= faController.initLeague(fa,league,"2000");
        //check leagueInfo added to FootballAssociation
        assertEquals(fa.getLeagueInformations().get(leagueInfoTest.getId()),leagueInfoTest);
        //check leagueInfo added to league
        assertEquals(league.getLeagueInformation().get(0),leagueInfoTest);
        //check season added to leagueInfo
        assertEquals(leagueInfoTest.getSeason().getYear(),"2000");
        //check controller added new season
        assertEquals(controller.getAllSeasons().get(0).getYear(),"2000");
    }

    @Test
    /*Test ID:    #9.2.2 -- checks if initLeague working with exist season
     */
    public void initLeagueTest2(){
        List<Team> teams=faController.getAllTeams();
        Season season= new Season(2000);
        controller.addSeason(season);
        FootballAssociation fa= new FootballAssociation(123,"fa1", "123","123");
        League league= new League("leagueTest",teams);
        LeagueInformation leagueInfoTest= faController.initLeague(fa,league,"2000");
        //check leagueInfo added to FootballAssociation
        assertEquals(fa.getLeagueInformations().get(leagueInfoTest.getId()),leagueInfoTest);
        //check leagueInfo added to league
        assertEquals(league.getLeagueInformation().get(0),leagueInfoTest);
        //check season added to leagueInfo
        assertEquals(leagueInfoTest.getSeason().getYear(),"2000");
        //check controller didnt add duplicate seasons
        assertEquals(controller.getAllSeasons().size(),1);
    }

    @Test
    /*Test ID:    #9.3.1 -- Add a new referee
     */
    public void addRefereeTest1(){
        FootballAssociation fa= new FootballAssociation(123,"fa1", "123","123");
        try {
            Referee ref=faController.addReferee(fa,"ref1", RefereeType.MAIN,111,"111","ref1");
            assertEquals(faController.getAllReferee().get(0),ref);
        } catch (UserNameAlreadyExistException e) {
            assert(false);
        }
    }

    @Test
    /*Test ID:    #9.3.2 -- Add a new referee with a user name that exists in the system
                            The function needs throw exception
     */
    public void addRefereeTest2(){
        FootballAssociation fa= new FootballAssociation(123,"fa1", "123","123");
        try {
            Referee ref1=faController.addReferee(fa,"ref1", RefereeType.MAIN,111,"111","ref1");
            Referee ref2=faController.addReferee(fa,"ref1", RefereeType.MAIN,111,"111","ref1");
        } catch (UserNameAlreadyExistException e) {
            assert(true);
            assertEquals(faController.getAllReferee().size(),1);
        }
    }
    @Test
    /*Test ID:    #9.3.3 -- Remove referee without games.
     */
    public void removeRefereeTest1(){
        FootballAssociation fa= new FootballAssociation(123,"fa1", "123","123");
        Fan fan1= new Fan( 1,"fan1","123","fan1");
        Fan fan2= new Fan( 2,"fan2","123","fan2");
        Fan fan3= new Fan( 3,"fan3","123","fan3");
        controller.addUser(fan1.getUserName(),fan1);
        controller.addUser(fan2.getUserName(),fan2);
        controller.addUser(fan3.getUserName(),fan3);
        try {
            Referee ref1=faController.addReferee(fa,"ref1", RefereeType.MAIN,111,"111","ref1");
            List<Referee> refereeList= faController.getAllReferee();
            assertEquals(refereeList.isEmpty(),false);
            faController.removeReferee(fa,ref1);
            refereeList= faController.getAllReferee();
            assertEquals(refereeList.isEmpty(),true);
        } catch (UserNameAlreadyExistException e) {
            assert(false);
        } catch (IllegalInputException e) {
            assert(false);
        } catch (NoSuchAUserNamedException e) {
            assert(false);
        }
    }

    @Test
    /*Test ID:    #9.3.4 -- Remove referee with games
                            The function needs throw IllegalInputException exception
     */
    public void removeRefereeTest2(){
        FootballAssociation fa= new FootballAssociation(123,"fa1", "123","123");
        List<Team> teams=faController.getAllTeams();
        try {
            Referee ref1=faController.addReferee(fa,"ref1", RefereeType.MAIN,111,"111","ref1");
            Referee ref2=faController.addReferee(fa,"ref2", RefereeType.ASSISTANT,111,"111","ref2");
            Referee ref3=faController.addReferee(fa,"ref3", RefereeType.ASSISTANT,111,"111","ref3");
            List<Referee> refereeList= faController.getAllReferee();
            assertEquals(refereeList.isEmpty(),false);
            League league= new League("leagueTest",teams);
            controller.addLeague(league);
            LeagueInformation leagueInfoTest= faController.initLeague(fa,league,"2000");
            faController.schedulingGames(fa,leagueInfoTest);
            faController.schedulingReferee(fa,leagueInfoTest,refereeList);
            faController.removeReferee(fa,ref1);
            refereeList= faController.getAllReferee();
            assertEquals(refereeList.size(),3);
        } catch (UserNameAlreadyExistException e) {
            assert(false);
        } catch (IllegalInputException e) {
            List<Referee> refereeList= faController.getAllReferee();
            assertEquals(refereeList.isEmpty(),false);
        } catch (NoSuchAUserNamedException e) {
            assert(false);
        } catch (CantSchedulingRefereeWithoutGames e) {
            assert(false);
        }catch (MustHaveLeastOneMainReferee e){
            assert(false);
        }catch (MustHaveLeastTwoSideReferee e){
            assert(false);
        } catch (MustHaveLeastTwoTeams mustHaveLeastTwoTeams) {
            assert(false);
        }
    }

    @Test
    /*Test ID:    #9.3.4 --Removes referee with a games after replacing referees
     the referee will be remove
     */
    public void replaceRefereeTest1(){
        FootballAssociation fa= new FootballAssociation(123,"fa1", "123","123");
        List<Team> teams=faController.getAllTeams();
        try {
            Referee ref1=faController.addReferee(fa,"ref1", RefereeType.MAIN,111,"111","ref1");
            Referee ref2=faController.addReferee(fa,"ref2", RefereeType.ASSISTANT,111,"111","ref2");
            Referee ref3=faController.addReferee(fa,"ref3", RefereeType.ASSISTANT,111,"111","ref3");
            Referee ref4=faController.addReferee(fa,"ref4", RefereeType.MAIN,111,"111","ref4");
            List<Referee> refereeList= faController.getAllReferee();
            assertEquals(refereeList.isEmpty(),false);
            League league=faController.initEmptyLeague("leagueTest",teams);
            LeagueInformation leagueInfoTest= faController.initLeague(fa,league,"2000");
            faController.schedulingGames(fa,leagueInfoTest);
            faController.schedulingReferee(fa,leagueInfoTest,refereeList);
            faController.replaceReferee(fa,leagueInfoTest,refereeList,ref1);
            refereeList= faController.getAllReferee();
            assertEquals(refereeList.size(),3);//Check that ref1 removed
            for (Game game: leagueInfoTest.getGames()){//check that ref1 has not assigned to any game
                if(game.getMainReferee().getUserName().equals("ref1")){
                    assert(false);
                }
            }
        } catch (UserNameAlreadyExistException e) {
            assert(false);
        } catch (IllegalInputException e) {
            assert(false);
        } catch (NoSuchAUserNamedException e) {
            assert(false);
        } catch (CantSchedulingRefereeWithoutGames e) {
            assert(false);
        }catch (MustHaveLeastOneMainReferee e){
            assert(false);
        }catch (MustHaveLeastTwoSideReferee e){
            assert(false);
        } catch (LeagueNameAlreadyExist leagueNameAlreadyExist) {
            assert(false);
        } catch (MustHaveLeastTwoTeams mustHaveLeastTwoTeams) {
            assert(false);
        }
    }

    @Test
      /*Test ID:    #9.4.1 --Scheduling referees to games
                             all the referee by their role scheduled to game
     */
    public void schedulingRefereeTest1() {
        FootballAssociation fa = new FootballAssociation(123, "fa1", "123", "123");
        List<Team> teams = faController.getAllTeams();
        try {
            Referee ref1 = faController.addReferee(fa, "ref1", RefereeType.MAIN, 111, "111", "ref1");
            Referee ref2 = faController.addReferee(fa, "ref2", RefereeType.ASSISTANT, 111, "111", "ref2");
            Referee ref3 = faController.addReferee(fa, "ref3", RefereeType.ASSISTANT, 111, "111", "ref3");
            Referee ref4 = faController.addReferee(fa, "ref4", RefereeType.MAIN, 111, "111", "ref4");
            Referee ref5 = faController.addReferee(fa, "ref5", RefereeType.ASSISTANT, 111, "111", "ref5");
            Referee ref6 = faController.addReferee(fa, "ref6", RefereeType.ASSISTANT, 111, "111", "ref6");
            Referee ref7 = faController.addReferee(fa, "ref7", RefereeType.ASSISTANT, 111, "111", "ref7");
            List<Referee> refereeList = faController.getAllReferee();
            League league = faController.initEmptyLeague("leagueTest", teams);
            LeagueInformation leagueInfoTest = faController.initLeague(fa, league, "2000");
            faController.schedulingGames(fa, leagueInfoTest);
            faController.schedulingReferee(fa, leagueInfoTest, refereeList);
            for(Referee ref:refereeList){
                for(Game game: ref.getGames()){
                    if(ref.getRefereeType()==RefereeType.MAIN){
                        assertEquals(game.getMainReferee().equals(ref),true);
                    }else {
                        if(!game.getAssistantRefereeOne().getUserName().equals(ref.getUserName())&& !game.getAssistantRefereeTwo().getUserName().equals(ref.getUserName())){
                            assert(false);
                        }else{
                            assert(true);
                        }
                    }
                }
            }

        } catch (Exception e) {
            assert(false);
        }
    }

//    @Test
//    public void editScorePolicyTest1(){
//        FootballAssociation fa = new FootballAssociation(123, "fa1", "123", "123");
//        List<Team> teams = faController.getAllTeams();
//        try {
//            Referee ref1 = faController.addReferee(fa, "ref1", RefereeType.MAIN, 111, "111", "ref1");
//            Referee ref2 = faController.addReferee(fa, "ref2", RefereeType.ASSISTANT, 111, "111", "ref2");
//            Referee ref3 = faController.addReferee(fa, "ref3", RefereeType.ASSISTANT, 111, "111", "ref3");
//            Referee ref4 = faController.addReferee(fa, "ref4", RefereeType.MAIN, 111, "111", "ref4");
//            Referee ref5 = faController.addReferee(fa, "ref5", RefereeType.ASSISTANT, 111, "111", "ref5");
//            Referee ref6 = faController.addReferee(fa, "ref6", RefereeType.ASSISTANT, 111, "111", "ref6");
//            Referee ref7 = faController.addReferee(fa, "ref7", RefereeType.ASSISTANT, 111, "111", "ref7");
//            List<Referee> refereeList = faController.getAllReferee();
//            League league = faController.initEmptyLeague("leagueTest", teams);
//            LeagueInformation leagueInfoTest = faController.initLeague(fa, league, "2000");
//
//            faController.editScorePolicy(leagueInfoTest,
//            faController.schedulingGames(fa, leagueInfoTest));
//            faController.schedulingReferee(fa, leagueInfoTest, refereeList);
//        }catch (Exception e){
//
//        }
//    }

    @Test
    /*Test ID:    #9.6.1 --Edit team allocate policy before the season started
     */
    public void editTeamAllocatePolicyTest1() {
        FootballAssociation fa = new FootballAssociation(123, "fa1", "123", "123");
        List<Team> teams = faController.getAllTeams();
        try {
            League league = faController.initEmptyLeague("leagueTest", teams);
            LeagueInformation leagueInfoTest = faController.initLeague(fa, league, "2000");
            faController.editTeamAllocatePolicy(leagueInfoTest,new OneGameAllocatePolicy());//change policy allocate games
            faController.schedulingGames(fa, leagueInfoTest);
            assertEquals(leagueInfoTest.getGames().size(), 3);
        } catch (LeagueNameAlreadyExist leagueNameAlreadyExist) {
            assert (false);
        } catch (IsNotStartOFSeason isNotStartOFSeason) {
            assert(false);
        } catch (MustHaveLeastTwoTeams mustHaveLeastTwoTeams) {
            assert(false);
        }
    }

    @Test
    /*Test ID:    #9.6.2 --Edit team allocate policy before the season started
     */
    public void editTeamAllocatePolicyTest2() {
        FootballAssociation fa = new FootballAssociation(123, "fa1", "123", "123");
        List<Team> teams = faController.getAllTeams();
        try {
            League league = faController.initEmptyLeague("leagueTest", teams);
            LeagueInformation leagueInfoTest = faController.initLeague(fa, league, "2000");
            faController.schedulingGames(fa, leagueInfoTest);
            faController.editTeamAllocatePolicy(leagueInfoTest,new OneGameAllocatePolicy());//change policy allocate games
            assert(false);
        } catch (LeagueNameAlreadyExist leagueNameAlreadyExist) {
            assert (false);
        } catch (IsNotStartOFSeason isNotStartOFSeason) {
            assert(true);
        } catch (MustHaveLeastTwoTeams mustHaveLeastTwoTeams) {
            assert(false);
        }
    }

    @Test
    /*Test ID:    #9.7.1 --Scheduling games to league
                           result: exists 30 games for 6 teams
     */
    public void schedulingGamesTest1(){
        FootballAssociation fa = new FootballAssociation(123, "fa1", "123", "123");
        controller.addTeam(new Team("t1",null));
        controller.addTeam(new Team("t2",null));
        controller.addTeam(new Team("t3",null));
        List<Team> teams = faController.getAllTeams();
        try {
            League league = faController.initEmptyLeague("leagueTest", teams);
            LeagueInformation leagueInfoTest = faController.initLeague(fa, league, "2000");
            faController.schedulingGames(fa, leagueInfoTest);
            assertEquals(leagueInfoTest.getGames().size(),30);
            for(Team t:league.getTeams()){
                if(t.getFutureGames().size()<5){
                    assert(false);
                }else {
                    assert(true);
                }
            }
        } catch (LeagueNameAlreadyExist leagueNameAlreadyExist) {
            assert(false);
        } catch (MustHaveLeastTwoTeams mustHaveLeastTwoTeams) {
            assert(false);
        }
    }

    @Test
    /*Test ID:    #9.7.2 --Scheduling games to league for only one team
                           result: throw MustHaveLeastTwoTeams exception
     */
    public void schedulingGamesTest2(){
        FootballAssociation fa = new FootballAssociation(123, "fa1", "123", "123");
        List<Team> teams = faController.getAllTeams();
        teams.remove(0);
        teams.remove(0);
        try {
            League league = faController.initEmptyLeague("leagueTest", teams);
            LeagueInformation leagueInfoTest = faController.initLeague(fa, league, "2000");
            faController.schedulingGames(fa, leagueInfoTest);
            assert(false);
        } catch (LeagueNameAlreadyExist leagueNameAlreadyExist) {
            assert(false);
        } catch (MustHaveLeastTwoTeams mustHaveLeastTwoTeams) {
            assert(true);
        }
    }

    @Test
    /*Test ID:    #???? --Create a new team
                          result: team added to teamOwner and controller list
    */
    public void createTeamTest1(){
        TeamOwner teamOwner= new TeamOwner(111,"owner1","111","111",111);
        Team t1=faController.createTeam("t1", teamOwner);
        assertEquals(teamOwner.getTeamList().get(0),t1);
        assertEquals(t1.getAllTeamOwners().isEmpty(),false);
        boolean teamAdded=false;
        for (Team t:faController.getAllTeams()){
            if(t.equals(t1)){
                teamAdded=true;
                break;
            }
        }
        assertEquals(teamAdded,true);
        assertEquals(t1.getTeamStatus(), TeamStatus.Active);
    }

}

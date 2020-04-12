//import System.Enum.RefereeType;
//import System.Enum.TeamStatus;
//import System.FootballObjects.Game;
//import System.FootballObjects.League;
//import System.FootballObjects.LeagueInformation;
//import System.FootballObjects.Season;
//import System.FootballObjects.Team.Team;
//import System.Users.Referee;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class LeagueInformationTest {
//    League PremierLeague;
//    Season season;
//    LeagueInformation leagueInformation;
//
//    Team Arsenal= new Team("Arsenal", TeamStatus.Active, null, null, null, null, null, 0, 0, null);
//    Team Liverpool = new Team("Liverpool", TeamStatus.Active, null, null, null, null, null, 0, 0, null);
//    Team Chelsea= new Team("Chelsea", TeamStatus.Active, null, null, null, null, null, 0, 0, null);
//
////    Game game=new Game(null,2000,null,null,null,Arsenal,Liverpool);
////    Game game1=new Game(null,2000,null,null,null,Chelsea,Liverpool);
////    Game game2=new Game(null,2000,null,null,null,Arsenal,Chelsea);
//
//
//    Referee rTest1=new Referee("Hen", RefereeType.MainReferee,204,"abc","KillerReferee");
//    Referee rTest0=new Referee("Max", RefereeType.AssistantReferee,205,"abc","KillerReferee");
//    Referee rTest2=new Referee("Dana", RefereeType.AssistantReferee,206,"abc","KillerReferee");
//    Referee rTest3=new Referee("Shachar", RefereeType.AssistantReferee,207,"abc","KillerReferee");
//
//    List<Referee> referees= new ArrayList<>();
//
//
//
//    @Before
//    public void setUp() throws Exception {
//        PremierLeague= new League("PremierLeague");
//        List<Team> teams= new ArrayList<>();
//
//        teams.add(Arsenal);
//        teams.add(Liverpool);
//        teams.add(Chelsea);
//        season= new Season(2000,teams);
////        season.setGameToGames(game);
////        season.setGameToGames(game1);
////        season.setGameToGames(game2);
//        leagueInformation= new LeagueInformation(PremierLeague,season);
//    }
//
//    @Test
//    public void getLeagueTable() {
//        leagueInformation.updateScoreTeamInLeageTable(season.getTeam().get(0),5);
//        leagueInformation.updateScoreTeamInLeageTable(season.getTeam().get(1),3);
//        leagueInformation.updateScoreTeamInLeageTable(season.getTeam().get(2),1);
//
//        Team [] teams= new Team[3];
//        int i=0;
//        HashMap<Team,Integer> temp= leagueInformation.getLeagueTable();
//        for (HashMap.Entry me : temp.entrySet()) {
//            teams[i]=(Team) me.getKey();
//            i++;
//        }
//
//        Team [] teams2= new Team[3];
//        teams2[0]=Arsenal;
//        teams2[1]=Liverpool;
//        teams2[2]=Chelsea;
//
//        assertArrayEquals(teams,teams2);
//
//        leagueInformation.updateScoreTeamInLeageTable(season.getTeam().get(1),10);
//
//        i=0;
//        HashMap<Team,Integer> temp2= leagueInformation.getLeagueTable();
//        for (HashMap.Entry me : temp2.entrySet()) {
//            teams[i]=(Team) me.getKey();
//            i++;
//        }
//
//        Team [] teams3= new Team[3];
//        teams3[0]=Liverpool;
//        teams3[1]=Arsenal;
//        teams3[2]=Chelsea;
//        assertArrayEquals(teams,teams3);
//
//    }
//
//    @Test
//    public void schedulingReferee() {
//        referees.add(rTest0);
//        referees.add(rTest1);
//        referees.add(rTest2);
//        referees.add(rTest3);
//        leagueInformation.schedulingReferee(referees);
//
//        assertEquals(season.getGames().get(0).getMainReferee().getName(),"Hen");
//        assertEquals(season.getGames().get(0).getAssistantRefereeOne().getName(),"Max");
//        assertEquals(season.getGames().get(0).getAssistantRefereeTwo().getName(),"Dana");
//
//        assertEquals(season.getGames().get(1).getMainReferee().getName(),"Hen");
//        assertEquals(season.getGames().get(1).getAssistantRefereeOne().getName(),"Shachar");
//        assertEquals(season.getGames().get(1).getAssistantRefereeTwo().getName(),"Max");
//
//        assertEquals(season.getGames().get(2).getMainReferee().getName(),"Hen");
//        assertEquals(season.getGames().get(2).getAssistantRefereeOne().getName(),"Dana");
//        assertEquals(season.getGames().get(2).getAssistantRefereeTwo().getName(),"Shachar");
//
//        assertEquals(season.getGames().get(3).getMainReferee().getName(),"Hen");
//        assertEquals(season.getGames().get(3).getAssistantRefereeOne().getName(),"Max");
//        assertEquals(season.getGames().get(3).getAssistantRefereeTwo().getName(),"Dana");
//
//        assertEquals(season.getGames().get(4).getMainReferee().getName(),"Hen");
//        assertEquals(season.getGames().get(4).getAssistantRefereeOne().getName(),"Shachar");
//        assertEquals(season.getGames().get(4).getAssistantRefereeTwo().getName(),"Max");
//
//        assertEquals(season.getGames().get(5).getMainReferee().getName(),"Hen");
//        assertEquals(season.getGames().get(5).getAssistantRefereeOne().getName(),"Dana");
//        assertEquals(season.getGames().get(5).getAssistantRefereeTwo().getName(),"Shachar");
//
//        assertEquals(rTest1.getGames().size(),6);
//        assertEquals(rTest0.getGames().size(),4);
//        assertEquals(rTest2.getGames().size(),4);
//        assertEquals(rTest3.getGames().size(),4);
//
//
//    }
//
//    @Test
//    public void editGameSchedulingPolicy() {
//    }
//
//    @Test
//    public void editScoreSchedulingPolicy() {
//    }
//}
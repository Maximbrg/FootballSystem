//import System.Enum.RefereeType;
//import System.Enum.TeamStatus;
//import System.FootballObjects.League;
//import System.FootballObjects.LeagueInformation;
//import System.FootballObjects.Season;
//import System.FootballObjects.Team.Team;
//import System.Users.FootballAssosiation;
//import System.Users.Referee;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class FootballAssosiationTest {
//    FootballAssosiation footballAssosiation;
//
//    League PremierLeague;
//    Season season;
//    List<Team> teams= new ArrayList<>();
//    List<Referee> referees= new ArrayList<>();
//
//    Team Arsenal= new Team("Arsenal", TeamStatus.Active, null, null, null, null, null, 0, 0, null);
//    Team Liverpool = new Team("Liverpool", TeamStatus.Active, null, null, null, null, null, 0, 0, null);
//    Team Chelsea= new Team("Chelsea", TeamStatus.Active, null, null, null, null, null, 0, 0, null);
//
//    Referee rTest1=new Referee("Hen", RefereeType.MainReferee,204,"abc","KillerReferee");
//    Referee rTest0=new Referee("Max", RefereeType.AssistantReferee,205,"abc","KillerReferee");
//    Referee rTest2=new Referee("Dana", RefereeType.AssistantReferee,206,"abc","KillerReferee");
//    Referee rTest3=new Referee("Shachar", RefereeType.AssistantReferee,207,"abc","KillerReferee");
//
//
//    @Before
//    public void setUp() throws Exception {
//        footballAssosiation= new FootballAssosiation(1, "Shachar", "123", "sha");
//        season=new Season(2000,teams);
//
//        teams.add(Arsenal);
//        teams.add(Liverpool);
//        teams.add(Chelsea);
//        referees.add(rTest0);
//        referees.add(rTest1);
//        referees.add(rTest2);
//        referees.add(rTest3);
//
//    }
//
//    @Test
//    public void initLeague() {
//
//    }
//
//    @Test
//    public void addSeason() {
//    }
//
//    @Test
//    public void getSeasonFromController() {
//    }
//
//    @Test
//    public void addNewReferee() {
//        try{
//            footballAssosiation.addNewReferee("Hen", RefereeType.MainReferee,204,"123","KillerReferee");
//        }catch (Exception e){
//
//        }
//    }
//
//    @Test
//    public void removeReferee() {
//    }
//
//    @Test
//    public void manuallChangingReferee() {
//    }
//}
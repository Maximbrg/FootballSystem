
import System.Enum.RefereeType;
import System.FootballObjects.League;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.FootballObjects.Team.Team;
import System.Users.FootballAssociation;
import System.Users.Referee;
import System.Users.TeamOwner;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class LeagueInformationTest {
    League PremierLeague;
    Season season;
    LeagueInformation leagueInformation;

    TeamOwner teamOwner=new TeamOwner(1,"Inbar","123","Inbar",100);
    Team Arsenal= new Team("Arsenal", teamOwner);
    Team Liverpool = new Team("Liverpool", teamOwner);
    Team Chelsea= new Team("Chelsea",  teamOwner);

//    Game game=new Game(null,2000,null,null,null,Arsenal,Liverpool);
//    Game game1=new Game(null,2000,null,null,null,Chelsea,Liverpool);
//    Game game2=new Game(null,2000,null,null,null,Arsenal,Chelsea);


    Referee rTest1=new Referee("Hen", RefereeType.MAIN,204,"abc","KillerReferee");
    Referee rTest0=new Referee("Max", RefereeType.ASSISTANT,205,"abc","KillerReferee");
    Referee rTest2=new Referee("Dana", RefereeType.ASSISTANT,206,"abc","KillerReferee");
    Referee rTest3=new Referee("Shachar", RefereeType.ASSISTANT,207,"abc","KillerReferee");

    List<Referee> referees= new ArrayList<>();





    @Before
    public void setUp() throws Exception {

        List<Team> teams= new ArrayList<>();

        teams.add(Arsenal);
        teams.add(Liverpool);
        teams.add(Chelsea);
        PremierLeague= new League("PremierLeague",teams);
        season= new Season(2000);
        FootballAssociation footballAssociation = new FootballAssociation(1, "Shachar", "111", "Sha");
        leagueInformation= new LeagueInformation(PremierLeague,season, footballAssociation);
        leagueInformation.initLeagueInformation();

    }

    @Test
    public void getLeagueTable() {
        leagueInformation.updateScoreTeamInLeagueTable(PremierLeague.getTeams().get(0),"WIN");
        leagueInformation.updateScoreTeamInLeagueTable(PremierLeague.getTeams().get(1),"TIE");
        leagueInformation.updateScoreTeamInLeagueTable(PremierLeague.getTeams().get(2),"LOSS");

        Team [] teams= new Team[3];
        int i=0;
        HashMap<Team,Integer> temp= leagueInformation.getLeagueTable();
        for (HashMap.Entry me : temp.entrySet()) {
            teams[i]=(Team) me.getKey();
            i++;
        }

        Team [] teams2= new Team[3];
        teams2[0]=Arsenal;
        teams2[1]=Liverpool;
        teams2[2]=Chelsea;

        assertArrayEquals(teams,teams2);

        leagueInformation.updateScoreTeamInLeagueTable(PremierLeague.getTeams().get(1),"WIN");

        i=0;
        HashMap<Team,Integer> temp2= leagueInformation.getLeagueTable();
        for (HashMap.Entry me : temp2.entrySet()) {
            teams[i]=(Team) me.getKey();
            i++;
        }

        Team [] teams3= new Team[3];
        teams3[0]=Liverpool;
        teams3[1]=Arsenal;
        teams3[2]=Chelsea;
        assertArrayEquals(teams,teams3);

    }

    @Test
    public void schedulingReferee() {
        referees.add(rTest0);
        referees.add(rTest1);
        referees.add(rTest2);
        referees.add(rTest3);
        leagueInformation.schedulingReferee(referees);

        assertEquals(leagueInformation.getGames().get(0).getMainReferee().getName(),"Hen");
        assertEquals(leagueInformation.getGames().get(0).getAssistantRefereeOne().getName(),"Max");
        assertEquals(leagueInformation.getGames().get(0).getAssistantRefereeTwo().getName(),"Dana");

        assertEquals(leagueInformation.getGames().get(1).getMainReferee().getName(),"Hen");
        assertEquals(leagueInformation.getGames().get(1).getAssistantRefereeOne().getName(),"Shachar");
        assertEquals(leagueInformation.getGames().get(1).getAssistantRefereeTwo().getName(),"Max");

        assertEquals(leagueInformation.getGames().get(2).getMainReferee().getName(),"Hen");
        assertEquals(leagueInformation.getGames().get(2).getAssistantRefereeOne().getName(),"Dana");
        assertEquals(leagueInformation.getGames().get(2).getAssistantRefereeTwo().getName(),"Shachar");

        assertEquals(leagueInformation.getGames().get(3).getMainReferee().getName(),"Hen");
        assertEquals(leagueInformation.getGames().get(3).getAssistantRefereeOne().getName(),"Max");
        assertEquals(leagueInformation.getGames().get(3).getAssistantRefereeTwo().getName(),"Dana");

        assertEquals(leagueInformation.getGames().get(4).getMainReferee().getName(),"Hen");
        assertEquals(leagueInformation.getGames().get(4).getAssistantRefereeOne().getName(),"Shachar");
        assertEquals(leagueInformation.getGames().get(4).getAssistantRefereeTwo().getName(),"Max");

        assertEquals(leagueInformation.getGames().get(5).getMainReferee().getName(),"Hen");
        assertEquals(leagueInformation.getGames().get(5).getAssistantRefereeOne().getName(),"Dana");
        assertEquals(leagueInformation.getGames().get(5).getAssistantRefereeTwo().getName(),"Shachar");

        assertEquals(rTest1.getGames().size(),6);
        assertEquals(rTest0.getGames().size(),4);
        assertEquals(rTest2.getGames().size(),4);
        assertEquals(rTest3.getGames().size(),4);


    }

    @Test
    public void editGameSchedulingPolicy() {
    }

    @Test
    public void editScoreSchedulingPolicy() {
    }
}

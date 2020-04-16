
import System.Enum.RefereeType;
import System.Exeptions.UserNameAlreadyExistException;
import System.FootballObjects.League;
import System.Controller;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Season;
import System.FootballObjects.Team.Team;
import System.Users.FootballAssociation;
import System.Users.Referee;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FootballAssociationTest {
    FootballAssociation footballAssociation;

    League PremierLeague;
    Season season;
    List<Team> teams= new ArrayList<>();
    List<Referee> referees= new ArrayList<>();

    Team Arsenal= new Team("Arsenal", null);
    Team Liverpool = new Team("Liverpool", null);
    Team Chelsea= new Team("Chelsea", null);

    Referee rTest1=new Referee("Hen", RefereeType.MAIN,204,"abc","KillerReferee");
    Referee rTest0=new Referee("Max", RefereeType.ASSISTANT,205,"abc","Max");
    Referee rTest2=new Referee("Dana", RefereeType.ASSISTANT,206,"abc","Dana");
    Referee rTest3=new Referee("Shachar", RefereeType.ASSISTANT,207,"abc","Shachar");


    @Before
    public void setUp() throws Exception {
        footballAssociation = new FootballAssociation(1, "Shachar", "123", "sha");
        season=new Season(2000);
        teams.add(Arsenal);
        teams.add(Liverpool);
        teams.add(Chelsea);
        referees.add(rTest0);
        referees.add(rTest1);
        referees.add(rTest2);
        referees.add(rTest3);
        PremierLeague= new League("PremierLeague",teams);

    }

    @Test
    public void initLeague() {
        footballAssociation.initLeague(season,PremierLeague);
        assertEquals(footballAssociation.getLeagueInformations().size(),1);
        assertEquals(footballAssociation.getLeagueInformations().get(1).getId(),1);

        assertEquals(season.getLeaguesInformation().size(),1);
        assertEquals(PremierLeague.getLeagueInformation().size(),1);
    }

    @Test
    public void addSeason() {
    }

    @Test
    public void getSeasonFromController() {
    }

    @Test
    public void addNewReferee() {
        //user not found , username not exist in the Controller
        try {
            footballAssociation.addNewReferee("Shiran", RefereeType.MAIN,204,"123","Shiran");
        } catch (UserNameAlreadyExistException e) {
            assert(true);
        }
        //user found
        try {
            footballAssociation.addNewReferee("Hen", RefereeType.MAIN,204,"abc","KillerReferee");

        } catch (UserNameAlreadyExistException e) {
            assert(false);
        }
    }

    @Test
    public void removeReferee() {
        Referee Shiran=new Referee("Shiran", RefereeType.MAIN,204,"123","Shiran");
        //user not found , username not exist in the Controller
        try {
            footballAssociation.removeReferee(Shiran);
        } catch (Exception e) {
            assert(true);
        }


        try {
            footballAssociation.addNewReferee("Shiran", RefereeType.MAIN,204,"123","Shiran");
        } catch (UserNameAlreadyExistException e) {
            assert(false);
        }

        //user found- shiran removed
        try {
            footballAssociation.removeReferee((Referee) Controller.getInstance().getUsers().get("Shiran"));
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void manuallChangingReferee() {
        LeagueInformation leagueInformation=new LeagueInformation(PremierLeague,season, footballAssociation);
        leagueInformation.initLeagueInformation();
        leagueInformation.schedulingReferee(referees);
        footballAssociation.manualChangingReferee(leagueInformation,referees,rTest0);
        assertEquals(rTest0.getFutureGames().size(),0);
    }
}

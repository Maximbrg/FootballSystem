
import System.Enum.TeamStatus;
import System.FootballObjects.League;
import System.FootballObjects.Team.Team;
import System.Users.TeamOwner;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class LeagueTest  {

     League PremierLeague;
    @Before
    public void setUp() throws Exception {
        Team Arsenal= new Team("Arsenal", null);
        Team Liverpool = new Team("Liverpool", null);
        Team Chelsea= new Team("Chelsea", null);
        List<Team> teams= new ArrayList<>();
        teams.add(Arsenal);
        teams.add(Liverpool);
        teams.add(Chelsea);
        PremierLeague= new League("PremierLeague",teams);

    }


    @Test
    public void addTeam() {
        assertEquals(PremierLeague.getTeams().size(),3);
        Team ManchesterUnited = new Team("Manchester United", null);
        PremierLeague.addTeam(ManchesterUnited);
        assertEquals(PremierLeague.getTeams().size(),4);
        assertEquals(PremierLeague.getTeams().get(3).getName(),"Manchester United");
    }
}


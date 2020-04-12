/*
import System.Enum.TeamStatus;
import System.FootballObjects.League;
import System.FootballObjects.Team.Team;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LeagueTest  {

     League PremierLeague;
    @Before
    public void setUp() throws Exception {
        PremierLeague= new League("PremierLeague");
    }


    @Test
    public void addTeam() {
        assertEquals(PremierLeague.getTeams().size(),0);
        Team Liverpool = new Team("Liverpool", TeamStatus.Active, null, null, null, null, null, 0, 0, null);
        PremierLeague.addTeam(Liverpool);
        assertEquals(PremierLeague.getTeams().size(),1);
        assertEquals(PremierLeague.getTeams().get(0).getName(),"Liverpool");
    }
}

*/
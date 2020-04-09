
import System.FootballObjects.Team.OneGameAllocatePolicy;
import System.FootballObjects.Team.Team;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import System.Enum.TeamStatus;
import System.FootballObjects.Game;


public class OneGameAllocatePolicyTest extends OneGameAllocatePolicy {
    List<Team> teams= new ArrayList<>();
    List<Game> games= new ArrayList<>();
    @Before
    public void init() throws Exception {
        Team Arsenal= new Team("Arsenal", TeamStatus.Active, null, null, null, null, null, 0, 0, null);
        Team Liverpool = new Team("Liverpool", TeamStatus.Active, null, null, null, null, null, 0, 0, null);
        Team Chelsea= new Team("Chelsea", TeamStatus.Active, null, null, null, null, null, 0, 0, null);
        teams.add(Arsenal);
        teams.add(Liverpool);
        teams.add(Chelsea);
    }

    @Test
    public void setTeamPolicyTest(){
        assertEquals(games.size(),0);
        assertEquals(teams.size(),3);

        setTeamPolicy(teams,games);
        assertEquals(games.size(),3);
        assertEquals(teams.size(),3);
        assertEquals(games.get(0).getHome().getName(), "Arsenal");
        assertEquals(games.get(0).getAway().getName(), "Liverpool");

        assertEquals(games.get(1).getHome().getName(), "Arsenal");
        assertEquals(games.get(1).getAway().getName(), "Chelsea");

        assertEquals(teams.get(0).getGamesOfTeams().size(), 2);
        assertEquals(teams.get(1).getGamesOfTeams().size(), 2);
        assertEquals(teams.get(2).getGamesOfTeams().size(), 2);
    }

}
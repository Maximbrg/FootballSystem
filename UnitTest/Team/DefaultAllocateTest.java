

import System.FootballObjects.Team.DefaultAllocate;
import System.FootballObjects.Team.Team;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import System.FootballObjects.Game;


public class DefaultAllocateTest extends DefaultAllocate {
    List<Team> teams= new ArrayList<>();
    List<Game> games= new ArrayList<>();
    @Before
    public void init() throws Exception {
        Team Arsenal= new Team("Arsenal", null);
        Team Liverpool = new Team("Liverpool", null);
        Team Chelsea= new Team("Chelsea", null);
        teams.add(Arsenal);
        teams.add(Liverpool);
        teams.add(Chelsea);
    }

    @Test
    public void setTeamPolicyTest(){
        assertEquals(games.size(),0);
        assertEquals(teams.size(),3);

        setTeamPolicy(teams,games);

        assertEquals(games.size(),6);
        assertEquals(teams.size(),3);
        assertEquals(games.get(0).getHome().getName(), "Arsenal");
        assertEquals(games.get(0).getAway().getName(), "Liverpool");
        assertEquals(games.get(1).getHome().getName(), "Liverpool");
        assertEquals(games.get(1).getAway().getName(), "Arsenal");

        assertEquals(games.get(2).getHome().getName(), "Arsenal");
        assertEquals(games.get(2).getAway().getName(), "Chelsea");
        assertEquals(games.get(3).getHome().getName(), "Chelsea");
        assertEquals(games.get(3).getAway().getName(), "Arsenal");

        assertEquals(teams.get(0).getGamesOfTeams().size(), 4);
        assertEquals(teams.get(1).getGamesOfTeams().size(), 4);
        assertEquals(teams.get(2).getGamesOfTeams().size(), 4);

    }

}



package System.FootballObjects.Team;

import System.FootballObjects.Game;
import java.util.List;

/**
 * Interface for setting games policy
 */
public interface ITeamAllocatePolicy {

    void setTeamPolicy(List<Team> teams, List<Game> games);

}
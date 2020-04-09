package System.Asset;

import System.Exeptions.HasTeamAlreadyException;
import System.FootballObjects.Team.Team;

public interface Asset {

    void editAssetValue(int newVal);

    void resetMyTeam();

    void addMyTeam(Team team) throws HasTeamAlreadyException;

    int getSalary();



}

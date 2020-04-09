package System.Asset;

import System.FootballObjects.Team.Team;

public interface Asset {

    void editAssetValue(int newVal);

    void resetMyTeam();

    void addMyTeam(Team team);

    int getSalary();



}

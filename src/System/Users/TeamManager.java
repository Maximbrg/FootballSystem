package System.Users;

import System.Asset.Asset;
import System.FootballObjects.Team.Team;
import System.I_Observer.IObserverTeam;

public class TeamManager extends User implements Asset, IObserverTeam {

    private int salary;
    private Team team;

    @Override
    public void edit() {

    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void update() {

    }
}

package System.FootballObjects;

import System.FootballObjects.Team.Team;

import java.util.List;

public class League {

    private static int ID=1;
    private int id;
    private String name;
    private List<Team> teams;
    private List<LeagueInformation> leagueInformation;

    public League(String name) {
        this.id= ID;
        ID++;
        this.name=name;

    }


    //<editor-fold desc="Getters">
    public String getName() {
        return name;
    }
    public List<LeagueInformation> getLeagueInformation() {
        return leagueInformation;
    }

    public void addTeam(Team t){
        teams.add(t);
    }
    //</editor-fold>

}

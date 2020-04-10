package System.FootballObjects;

import System.FootballObjects.Team.Team;
import System.IShowable;

import java.util.ArrayList;
import java.util.List;

public class League implements IShowable {

    private static int ID=1;
    private int id;
    private String name;
    private List<Team> teams;
    private List<LeagueInformation> leagueInformation;

    public League(String name) {
        this.id= ID;
        ID++;
        this.name=name;
        this.teams= new ArrayList<>();
        this.leagueInformation= new ArrayList<>();

    }


    //<editor-fold desc="Getters">
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "League";
    }

    @Override
    public String getDetails() {
        String str = "@name:"+name;
        return str;
    }

    public List<LeagueInformation> getLeagueInformation() {
        return leagueInformation;
    }

    public List<Team> getTeams(){
        return teams;
    }
    public void addTeam(Team t){
        teams.add(t);
    }
    //</editor-fold>

}

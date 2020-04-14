package System.FootballObjects;

import System.FootballObjects.Team.Team;
import System.IShowable;
import System.*;
import java.util.ArrayList;
import java.util.List;

public class League implements IShowable {

    private static int ID=1;
    private int id;
    private String name;
    private List<Team> teams;
    private List<LeagueInformation> leagueInformations;

    //<editor-fold desc="Constructor">
    public League(String name, List<Team> teams) {
        this.id= ID;
        ID++;
        this.name=name;
        this.teams= new ArrayList<>();
        this.leagueInformations= new ArrayList<>();
        for (Team t : teams) {
            this.teams.add(t);
        }
    }
    //</editor-fold>


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
        return leagueInformations;
    }

    public List<Team> getTeams(){
        return teams;
    }
    public void addTeam(Team t){
        teams.add(t);
    }

    //</editor-fold>


    public void addLeagueInformation(LeagueInformation leagueInformation) {
        this.leagueInformations.add(leagueInformation);
    }
}

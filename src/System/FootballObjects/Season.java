package System.FootballObjects;

import System.FootballObjects.Team.Team;
import System.Controller;
import System.IShowable;

import java.util.ArrayList;
import java.util.List;

public class Season implements IShowable {

    private int year;
    private List<LeagueInformation> leagueInformations;


    //<editor-fold desc="constructor">
    public Season(int year){
        this.year=year;
        leagueInformations=new ArrayList<>();

        //iTeamAllocatePolicy= new DefualtAllocte();

    }
    //</editor-fold>
    public String getYear() {
        return String.valueOf(year);
    }
    public int getIntYear() {
        return year;
    }

    public List<LeagueInformation> getLeagueInformations() {
        return leagueInformations;
    }


    @Override
    public String getName() {
        return "Season:"+year;
    }

    @Override
    public String getType() {
        return "Season";
    }

    @Override
    public String getDetails() {
        String str = "@year:"+year;
        return str;
    }

    public void addLeagueInformation(LeagueInformation leagueInformation) {
        this.leagueInformations.add(leagueInformation);
    }



    //Methods


    //public void schedulingGames(){} //UC-35


}

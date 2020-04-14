package System.FootballObjects;

import System.IShowable;

import java.util.ArrayList;
import java.util.List;

public class Season implements IShowable {

    private int year;
    private List<LeagueInformation> leagueInformations;




    //<editor-fold desc="constractur">
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
    public List<LeagueInformation> getLeagueInformations() {
        return leagueInformations;
    }


    //</editor-fold>

    //Methods
    public void addLeagueInformation(LeagueInformation leagueInformation) {
        this.leagueInformations.add(leagueInformation);
    }




}

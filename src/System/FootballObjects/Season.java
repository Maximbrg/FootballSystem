package System.FootballObjects;

import System.Enum.RefereeType;
import System.FootballObjects.Team.Team;
import System.Controller;
import System.Log;
import java.util.ArrayList;
import java.util.List;

public class Season {

    private int year;
    //private String result;
    private List<Game> games;
    private List<Team> teams;
    private Controller controller;

    //<editor-fold desc="constractur">
    public Season(int year, List<Game> games){
        this.year=year;
        this.games=games;
        //iTeamAllocatePolicy= new DefualtAllocte();

    }
    //</editor-fold>
    public List<Game> getGames() {
        return games;
    }
    public String getYear() {
        return String.valueOf(year);
    }
    public List<Team> getTeam(){
        return teams;
    }


    //Methods


    //public void schedulingGames(){} //UC-35


}

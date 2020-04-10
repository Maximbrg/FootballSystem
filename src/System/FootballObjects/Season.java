package System.FootballObjects;

import System.FootballObjects.Team.Team;
import System.Controller;
import System.IShowable;

import java.util.ArrayList;
import java.util.List;

public class Season implements IShowable {

    private int year;
    //private String result;
    private List<Game> games;
    private List<Team> teams;
    private Controller controller;

    //<editor-fold desc="constractur">
    public Season(int year, List<Team> teams){
        this.year=year;
        this.teams=teams;
        this.games=new ArrayList<>();
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

    public void setGameToGames(Game g){
        games.add(g);
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


    //Methods


    //public void schedulingGames(){} //UC-35


}

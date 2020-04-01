package System.FootballObjects;

import System.Users.Referee;
import System.Controller;

import java.util.List;

public class Season {

    private int year;
    private int hour;
    private String result;
    private List<Game> games;
    private Controller controller;

    //Methods
    public void schedulingReferee(List<Referee> referees){} //UC-32

    public void schedulingGames(){} //UC-35
}

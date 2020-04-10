package System.FootballObjects;

import System.Enum.RefereeType;
import System.FootballObjects.Team.*;
import System.Users.FootballAssosiation;
import System.Users.Referee;
import System.Log;

import javax.swing.*;
import java.util.*;


public class LeagueInformation {
    private static int ID=1;
    private int id;
    League league;
    Season season;
    FootballAssosiation footballAssosiation;
    String name;
    HashMap<Team,Integer> leagueTable;
    private List<Game> games;

    ITeamAllocatePolicy iTeamAllocatePolicy;
    IScoreMethodPolicy iScoreMethodPolicy;
    int WIN;
    int LOSS;
    int TIE;
    Team Champion;

    //<editor-fold desc="constructor">
    public LeagueInformation(League league, Season season, FootballAssosiation footballAssosiation) {
        this.id= ID;
        ID++;
        this. league=league;
        this. season= season;
        name= season.getName()+" "+league.getName();
        this.footballAssosiation=footballAssosiation;
        iTeamAllocatePolicy= new DefualtAllocte();
        leagueTable= new LinkedHashMap<>();
        iScoreMethodPolicy= new DefualtMethod();

        games=new ArrayList<>();
        //init league Table with 0 points to all the team.
        for(int i=0;i<league.getTeams().size();i++){
            leagueTable.put(league.getTeams().get(i),0);
        }
    }

    //<editor-fold desc="Getters">
    public int getId() {
        return id;
    }

    public League getLeague() {
        return league;
    }

    public Season getSeason() {
        return season;
    }

    public FootballAssosiation getFootballAssosiation() {
        return footballAssosiation;
    }

    public String getName() {
        return name;
    }

    public ITeamAllocatePolicy getiTeamAllocatePolicy() {
        return iTeamAllocatePolicy;
    }

    public IScoreMethodPolicy getiScoreMethodPolicy() {
        return iScoreMethodPolicy;
    }

    public Team getChampion() {
        return Champion;
    }
    //</editor-fold>

    /**
     * init leagueInformation policy-  Team Allocate Policy AND Score Method Policy.
     */
    public void initLeagueInformation(){
        iTeamAllocatePolicy.setTeamPolicy(league.getTeams(),games);
        //get list of score for policy
        //setSore[0]= WIN, setSore[1]=LOSS, setSore[2]=TIE
        List<Integer> setScore= iScoreMethodPolicy.setScorePolicy();
        WIN=setScore.get(0);
        LOSS=setScore.get(1);
        TIE=setScore.get(2);
    }

    //</editor-fold>



    /**
     *
     * @return the league table sorted by the high scoring team.
     */
    // function to sort hashmap by values
    public  HashMap<Team,Integer> getLeagueTable()
    {
        // Create a list from elements of HashMap
        List<HashMap.Entry<Team,Integer> > list =
                new LinkedList<HashMap.Entry<Team,Integer> >(leagueTable.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<HashMap.Entry<Team,Integer> >() {
            public int compare(Map.Entry<Team,Integer> o1,
                               Map.Entry<Team,Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Team,Integer> temp = new LinkedHashMap<Team,Integer>();
        for (HashMap.Entry<Team,Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }




    /**
     * The Referee were inducted for games this season.
     * @param referees list of all referees
     */
    public void schedulingReferee(List<Referee> referees){
        List <Referee> mainReferee= new ArrayList<>();
        List <Referee> assistentsReferee= new ArrayList<>();

        for(Referee referee:referees){
            if(referee.getRefereeType()== RefereeType.MainReferee){
                mainReferee.add(referee);
            }
            else {
                assistentsReferee.add(referee);
            }
        }
        int i=0;
        int j=0;
        Referee R1;
        Referee R2;
        Referee R3;
        for(Game game:games){
            if( i< mainReferee.size()){
                R1=mainReferee.get(i++);
            }
            else{
                i=0;
                R1=mainReferee.get(i++);
            }
            if( j< assistentsReferee.size()){
                R2= assistentsReferee.get(j++);
            }
            else{
                j=0;
                R2= assistentsReferee.get(j++);
            }
            if( j< assistentsReferee.size()){
                R3= assistentsReferee.get(j++);
            }
            else{
                j=0;
                R3= assistentsReferee.get(j++);
            }
            game.setMainReferee(R1);
            game.setAssistantRefereeOne(R2);
            game.setAssistantRefereeTwo(R3);

            R1.addGame(game);
            R2.addGame(game);
            R3.addGame(game);

        }

        Log.getInstance().writeToLog("The Referee were inducted for games this season. Name: "+league.getName());
    } //UC-32


    /**
     * Edit game scheduling policy with the help of Strategy DP.
     * @param iTeamAllocatePolicy Interface that refers to change policy.
     */
    public void editGameSchedulingPolicy(ITeamAllocatePolicy iTeamAllocatePolicy){

        this.iTeamAllocatePolicy=iTeamAllocatePolicy;
    } //UC-34

    /**
     * Edit score scheduling policy with the help of Strategy DP.
     * @param iScoreMethodPolicy  Interface that refers to change policy.
     */
    public void editScoreSchedulingPolicy(IScoreMethodPolicy iScoreMethodPolicy){//UC-37
        this.iScoreMethodPolicy=iScoreMethodPolicy;
    }

    public void updateScoreTeamInLeageTable(Team t, int score){
        leagueTable.replace(t,score);
    }


    //public void editScoreSchedulingPolicy(League league, Season season , IScoreMethodPolicy iScoreMethodPolicy){} //UC-37

}

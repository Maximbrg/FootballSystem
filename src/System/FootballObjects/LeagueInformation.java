package System.FootballObjects;

import System.Enum.RefereeType;
import System.FootballObjects.Team.*;
import System.Users.Referee;
import System.Log;
import java.util.*;

public class LeagueInformation {
    private static int ID=1;
    private int id;
    League league;
    Season season;
    HashMap<Team,Integer> leagueTable;
    ITeamAllocatePolicy iTeamAllocatePolicy;
    IScoreMethodPolicy iScoreMethodPolicy;

    //<editor-fold desc="constructor">
    public LeagueInformation(League league, Season season) {
        this.id= ID;
        ID++;
        this. league=league;
        this. season= season;
        leagueTable= new LinkedHashMap<>();
        for(int i=0;i<season.getTeam().size();i++){
            leagueTable.put(season.getTeam().get(i),0);
        }

        iTeamAllocatePolicy= new DefualtAllocte();
        iTeamAllocatePolicy.setTeamPolicy(season.getTeam(),season.getGames());

        iScoreMethodPolicy= new DefualtMethod();
        iScoreMethodPolicy.setScorePolicy();
    }
    //</editor-fold>

    //<editor-fold desc="Getters">

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
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Team,Integer> temp = new LinkedHashMap<Team,Integer>();
        for (HashMap.Entry<Team,Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    //</editor-fold>



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
        for(Game game:season.getGames()){
            if( i<= mainReferee.size()){
                R1=mainReferee.get(i++);
            }
            else{
                i=0;
                R1=mainReferee.get(i++);
            }
            if( i<= assistentsReferee.size()){
                R2= assistentsReferee.get(j++);
            }
            else{
                i=0;
                R2= assistentsReferee.get(j++);
            }
            if( i<= assistentsReferee.size()){
                R3= assistentsReferee.get(j++);
            }
            else{
                i=0;
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

    //public void editScoreSchedulingPolicy(League league, Season season , IScoreMethodPolicy iScoreMethodPolicy){} //UC-37

}

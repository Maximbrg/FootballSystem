package System.FootballObjects.Team;

import System.FootballObjects.Game;
import System.Log;
import System.Users.Referee;
import java.util.Date;
import java.util.List;

public class DefualtAllocte implements ITeamAllocatePolicy {
    /**
     *Inaugural games for the league- home and away games for each league team
     * @param team
     * @param games
     */
    public void setTeamPolicy(List<Team> team, List<Game> games){
        Date date=new Date();
        for(int i=0;i<team.size();i++){
            for(int j=i+1; j<team.size();j++){
                //add game1
                Team home= team.get(i);
                Team away= team.get(j);
                Game game=new Game(date, 2000, null, null,null, away, home);
                games.add(game);

                home.getGamesOfTeams().add(game);
                away.getGamesOfTeams().add(game);

                //add game2
                home= team.get(j);
                away= team.get(i);
                Game game2=new Game(date, 2000, null, null,null, away, home);
                games.add(game2);

                home.getGamesOfTeams().add(game2);
                away.getGamesOfTeams().add(game2);

            }
        }
        Log.getInstance().writeToLog("Inaugural games for the league- home and away games for each league team");
    }
}

package System.Users;
import System.FootballObjects.Event.*;
import System.I_Observer.IObserverGame;
import System.I_Observer.ISubjectGame;
import System.Log;
import System.FootballObjects.Game;
import System.FootballObjects.Season;
import System.Enum.RefereeType;



import java.awt.*;
import java.util.*;
import java.util.List;

public class Referee extends User implements IObserverGame {

    private RefereeType type;
    private List<ISubjectGame> subjectGame;



    private List<Game> games;

    public Referee(String name, RefereeType type, int id, String pass, String userName){
        super(id,name,pass,userName);
        this.type=type;
        games=new LinkedList<>();
    }

    //Methods

    public void addGame(Game g){
        games.add(g);
    }
    /**
     * edit an event in the game and till 5 hours from the end of the game . only by the main Referee
     * @param game concret game
     * @param oldEvent old event to edit
     * @param type type of event
     */
    public void editEventAfterGame(Game game , AEvent oldEvent,String type){
        long diffHours =  (new Date(System.currentTimeMillis()).getTime()-game.getDate().getTime() ) / (60 * 60 * 1000);
        if(diffHours<=6.5 && this.type== RefereeType.MainReferee){// 1.5 hours after the beginning
            AEvent editedEvent = createEvent(type,oldEvent.getMinute());
            game.getEventLog().removeEvent(oldEvent);
            game.getEventLog().addEventToLog(editedEvent);
            Log.writeToLog("The referee "+getName()+" edited event"+"(event Id:"+oldEvent.getId()+")." );
        }
    } //UC-40

    /**
     * add new event in the middle of the game - by every referee while it is in the middle of the game
     * @param game current game
     * @param type type of the event
     * @param min min of the occasion
     */
    public void addEventMidGame(Game game,String type,int min){
        long diffHours =  (new Date(System.currentTimeMillis()).getTime()-game.getDate().getTime() ) / (60 * 60 * 1000);
        if(diffHours<1.5 &&(game.getMainReferee().getId()==getId() || game.getAssistantRefereeTwo().getId()==getId() ||game.getAssistantRefereeOne().getId()==getId() )) {
            game.addEventToLogEvent(createEvent(type, min));
            Log.writeToLog("Event was add to the log event game "+game.getId()+"by the referee " + getName()+"." );
        }else{
            //////////to be continue
        }
    }

    /**
     * add event to the game log event
     * @param game current game
     * @param type of the event
     * @param minute in the game
     */
    public void addEventToLogEvent(Game game,String type, int minute){
        AEvent newEvent = createEvent(type,minute);
        game.addEventToLogEvent(newEvent);
        Log.writeToLog("Event was add to the log event game "+game.getId()+"by the referee " + getName()+"." );
    }

    /**
     * create new event
     * @param type of the event
     * @param minute of the occasion
     * @return new AEvent by his type and his minute
     */
    private AEvent createEvent(String type, int minute){
        switch (type){
            case "Goal":
                return new Goal(minute);
            case "Injury":
                return new Injury(minute);
            case "Offense":
                return new Offense(minute);
            case "Offside":
                return new Offside(minute);
            case "RedCard":
                return new RedCard(minute);
            case "YellowCard":
                return new YellowCard(minute);
        }
        return null;
    }

    /**
     * create gameReport
     * @param game current game
     * @return string with the report of the Referee
     */
    public String createGameReport(Game game){
        String report="";
        long diffHours =  (new Date(System.currentTimeMillis()).getTime()-game.getDate().getTime() ) / (60 * 60 * 1000);
        if(diffHours>=1.5 &&type==RefereeType.MainReferee) {
            report = "Report for the game:" + game.getHome().getName() + " vs " + game.getAway().getName() + "\n";
            report += "Main Referee:" + game.getMainReferee().getName() + ".\n";
            report += "Assistant Referee:" + game.getAssistantRefereeOne().getName() + ".\n";
            report += "Assistant Referee:" + game.getAssistantRefereeTwo().getName() + ".\n";
            for (AEvent a : game.getEventLog().getaEventList()) {
                report += a.getMinute() + ". " + a.getClass().toString().substring(35, a.getClass().toString().length()) + "\n";
            }
        }else{
            /////to be continue
        }
        Log.writeToLog("Report for the game:"+game.getHome().getName() + "vs" +game.getAway().getName()+"was created by the referee " + this.getName()+".");
        return report;
    } //UC-41

    /**
     * return a list of future games of the referee
     * @return games for future
     */

    public List<Game> getFutureGames(){
        List<Game> futureGames = new ArrayList<>();
        for(Game game:games){
            long diffHours =  (new Date(System.currentTimeMillis()).getTime()-game.getDate().getTime() ) / (60 * 60 * 1000);
            if(diffHours<=0){//to check it
                futureGames.add(game);
            }
        }
        Collections.sort(futureGames, new Comparator<Game>() {
            public int compare(Game o1, Game o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        Log.writeToLog("The referee "+getName()+"id: "+getId() +" pull his future games.");
        return futureGames;
    }
    /**
     * Referee get the games that he\she was scheduled for them in the season
     * @param season current season to get the relevant games
     * @return
     */
    public List<Game> getGamesForSeason(Season season){
        List<Game> seasonGames= season.getGames();
        List<Game> relevantGames= new ArrayList<>();

        for (int i = 0; i <seasonGames.size() ; i++) {
            Game game=seasonGames.get(i);
            if(game.getMainReferee().getUserName()==getUserName()){
                relevantGames.add(game);
            }else if(game.getAssistantRefereeOne().getUserName()==getUserName()){
                relevantGames.add(game);
            }else if(game.getAssistantRefereeTwo().getUserName()==getUserName()){
                relevantGames.add(game);
            }
        }
        //to check it
        Collections.sort(relevantGames, new Comparator<Game>() {
            public int compare(Game o1, Game o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        Log.writeToLog("The referee "+getName()+"id: "+getId() +" pull his games for "+ season.getYear()+" season.");
        return relevantGames;
    } //UC-39
    /**
     * show alert of event
     */
    @Override
    public void update() {


    }

    /**
     * Add a game to get alert (adding to subjectGame list)
     * @param iSubjectGame
     */
    @Override
    public void registerAlert(ISubjectGame iSubjectGame) {
        subjectGame.add(iSubjectGame);
    }

    /**
     * Remove a game to get alert (adding to subjectGame list)
     * @param iSubjectGame
     */
    @Override
    public void removeAlert(ISubjectGame iSubjectGame) {
        subjectGame.remove(iSubjectGame);
    }

    public RefereeType getType(){
        return type;
    }
    public List<Game> getGames() {
        return games;
    }

}

package System.Users;

import System.I_Observer.IObserverGame;
import System.I_Observer.ISubjectGame;
import System.PersonalPages.PersonalPage;
import System.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Fan extends User implements IObserverGame {

    private List<PersonalPage> FollowPages;
    private List<ISubjectGame> subjectGame;
    private HashMap<Integer, Report> myReports;

    //<editor-fold desc="constructor">
    public Fan(int id, String name, String password, String userName) {
        super(id, name,password,userName);
        this.FollowPages = new LinkedList<>();
        this.subjectGame=new LinkedList<>();
        this.myReports=new HashMap<Integer, Report>();
    }

    @Override
    public void removeUser() {

    }
    //</editor-fold>

    //<editor-fold desc="getter">
    public List<PersonalPage> getFollowPages() {
        return FollowPages;
    }

    public List<ISubjectGame> getSubjectGame() {
        return subjectGame;
    }

    public HashMap<Integer, Report> getMyReports() {
        return myReports;
    }
    //</editor-fold>

    //<editor-fold desc="setter">
    private void setFollowPages(List<PersonalPage> followPages) {
        FollowPages = followPages;
    }

    private void setSubjectGame(List<ISubjectGame> subjectGame) {
        this.subjectGame = subjectGame;
    }

    private void setMyReports(HashMap<Integer, Report> myReports) {
        this.myReports = myReports;
    }

    //</editor-fold>

    //method
    /**
     * Add a personal page for follow
      * @param personalPage
     */
    public void addFollowPage(PersonalPage personalPage){
        this.FollowPages.add(personalPage);

    }

    /**
     * Remove a personal page for follow
     * @param personalPage
     */
    public void removeFollowPage(PersonalPage personalPage){
        this.FollowPages.remove(personalPage);
    }

    /**
     * Create a new report and sensing to manager system
     * @param reportTxt
     * @return report
     */
    public Report submitReport(String reportTxt){
        Report report= new Report(this, reportTxt);
        myReports.put(report.getId(), report);
        SystemManager.addReport(report);
        Log.getInstance().writeToLog("User submit report. (userId:"+getId()+"reportId"+report.getId()+")");
        return report;
    } //UC-11
    /**
     * Add a game to get alert (adding to subjectGame list)
     * @param iSubjectGame
     */
    @Override
    public void registerAlert(ISubjectGame iSubjectGame){
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

    @Override
    public void update() {
        //showAlert
        //log
        Log.getInstance().writeToLog("Fan was updated about a msg. username's fan:"+getUserName());
    }

}

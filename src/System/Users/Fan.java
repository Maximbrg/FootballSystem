package System.Users;

import System.I_Observer.IObserverGame;
import System.I_Observer.ISubjectGame;
import System.PersonalPages.PersonalPage;
import System.Log;


import java.util.LinkedList;
import java.util.List;

public class Fan extends User implements IObserverGame {

    private List<PersonalPage> FollowPages;
    private List<ISubjectGame> subjectGame;

    //<editor-fold desc="constructor">
    public Fan(int id, String name, String password, String userName) {
        super(id, name,password,userName);
        this.FollowPages = new LinkedList<>();
        this.subjectGame=new LinkedList<>();
    }
    //</editor-fold>
    //<editor-fold desc="Methods">
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
    }
    //</editor-fold>
    //<editor-fold desc="setter">
    public void setFollowPages(List<PersonalPage> followPages) {
        FollowPages = followPages;
    }

    public void setSubjectGame(List<ISubjectGame> subjectGame) {
        this.subjectGame = subjectGame;
    }
    //</editor-fold>
    //<editor-fold desc="getter">
    public List<PersonalPage> getFollowPages() {
        return FollowPages;
    }

    public List<ISubjectGame> getSubjectGame() {
        return subjectGame;
    }
    //</editor-fold>



}

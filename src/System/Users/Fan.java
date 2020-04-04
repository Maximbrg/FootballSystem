package System.Users;

import System.I_Observer.IObserverGame;
import System.I_Observer.ISubjectGame;
import System.PersonalPages.PersonalPage;

import java.util.LinkedList;
import java.util.List;

public class Fan extends User implements IObserverGame {

    private String name;
    private List<PersonalPage> FollowPages;
    private List<ISubjectGame> subjectGame;

    public Fan(String name, int id) {
        super(id);
        this.name = name;
        FollowPages = new LinkedList<>();
        subjectGame=new LinkedList<>();
    }


    @Override
    public void update() {
        //showAlert
    }

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


}

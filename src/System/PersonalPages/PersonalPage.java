package System.PersonalPages;

import System.Users.Fan;
import System.Users.User;

import java.util.LinkedList;
import java.util.List;

public class PersonalPage {

     private IPageAvailable pageAvailable;
     private List <Fan> followers;

    public PersonalPage(IPageAvailable pageAvailable) {
        this.pageAvailable = pageAvailable;
        this.followers = new LinkedList<>();
    }

    /**
     * Add follower to personal page
     * @param fan
     */
    public void follow(Fan fan){
        followers.add(fan);
        fan.addFollowPage(this);
    } //UC-7

    /**
     * Remove follower to personal page
     * @param fan
     */
    public void unfollow(Fan fan) {
        followers.remove(fan);
        fan.removeFollowPage(this);
    } //UC-8
}

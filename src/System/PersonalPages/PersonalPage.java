package System.PersonalPages;

import System.Users.Fan;
import System.Log;
import System.Users.User;

import java.util.LinkedList;
import java.util.List;

public class PersonalPage {

     private IPageAvailable pageAvailable;
     private List<String> posts;
     private List <Fan> followers;

    //<editor-fold desc="constructor">
    public PersonalPage(IPageAvailable pageAvailable) {
        this.pageAvailable = pageAvailable;
        this.followers = new LinkedList<>();
        this.posts= new LinkedList<>();
    }
    //</editor-fold>

    //<editor-fold desc="getter">
    public IPageAvailable getPageAvailable() {
        return pageAvailable;
    }

    public List<Fan> getFollowers() {
        return followers;
    }

    public List<String> getPosts() {
        return posts;
    }

    //</editor-fold>

    //<editor-fold desc="setter">
    public void setPageAvailable(IPageAvailable pageAvailable) {
        this.pageAvailable = pageAvailable;
    }

    public void setFollowers(List<Fan> followers) {
        this.followers = followers;
    }
    //</editor-fold>

    /**
     * Add follower to personal page
     * @param fan
     */
    public void follow(Fan fan){
        followers.add(fan);
        fan.addFollowPage(this);
        Log.getInstance().writeToLog(fan.getName() +" (id: "+ fan.getId()+") follows "+ this.pageAvailable.getName());
    } //UC-7

    /**
     * Remove follower to personal page
     * @param fan
     */
    public void unfollow(Fan fan) {
        followers.remove(fan);
        fan.removeFollowPage(this);
        Log.getInstance().writeToLog(fan.getName() +" (id: "+ fan.getId()+") unfollow "+ this.pageAvailable.getName());

    } //UC-8

    /**
     * Add a new post to a personal page
     * @param post
     */
    public void upload(String post){
        this.posts.add(post);

    }

    public void removePost(String post){}

}

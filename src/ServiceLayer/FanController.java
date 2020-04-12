package ServiceLayer;

import System.FootballObjects.Game;
import System.FootballObjects.Team.Team;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;
import System.Users.*;
import System.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FanController extends GuestController implements Observable {

    private static FanController ourInstance = new FanController();

    public static FanController getInstance() {
        return ourInstance;
    }

    private FanController() {
    }

    List<Observer> listeners;

    public List<PersonalPage> getAllpersonalPages() {
        List<PersonalPage> results = new LinkedList<>();
        HashMap<String, User> users = Controller.getInstance().getUsers();
        for (User user : users.values()) {
            if (((user instanceof Player) || (user instanceof Coach))) {
                IPageAvailable iPageAvailable = (IPageAvailable) user;
                if (iPageAvailable.getPersonalPage() != null) {
                    results.add(iPageAvailable.getPersonalPage());
                }
            }
        }
        List<Team> teamList = Controller.getInstance().getAllTeams();
        for(Team team : teamList){
            if(team.getPersonalPage()!=null){
                results.add(team.getPersonalPage());
            }
        }
        return results;
    }

    public void followPersonalPage(Fan fan, PersonalPage personalPage) {
        personalPage.follow(fan);
    }

    public void unfollowPersonalPage(Fan fan, PersonalPage personalPage) {
        personalPage.unfollow(fan);
    }

    public void followGame(Observer observer, Fan fan, Game game) {
        game.registerFanToAlert(fan);
        addObserver(observer);
    }

    public void unfollowGame(Observer observer,Fan fan, Game game){
        game.removeAlertToFan(fan);
        removeObserver(observer);
    }

    public void submitReport(Fan fan,String report){
        Report newReport = new Report(fan,report);
        SystemManager.addReport(newReport);
    }

    public List<String> getHistory(Fan fan){
        return fan.getSearchHistory();
    }

    public String getDetils(Fan fan){
        return "@id:"+fan.getId()+"@name:"+fan.getName()+"@UserStatus:"+fan.getStatus().toString();
    }

    public void editDetails(User fan, int id, String name, String password, String useName){
        if(id!=-1){
            fan.setId(id);
        }
        if(!name.equals("")){
            fan.setName(name);
        }
        if(!password.equals("")){
            fan.setPassword(password);
        }
        if(!useName.equals("")){
            fan.setUserName(useName);
        }
    }

    @Override
    public Fan signUp(int id, String name, String password, String userName) {
        throw new UnsupportedOperationException();
    }
    @Override
    public void logOut(User user) {
        Controller controller = Controller.getInstance();
        controller.logOut(user);
    }

    @Override
    public void addObserver(Observer observer) {
        listeners.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listeners.remove(observer);
    }

    @Override
    public void notifyUI() {
        for (Observer observer : listeners){
            observer.update();
        }
    }
}

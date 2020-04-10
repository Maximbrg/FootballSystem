package System.Users;
import System.Enum.TeamStatus;
import System.Enum.UserStatus;
import System.Report;
import System.Log;
import System.Users.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class User extends Guest {
    protected int id;
    protected String name;
    protected String password;
    protected String userName;
    private List<String> searchHistory;
    protected UserStatus status;



    //Constructor
    public User(int id, String password, String userName, TeamStatus teamStatus){}//delete

    //<editor-fold desc="constructor">
    public User(int id, String name, String password, String userName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userName = userName;
        this.searchHistory= new LinkedList<>();
        this.status= UserStatus.ACTIVE;
    }
    //</editor-fold>

    //<editor-fold desc="getter">
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    public UserStatus getStatus() {
        return status;
    }



    /**
     * Get string of personal details
     * @return
     */
    public String getPersonalSDetails(){
        String str= "ID: "+ id+ "\n" + "User name: "+ userName+"\n";
        return str;
    } //UC-13
    //</editor-fold>

    //<editor-fold desc="setter">
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
        Log.getInstance().writeToLog(name+"(id: "+ id+ ") update his name");

    }

    public void setPassword(String password) {
        this.password = password;
        Log.getInstance().writeToLog(name+"(id: "+ id+ ") update his password");
    }

    public void setUserName(String userName) {
        this.userName = userName;
        Log.getInstance().writeToLog(name+"(id: "+ id+ ") update his userName");

    }

    public void setSearchHistory(List<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
        Log.getInstance().writeToLog(name+"(id: "+ id+ ") update his status");

    }


    //</editor-fold>

    // Methods

    /**
     * Adding search to search history
     * @param line
     * @return
     */
    public List<String> addSearchHistory(String line){
        this.searchHistory.add(line);
        return searchHistory;
    }


}

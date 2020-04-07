package System.Users;
import System.Report;
import System.Log;
import System.Users.UserStatus;

import System.Controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class User extends Guest {
    private  int id;
    private String name;
    private String password;
    private String userName;
    private List<String> searchHistory;
    private UserStatus status;
    private HashMap<Integer, Report> myReports;


    //Constructor
    public User(){}//delete

    //<editor-fold desc="constructor">
    public User(int id, String name, String password, String userName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userName = userName;
        this.searchHistory= new LinkedList<>();
        this.myReports=new HashMap<Integer, Report>();
        this.status=UserStatus.ACTIVE;
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

    public HashMap<Integer, Report> getMyReports() {
        return myReports;
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

    public void setMyReports(HashMap<Integer, Report> myReports) {
        this.myReports = myReports;
    }
    //</editor-fold>

    // Methods

    /**
     * Create a new report and sensing to manager system
     * @param reportTxt
     * @return report
     */
    public Report sumbitReport(String reportTxt){
        Report report= new Report(this, reportTxt);
        this.myReports.put(report.getId(), report);
        SystemManager systemManager=SystemManager.getInstance();
        systemManager.addReport(report);
        Log.getInstance().writeToLog(name +"(id: " + id + ") submit report (id: " + report.getId() + ") to system manager");
        return report;
    } //UC-11

    /**
     * Adding search to search history
     * @param line
     * @return
     */
    public List<String> addSearchHistory(String line){
        this.searchHistory.add(line);
        return searchHistory;
    }

//////////////////////////////////////////
    public void test(){}
/////////////////////////////////////////

}

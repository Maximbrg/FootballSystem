package System.Users;
import System.Report;
import System.Controller;

import java.util.HashMap;

public class User {

    private  int id;
    private String password;
    private String userName;
    private String searchHistory;
   // private List<PersonalPage> personalPages;
    private Status status;
    private HashMap<Integer, Report> myReports;


    //Constructor
    public User(){}

    public User(int id) {
        this.id = id;
        password= null;
        userName=null;
        myReports=new HashMap<Integer, Report>();
        status=Status.ACTIVE;
    }

    // Methods

    /**
     * Create a new report and sensing to manager system
     * @param reportTxt
     * @return report
     */
    public Report sumbitReport(String reportTxt){
        Report report= new Report(this, reportTxt);
        this.myReports.put(report.getId(), report);
        Controller controller= Controller.getInstance();
        controller.submitReport(report);
        //write to LOG
        return report;
    } //UC-11

    /**
     * Get search history
     * @return
     */
    public String GetSearchHistory(){
        return searchHistory;
    } //UC-12

    /**
     * Adding search to search history
     * @param line
     * @return
     */
    public String addSearchHistory(String line){
        this.searchHistory +="\n"+ line;
        return searchHistory;
    }

    /**
     * Get string of personal details
     * @return
     */
    public String showPersonalSDetails(){
        String str= "ID: "+ id+ "\n" + "User name: "+ userName+"\n";
        return str;
    } //UC-13


    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getId() { return id; }

    public String getPassword() { return password; }
}

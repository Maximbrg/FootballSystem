package System;

import System.Users.User;

public class Report {

    private static int id =0;
    private User user;
    private String report;
    private String answer;

    public Report(User user, String report) {
        id++;
        this.user = user;
        this.report = report;
        this.answer="";
    }

    public int getId() {
        return id;
    }

    //Methods
    public String showReport(){
        return "";
    } //UC-27

    public void answer(){} //UC-27


}

package System.Users;

import System.PersonalPages.PersonalPage;

import java.util.List;

public class User {

    private  int id;

    private String password;
    private String userName;
   // private List<PersonalPage> personalPages;
    private Status status;

    //Constructor

    public User (){

    }

    public String getUserName() {
        return userName;
    }
// Methods

    public boolean sumbitReport(String report){
        return true;
    } //UC-11

    public String showSearchHistory(){
        return "";
    } //UC-12

    public String showPersonalSDetails(){
        return "";
    } //UC-13

    public void  editPersonalDetails(/* need to add arguments of the new details */ ){

    } //UC-14
}

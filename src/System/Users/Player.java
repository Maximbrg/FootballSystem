package System.Users;

import System.Asset.Asset;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;

import java.util.Date;

public class Player extends User implements Asset, IPageAvailable {

    private Date birthDate;
    private String role;
    private PersonalPage personalPage;
    private int salary;


    @Override
    public String showDetails() {
        return null;
    }

    @Override
    public void edit() {

    }

    @Override
    public int getSalary() {
        return salary;
    }
}

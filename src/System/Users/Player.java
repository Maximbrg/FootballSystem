package System.Users;

import System.Asset.Asset;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;

import java.util.Date;

public class Player extends User implements Asset, IPageAvailable {

    private String name;
    private Date birthDate;
    private String role;
    private PersonalPage personalPage;


    @Override
    public String showDetails() {
        return null;
    }

    @Override
    public void edit() {

    }
}

package System.Users;

import System.Asset.Asset;
import System.PersonalPages.IPageAvailable;
import System.PersonalPages.PersonalPage;

public class Couch extends User implements Asset, IPageAvailable {

    private String name;
    private String preparation;
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

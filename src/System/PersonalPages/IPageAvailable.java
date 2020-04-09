package System.PersonalPages;

import System.Exeptions.PersonalPageAlreadyExist;

public interface IPageAvailable {

    PersonalPage getPersonalPage();
    String showDetails();
    String getName();
    PersonalPage createPersonalPage() throws PersonalPageAlreadyExist;
}

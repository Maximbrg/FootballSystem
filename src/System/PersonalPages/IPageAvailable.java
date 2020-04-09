package System.PersonalPages;

import System.Exeptions.PersonalPageAlreadyExist;

public interface IPageAvailable {

    String showDetails();
    String getName();
    PersonalPage createPersonalPage() throws PersonalPageAlreadyExist;
}

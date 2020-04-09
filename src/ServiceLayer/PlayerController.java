package ServiceLayer;

import System.PersonalPages.PersonalPage;
import System.Users.Player;
import System.Exeptions.*;

public class PlayerController extends MainUserController{

    private static PlayerController ourInstance = new PlayerController();

    public static PlayerController getInstance() {
        return ourInstance;
    }

    private PlayerController() {
    }

    public String getDetails(Player player){
        return player.getDetails();
    }

    public void createPersonalPage(Player player) throws alreadyHasPageException{
        if(player.getPersonalPage()!=null) {
            throw new alreadyHasPageException();
        }
        else{
            PersonalPage personalPage = new PersonalPage(player);
            player.setPersonalPage(personalPage);
        }
    }

    public void editPersonalPage(Player player, String newContent){
        PersonalPage personalPage = player.getPersonalPage();
        personalPage.setContent(newContent);
    }

}

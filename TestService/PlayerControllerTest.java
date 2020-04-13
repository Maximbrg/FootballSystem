import ServiceLayer.PlayerController;
import System.Exeptions.AlreadyHasPageException;
import System.Exeptions.PersonalPageAlreadyExist;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import System.Users.Player;

import java.util.Date;

public class PlayerControllerTest {

    PlayerController playerController;
    Player p1,p2;

    @Before
    public void init()  {

        playerController = PlayerController.getInstance();
        Date pDate=new Date(1900,3,3,10,10);
        p1=new Player(123,"Harel Sror","abc123","harelush",pDate,"wide receiver",15000,10000);
        p2=new Player(123,"Maxim ","abc123","maxx",pDate,"wide receiver",15000,10000);
    }

    @Test
    public void setDetailsTest(){//update coach details
        Date newDate=new Date(2021,6,15,10,10);
        playerController.setDetails(p1,456,"Harel Chror","aaa111",newDate,"defensive tackle");
        assertTrue(p1.getId()==456);
        assertTrue(p1.getName()=="Harel Chror");
        assertTrue(p1.getPassword()=="aaa111");
        assertTrue(p1.getBirthDate()==newDate);
        assertTrue(p1.getRole()=="defensive tackle");

    }//Test ID:    #4.1

    @Test
    public void createPersonalPageTest() throws AlreadyHasPageException {
        try {
            playerController.createPersonalPage(p1);
            assertTrue(p1.getPersonalPage()!=null);
        }catch (AlreadyHasPageException e){
            fail();
        }
    }

    @Test
    public void editPersonalPageTest(){
        try {
            p2.createPersonalPage();
        }catch (PersonalPageAlreadyExist e){}
        playerController.editPersonalPage(p2,"newContent");
        assertTrue(p2.getPersonalPage().getPosts().contains("newContent"));
    }//Test ID:    #4.2
}

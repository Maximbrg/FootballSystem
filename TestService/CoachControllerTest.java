import ServiceLayer.CoachController;
import System.Exeptions.AlreadyHasPageException;
import System.Exeptions.PersonalPageAlreadyExist;
import System.Users.Coach;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CoachControllerTest {

    CoachController coachController;
    Coach c1,c2,c3,c4;

    @Before
    public void init()  {
        coachController = CoachController.getInstance();
        c1=new Coach(123,"Harel Sror","abc123","harelush",null,null,15000,10000);
        c2=new Coach(123,"Maxim ","abc123","maxx",null,null,15000,10000);
      //  c3=new Coach(123,"Hen","abc123","hen",null,null,15000,10000);
      //  c4=new Coach(123,"Shachar","abc123","Shchar",null,null,15000,10000);
    }

    @Test
    public void setDetailsTest(){//update coach details
    coachController.setDetails(c1,456,"Harel Chror","aaa111","assistant coach");
    assertTrue(c1.getId()==456);
    assertTrue(c1.getName()=="Harel Chror");
    assertTrue(c1.getPassword()=="aaa111");
    assertTrue(c1.getPreparation()=="assistant coach");
    }//Test ID:    #5.1

    @Test
    public void createPersonalPageTest() throws AlreadyHasPageException {
    try {
        coachController.createPersonalPage(c1);
        assertTrue(c1.getPersonalPage()!=null);
    }catch (AlreadyHasPageException e){
        fail();
    }
    }

    @Test
    public void editPersonalPageTest(){
        try {
            c2.createPersonalPage();
        }catch (PersonalPageAlreadyExist e){}
        coachController.editPersonalPage(c2,"newContent");
        assertTrue(c2.getPersonalPage().getPosts().contains("newContent"));
    }//Test ID:    #5.2


}

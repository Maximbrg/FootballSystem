import System.Enum.TeamStatus;
import System.PersonalPages.PersonalPage;
import System.Users.Fan;
import System.Users.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FanStub {
    Fan fTest ;
    PersonalPage personalPageTest,personalPageTest1;
    Player playerTest,playerTest1;

    @Before
    public void init(){
        fTest= new Fan(201,"Itzik","h124","ItzikHaGadol");
        playerTest= new Player(208,"MaorMelichson","aa","maori","maorit",new Date(System.currentTimeMillis()),"Forward",null,150,null,10);
        playerTest1= new Player(207,"ElyanivBarda","aa","maori","maorit",new Date(System.currentTimeMillis()),"Forward",null,150,null,10);
        personalPageTest=new PersonalPage(playerTest);
        personalPageTest1=new PersonalPage(playerTest);
    }

    @Test
    public void addFollowPageTest(){
        fTest.addFollowPage(personalPageTest);
        assertEquals(playerTest.getId(),fTest.getFollowPages().get(0).getId());

    }
    @Test
    public void removeFollowPageTest(){
        fTest.addFollowPage(personalPageTest);
        fTest.addFollowPage(personalPageTest1);
        assertEquals(playerTest.getId(),fTest.getFollowPages().get(0).getId());
        assertEquals(playerTest1.getId(),fTest.getFollowPages().get(0).getId());
        fTest.removeFollowPage(personalPageTest1);
        for (int i = 0; i <fTest.getFollowPages().size() ; i++) {
            assertNotEquals(personalPageTest1.getId(),fTest.getFollowPages().get(i));
        }
    }
}

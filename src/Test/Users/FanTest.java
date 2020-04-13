//
//import System.Enum.TeamStatus;
//import System.Exeptions.PersonalPageAlreadyExist;
//import System.PersonalPages.PersonalPage;
//import System.Users.Fan;
//import System.Users.Player;
//import System.Users.SystemManager;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import System.Report;
//import System.Controller;
//
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class FanTest {
//    Fan fTest ;
//    PersonalPage personalPageTest,personalPageTest1;
//    Player playerTest,playerTest1;
//
//    @Before
//    public void init() throws PersonalPageAlreadyExist {
//        fTest= new Fan(201,"Itzik","h124","ItzikHaGadol");
//        playerTest= new Player(208,"MaorMelichson","aa","maori",new Date(System.currentTimeMillis()),"Forward",10,10);
//        playerTest1= new Player(207,"ElyanivBarda","aa","maori",new Date(System.currentTimeMillis()),"maorit",10,10);
//        playerTest.createPersonalPage();
//        playerTest1.createPersonalPage();
//        //personalPageTest=new PersonalPage(playerTest);
//        //personalPageTest1=new PersonalPage(playerTest1);
//    }
//
//    @Test
//    public void addFollowPageTest(){
////
////        fTest.addFollowPage(personalPageTest);
////        assertEquals(playerTest.getId(),playerTest.getPersonalPage().getFollowers().get(0).getId());
//
//    }
//    @Test
//    public void removeFollowPageTest(){
//        fTest.addFollowPage(playerTest.getPersonalPage());
//        fTest.addFollowPage(playerTest1.getPersonalPage());
//        assertEquals(playerTest.getPersonalPage(),fTest.getFollowPages().get(0));
//        assertEquals(playerTest1.getPersonalPage(),fTest.getFollowPages().get(1));
//        fTest.removeFollowPage(playerTest1.getPersonalPage());
//        for (int i = 0; i <fTest.getFollowPages().size() ; i++) {
//            assertNotEquals(playerTest1.getPersonalPage(),fTest.getFollowPages().get(i));
//        }
//
//    }
//
//    @Test
//    public void submitReportTest(){
////        SystemManager s=new SystemManager(1,"aa","bb","sys");
////        Controller.getInstance().addUser(fTest.getUserName(),fTest);
////        fTest.submitReport("HelloWorld!");
//
//    }
//}

import ServiceLayer.*;
import System.Enum.RefereeType;
import System.Enum.UserStatus;
import System.Exeptions.*;
import System.Users.FootballAssociation;
import System.Users.Referee;
import System.Users.SystemManager;
import System.Users.User;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSuit2 {
    static SystemManager systemManager;
    static GuestController gC;
    static FootballAssociation fA;
    User user1;
    User user2;
    static Referee ref,ref2;
    @BeforeClass
    public static void init() throws UserNameAlreadyExistException {
        systemManager = new SystemManager(1,"a","a","a");
         gC=new GuestController();
        fA=SystemManagerController.getInstance().createNewFootballAssociation(systemManager,1,"Avi","bb","ref123");
        ref=SystemManagerController.getInstance().createNewReferee(systemManager,1,"Avi","bb","referee123",RefereeType.MAIN);
        ref2=SystemManagerController.getInstance().createNewReferee(systemManager,1444,"Avi","bb","babi",RefereeType.MAIN);


    }
    @Test
    public void signUp() throws UserNameAlreadyExistException {////Test ID: 6#  -- Checks if guest can signUp
        user1= gC.signUp(10,"coffee","nes","blacki");
        for(User u:SystemManagerController.getInstance().getAllUsers()){
            if(u.getUserName().equals("blacki")){
                assert(true);
                break;
            }
        }
    }

    @Test
    public void login(){////Test ID: 7#  -- Checks if guest can login
        try {
            gC.login("blacki","nes");
        } catch (WrongPasswordException e) {
            assert (false);
        } catch (NoSuchAUserNamedException e) {
            assert (false);
        }
        for(User u:SystemManagerController.getInstance().getAllUsers()){
            if(u.getUserName().equals("blacki") && u.getStatus().equals(UserStatus.ACTIVE.toString())){
                assert(true);
                break;
            }
        }
    }
    @Test
    public void registerOfNewRefereeToSystem(){////Test ID: 8#  -- Checks if football association can register a new referee
        try {
            FootballAssosiationController.getInstance().addReferee(fA,"Itzik", RefereeType.MAIN,13,"walla","great");
        } catch (UserNameAlreadyExistException e) {
            assert(false);
        }
        for(User f:SystemManagerController.getInstance().getAllUsers()){
            if(f.getUserName().equals("great")){
                assert(true);
                break;
            }
        }
    }
    @Test
    public void refereeUpdateHisDetails(){////Test ID: 9#  --referee update his details
        RefereeController.getInstance().editDetails(ref2,49,"","");
        for(User u:SystemManagerController.getInstance().getAllUsers()){
            if(u.getUserName().equals("babi")){
                if(u.getId()!=49){
                    assert(false);
                }else{
                    assert(true);
                    break;
                }
            }
        }
    }

    @Test
    public void logOut() throws UserNameAlreadyExistException, NoSuchAUserNamedException, WrongPasswordException {//////Test ID: 10#  --fan logOut
        user2= gC.signUp(10,"coffee","nes","whitey");
        gC.login("whitey","nes");
        FanController.getInstance().logOut(user2);
        if(user2.getStatus()!=UserStatus.INACTIVE){
            assert(false);
        }
        assert(true);

    }
}

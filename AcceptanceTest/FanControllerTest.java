import System.*;
import System.Asset.Asset;
import System.Exeptions.UserNameAlreadyExistException;
import System.Users.Fan;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class FanControllerTest {

    @Test
    public void sumbitReport() throws UserNameAlreadyExistException {
        Fan fan = Controller.getInstance().signUp(1,"a","a","b");
        fan.submitReport("report");
        assertTrue(fan.getMyReports().size()==1);
    }

}
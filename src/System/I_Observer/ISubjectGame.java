package System.I_Observer;

import System.Users.Fan;
import System.Users.Referee;
import System.Users.User;

public interface ISubjectGame {

    void registerFanToAlert(IObserverGame fan);
    void registerRefereeToAlert(IObserverGame referee);
    void removeAlertToFan(IObserverGame fan);
    void removeAlertToReferee(IObserverGame referee);
    void notifyFan ();
    void notifyReferee ();
}

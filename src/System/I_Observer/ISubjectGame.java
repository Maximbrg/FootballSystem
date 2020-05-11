package System.I_Observer;

import System.FootballObjects.Event.AEvent;

public interface ISubjectGame {

    void registerFanToAlert(IObserverGame fan);
    void registerRefereeToAlert(IObserverGame referee);
    void removeAlertToFan(IObserverGame fan);
    void removeAlertToReferee(IObserverGame referee);
    void notifyFan (AEvent event);
    void notifyReferee ();

}

package System.I_Observer;

public interface ISubjectGame {

    void registerFanToAlert(IObserverGame fan);
    void registerRefereeToAlert(IObserverGame referee);
    void removeAlertToFan(IObserverGame fan);
    void removeAlertToReferee(IObserverGame referee);
    void notifyFan ();
    void notifyReferee ();

}

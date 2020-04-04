package System.I_Observer;

public interface IObserverGame {

    void update();
    void registerAlert(ISubjectGame iSubjectGame);
    void removeAlert(ISubjectGame iSubjectGame) ;
}

package System.I_Observer;

public interface IObserverTeam {

    void update();
    void registerAlert(ISubjectTeam iSubjectTeam);
    void removeAlert(ISubjectTeam iSubjectTeam) ;
}

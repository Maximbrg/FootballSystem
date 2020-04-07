package System.I_Observer;

import System.Users.User;


public interface ISubjectTeam {

    void registerPlayerToAlert(IObserverTeam player);
    void registerFieldToAlert(IObserverTeam field);
    void registerTeamManagerToAlert(IObserverTeam teamManager);
    void registerCouchToAlert(IObserverTeam couch);

    void removeAlertToPlayer(IObserverTeam player);
    void removeAlertToField(IObserverTeam field);
    void removeAlertToTeamManager(IObserverTeam teamManager);
    void removeAlertToCouch(IObserverTeam couch);

    void notifyPlayer ();
    void notifyField ();
    void notifyTeamManager ();
    void notifyCouch ();
}

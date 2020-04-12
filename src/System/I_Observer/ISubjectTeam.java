package System.I_Observer;

import System.Users.User;


public interface ISubjectTeam {

    void registerSystemManagerToAlert(IObserverTeam systemManager);
    void registerAlert(IObserverTeam obs);


    void removeAlertToSystemManager(IObserverTeam systemManager);
    void removeAlert(IObserverTeam obs);


    void notifySystemManager (String s);

    void notifyTeamOwnersAndManager(String s);

}

package System.I_Observer;

import System.Users.User;


public interface ISubjectTeam {

    void registerAlert (User user);
    void removeAlert (User user);
    void notifyUsers (User user);
}

package System.I_Observer;

import System.Users.User;

public interface ISubjectGame {

    void registerAlert (User user);
    void removeAlert (User user);
    void notifyUsers (User user);
}

package System.FootballObjects.Team;


import System.Enum.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class DefualtMethod implements IScoreMethodPolicy {

    public List <Integer> setScorePolicy() {
        List <Integer> toReturn=new ArrayList();
        toReturn.add(3);
        toReturn.add(0);
        toReturn.add(1);
        return toReturn;
    }

}

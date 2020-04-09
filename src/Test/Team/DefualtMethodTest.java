

import System.Enum.TeamStatus;
import System.FootballObjects.Game;
import System.FootballObjects.LeagueInformation;
import System.FootballObjects.Team.DefualtMethod;
import System.FootballObjects.Team.Team;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;



public class DefualtMethodTest extends DefualtMethod {

    @Test
    public void setTeamPolicyTest(){
        List<Integer> temp= setScorePolicy();
        Integer integer=new Integer(3);
        assertEquals(temp.get(0), integer);
        Integer integer1=new Integer(0);
        assertEquals(temp.get(1), integer1);
        Integer integer2=new Integer(1);
        assertEquals(temp.get(2), integer2);

    }

}
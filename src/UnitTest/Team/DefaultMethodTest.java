

import System.FootballObjects.Team.DefaultMethod;
import org.junit.Test;

import java.util.List;


import static org.junit.Assert.*;



public class DefaultMethodTest extends DefaultMethod {

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
package simulation.adt.test;

import org.junit.Test;
import static org.junit.Assert.*;
import simulation.adt.physics_value.interfaces.TimeDiff;
import simulation.adt.physics_value.interfaces.Acc;
import simulation.adt.physics_value.interfaces.Force;
import simulation.adt.physics_value.classes.Values;

public class TimeDiffTest {
    TimeDiff timediff  = Values.timeDiffInSec(0.0);
    TimeDiff timediff1 = Values.timeDiffInSec(1.0);
    TimeDiff timediff2 = Values.timeDiffInSec(2.0);
    TimeDiff timediff3 = Values.timeDiffInSec(-1.0);
    TimeDiff timediff4 = Values.timeDiffInSec(15.0);
    TimeDiff timediff5 = Values.timeDiffInSec(30.0);

    String string = "30.0s";
    
    @Test
    public void testAdd() {
        assertEquals(timediff4,timediff.add(timediff4));
        assertEquals(timediff5,timediff4.add(timediff4));
        assertEquals(timediff,timediff1.add(timediff3));
    }
    
    @Test 
    public void testSub() {
        assertEquals(timediff,timediff1.sub(timediff1));
        assertEquals(timediff4,timediff5.sub(timediff4));
    }
    
    @Test 
    public void testMul() {
        assertEquals(timediff5,timediff4.mul(2.0));
        assertEquals(timediff5,timediff2.mul(15.0));
        assertEquals(timediff1,timediff1.mul(1.0));
    }
    
    @Test
    public void testDiv() {
        assertEquals(timediff2,timediff5.div(15.0));
        assertEquals(timediff3,timediff3.div(1.0));
    }
    
    @Test 
    public void testAbs() {
        assertEquals(timediff1,timediff3.abs());
        assertEquals(timediff5,timediff5.abs());
    }
    
    @Test
    public void testSignum() {
        assertEquals(timediff,timediff.signum());
        assertEquals(timediff1,timediff5.signum());
        assertEquals(timediff3,timediff3.signum());
    }
        
    @Test
    public void testToString() {
        assertEquals(string,timediff5.toString());
    }
}

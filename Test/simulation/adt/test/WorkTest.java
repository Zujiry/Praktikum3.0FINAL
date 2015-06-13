package simulation.adt.test;

import org.junit.Test;
import static org.junit.Assert.*;
import simulation.adt.physics_value.interfaces.Work;
import simulation.adt.physics_value.interfaces.Power;
import simulation.adt.physics_value.interfaces.TimeDiff;
import simulation.adt.physics_value.classes.Values;

public class WorkTest {
    Work work  = Values.workInJ(0.0);
    Work work1 = Values.workInJ(1.0);
    Work work2 = Values.workInJ(2.0);
    Work work3 = Values.workInJ(-1.0);
    Work work4 = Values.workInJ(10.0);
    Work work5 = Values.workInJ(20.0);
    
    Power power = Values.powerInW(2.0);
    
    TimeDiff timediff = Values.timeDiffInSec(10.0);
    
    String string = "20.0J";
    
    @Test
    public void testAdd() {
        assertEquals(work5,work4.add(work4));
        assertEquals(work4,work4.add(work));
        assertEquals(work2,work1.add(work1));
    }
    
    @Test 
    public void testSub() {
        assertEquals(work4,work5.sub(work4));
        assertEquals(work2,work1.sub(work3));
        assertEquals(work,work4.sub(work4));
    }
    
    @Test 
    public void testMul() {
        assertEquals(work5,work1.mul(20.0));
    }
    
    @Test
    public void testDiv() {
        assertEquals(power,work5.div(timediff));
    }
    
    @Test 
    public void testAbs() {
        assertEquals(work,work.abs());
        assertEquals(work1,work3.abs());
    }
    
    @Test
    public void testSignum() {
        assertEquals(work,work.signum());
        assertEquals(work1,work5.signum());
        assertEquals(work3,work3.signum());
    }
        
    @Test
    public void testToString() {
        assertEquals(string,work5.toString());
    }
}

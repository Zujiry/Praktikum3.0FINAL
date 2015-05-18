package simulation.adt.test;

import org.junit.Test;
import static org.junit.Assert.*;
import simulation.adt.interfaces.Power;
import simulation.adt.interfaces.TimeDiff;
import simulation.adt.interfaces.Force;
import simulation.adt.interfaces.Work;
import simulation.adt.classes.Values;

public class PowerTest {
    Power power = Values.powerInW(0.0);
    Power power1 = Values.powerInW(1.0);
    Power power2 = Values.powerInW(2.0);
    Power power3 = Values.powerInW(-1.0);
    Power power4 = Values.powerInW(10.0);
    Power power5 = Values.powerInW(20.0);
    
    Force force = Values.forceInN(4.0);
    
    TimeDiff timediff = Values.timeDiffInSec(5.0);
    
    Work work = Values.workInJ(10.0);
    
    String string = "20.0W";
    
    @Test
    public void testAdd() {
        assertEquals(power, power.add(power));
        assertEquals(power2, power1.add(power1));
        assertEquals(power, power3.add(power1));
    }
    
    @Test 
    public void testSub() {
        assertEquals(power, power.sub(power));
        assertEquals(power4, power5.sub(power4));
        assertEquals(power1, power2.sub(power1));
    }
    
    @Test 
    public void testMul() {
        assertEquals(power, power5.mul(0.0));
        assertEquals(power5, power2.mul(10.0));
        assertEquals(work, power2.mul(timediff));
    }
    
    @Test
    public void testDiv() {
        assertEquals(force,power5.div(timediff));
    }
    
    @Test 
    public void testAbs() {
        assertEquals(power1,power3.abs());
    }
    
    @Test
    public void testSignum() {
        assertEquals(power,power.signum());
        assertEquals(power1,power5.signum());
        assertEquals(power3,power3.signum());
    }
        
    @Test
    public void testToString() {
        assertEquals(string, power5.toString());
    }
}

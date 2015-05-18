package simulation.adt.test;

import org.junit.Test;
import static org.junit.Assert.*;
import simulation.adt.interfaces.Speed;
import simulation.adt.interfaces.Length;
import simulation.adt.interfaces.TimeDiff;
import simulation.adt.classes.Values;

public class SpeedTest {
    Speed speed = Values.speedInMS(0.0);
    Speed speed1 = Values.speedInMS(1.0);
    Speed speed2 = Values.speedInMS(2.0);
    Speed speed3 = Values.speedInMS(-1.0);
    Speed speed4 = Values.speedInMS(10.0);
    Speed speed5 = Values.speedInMS(20.0);
    
    Length length = Values.lengthInM(10.0);
    
    TimeDiff timediff = Values.timeDiffInSec(10.0);
    
    String string = "10.0m/s";
    
    @Test
    public void testAdd() {
        assertEquals(speed,speed.add(speed));
        assertEquals(speed5,speed4.add(speed4));
        assertEquals(speed,speed3.add(speed1));
    }
    
    @Test 
    public void testSub() {
        assertEquals(speed1,speed2.sub(speed1));
        assertEquals(speed4,speed5.sub(speed4));
        assertEquals(speed,speed5.sub(speed5));
    }
    
     @Test 
    public void testMul() {
        assertEquals(speed5,speed4.mul(2.0));
        assertEquals(speed4,speed2.mul(5.0));   
    }
    
    @Test
    public void testDiv() {
        assertEquals(length,speed1.mul(timediff));
        assertEquals(speed1,speed5.div(20.0));
    }
    
    @Test 
    public void testAbs() {
        assertEquals(speed1,speed3.abs());
        assertEquals(speed5,speed5.abs());
    }
    
    @Test
    public void testSignum() {
        assertEquals(speed3,speed3.signum());
        assertEquals(speed1,speed5.signum());
        assertEquals(speed,speed.signum());
    }
        
    @Test
    public void testToString() {
        assertEquals(string, speed4.toString());
    }
}

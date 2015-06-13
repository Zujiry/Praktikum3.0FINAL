package simulation.adt.test;

import org.junit.Test;
import static org.junit.Assert.*;
import simulation.adt.physics_value.interfaces.Length;
import simulation.adt.physics_value.interfaces.Speed;
import simulation.adt.physics_value.interfaces.TimeDiff;
import simulation.adt.physics_value.classes.Values;

public class LengthTest {
    Length length  = Values.lengthInM(0.0);
    Length length1 = Values.lengthInM(1.0);
    Length length2 = Values.lengthInM(2.0);
    Length length3 = Values.lengthInM(-1.0);
    Length length4 = Values.lengthInM(10.0);
    Length length5 = Values.lengthInM(20.0);
    
    Speed speed = Values.speedInMS(2.0);
    
    TimeDiff timediff = Values.timeDiffInSec(10.0);
    
    String string = "20.0m";
    
    @Test
    public void testAdd() {
        assertEquals(length5,length4.add(length4));
        assertEquals(length,length1.add(length3));
    }
    
    @Test 
    public void testSub() {
        assertEquals(length, length1.sub(length1));
        assertEquals(length4,length5.sub(length4));
    }
    
    @Test 
    public void testMul() {
        assertEquals(length5,length4.mul(2.0));
        assertEquals(length,length.mul(2.0));
    }
    
    @Test
    public void testDiv() {
        assertEquals(speed,length5.div(timediff));
        assertEquals(length1,length5.div(20.0));
        assertEquals(length4,length5.div(2.0));
    }
    
    @Test 
    public void testAbs() {
        assertEquals(length1,length3.abs());
    }
    
    @Test
    public void testSignum() {
        assertEquals(length1,length5.signum());
    }
        
    @Test
    public void testToString() {
        assertEquals(string,length5.toString());
    }
}

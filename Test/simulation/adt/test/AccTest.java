package simulation.adt.test;

import org.junit.Test;
import static org.junit.Assert.*;
import simulation.adt.physics_value.interfaces.Mass;
import simulation.adt.physics_value.interfaces.Acc;
import simulation.adt.physics_value.interfaces.Force;
import simulation.adt.physics_value.classes.Values;

public class AccTest {
    Acc acc = Values.accInMS2(10.0);
    Acc acc1 = Values.accInMS2(0.0);
    Acc acc2 = Values.accInMS2(1.0);
    Acc acc3 = Values.accInMS2(-1.0);
    Acc acc4 = Values.accInMS2(20.0);
    
    Force force = Values.forceInN(10.0);
    Mass mass = Values.massInKG(1.0);
    
    String string = "10.0m/s²";
    
    @Test
    public void testAdd() {
        assertEquals(acc,acc.add(acc1));
        assertEquals(acc4,acc.add(acc));
    }
    
    @Test 
    public void testSub() {
        assertEquals(acc1,acc.sub(acc));
        assertEquals(acc3,acc1.sub(acc2));
    }
    
    @Test 
    public void testMul() {
        assertEquals(acc4,acc.mul(2.0));
        assertEquals(acc1,acc4.mul(0.0));
    }
    
    @Test
    public void testDiv() {
        assertEquals(acc2,acc.div(10.0));
        assertEquals(acc2,acc3.div(-1.0));
        assertEquals(mass,acc.div(force));
        assertEquals(force,acc.div(mass));
    }
    
    @Test 
    public void testAbs() {
        assertEquals(acc2,acc3.abs());
    }
    
    @Test
    public void testSignum() {
        assertEquals(acc3,(acc.sub(acc4)).signum());
    }
        
    @Test
    public void testToString() {
        assertEquals(string,acc.toString());
    }
}

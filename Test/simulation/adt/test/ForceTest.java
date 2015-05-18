package simulation.adt.test;

import org.junit.Test;
import static org.junit.Assert.*;
import simulation.adt.interfaces.Mass;
import simulation.adt.interfaces.Acc;
import simulation.adt.interfaces.Force;
import simulation.adt.interfaces.Length;
import simulation.adt.interfaces.Work;
import simulation.adt.classes.Values;

public class ForceTest {
    Force force = Values.forceInN(0.0);
    Force force1 = Values.forceInN(1.0);
    Force force2 = Values.forceInN(-1.0);
    Force force3 = Values.forceInN(10.0);
    Force force4 = Values.forceInN(20.0);
    
    Mass mass = Values.massInKG(10.0);
    Acc acc = Values.accInMS2(2.0);
    Work work = Values.workInJ(10.0);
    Length length = Values.lengthInM(10.0);
    
    String string = "20.0N";
    
    @Test
    public void testAdd() {
        assertEquals(force1,force.add(force1));
        assertEquals(force4,force3.add(force3));
    }
    
    @Test 
    public void testSub() {
        assertEquals(force3,force4.sub(force3));
        assertEquals(force2,force.sub(force1));
    }
    
    @Test 
    public void testMul() {
        assertEquals(force4,force3.mul(2.0));
        assertEquals(work,force1.mul(length));
    }
    
    @Test
    public void testDiv() {
        assertEquals(force1, force3.div(10.0));
        assertEquals(force1, force2.div(-1.0));
        assertEquals(force3, force4.div(2.0));
        assertEquals(acc,force4.div(mass));
    }
    
    @Test 
    public void testAbs() {
        assertEquals(force1, force2.abs());
    }
    
    @Test
    public void testSignum() {
        assertEquals(force1,force4.signum());
    }
        
    @Test
    public void testToString() {
        assertEquals(string,force4.toString());
    }
}

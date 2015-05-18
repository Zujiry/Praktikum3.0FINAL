package simulation.adt.test;

import org.junit.Test;
import static org.junit.Assert.*;
import simulation.adt.interfaces.Mass;
import simulation.adt.interfaces.Acc;
import simulation.adt.interfaces.Force;
import simulation.adt.classes.Values;
import simulation.adt.units.MassUnit;

public class MassTest {
    Mass mass = Values.massInG(30000.0);
    Mass mass1 = Values.ZERO_MASS;
    Mass mass2 = Values.massInKG(-30.0);
    Mass mass3 = Values.mass(1.0, MassUnit.KG);
    Mass mass4 = Values.massInKG(29.0);
    
    Acc acc = Values.accInMS2(10.0);
    
    Force force = Values.forceInN(300.0);
    
    String string = "30.0kg";
    
    @Test
    public void testAdd() {
        assertEquals(mass, mass.add(mass1));
    }
    
    @Test 
    public void testSub() {
        assertEquals(mass1, mass.sub(mass));
        assertEquals(mass4, mass.sub(mass3));
        assertEquals(mass2, mass1.sub(mass));
    }
    
    @Test 
    public void testMul() {
        //Force
        assertEquals(force,mass.mul(acc));
        //Mass
        assertEquals(mass1, mass.mul(0.0));
        assertEquals(mass, mass.mul(1.0));
    }
    
    @Test
    public void testDiv() {
        assertEquals(mass3,mass.div(30.0));
    }
    
    @Test 
    public void testAbs() {
        assertEquals(mass, mass2.abs());
    }
    
    @Test
    public void testSignum() {
        assertEquals(mass3,mass.signum());
    }
        
    @Test
    public void testToString() {
        assertEquals(string, mass.toString());
    }
}

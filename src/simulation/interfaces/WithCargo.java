package simulation.interfaces;

import simulation.adt.physics_value.interfaces.Mass;

public interface WithCargo {
    public Mass emptyMass();    
    public Mass maxMass();    
    public boolean isEmpty();     
    public boolean isFull(); 
    
    //Leermasse: 2280 kg
    //Maximalmasse: 24 t
}


package simulation.interfaces;

public interface Container extends Stowage<Pallet>, /*WithUniqueId,*/ WithStowLoc, WithForm, Comparable<Container>{    
    public boolean isFree();  
}

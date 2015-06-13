package simulation.interfaces;

import simulation.marker.interfaces.Immutable;

public interface Pallet extends Immutable, /*Body, WithUniqueId*/ WithStowLoc, WithForm, Comparable<Pallet>{
    
}

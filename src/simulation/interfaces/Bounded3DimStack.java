package simulation.interfaces;

import java.util.Collection;
import java.util.Set;
import simulation.adt.admin_value.interfaces.StowageLocation;
import simulation.marker.interfaces.Mutable;

public interface Bounded3DimStack<E> extends Mutable{
    public void load(int bayNo, int rowNo, E elem);
    public void load(E elem);
    public void loadAll(Collection<? extends E> coll);
    boolean isEmpty();
    boolean isFull();
    boolean tierIsFull(int bay,int row);
    boolean tierIsEmpty(int bay, int row);
    boolean contains(Object elem);
    boolean containsAll(Collection<? extends E> coll);
    E get(StowageLocation loc);
    Set<E> getAll();
    StowageLocation locationOf(E elem);
    
}

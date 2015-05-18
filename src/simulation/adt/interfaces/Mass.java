package simulation.adt.interfaces;

public interface Mass extends AbstractValue<Mass>{
    public Force mul(Acc acc);
}

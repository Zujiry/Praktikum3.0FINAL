package simulation.adt.physics_value.interfaces;

public interface Mass extends AbstractValue<Mass>{
    public Force mul(Acc acc);
}

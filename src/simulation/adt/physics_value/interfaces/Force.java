package simulation.adt.physics_value.interfaces;

public interface Force extends AbstractValue<Force>{
    public Work mul(Length length);
    public Acc div(Mass mass);
}

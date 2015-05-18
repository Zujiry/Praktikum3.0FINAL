package simulation.adt.interfaces;

public interface Force extends AbstractValue<Force>{
    public Work mul(Length length);
    public Acc div(Mass mass);
}

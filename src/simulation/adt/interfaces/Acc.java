package simulation.adt.interfaces;

public interface Acc extends AbstractValue<Acc>{  
    public Mass div(Force force);
    public Force div(Mass mass);
}

package simulation.adt.physics_value.interfaces;

public interface Length extends AbstractValue<Length> {
    public Speed div(TimeDiff timediff);
}

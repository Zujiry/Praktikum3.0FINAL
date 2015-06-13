package simulation.adt.physics_value.interfaces;

public interface Power extends AbstractValue<Power>{   
    public Force div(TimeDiff timediff);
    public Work mul(TimeDiff timediff);
}

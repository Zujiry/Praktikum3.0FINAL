package simulation.adt.interfaces;

public interface Length extends AbstractValue<Length> {
    public Speed div(TimeDiff timediff);
}

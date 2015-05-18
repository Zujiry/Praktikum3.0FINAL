package simulation.adt.interfaces;

public interface Speed extends AbstractValue<Speed>{
    public Length mul(TimeDiff timediff);
}

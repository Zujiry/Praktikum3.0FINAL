package simulation.adt.classes;

import simulation.adt.interfaces.TimeDiff;

class TimeDiffImpl extends AbstractValueImpl<TimeDiff> implements TimeDiff{

    private TimeDiffImpl(double inValue) {
        super(inValue);
    }
    
    static TimeDiff valueOf(double inValue) {
        return new TimeDiffImpl(inValue);
    }

    @Override
    public TimeDiff fromPrototype(double value) {
        return valueOf(value);
    }
    
    @Override
    public boolean checkInstance(Object other) {
         return other instanceof TimeDiff;
    }
    
    @Override
    public TimeDiff add(TimeDiff other) {
        return TimeDiffImpl.valueOf(other.value() + this.value());
    }

    @Override
    public TimeDiff mul(double other) {
        return TimeDiffImpl.valueOf(other * this.value());
    }
            
    @Override
    public TimeDiff div(double other) {
        return TimeDiffImpl.valueOf(this.value() / other);
    }   
    
    @Override
    public TimeDiff sub(TimeDiff other) {
        return this.add(TimeDiffImpl.valueOf(-other.value()));
    } 
    
    @Override
    public String toString() {
        return toString_EU();
    }
    
    public String toString_EU() {
        String text = this.value + "s";
        return text;
    }
}

package simulation.adt.classes;

import simulation.adt.interfaces.Power;
import simulation.adt.interfaces.TimeDiff;
import simulation.adt.interfaces.Force;
import simulation.adt.interfaces.Work;

class PowerImpl extends AbstractValueImpl<Power> implements Power{

    private PowerImpl(double inValue) {
        super(inValue);
    }
    
    static Power valueOf(double inValue) {
        return new PowerImpl(inValue);
    }

    @Override
    public Power fromPrototype(double value) {
        return valueOf(value);
    }
    
    @Override
    public boolean checkInstance(Object other) {
         return other instanceof Power;
    }
    
    @Override
    public Power add(Power other) {
        return PowerImpl.valueOf(other.value() + this.value());
    }

    @Override
    public Power mul(double other) {
        return PowerImpl.valueOf(other * this.value());
    }
    
    public Work mul(TimeDiff other) {
        return WorkImpl.valueOf(other.value() * this.value());
    }

    @Override
    public Power div(double other) {
        return PowerImpl.valueOf(this.value() / other);
    }   
    
    public Force div(TimeDiff other) {
        return ForceImpl.valueOf(this.value() / other.value());
    } 
    
    @Override
    public Power sub(Power other) {
        return this.add(PowerImpl.valueOf(-other.value()));
    }    
    
    @Override
    public String toString() {
        return toString_EU();
    }
    
    public String toString_EU() {
        String text = this.value + "W";
        return text;
    }
}
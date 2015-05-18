package simulation.adt.classes;

import simulation.adt.interfaces.Acc;
import simulation.adt.interfaces.Mass;
import simulation.adt.interfaces.Force;

public class AccImpl extends AbstractValueImpl<Acc> implements Acc{

    private AccImpl(double inValue) {
        super(inValue);
    }
    
    public static Acc valueOf(double inValue) {
        return new AccImpl(inValue);
    }

    @Override
    public Acc fromPrototype(double value) {
        return valueOf(value);
    }
    
    @Override
    public boolean checkInstance(Object other) {
         return other instanceof Acc;
    }
    
    @Override
    public Acc add(Acc other) {
        return AccImpl.valueOf(other.value() + this.value());
    }

    public Acc mul(double other) {
        return AccImpl.valueOf(other * this.value());
    }

    public Acc div(double other) {
        return AccImpl.valueOf(other / this.value());
    }   
    
    public Mass div(Force other) {
        return MassImpl.valueOf(this.value() / other.value());
    }
    
    public Force div(Mass other) {
        return ForceImpl.valueOf(this.value() / other.value());
    }
    
    @Override
    public Acc sub(Acc other) {
        return this.add(AccImpl.valueOf(-other.value()));
    } 
    
    @Override
    public String toString() {
        return toString_EU();
    }
    
    public String toString_EU() {
        String text = this.value + "m/s²";
        return text;
    }
}
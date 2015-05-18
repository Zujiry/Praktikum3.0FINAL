package simulation.adt.classes;

import simulation.adt.interfaces.Speed;
import simulation.adt.interfaces.Acc;
import simulation.adt.interfaces.Mass;
import simulation.adt.interfaces.TimeDiff;
import simulation.adt.interfaces.Length;

class SpeedImpl extends AbstractValueImpl<Speed> implements Speed{

    private SpeedImpl(double inValue) {
        super(inValue);
    }
    
    static Speed valueOf(double inValue) {
        return new SpeedImpl(inValue);
    }

    @Override
    public Speed fromPrototype(double value) {
        return valueOf(value);
    }
    
    @Override
    public boolean checkInstance(Object other) {
         return other instanceof Speed;
    }
    
    @Override
    public Speed add(Speed other) {
        return SpeedImpl.valueOf(other.value() + this.value());
    }

    @Override
    public Speed mul(double other) {
        return SpeedImpl.valueOf(other * this.value);
    }
    
    public Length mul(TimeDiff other) {
        return LengthImpl.valueOf(this.value() * other.value());
    }

    @Override
    public Speed div(double other) {
        return SpeedImpl.valueOf(this.value / other);
    }   
    
//    public Acc div(TimeDiff other) {
//        return AccImpl.valueOf(this.value / other.value());
//    }
    
    @Override
    public Speed sub(Speed other) {
        return this.add(SpeedImpl.valueOf(-other.value()));
    } 
    
    @Override
    public String toString() {
        return toString_EU();
    }
    
    public String toString_EU() {
        String text = this.value + "m/s";
        return text;
    }
}
package simulation.adt.physics_value.classes;

import simulation.adt.physics_value.interfaces.Speed;
import simulation.adt.physics_value.interfaces.Acc;
import simulation.adt.physics_value.interfaces.Mass;
import simulation.adt.physics_value.interfaces.TimeDiff;
import simulation.adt.physics_value.interfaces.Length;

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
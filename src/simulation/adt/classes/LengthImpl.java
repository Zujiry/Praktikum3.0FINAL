package simulation.adt.classes;

import simulation.adt.interfaces.Length;
import simulation.adt.interfaces.Mass;
import simulation.adt.interfaces.Work;
import simulation.adt.interfaces.Force;
import simulation.adt.interfaces.Speed;
import simulation.adt.interfaces.TimeDiff;

class LengthImpl extends AbstractValueImpl<Length> implements Length{

    private LengthImpl(double inValue) {
        super(inValue);
    }
    
    static Length valueOf(double inValue) {
        return new LengthImpl(inValue);
    }

    @Override
    public Length fromPrototype(double value) {
        return valueOf(value);
    }
    
    @Override
    public boolean checkInstance(Object other) {
         return other instanceof Length;
    }
    
    @Override
    public Length add(Length other) {
        return LengthImpl.valueOf(other.value() + this.value());
    }
    
    public Work mul(Force other) {
        return WorkImpl.valueOf(other.value() * this.value());
    }
    
    @Override
    public Length mul(double other) {
        return LengthImpl.valueOf(other * this.value());
    }

    @Override
    public Length div(double other) {
        return LengthImpl.valueOf(this.value() / other);
    }   
    
    public Speed div(TimeDiff other) {
        return SpeedImpl.valueOf(this.value / other.value());
    }
    
    @Override
    public Length sub(Length other) {
        return this.add(LengthImpl.valueOf(-other.value()));
    }     
    
    @Override
    public String toString() {
        return toString_EU();
    }
    
    public String toString_EU() {
        String text = this.value + "m";
        return text;
    }
}

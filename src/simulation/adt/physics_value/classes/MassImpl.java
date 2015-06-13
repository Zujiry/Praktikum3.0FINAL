package simulation.adt.physics_value.classes;

import simulation.adt.physics_value.interfaces.Force;
import simulation.adt.physics_value.interfaces.Acc;
import simulation.adt.physics_value.interfaces.Mass;

public class MassImpl extends AbstractValueImpl<Mass> implements Mass{

    private MassImpl(double inValue) {
        super(inValue);
    }
    
    public static Mass valueOf(double inValue) {
        return new MassImpl(inValue);
    }

    @Override
    public Mass fromPrototype(double value) {
        return valueOf(value);
    }
    
    @Override
    public boolean checkInstance(Object other) {
         return other instanceof Mass;
    }
    
    @Override
    public Mass add(Mass other) {
        return MassImpl.valueOf(other.value() + this.value());
    }
    
    @Override
    public Mass mul (double other) {
        return MassImpl.valueOf(other * this.value());
    }
    
    @Override
    public Force mul(Acc other) {
        return ForceImpl.valueOf(this.value() * other.value());
    }

    public Mass div(double other) {
        return MassImpl.valueOf(other / this.value);
    }   
    
    @Override
    public Mass sub(Mass other) {
        return this.add(MassImpl.valueOf(-other.value()));
    }    
    
    @Override
    public String toString() {
        return toString_EU();
    }
    
    public String toString_EU() {
        String text = this.value + "kg";
        return text;
    }    
}

package simulation.adt.physics_value.classes;

import simulation.adt.physics_value.interfaces.Work;
import simulation.adt.physics_value.interfaces.TimeDiff;
import simulation.adt.physics_value.interfaces.Power;

class WorkImpl extends AbstractValueImpl<Work> implements Work{

    private WorkImpl(double inValue) {
        super(inValue);
    }
    
    static Work valueOf(double inValue) {
        return new WorkImpl(inValue);
    }

    @Override
    public Work fromPrototype(double value) {
        return valueOf(value);
    }
    
    @Override
    public boolean checkInstance(Object other) {
         return other instanceof Work;
    }
    
    @Override
    public Work add(Work other) {
        return WorkImpl.valueOf(other.value() + this.value());
    }

    @Override
    public Work mul(double other) {
        return WorkImpl.valueOf(other * this.value());
    }
    
    public Power div(TimeDiff other) {
        return PowerImpl.valueOf(this.value() / other.value());
    }
    
    public TimeDiff div(Work other) {
        return TimeDiffImpl.valueOf(this.value() / other.value());
    }
    
    @Override
    public Work div(double other) {
        return WorkImpl.valueOf(this.value() / other);
    }   
    
    @Override
    public Work sub(Work other) {
        return this.add(WorkImpl.valueOf(-other.value()));
    } 
    
    @Override
    public String toString() {
        return toString_EU();
    }
    
    public String toString_EU() {
        String text = this.value + "J";
        return text;
    }
}
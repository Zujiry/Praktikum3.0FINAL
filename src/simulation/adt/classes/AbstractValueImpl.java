package simulation.adt.classes;
import simulation.adt.interfaces.AbstractValue;

abstract class AbstractValueImpl<E extends AbstractValue> implements AbstractValue<E>{

    double value;
    
    protected AbstractValueImpl(double inValue) {
        this.value = inValue;
    }
    
    
    @Override
    public E add(E other) {
        return fromPrototype(value + other.value());
    }

    @Override
    public double value() {
        return value;
    }
    
    @Override
    public int compareTo(E t) {
        // if (this.value() < t.value)
        //     return negativeValue;
        // else if (this.value() > t.value
        //     return positiveValue;
        // else
        //     return 0;
        
        return Double.compare(this.value(), t.value());
        //this.value().compareTo(t.value());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        // Referenzgleichheit
        if (this == obj) {
            return true;
        }
        
        // Typgleichheit
        if (!this.checkInstance(obj)) {
            return false;
        }
        
        // Wertegleichheit
        E other = (E)(obj);
        return (this.compareTo(other) == 0);
    }
}

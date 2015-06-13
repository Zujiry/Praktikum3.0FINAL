package simulation.adt.physics_value3d.classes;

import java.util.Objects;
import simulation.adt.phsyics_value3d.interfaces.BoundingBox;
import simulation.adt.physics_value.interfaces.Length;

public class BoundingBoxImpl implements BoundingBox{
    
    //Var
    Length width;
    Length height;
    Length length;
    
    //Constructor
    private BoundingBoxImpl(Length width, Length height, Length length) {
        this.width = width;
        this.height = height;
        this.length = length;        
    }
    
    //Factory
    public static BoundingBox valueOf(Length width, Length height, Length length) {
        return new BoundingBoxImpl(width,height,length);
    }

    //Getter
    public Length getWidth() {
        return width;
    }

    public Length getHeight() {
        return height;
    }

    public Length getLength() {
        return length;
    }
    
    //Boolean
    public boolean checkInstance(Object other) {
         return other instanceof BoundingBox;
    }
    
    public boolean fitsInto(BoundingBoxImpl other) {
        if(width.value()  < other.getWidth().value() &&
           height.value() < other.getHeight().value()&& 
           length.value() < other.getLength().value()) {
           return true;
        }
        return false;
    }

    //Hash&Equal
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.width);
        hash = 97 * hash + Objects.hashCode(this.height);
        hash = 97 * hash + Objects.hashCode(this.length);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoundingBoxImpl other = (BoundingBoxImpl) obj;
        if (!Objects.equals(this.width, other.width)) {
            return false;
        }
        if (!Objects.equals(this.height, other.height)) {
            return false;
        }
        if (!Objects.equals(this.length, other.length)) {
            return false;
        }
        return true;
    }
        
    //toString
    public String toString() {
        return toString_EU();
    }
   
    public String toString_EU() {
        String text = (this.width.mul(this.height.value() * this.length.value())) + "m²";
        return text;
    }
}

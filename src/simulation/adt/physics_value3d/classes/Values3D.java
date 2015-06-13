package simulation.adt.physics_value3d.classes;

import simulation.adt.phsyics_value3d.interfaces.BoundingBox;
import simulation.adt.physics_value.interfaces.Length;

public class Values3D {
    
    private Values3D() {}
    
    public static BoundingBox boundingBoxInM3(Length width, Length height, Length length) {
        return BoundingBoxImpl.valueOf(width, height, length);
    }
}

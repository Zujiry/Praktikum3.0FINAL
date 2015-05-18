/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation.adt.units;

public enum MassUnit {
    KG(1),
    G(0.001),
    T(1000);
    
    private final double factor;
    MassUnit(double factor) {
        this.factor = factor;
    }
    
    public double factor() {
        return factor;
    }
}

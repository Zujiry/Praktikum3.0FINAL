/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation.adt.admin_value.classes;

import simulation.adt.admin_value.interfaces.StowageLocation;
import simulation.marker.interfaces.AdminValue;

/**
 *
 * @author Zujiry
 */
public class StowageLocationImpl implements AdminValue,StowageLocation{
    int bay;
    int row;
    int tier;
    boolean isNull;
    
    private StowageLocationImpl(int bay, int row, int tier, boolean isNull){
        this.bay = bay;
        this.row = row;
        this.tier = tier;
        this.isNull =  isNull;
    }
    
    public static StowageLocation valueOf(int bay, int row, int tier, boolean isNull) {
        return new StowageLocationImpl(bay, row, tier, isNull);
    }
    
    public int bay(){
        return bay;
    }
    
    public int row() {
        return row;
    }
    
    public int tier(){
        return tier;
    }
    
    public boolean isNull() {
        return isNull;
    }

}

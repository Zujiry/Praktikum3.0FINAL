/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation.adt.admin_value.interfaces;

/**
 *
 * @author Zujiry
 */
public interface StowageLocation {
    public int bay();
    public int row();
    public int tier();
    public boolean isNull();
}

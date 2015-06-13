/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation.adt.admin_value.classes;

/**
 *
 * @author Zujiry
 */
public class UniqueIdImpl {
    long idNumber;
    
    private UniqueIdImpl(long idNumber) {
        this.idNumber = idNumber;
    }
    
    public long idNumber() {
        return idNumber;
    }
}

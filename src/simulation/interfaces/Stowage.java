/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation.interfaces;

import simulation.marker.interfaces.Mutable;

/**
 *
 * @author Zujiry
 */
public interface Stowage<E> extends Mutable, /*Body,*/ WithCargo, Bounded3DimStack<E>{
    
}

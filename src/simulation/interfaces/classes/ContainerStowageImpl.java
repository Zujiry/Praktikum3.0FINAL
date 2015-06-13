package simulation.interfaces.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import simulation.adt.admin_value.classes.StowageLocationImpl;
import simulation.adt.admin_value.interfaces.StowageLocation;
import simulation.adt.physics_value.classes.Values;
import simulation.adt.physics_value.interfaces.Mass;
import simulation.interfaces.Container;
import simulation.interfaces.Stowage;

public class ContainerStowageImpl implements Stowage<Container>{
     
    ArrayList<ArrayList<ArrayList<Container>>> stack;
    final int groeseBay;
    final int groeseRow;
    final int groeseTier;
    
    private ContainerStowageImpl(int groeseBay, int groeseRow, int groeseTier) {
        this.groeseBay  = groeseBay;
        this.groeseRow  = groeseRow;
        this.groeseTier = groeseTier;
        
        this.stack = new ArrayList<>();
        
        for(int i = 0; i != groeseBay; i++) {
            this.stack.add(new ArrayList<>());
                for(int j = 0; j != groeseRow; j++) {
                    this.stack.get(i).add(new ArrayList<>());
                        for(int k = 0; k != groeseTier; k++) {
                            this.stack.get(i).get(j).add(new NullContainer());
                        }
                }
        }
    }
       
    @Override
    public Mass emptyMass() {
        return Values.massInKG(2280.0);
    }

    @Override
    public Mass maxMass() {
        return Values.massInKG(24000.0);
    }

    @Override
    public boolean isEmpty() {
        for(ArrayList<ArrayList<Container>> row : stack){
             for(ArrayList<Container> tier: row){
                for(Container e : tier) {
                    if(!(e.isBlocked() || e.isFree())) {
                        return false;
                    }
                }
             }   
        }
        return true;
    }

    @Override
    public boolean isFull() {
        for(ArrayList<ArrayList<Container>> row : stack){
             for(ArrayList<Container> tier: row){
                for(Container e : tier) {
                    if(!(e.isBlocked() || e.isFull() || e.isOccupied())) {
                        return false;
                    }
                }
             }   
        }
        return true;
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void load(int bayNo, int rowNo, Container elem) {
        int tier;
        for(tier=0; stack.get(bayNo).get(rowNo).get(tier).isFree(); tier++) ;
        stack.get(bayNo).get(rowNo).set(tier, elem);
        elem.setLoc(kram,StowageLocationImpl.valueOf(bayNo,rowNo,tier,false));
    }

    @Override
    public void load(Container elem) {
        for(ArrayList<ArrayList<Container>> row : stack){
            for(ArrayList<Container> tier: row){
               for(Container e : tier) {
                   if(e.isFree()) {
                       load(e.loc().bay(),e.loc().row(),elem);
                       break;
                   }
               }
            }
        }
        
    }

    @Override
    public void loadAll(Collection<? extends Container> coll) {
        for(Container o : coll) {
            load(o);
        }
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        for(Container con: stack.get(bay).get(row)){
            if(con.isFree()){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        for(Container con: stack.get(bay).get(row)){
            if (!(con.isFree())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object elem) {
        for(ArrayList<ArrayList<Container>> row : stack){
            for(ArrayList<Container> tier: row){
               for(Container e : tier) {
                   if(e == elem)
                       return true;
               }
            }               
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<? extends Container> coll) {
        for(Container c : coll) {
            if(!(this.contains(c))) {
                return false;
            }                
        }
        return true;
    }

    @Override
    public Container get(StowageLocation loc) {
        return stack.get(loc.bay()).get(loc.row()).get(loc.tier());
    }

    @Override
    public Set<Container> getAll() {
        Set set = new HashSet<Container>();
        
        for(ArrayList<ArrayList<Container>> row : stack){
            for(ArrayList<Container> tier: row){
               for(Container e : tier) {
                   if(!(e.isBlocked() || e.isFree())) {
                       set.add(e);
                   }
               }
            }
        }
        return set;
    }

    @Override
    public StowageLocation locationOf(Container elem) {
        return elem.loc();
    }   
}

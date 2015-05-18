
package simulation.controls;

import simulation.Engine;

public class SwitchControl {
    private final String name;
    private final int key;
    private boolean isOn;
    private boolean isKeyDown;
    
    public String getName() {
        return name;
    }
    
    private int getKey() {
        return key;
    }
    
    public boolean getIsOn() {
        return isOn;
    }
    
    public SwitchControl(String name, int key) {
        this.name = name;
        this.key = key;
        isOn = true;
        isKeyDown = false;
    }
    
    @Override
    public String toString() {
        return getName() + ": " + getIsOn();
    }
    
    public void step() {
        if(isKeyDown == false && Engine.getInstance().getKey(getKey())) {
            isOn = !isOn;
            isKeyDown = true;
        } else if(!Engine.getInstance().getKey(getKey()))
            isKeyDown = false;
    }
}

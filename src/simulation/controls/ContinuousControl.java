package simulation.controls;

import simulation.Engine;

public class ContinuousControl {
    // <editor-fold desc="fields">
    private final String name;
    private final double min;
    private final double max;
    private final double timeToMax;
    private double currentValue;
    private final Integer keyRaise;
    private final Integer keyLower;
    private final boolean autoZero;
    private static final double ACTIVATION_LEVEL = 0.001; 
    // </editor-fold>

    /**
     * Standart Konstruktor
     * @param name - Name of lever
     * @param minValue - Minimum
     * @param maxValue - Maximum
     * @param timeToMax - Zeit bis erreichen des Minimums/Maximums
     * @param keyRaise - Taste zum erhöhen des Levels (can be Null : means not Functional)
     * @param keyLower - Taste zum verringern des Levels (can be Null : means not Functional)
     * @param autoZero - Automatisch auf 0.0 zurücksinken?
     */
    public ContinuousControl(String name, double minValue, double maxValue, double timeToMax, Integer keyRaise, Integer keyLower, boolean autoZero) {
        this.min = minValue;
        this.max = maxValue;
        this.timeToMax = timeToMax;
        this.keyRaise = keyRaise;
        this.keyLower = keyLower;
        this.autoZero = autoZero;
        this.name = name;
    }

    // <editor-fold desc="Getter" defaultstate="collapsed">
    private double getMin() {
        return this.min;
    }

    private double getMax() {
        return this.max;
    }

    private double getTimeToMax() {
        return this.timeToMax;
    }

    public double getCurrentValue() {
        return this.currentValue;
    }

    private Integer getKeyUp() {
        return this.keyRaise;
    }

    private Integer getKeyDown() {
        return this.keyLower;
    }
    
    private static Engine getEngine() {
        return Engine.getInstance();
    }

    private boolean isToggleToZero() {
        return this.autoZero;
    }
    
    private String getName() {
        return this.name;
    }
    // </editor-fold>
    
    /**
     * Setzt das Level und verhindert das setzen des Levels 
     * außerhalb des angegebenen Bereichs
     * @param newLevel - double Level
     */
    private void setLevel(double newLevel) {
        if (newLevel > getMax())
            newLevel = getMax();
        else if(newLevel < getMin())
            newLevel = getMin();
        this.currentValue = newLevel;
    }
    
    @Override
    public String toString() {
        return getName() + ": ";
    }
    /**
     * Diese funktion sollte bei jedem Framedurchlauf aufgerufen werden.
     * Sie berechnet die Levelveränderung
     * @param aDeltaTime - Zeit seit der Letzten Ausführung
     */
    public void step(double aDeltaTime) {
        if (getKeyUp() != null && getEngine().getKey(getKeyUp())){
            setLevel(getCurrentValue() + ((Math.abs(getMax())/getTimeToMax()) * aDeltaTime));
        } else if (getKeyDown() != null && getEngine().getKey(getKeyDown()))  {
            setLevel(getCurrentValue() - ((Math.abs(getMax())/getTimeToMax()) * aDeltaTime));
        } else if (isToggleToZero()){
            setLevel(getCurrentValue() + (Math.signum(-getCurrentValue()) * ((getMax()/getTimeToMax()) * aDeltaTime)));
            if (getCurrentValue() < ACTIVATION_LEVEL && getCurrentValue() > -ACTIVATION_LEVEL)
                setLevel(0.0);
        }
    }
}
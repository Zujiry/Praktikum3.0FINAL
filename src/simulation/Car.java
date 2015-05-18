
package simulation;

import java.text.*;
import jgame.*;
import jgame.platform.*;


public class Car implements ParticleInterface {
    
    private enum DriveStates {Stopp, Drive, DriveCurve, LostControl}
    
    //car-spezific constants
    private final double mass;         // [kg]
    private final double powerPropMax; // [W] = [kg * m² / s³]
    private final double speedMax;     // [m/s]
    private final double dragConst;    // [kg*m / s²]
    private final double forcePropMax; // [kg*m / s²]
    private final double curveRadiusMin; // [m]
    
    
    private final double ACTIVATION_LEVEL = 0.01;
    private final double DRIVE_MIN_SPEED = 0.01; // [m/2]
    
    //physical constants
    private static final double ACC_EARTH = 9.81; // [m/s²]
    private static final double MS_IN_KMH = 3.6; 
    private static final double KILO = 1000; // [X] * this -> [kX]
     
    //properties of car
    private double propLevel; // position of gaslevel in [%] (0.00 - 1.00)
    private double brakeLevel; // position of brakelevel in [%] (0.00 - 1.00)
    private double posX = 0.0; // [m] relative to start
    private double posY = 0.0; // [m] relative to start
    private double time; // [s]
    private double speed; // [m/s]
    private double traction; // %
    private boolean abs; // on/off
    private boolean asr; // on/off
    private double steeringLevel; // +-%
    private double direction; // [rad]
    
    private DriveStates currentState;
    
    private boolean lostControl; // yes/no
    
    //<editor-fold desc="getter">
    @Override
    public double getPropLevel() {
        return propLevel;
    }
    
    @Override
    public double getBrakeLevel() {
        return brakeLevel;
    }
    
    @Override
    public double getSteeringLevel() {
        return steeringLevel;
    }
    
    @Override
    public double getPosX() {
        return posX;
    }
    
    @Override
    public double getPosY() {
        return posY;
    }
    
    public double getTime() {
        return time;
    }
    
    @Override
    public double getSpeed() {
        return speed;
    }
    
    @Override
    public double getTraction() {
        return traction;
    }
    
    @Override
    public boolean getABS() {
        return abs;
    }
    
    @Override
    public boolean getASR() {
        return asr;
    }
    
    @Override
    public boolean getLostControl() {
        return lostControl;
    }
    
    private double getAccEarth() {
        return Car.ACC_EARTH;
    }
    
    public double getDirection() {
        return direction;
    }
    //</editor-fold>
    
    //<editor-fold desc="setter">
    public void setPosY(double posY) {
        this.posY = posY;
    }
    
    public void setPosX(double posX) {
        this.posX = posX;
    }
    
    public void setTime(double time) {
        this.time = time;
    }
    
    @Override
    public void setPropLevel(double proplevel) {
        this.propLevel = proplevel; 
    }
    
    @Override
    public void setBrakeLevel(double brakeLevel) {
        this.brakeLevel = brakeLevel;             
    }
    
    @Override
    public void setSteeringLevel(double newSteeringLevel) {
        this.steeringLevel = newSteeringLevel;
    }
    
    public void setSpeed(double speed) {
        if (speed <= 0.0)
            this.speed = 0.0;
        else
            this.speed = speed;
    }
    
    @Override
    public void setTraction(double newTraction) {
        this.traction = newTraction;
    }
    
    @Override
    public void setABS(boolean newAbsState) {
        this.abs = newAbsState;
    }
    
    @Override
    public void setASR(boolean newAsrState) {
        this.asr = newAsrState;
    }
    
    @Override
    public void setLostControl(boolean hasLostControl) {
        lostControl = hasLostControl;
    }
    //</editor-fold>
    
    //Constructor and Setup functions
    public Car(double mass_kg, double powerPropMax_kw, double speedMax_kmh, double curveRadiusMin_m) {
        //checkpre?
        this.mass = mass_kg;
        this.powerPropMax = powerPropMax_kw * KILO;
        this.speedMax = (speedMax_kmh / MS_IN_KMH);
        this.curveRadiusMin = curveRadiusMin_m;
        this.forcePropMax = mass * ACC_EARTH;
        this.dragConst = Math.abs(powerPropMax / Math.pow(speedMax, 3));
        this.currentState = DriveStates.Stopp;
        
        this.lostControl = false;
        
        this.direction = 0;
        this.setPosX(0.0);
        this.setPosY(0.0);
        reset();
    }
    
    public static Car porsche() {
        return new Car(1445.0, 365.0, 330.0, 10.0);
    }
    
    @Override
    public void set(double time, double posX, double posY, double speed, double propLevel, double brakeLevel) {
        setTime(time);
        setPosX(posX);
        setPosY(posY);
        setSpeed(speed);
        setPropLevel(propLevel);
        setBrakeLevel(brakeLevel);
    }
    
    public final void reset() {
        set(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }
    
    //Dynamik
    public double powerProp() {
        return propLevel * powerPropMax;  // [W] = [kg * m² / s³]
    }
    
    public double forcePropMax(boolean withTraction) {
        if(withTraction)
            return forcePropMax * getTraction();
        else
            return forcePropMax;
    }
    
    public double forcePropAbs() {
        if (speed <= DRIVE_MIN_SPEED && getPropLevel() > ACTIVATION_LEVEL)
            return forcePropMax(getASR());                                  // [kg*m / s²]
        else if (speed > DRIVE_MIN_SPEED)
            return Math.min(forcePropMax(getASR()), (powerProp() / speed)); // [kg*m / s²]
        else
            return 0.0;
    }
    
    public double forceProp() {
        return forcePropAbs() * Math.signum(propLevel); //[kg*m / s²]
    }
    
    public double forceDrag() {
        return dragConst * Math.pow(speed, 2) * Math.signum(-getSpeed()); // [kg*
    }
    
    public double forceBrake() {
        //System.out.println(getBreakLevel());
        return brakeLevel * forcePropMax(getABS()) *  Math.signum(-getSpeed());
    }
    
    public double force() {
        return forceProp() + forceDrag() + forceBrake(); // [kg*m / s²]
    }
    
    //Kinematik
    public double acc() {
        return force() / mass; // [kg / s²]
    }
    
    public double maxAcc() {
        return getAccEarth() * getTraction();
    }
    
    public double curveAcc() {
        if(getSteeringLevel() < -ACTIVATION_LEVEL || getSteeringLevel() > ACTIVATION_LEVEL)
            return (speed * speed) / currentCurveRadius();
        else
            return 0.0;
    }
    
    public double currentCurveRadius() {
        if(getSteeringLevel() < -ACTIVATION_LEVEL || getSteeringLevel() > ACTIVATION_LEVEL)
            return curveRadiusMin / getSteeringLevel();
        else
            return 9999999.99;
    }
    
    public double deltaDirection(double deltaTime) {
        if(acc() > 0.1)
            return (curveAcc() / acc()) * deltaTime / 1000.0; // [rad]
        else
            return 0.0;
    }
    
    public double deltaPos(double deltaTime) {
        return speed * deltaTime; // [m]
    }
    
    public double deltaPosX(double deltaTime) {
        return deltaPos(deltaTime) * Math.sin(direction); // [m]
    }
    
    public double deltaPosY(double deltaTime) {
        return deltaPos(deltaTime) * Math.cos(direction); // [m]
    }
    
    //Methods
    @Override
    public void step(double deltaTime) {
        setPosX(getPosX() + deltaPosX(deltaTime));
        setPosY(getPosY() + deltaPosY(deltaTime));
        setSpeed(speed + (acc() * deltaTime));
        setTime(time + deltaTime);
        
        direction = (direction + deltaDirection(deltaTime)) % (2*Math.PI);
        
        if(forcePropAbs() > forcePropMax(true) || (forcePropMax(getABS()) * getBrakeLevel()) > forcePropMax(true))
        //if(Math.abs(acc()) + curveAcc() > maxAcc())
            setLostControl(true);  
        else
            setLostControl(false);
        
        /*if(lostControl)
            //currentState = DriveStates.LostControl;
        if(speed <= DRIVE_MIN_SPEED && getPropLevel() < ACTIVATION_LEVEL) {
            currentState = DriveStates.Stopp;
            if (getSteeringLevel() > ACTIVATION_LEVEL || getSteeringLevel() < -ACTIVATION_LEVEL)
                currentState = DriveStates.DriveCurve;
        } else if(speed > DRIVE_MIN_SPEED)
            currentState = DriveStates.Drive; */
    }
    
    @Override
    public String toString() {
        return to_String_EU();
    }
    
    public String to_String_EU() {
        DecimalFormat f = new DecimalFormat("#0.00");
        String text =   "Positon: " +  f.format(getPosX()) + "m" +
                        " Speed: " + f.format(speed * MS_IN_KMH) + " Km/h" +
                        " Time: " + f.format(time) + "s" +
                        " Gas Level: " + f.format(propLevel) + " %" +
                        " Brake Level: " + f.format(brakeLevel) + " %";
        return text;
    }
}

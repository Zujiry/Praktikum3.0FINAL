/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

public interface ParticleInterface {
    double getPosX();
    double getPosY();
    double getPropLevel();
    double getBrakeLevel();
    double getSteeringLevel();
    double getSpeed();
    double getTraction();
    boolean getABS();
    boolean getASR();
    boolean getLostControl();
    
    void setTraction(double newTraction);
    void setABS(boolean newAbsState);
    void setASR(boolean newAsrState);
    void setPropLevel(double newPropLevel);
    void setBrakeLevel(double newBrakeLevel);
    void setSteeringLevel(double newSteeringLevel);
    void set(double newTime, double newPosX, double newPosY, double newSpeed, double newPropLevel, double newBrakeLevel);
    void setLostControl(boolean hasLostControl);
    
    void step(double aDeltaTime);
    
    public double getDirection();
}

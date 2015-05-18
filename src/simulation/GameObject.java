/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

//only one game object at a time

import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;
import simulation.controls.*;
import simulation.ground.Ground;
import static simulation.util.Utility.*;

public class GameObject extends JGObject {
    private static GameObject obj; //only one car for the moment.
    private final ParticleInterface particle;
    private final ContinuousControl gasPedal;
    private final ContinuousControl brakePedal;
    private final SwitchControl absSwitch;
    private final SwitchControl asrSwitch;
    private final ContinuousControl steeringWheel;
    
    //<editor-fold desc="getter">
    private ParticleInterface getParticle() {
        return this.particle;
    }
    
    private ContinuousControl getGasPedal() {
        return gasPedal;
    }
    
    private ContinuousControl getBrakePedal() {
        return brakePedal;
    }
    
    private SwitchControl getAbsSwitch() {
        return absSwitch;
    }
    
    private SwitchControl getAsrSwitch() {
        return asrSwitch;
    }
    
    private ContinuousControl getSteeringWheel() {
        return steeringWheel;
    }
    
    private static Engine getEngine() {
        return Engine.getInstance();
    }
    
    public static GameObject getGameObject(ParticleInterface particle) {
        if (obj == null)
            obj = new GameObject(particle);
        return obj;
    }
    //</editor-fold>
    
    //constructor
    private GameObject(ParticleInterface particle) {
        super("Player",true,300,300,1,"Player90");
        this.particle = particle;
        gasPedal = new ContinuousControl("gas pedal", 0.0, 1.0, 0.5, Engine.KeyUp, null, true);
        brakePedal = new ContinuousControl("brake pedal", 0.0, 1.0, 0.5, Engine.KeyDown, null, true);
        absSwitch = new SwitchControl("ABS", Engine.KeyCtrl);
        asrSwitch = new SwitchControl("ASR", Engine.KeyShift);
        steeringWheel = new ContinuousControl("steering Wheel", -1.0, 1.0, 1.0, Engine.KeyRight, Engine.KeyLeft, false);
    }

    @Override
    public void move() {
        getParticle().step(getEngine().getDeltaTime());
        this.x = getParticle().getPosX();
        this.y = getParticle().getPosY();
        setGraphic("Player" + "0");
        // evaluate new control-levels, etc
        getGasPedal().step(getEngine().getDeltaTime());
        getBrakePedal().step(getEngine().getDeltaTime());
        getAsrSwitch().step();
        getAbsSwitch().step();
        getSteeringWheel().step(getEngine().getDeltaTime());
        // apply new values to particle
        getParticle().setPropLevel(getGasPedal().getCurrentValue());
        getParticle().setBrakeLevel(getBrakePedal().getCurrentValue());
        getParticle().setTraction(Ground.getGroundByPosition((int)((x / 16)%(getEngine().pfTilesX())), (int)((y / 16)%(getEngine().pfTilesX()))).getTraction());
        getParticle().setABS(getAbsSwitch().getIsOn());
        getParticle().setASR(getAsrSwitch().getIsOn());
        getParticle().setSteeringLevel(getSteeringWheel().getCurrentValue());
        
        // center camera on car
        getEngine().setViewOffset((int) x, (int) y, true);
    }
    
    @Override
    public void paint(){
        Engine.getInstance().setColor(JGColor.black);
        Engine.getInstance().drawString("Speed: " + useValueFormat(getParticle().getSpeed() * 3.6) + " km/h" , 10, 10, -1, new JGFont("Arial", 0, 16), JGColor.white);
        Engine.getInstance().drawString(gasPedal.toString() + usePercentFormat(getParticle().getPropLevel()), 10, 26, -1, new JGFont("Arial", 0, 16), JGColor.white);
        Engine.getInstance().drawString(brakePedal.toString() + usePercentFormat(getParticle().getBrakeLevel()), 10, 42, -1, new JGFont("Arial", 0, 16), JGColor.white);
        Engine.getInstance().drawString(steeringWheel.toString() + usePercentFormat(getParticle().getSteeringLevel()), 10, 58, -1, new JGFont("Arial", 0, 16), JGColor.white);
        Engine.getInstance().drawString("Traction: " + usePercentFormat(getParticle().getTraction()), 10, 74, -1, new JGFont("Arial", 0, 16), JGColor.white);
        Engine.getInstance().drawString("ABS: " + getParticle().getABS(), 10, 90, -1, new JGFont("Arial", 0, 16), JGColor.white);
        Engine.getInstance().drawString("ASR: " + getParticle().getASR(), 10, 106, -1, new JGFont("Arial", 0, 16), JGColor.white);
        
        Engine.getInstance().drawString("is out of Control: " + getParticle().getLostControl(), 10, 122, -1, new JGFont("Arial", 0, 16), JGColor.white);
        Engine.getInstance().drawString("X: " + getParticle().getPosX() + ", Y: " + getParticle().getPosY(), 10, 150, -1, new JGFont("Arial", 0, 16), JGColor.white);
        Engine.getInstance().drawString("Richtung: " + getParticle().getDirection(), 10, 170, -1, new JGFont("Arial", 0, 16), JGColor.white);
    }  
}

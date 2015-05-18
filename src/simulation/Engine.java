
package simulation;


import java.util.Calendar;
import jgame.*;
import jgame.platform.*;
import simulation.ground.Ground;

public class Engine extends JGEngine {
    private static Engine engine;
    private long timeLastFrame;
    private long currentTime;
        
    private Engine() {
	initEngineApplet(); 
    }

    public static Engine getInstance() {
        if (engine == null)
            engine = new Engine(800, 600);
        return Engine.engine;
    }
    
    public static void startGame() {
        getInstance().loadSprites();
        Ground.generateGround();
        GameObject.getGameObject(Car.porsche());
    }
        
    private Engine(int width, int heigth) {
		initEngine(width, heigth); 
    }
    
    //<editor-fold desc="timerelated">
    public long getCurrentTime() {
        return currentTime;
    }

    private void setCurrentTime(long _currentTime) {
        this.currentTime = _currentTime;
    }

    public long getTimeLastFrame() {
            return this.timeLastFrame;
    }

    private void setTimeLastFrame(long aTimeLastFrame) {
            this.timeLastFrame = aTimeLastFrame;
    }

    public double getDeltaTime(){
        return ((double) (getCurrentTime() - getTimeLastFrame()))/1000;
    }
    //</editor-fold>

    @Override
    public void initCanvas() {
	setCanvasSettings(40, 30, 16, 16, null, null, null);
    }

    @Override
    public void initGame() {
        setTimeLastFrame(Calendar.getInstance().getTimeInMillis());
		// We can set the frame rate, load graphics, etc, at this point. 
		// (note that we can also do any of these later on if we wish)
        setFrameRate(60, 2);
        setPFSize(80, 80);
        setPFWrap(true, true, -2, -2);
    }

    private void loadSprites() {
            Engine.getInstance().defineImage("Player" + "0", "p", 0, "./graphics/particles/" + "0" + ".png", "-");
            //load GroundTileGraphics
            Engine.getInstance().defineImage("Ice", "ice", 0, "./graphics/ground/ice.png", "-");
            Engine.getInstance().defineImage("Street", "road", 0, "./graphics/ground/street.png", "-");
            Engine.getInstance().defineImage("Wet", "wet", 0, "./graphics/ground/wet.png", "-");
            Engine.getInstance().defineImage("Snow", "snow", 0, "./graphics/ground/snow.png", "-");
    }
    
	/** Game logic is done here.  No painting can be done here, define
	* paintFrame to do that. */
    @Override
    public void doFrame() {
        setCurrentTime(Calendar.getInstance().getTimeInMillis());
        moveObjects();
        setTimeLastFrame(getCurrentTime());
    }
}

package simulation.ground;

import simulation.Engine;

public class Ground {
    private static Ground[][] groundMap;
    private String spriteName;
    private double traction;
    private int x;
    private int y;
           
    static{
        Ground.groundMap = new Ground[Engine.getInstance().pfTilesX()][Engine.getInstance().pfTilesY()];   
    }
    
    public static Ground[][] getGround() {
       return Ground.groundMap;
      
    }
    public static void setGround(Ground[][] groundMap) {
        Ground.groundMap = groundMap;
    }
    
    public static void setGroundByPosition(Ground ground, int x, int y) {
        Ground.groundMap[x][y] = ground;   
    }
    
    public static Ground getGroundByPosition(int x, int y){
        //System.out.println(x + " " + y);
        return Ground.getGround()[x][y];
    }
    
    public static void generateGround(){
        int pftilesX = Engine.getInstance().pfTilesX();
        int pftilesy = Engine.getInstance().pfTilesY();
        for (int x = 0; x<pftilesX;x++){
            for (int y = 0; y<pftilesy ; y++){
                if (y < pftilesy / 4)
                    new Street(x, y);
                else if(y < pftilesy / 4 * 2)
                    new Ice(x, y);
                else if (y < pftilesy / 4 * 3)
                    new Wet(x, y);
                else
                    new Snow(x, y); 
            }
        }
    }
    
    public Ground(String textureName, double traction, int x, int y){
        this.traction = traction;
        this.x = x;
        this.y = y;
        setGroundSprite(textureName);
        Ground.setGroundByPosition(this, x, y);
    }
    
    public String getGroundSprite() {
        return spriteName;
    }

    private void setGroundSprite(String spriteName) {
        this.spriteName = spriteName;
        Engine.getInstance().setTile(x, y, spriteName);
        
    }

    public double getTraction() {
        return traction;
    }

    private void setTraction(double traction) {
        this.traction = traction;
    }
}

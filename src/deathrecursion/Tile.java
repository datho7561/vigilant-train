/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deathrecursion;

/**
 *
 * @author D
 */
public abstract class Tile extends Sprite {
    
    private boolean isSolid;
    
    public Tile(String imageFileName, double xpos, double ypos, boolean tileIsSolid) {
        super(imageFileName, xpos, ypos);
        isSolid = tileIsSolid;
    }
    
    public Tile(String[] imageFileNames, double xpos, double ypos, boolean tileIsSolid){
        super(imageFileNames, xpos, ypos);
    }
    
    public Tile(double xpos, double ypos) {
        this("err", xpos, ypos, true);
    }
    
}

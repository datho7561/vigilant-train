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
    
    /**
     * All subclasses should override this constructor.
     * I can remove this if I need to and probably don't yet need it for anything.
     * @param xpos
     * @param ypos 
     */
    private Tile(double xpos, double ypos) {
        this("", xpos, ypos, false);
    }
    
    public Tile(String imageFileName, double xpos, double ypos, boolean tileIsSolid) {
        super(imageFileName, xpos, ypos);
        isSolid = tileIsSolid;
    }
    
    public Tile(String[] imageFileNames, double xpos, double ypos, boolean tileIsSolid){
        super(imageFileNames, xpos, ypos);
    }
    
}

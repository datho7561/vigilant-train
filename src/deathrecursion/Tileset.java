/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deathrecursion;

import deathrecursion.tiles.TileDirt;
import deathrecursion.tiles.TileGrass;
import deathrecursion.tiles.TileIce;
import deathrecursion.tiles.TileRock;

/**
 *
 * @author D
 */
public class Tileset {
    
    public static Tile getTileFromID(int id, double xpos, double ypos) {
        
        switch(id) {
            case 0:
                return new TileGrass(xpos, ypos);
            default:
                return new Tile(xpos, ypos) {};
        }
        
    }
    
    public int getIDFromTile(Tile t) {
        
        if (t.getClass() == TileGrass.class) return 0;
        if (t.getClass() == TileRock.class) return 1;
        if (t.getClass() == TileDirt.class) return 2;
        if (t.getClass() == TileIce.class) return 3;
        return -1;
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package deathrecursion;

import java.util.Random;

/**
 *
 * @author DaTho7561
 */
public class GenericBaddy extends Entity {

    public int lastDirection;
    
    public GenericBaddy(String filename, double posx, double posy) {
        super(filename, posx, posy);
        Random r = new Random();
        lastDirection = r.nextInt(8);
    }
    
    public void update(int notUsed) {
        
        Random r = new Random();
        int nextDirection;
        if(r.nextInt(8)>6) {
            nextDirection = r.nextInt(8);
            lastDirection = nextDirection;
        } else {
            nextDirection = lastDirection;
        }
        
        super.update(nextDirection);
    }
    
}

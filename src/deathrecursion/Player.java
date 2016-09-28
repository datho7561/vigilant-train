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
public class Player extends Entity {
    
    public Player(String filepath, double x, double y) {
        super(filepath, x, y);
    }
    
    public Player() {
        this("basicchar", 0, 0);
    }
    
}

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
public class World {
    
    private Room[] rooms;
    
    private Passage[] links;
    
    public World(String filepath) {
        // TODO: read rooms and passages from file to construct the World
    }
    
    private class Passage {
        
        WorldLocation startLocation, destLocation;
        
        public Passage(WorldLocation start, WorldLocation dest) {
            startLocation = start;
            destLocation = dest;
        }
        
        public boolean canTravel(WorldLocation entityLocation) {
            return entityLocation.equals(startLocation);
        }
        
        public WorldLocation destination() {
            return destLocation;
        }
        
    }
    
}

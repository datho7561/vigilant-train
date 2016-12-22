/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deathrecursion;

import java.util.ArrayList;

/**
 *
 * @author D
 */
public class World {
    
    private Room[] rooms;
    
    private Passage[] links;
    
    private Entity[] entities;
    
    public World(String filepath) {
        // TODO: read rooms and passages from file to construct the World
    }

    public Entity[] getEntitiesInRadius(double x, double y, double r) {
        ArrayList<Entity> es = new ArrayList<>();
        
        for(Entity e: entities) {
            if (DimensionalTools.distance(x, y, e.getX(), e.getY()) < r) {
                es.add(e);
            }
        }
        
        Entity[] output = new Entity[es.size()];
        for(int i = 0; i < es.size(); i++) {
            output[i] = es.get(i);
        }
        
        return output;
        
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package deathrecursion;

/**
 *
 * @author DaTho7561
 */
public class DimensionalTools {
    
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.abs(Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2)));
    }
    
}

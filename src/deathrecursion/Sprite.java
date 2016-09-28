/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deathrecursion;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 *
 * @author D
 */
public class Sprite {
    
    // Used for location
    private double x,y;
    
    // Used to draw to screen
    private ImageIcon[] images;
    
    // basically never used
    public Sprite(ImageIcon[] img, double xpos, double ypos) {
        images = img;
        x = xpos;
        y = ypos;
    }
    
    // Use me for animation or Enitity
    public Sprite(String[] filenames, double xpos, double ypos) {
        this(new ImageIcon[]{new ImageIcon()}, xpos, ypos);
        images = new ImageIcon[filenames.length];
        for (int i = 0; i < filenames.length; i++) {
            try {
                images[i] = new ImageIcon(DeathRecursion.class.getResource("/deathrecursion/resources/textures/" + filenames[i] + ".png"));
            } catch (Exception e) {
                System.out.println("Image file not found. Loading backup texture.");
                try {
                    images[i] = new ImageIcon(DeathRecursion.class.getResource("/deathrecursion/resources/textures/fallback/back.png"));
                } catch (Exception ioe) {
                    System.out.println("Fallback texture missing");
                }
            }
        }
    }
    
    // Use me when possible Sprite
    public Sprite(String filename, double xpos, double ypos) {
        this(new String[]{filename}, xpos, ypos);
    }
    
    // Blank textured sprite
    public Sprite(double xpos, double ypos) {
        this("", xpos, ypos);
    }
    
    // Blank Sprite
    public Sprite() {
        this(0, 0);
    }
    
    // Copy Sprite
    public Sprite(Sprite s) {
        x = s.x;
        y = s.y;
        images = s.images;
    }
    
    public void paint(Graphics g) {
        if(images.length > 1) {
            
            int timePerImage = (int)(1000/(double)images.length);
            int currentTime = (int)(System.currentTimeMillis()%1000);
            int imageToDisplay = 0;
            
            for (int i = 0; i < images.length; i++) {
                if (currentTime > timePerImage * i) {
                    imageToDisplay = i;
                }
            }
            
            int width = images[imageToDisplay].getIconWidth();
            int height = images[imageToDisplay].getIconHeight();

            g.drawImage(images[imageToDisplay].getImage(), (int)(x-width/2), (int)(y-height/2), null);
        } else {
            int width = images[0].getIconWidth();
            int height = images[0].getIconHeight();

            g.drawImage(images[0].getImage(), (int)(x-width/2), (int)(y-height/2), null);
        }
    }
    
    // Force which image in the list should be used
    public void paint(Graphics g, int imageIndex) {
        try {
            int width = images[imageIndex].getIconWidth();
            int height = images[imageIndex].getIconHeight();

            g.drawImage(images[imageIndex].getImage(), (int)(x-width/2), (int)(y-height/2), null);
        } catch(ArrayIndexOutOfBoundsException e) {
            int width = images[0].getIconWidth();
            int height = images[0].getIconHeight();
            System.out.println("Out of bounds. Loading first texture");
            g.drawImage(images[0].getImage(), (int)(x-width/2), (int)(y-height/2), null);
        }
    }
    
    
    
    /* Mutators and accessors */
    
    // accessors //
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    // mutators //
    
    public void setX(double xpos) {
        x = xpos;
    }
    
    public void setY(double ypos) {
        y = ypos;
    }
    
}

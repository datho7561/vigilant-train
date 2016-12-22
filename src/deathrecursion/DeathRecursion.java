/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deathrecursion;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author D
 */
public class DeathRecursion extends JPanel {
    
    private Timer timer;
    
    // used for rescaling
    BufferedImage sbi;
    
    // for keeping track of what keys are pressed
    private boolean wKeyPressed;
    private boolean aKeyPressed;
    private boolean sKeyPressed;
    private boolean dKeyPressed;
    
    private Sprite[][] boringBorder;
    private Entity player;
    private GenericBaddy bad;
    private Sprite otherBoringSprite;
    
    public DeathRecursion() {
        super();
        
        this.setSize(900, 700);
        
        sbi = new BufferedImage(288, 224, BufferedImage.TYPE_INT_ARGB);
        
        // setup game variables
        reset();
        
        timer = new Timer(20, (ActionEvent e) -> {
            update();
        });
        
        boringBorder = new Sprite[9][7];
        for (int x = 0; x < boringBorder.length; x++) {
            for (int y = 0; y < boringBorder[x].length; y++) {
                boringBorder[x][y] = new Sprite("grass", x * 32 + 16, y * 32 + 16);
            }
        }
        
        
        otherBoringSprite = new Sprite(new String[] {"torch_1", "torch_2", "torch_3", "torch_4",
            "torch_1", "torch_2", "torch_3", "torch_4",
            "torch_1", "torch_2", "torch_3", "torch_4"}, 48, 48);
        player = new Entity("basicchar", 150, 150);
        bad = new GenericBaddy("lel", 200, 200);
        
        
    }
    
    public void start() {
        timer.start();
    }
    
    // Handles logic
    public void update() {
        
        
        // Figure out which way the player should acclerate
        int playerMotionDirection;
        
        if (wKeyPressed && aKeyPressed && sKeyPressed && dKeyPressed) {
            playerMotionDirection = 0;
        } else if (wKeyPressed && sKeyPressed) {
            if (aKeyPressed) {
                playerMotionDirection = 6;
            } else if (dKeyPressed) {
                playerMotionDirection = 2;
            } else {
                playerMotionDirection = -1;
            }
        } else if (aKeyPressed && dKeyPressed) {
            if (wKeyPressed) {
                playerMotionDirection = 0;
            } else if (sKeyPressed) {
                playerMotionDirection = 4;
            } else {
                playerMotionDirection = -1;
            }
        } else if (wKeyPressed && dKeyPressed) {
            playerMotionDirection = 1;
        } else if (dKeyPressed && sKeyPressed) {
            playerMotionDirection = 3;
        } else if (sKeyPressed && aKeyPressed) {
            playerMotionDirection = 5;
        } else if (aKeyPressed && wKeyPressed){
            playerMotionDirection = 7;
        } else if (wKeyPressed) {
            playerMotionDirection = 0;
        } else if (dKeyPressed) {
            playerMotionDirection = 2;
        } else if (sKeyPressed) {
            playerMotionDirection = 4;
        } else if (aKeyPressed) {
            playerMotionDirection = 6;
        } else {
            playerMotionDirection = -1;
        }
        
        player.update(playerMotionDirection);
        bad.update(0);
        
        this.repaint();
    }
    
    // Handles graphics
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics preg = sbi.getGraphics();
        
        preg.fillRect(0, 0, sbi.getWidth(), sbi.getHeight());
        for (Sprite[] sl: boringBorder) {
            for (Sprite s: sl) {
                s.paint(preg);
            }
        }
        
        otherBoringSprite.paint(preg);
        
        // Player shuold be one of the last things to paint
        player.paint(preg);
        bad.paint(preg);
        
        
        g.drawImage(sbi.getScaledInstance(this.getWidth(), this.getHeight(), BufferedImage.SCALE_FAST), 0, 0, null);
    }
    
    
    public void reset() {
        wKeyPressed = false;
        aKeyPressed = false;
        sKeyPressed = false;
        dKeyPressed = false;
    }
    
    
    /* Handles KeyEvent input */
    // When a key gets pressed and released
    public void keyTyped(KeyEvent e) {
        
    }

    // When a key is pressed
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            wKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            aKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            sKeyPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            dKeyPressed = true;
        }
    }

    // When a key is released
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            wKeyPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            aKeyPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            sKeyPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            dKeyPressed = false;
        }
    }
    
}

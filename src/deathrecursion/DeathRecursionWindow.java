/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deathrecursion;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author D
 */
public class DeathRecursionWindow extends JFrame implements KeyListener{

    static DeathRecursion drg;
    static DeathRecursionWindow dr;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        dr = new DeathRecursionWindow();
        
        drg = new DeathRecursion();
        
        dr.add(drg);
        dr.setSize(drg.getSize());
        drg.start();
    }

    public DeathRecursionWindow() {
        super("Death and Recursion");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(this);
        
        this.setVisible(true);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        drg.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        drg.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        drg.keyReleased(e);
    }
    
}

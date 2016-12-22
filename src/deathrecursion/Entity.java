/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deathrecursion;

import java.awt.Graphics;

/**
 *
 * @author D
 */
public class Entity extends Sprite {
    
    private static final double MAX_VELOCITY = 10;
    
    private static final double ACCELERATION_RATE = 0.5;
    private static final double DIAGONAL_ACCELERATION = Math.sqrt(ACCELERATION_RATE)/2;
    private static final short MAX_HEALTH = 15;
    
    private static final int ANIMATION_FPS= 7;
    
    // 0 - 7, where 0 is straight up
    private int facingDirection;
    private double xVelocity, yVelocity, velocity;
    private int animationFrame;
    private long lastAnimationFrameTime;
    private short health;
    
    // shuold be used for most entities
    public Entity(String filename, double posx, double posy) {
        super(new String[]{filename + "_down", filename + "_left", filename + "_right", filename + "_up",
            filename + "_downwalk", filename + "_leftwalk", filename + "_rightwalk", filename + "_upwalk",
            filename + "_downalt", filename + "_leftalt", filename + "_rightalt", filename + "_upalt"},
                posx, posy);
        xVelocity = 0;
        yVelocity = 0;
        facingDirection = 4;
        animationFrame = 0;
        lastAnimationFrameTime = System.currentTimeMillis();
        health = MAX_HEALTH;
    }
    
    // copy entity
    public Entity(Entity e) {
        super(e);
        xVelocity = e.xVelocity;
        yVelocity = e.yVelocity;
        facingDirection = e.facingDirection;
        animationFrame = e.animationFrame;
        lastAnimationFrameTime = e.lastAnimationFrameTime;
        health = e.health;
    }
    
    // accelerates the entity in a direction from 0 to 7, 0 being straight up. anything out of bounds will not accelerate the entity
    public void move(int direction) {
        
        // avoid setting facing direction to out of bounds integer, since out of bounds integer can be used for no input or no movement
        facingDirection = (direction < 0 || direction > 7)? facingDirection : direction;
        
        if (velocity < MAX_VELOCITY) {
            switch (direction) {
                case 0:
                    yVelocity -= ACCELERATION_RATE;
                    break;
                case 1:
                    yVelocity -= DIAGONAL_ACCELERATION;
                    xVelocity += DIAGONAL_ACCELERATION;
                    break;
                case 2:
                    xVelocity += ACCELERATION_RATE;
                    break;
                case 3:
                    yVelocity += DIAGONAL_ACCELERATION;
                    xVelocity += DIAGONAL_ACCELERATION;
                    break;
                case 4:
                    yVelocity += ACCELERATION_RATE;
                    break;
                case 5:
                    xVelocity -= DIAGONAL_ACCELERATION;
                    yVelocity += DIAGONAL_ACCELERATION;
                    break;
                case 6:
                    xVelocity -= ACCELERATION_RATE;
                    break;
                case 7:
                    xVelocity -= DIAGONAL_ACCELERATION;
                    yVelocity -= DIAGONAL_ACCELERATION;
                    break;
                default:
                    break;
            }
        }
        
        velocity = Math.sqrt(xVelocity*xVelocity + yVelocity*yVelocity);
    }
    
    // updates the entities position based on derpy phisics engine
    public void update(int movementDirection) {
        
        /* update entity's velocity */
        // apply friction
        if (xVelocity < 0) {
            if(xVelocity > -1){
                xVelocity = 0;
            } else {
                xVelocity ++;
            }
        } else if (xVelocity > 0) {
            if (xVelocity < 1) {
                xVelocity = 0;
            } else {
                xVelocity --;
            }
        }
        if (yVelocity < 0) {
            if(yVelocity > -1){
                yVelocity = 0;
            } else {
                yVelocity ++;
            }
        } else if (yVelocity > 0) {
            if (yVelocity < 1) {
                yVelocity = 0;
            } else {
                yVelocity --;
            }
        }
        
        velocity = Math.sqrt(xVelocity*xVelocity + yVelocity*yVelocity);
        
        // apply desired motion
        move(movementDirection);
        
        // move entity based on velocity
        setX(getX() + xVelocity);
        setY(getY() + yVelocity);
        
    }
    
    public void attack(World w) {
        Entity[] entities = w.getEntitiesInRadius(getX(), getY(), 5);
        for(Entity e: entities) {
            e.damage(1);
        }
    }
    
    public boolean isDead() {
        return health>0;
    }
    
    public void damage(int amount) {
        if (health > 0) health--;
    }
    
    @Override
    public void paint(Graphics g) {
        
        int textureToLoad = 1;
        if(lastAnimationFrameTime + (1000/ANIMATION_FPS) < System.currentTimeMillis()) {
            animationFrame = (animationFrame==3)? 0 : animationFrame + 1;
            lastAnimationFrameTime = System.currentTimeMillis();
        }
        
        // Set texture based on direction
        switch(facingDirection) {
            case 7:
            case 0:
            case 1:
                if(velocity > 0) {
                    switch(animationFrame) {
                        case 1:
                            textureToLoad = 7;
                            break;
                        case 3:
                            textureToLoad = 11;
                             break;
                        default:
                            textureToLoad = 3;
                            break;
                    }
                } else {
                    textureToLoad = 3;
                }
                break;
            case 2:
                if(velocity > 0) {
                    switch(animationFrame) {
                        case 1:
                            textureToLoad = 6;
                            break;
                        case 3:
                            textureToLoad = 10;
                             break;
                        default:
                            textureToLoad = 2;
                            break;
                    }
                } else {
                    textureToLoad = 2;
                }
                break;
            case 3:
            case 4:
            case 5:
                if(velocity > 0) {
                    switch(animationFrame) {
                        case 1:
                            textureToLoad = 4;
                            break;
                        case 3:
                            textureToLoad = 8;
                             break;
                        default:
                            textureToLoad = 0;
                            break;
                    }
                } else {
                    textureToLoad = 0;
                }
                break;
            case 6:
                if(velocity > 0) {
                    switch(animationFrame) {
                        case 1:
                            textureToLoad = 5;
                            break;
                        case 3:
                            textureToLoad = 9;
                             break;
                        default:
                            textureToLoad = 1;
                            break;
                    }
                } else {
                    textureToLoad = 1;
                }
                break;
            default:
                break;
        }
        
        super.paint(g, textureToLoad);
    }
    
}

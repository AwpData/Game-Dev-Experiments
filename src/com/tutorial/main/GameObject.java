package com.tutorial.main;

import java.awt.*;

// Something I discovered: alt + insert to generate things (constructors, getters & setters, etc.)

// Refers to all the game objects (subclasses that extend this class will be a game object)
public abstract class GameObject {
    // Position on screen
    protected int x, y;
    // Unique ID to identify the game object
    protected ID id;
    // Speed of object
    protected int velX, velY;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    // ## Must have methods each game object will define ##
    public abstract void tick();

    public abstract void render(Graphics g);

    // For collisions
    public abstract Rectangle getBounds();

    // ## Getters and Setters ##

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

}

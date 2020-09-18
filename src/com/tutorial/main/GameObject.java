package com.tutorial.main;

import java.awt.*;

// Something I discovered: alt + insert to generate things (constructors, getters & setters, etc.)

// Refers to all the game objects (subclasses that extend this class will be a game object)
public abstract class GameObject {
    // Position on screen
    protected float x, y;
    // Unique ID to identify the game object
    protected ID id;
    // Speed of object
    protected float velX, velY;

    public GameObject(float x, float y, ID id) {
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


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}

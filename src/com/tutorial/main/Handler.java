package com.tutorial.main;

import java.awt.*;
import java.util.*;

// Maintains the rendering of every object in the game (loops through every object and updates each and every one)
public class Handler {

    // Stores each game object in LinkedList (Fast!)
    LinkedList<GameObject> object = new LinkedList<>();

    // ## tick and render each object with polymorphism ##
    public void tick() {
        for (GameObject tempObject : object) {
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (GameObject tempObject : object) {
            tempObject.render(g);
        }
    }

    // Adds an object to our linked list "object"
    public void addObject(GameObject object) {
        this.object.add(object);
    }

    // Removes an object from our linked list "object"
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

}

package com.tutorial.main;

import java.awt.*;
import java.util.*;

// Maintains the rendering of every object in the game (loops through every object and updates each and every one)
public class Handler {

    // Stores each game object in LinkedList (Fast!)
    LinkedList<GameObject> object = new LinkedList<>();

    // ## tick and render each object with polymorphism ##
    public void tick() {
        // I can't use a for-each loop because I get an error for the trail :(
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
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

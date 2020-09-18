package com.tutorial.main;

// Allows us to identify what object is what with an ID
public enum ID {
    Player(),
    BasicEnemy(), // Basic enemy and fast enemy share the same ID
    SmartEnemy(),
    Trail()
}

// new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
// new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);

// new Trail(x, y, ID.Trail, Color.REPLACEME, 16, 16, 0.07f, handler);

// Game ideas:
// Kill off enemies after some time, progressing the game while spawning harder enemies (use alpha / life trick for this)
// Make enemy superclass with subclasses for easier management of each type of enemy
// // Class would have to contain constructor with velx, vely, color, id; maybe lifespan and time?


// X, Y, velX, and velY are in floats -> need cast to ints (remember that data table? "You can add but can't remove")
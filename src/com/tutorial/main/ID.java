package com.tutorial.main;

// Allows us to identify what object is what with an ID
public enum ID {
    Player(),
    BasicEnemy(), // Basic enemy and fast enemy share the same ID
    HardEnemy(),
    SmartEnemy(),
    EnemyBoss(),
    Particle(),
    Trail()
}

// new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
// new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);

// new Trail(x, y, ID.Trail, Color.REPLACEME, 16, 16, 0.07f, handler);

// Game ideas:
// Make enemy boss class to take different parameters for different bosses
// // Class would have to contain constructor with velx, vely, color, id; maybe lifespan and time?
// Create a leaderboard screen
// Add a button (maybe a pause button) that takes you to the menu
// Add pausing sound


// X, Y, velX, and velY are in floats -> need cast to ints (remember that data table? "You can add but can't remove")
package com.tutorial.main;

// Allows us to identify what object is what with an ID
public enum ID {
    Player(),
    BasicEnemy(), // Basic enemy and fast enemy share the same ID
    BigEnemy(),
    Trail()
}

// new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
// new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);

// new Trail(x, y, ID.Trail, Color.REPLACEME, 16, 16, 0.07f, handler);

// Game ideas:
// Kill off enemies after some time, progressing the game while spawning harder enemies (use alpha / life trick)
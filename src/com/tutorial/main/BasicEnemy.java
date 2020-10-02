package com.tutorial.main;

import java.awt.*;

public class BasicEnemy extends GameObject {

    private final Handler handler;

    // Kills them off screen over time for next levels
   /* private int lifeSpan = 1000;
    private int ageTime = 2;*/

    public BasicEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 5;
        velY = 5;
        this.handler = handler;
        handler.addObject(this);
    }

    @Override
    // We set an invisible boundary as a collision mask at whatever point the enemy is at
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    // What changes every tick
    public void tick() {
        // I created a lifespan for these things that I will implement later (for alt. level progression)
      /*  lifeSpan -= ageTime;
        if (lifeSpan < 0) {
            handler.removeObject(this);
        }*/
        x += velX;
        y += velY;

        // We cannot clamp the enemies because they are constantly moving and we have to change their velocities instead
        // Bounces off edge if greater than max or min height (- 60 because it would bounce off screen so decreased the height!)
        if (y <= 0 || y >= Game.HEIGHT - 50) {
            AudioPlayer.playSound("res/bounce.wav");
            velY *= -1;
        }
        // Bounces off edge if greater than max or min width ( - 32 for same reason for height)
        if (x <= 0 || x >= Game.WIDTH - 30) {
            AudioPlayer.playSound("res/bounce.wav");
            velX *= -1;
        }

        // Creates a new trail for the enemy (smaller life = longer time on screen!)
        new Trail(x, y, ID.Trail, Color.orange, 16, 16, 0.07f, handler);
    }

    @Override
    // What you will see after the tick!
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}

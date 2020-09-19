package com.tutorial.main;

import java.awt.*;
import java.util.Random;

// This is the projectile the boss fires at the player
public class EnemyBossBullet extends GameObject {

    Random r = new Random();
    private Handler handler;
    private GameObject player;

    public EnemyBossBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = r.nextInt(5 - -5) + -5;
        velY = 5;
        this.handler = handler;
        handler.addObject(this);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    public void tick() {

        x += velX;
        y += velY;
        // Bullets do not need any boundaries because they are bullets

        // the bullet will be removed after it goes beyond the height
        if (y >= Game.HEIGHT) {
            handler.removeObject(this);
        }

        new Trail(x, y, ID.Trail, Color.yellow, 16, 16, 0.07f, handler);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int) x, (int) y, 16, 16);
    }

}

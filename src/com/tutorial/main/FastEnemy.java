package com.tutorial.main;

import java.awt.*;
import java.util.Random;

// Fast enemy is faster than basic by 4 pixels
public class FastEnemy extends GameObject {

    Random r = new Random();
    private Handler handler;

    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velX = 9;
        velY = 9;
        this.handler = handler;
        handler.addObject(this);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    // What changes every tick
    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 50) {
            velY *= -1;
        }
        if (x <= 0 || x >= Game.WIDTH - 30) {
            velX *= -1;
        }

        new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.07f, handler);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}


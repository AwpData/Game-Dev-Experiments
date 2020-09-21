package com.tutorial.main;

import java.awt.*;
import java.util.Random;


// Particles for menu screen
public class Particle extends GameObject {
    Random r = new Random();
    private Handler handler;
    private Color color;

    public Particle(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        // Sets speed of each direction based on a random number but not 0!
        velX = r.nextInt(7 - -7) + -7;
        velY = r.nextInt(7 - -7) + -7;
        if (velX == 0) {
            velX = 1;
        }
        if (velY == 0) {
            velY = 1;
        }

        // random color for each particle
        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
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

        if (y <= 0 || y >= Game.HEIGHT - 50) {
            velY *= -1;
        }
        if (x <= 0 || x >= Game.WIDTH - 30) {
            velX *= -1;
        }

        new Trail(x, y, ID.Trail, color, 16, 16, 0.05f, handler);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}


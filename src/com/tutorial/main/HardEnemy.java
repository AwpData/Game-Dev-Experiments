package com.tutorial.main;

import java.awt.*;
import java.util.Random;

// When hard enemies bounce off walls, they bounce at at a random velocity
public class HardEnemy extends GameObject {

    Random r = new Random();
    private final Handler handler;

    public HardEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 5;
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

        if (y <= 0 || y >= Game.HEIGHT - 50) {
            AudioPlayer.playSound("res/bounce.wav");
            if (y < 0) {
                velY = -(r.nextInt(7) + 3) * -1;
            } else {
                velY = (r.nextInt(7) + 3) * -1;
            }
        }
        if (x <= 0 || x >= Game.WIDTH - 30) {
            AudioPlayer.playSound("res/bounce.wav");
            if (x < 0) {
                velX = -(r.nextInt(7) + 3) * -1;
            } else {
                velX = (r.nextInt(7) + 3) * -1;
            }
        }

        new Trail(x, y, ID.Trail, Color.pink, 16, 16, 0.07f, handler);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}

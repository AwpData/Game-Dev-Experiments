package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class BasicEnemy extends GameObject {

    Random r = new Random();

    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        velX = r.nextInt(10) + 1;
        velY = r.nextInt(10) + 1;
    }

    @Override
    // What changes every tick
    public void tick() {
        x += velX;
        y += velY;

        // Bounces off edge if greater than max or min height (- 60 because it would bounce off screen so decreased the height!)
        if (y <= 0 || y >= Game.HEIGHT - 60) {
            velY *= -1;
        }
        // Bounces off edge if greater than max or min width ( - 32 for same reason for height)
        if (x <= 0 || x >= Game.WIDTH - 36) {
            velX *= -1;
        }
    }

    @Override
    // What you will see after the tick!
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
}

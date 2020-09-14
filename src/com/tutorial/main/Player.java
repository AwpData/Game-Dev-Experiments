package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();

    public Player(int x, int y, ID id) {
        super(x, y, id);

        // Sets a random speed for each box
        velX = r.nextInt(5) + 1;
        velY = r.nextInt(5);
    }

    @Override
    // moves each box every tick
    public void tick() {
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        g.fillRect(x, y, 32, 32);
    }
}

package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();

    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    // moves the player every tick
    public void tick() {
        x += velX;
        y += velY;

        // Prevents player from running off screen by restraining it with Game.clamp() method
        x = Game.clamp(x, 0, Game.WIDTH - 48);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);
    }

    @Override
    // What you will see after the tick!
    public void render(Graphics g) {
        // g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }
}

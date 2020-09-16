package com.tutorial.main;

import java.awt.*;

public class HUD {

    // Static because we only have one health (we do 100 because it is a good point value / most games use 100 for health)
    public static int HEALTH = 100;

    public void tick() {
        // And we clamp the health to not go outside of the bar at 0 while it decreases due to collisions
        HEALTH = Game.clamp(HEALTH, 0, 100);
    }

    // Yes, there is a difference between "fill" and "draw" (think about it)
    public void render(Graphics g) {
        // background of the health bar
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        // the amount of health you have (We HEALTH * 2 for now because the background is 200 and health is 100)
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH * 2, 32);
        // draws a border around the box
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
    }
}

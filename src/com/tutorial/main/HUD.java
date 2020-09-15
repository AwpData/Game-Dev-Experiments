package com.tutorial.main;

import java.awt.*;

public class HUD {

    // Static because we only have one health (we do 100 because it is a good point value / most games use 100 for health)
    public static int HEALTH = 100;

    public void tick() {
        HEALTH--;
        // And we clamp the health to not go outside of the bar at 0
        HEALTH = Game.clamp(HEALTH, 0, 100);
    }

    public void render(Graphics g) {
        // Border of the health bar
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        // the amount of health you have
        g.setColor(Color.green);
        g.fillRect(15, 15, HEALTH * 2, 32);
        // draws a border around the box
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
    }
}

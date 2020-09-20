package com.tutorial.main;

import java.awt.*;

public class HUD {

    // Static because we only have one health (we do 100 because it is a good point value / most games use 100 for health)
    public static float HEALTH = 100;

    // Score you see on screen
    private int score = 0;
    private int level = 1;

    /* private int greenValue = 255;*/

    public void tick() {
        // And we clamp the health to not go outside of the bar at 0 while it decreases due to collisions
        HEALTH = (int) Game.clamp(HEALTH, 0, 100);
        score++;

       /* greenValue = Game.clamp(greenValue, 0, 255);
        greenValue = HEALTH * 2;*/
    }

    // Yes, there is a difference between "fill" and "draw" (think about it)
    public void render(Graphics g) {
        // background of the health bar
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        // Sets color to get more red as health decreases
        g.setColor(Color.getHSBColor((1f * HEALTH) / 360, 1f, 1f));
        // the amount of health you have (We HEALTH * 2 for now because the background is 200 and health is 100)
        g.fillRect(15, 15, (int) (HEALTH * 2), 32);
        // draws a border around the box
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        // Draws strings below health bar that indicate score and level
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }

    // Getters and setters for usage in the Spawn class
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

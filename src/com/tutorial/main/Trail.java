package com.tutorial.main;

import java.awt.*;

public class Trail extends GameObject {

    // This is the life of our trail which fades slowly, and when it equals 0 we destroy it
    private float alpha = 1;
    // Life is like the time it will be on screen (because it subtracts from alpha)
    private float life;

    private Handler handler;
    private Color color;
    private int width, height;

    // life = 0.01 -> 0.1
    // The smaller the life, the longer the trail will be on screen

    // Trails can have different colors, widths, life (time on screen) and heights
    public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
        super(x, y, id);
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
        this.handler = handler;
        handler.addObject(this);
    }

    @Override
    // This is what decreases the time the trail is on screen before removing it
    public void tick() {
        // We subtract life from alpha (think of alpha as it's life subtracting it's lifetime on it)
        if (alpha > life) {
            alpha -= (life - 0.0001f);
        } else {
            handler.removeObject(this);
        }
    }

    @Override
    // Must have 2D graphics to render trails
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Renders be sandwiched or it will make other things transparent on accident (between alpha and 1)
        g2d.setComposite(makeTransparent(alpha));

        g2d.setColor(color);
        g2d.fillRect(x, y, width, height);

        g2d.setComposite(makeTransparent(1));
    }

    // Method that renders out transparencies in trails
    // I believe that this takes the alpha value, reads the value, and sets some sort of transparency level for it
    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}

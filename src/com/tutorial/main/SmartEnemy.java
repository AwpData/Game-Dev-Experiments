package com.tutorial.main;

import java.awt.*;
import java.util.Random;

// Smart enemies can track the player, thus, we need the player in this class
public class SmartEnemy extends GameObject {

    Random r = new Random();
    private Handler handler;

    private GameObject player;


    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        handler.addObject(this);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    // This is the only class (for now) that has to access another object's coordinates, thus we must keep getting that object for reference
    public void tick() {

        // Getting the player so it can follow it (We have to keep getting it to update it's x values I guess)
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                player = handler.object.get(i);
            }
        }

        // Using the pythagorean theorem to get the distance between smart enemy and the player which will thus set the velocity
        // I don't know how to explain this equation lol
        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2));

        velX = (float) ((-1.0 / distance) * diffX);
        velY = (float) ((-1.0 / distance) * diffY);

        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 50) {
            velY *= -1;
        }

        if (x <= 0 || x >= Game.WIDTH - 30) {
            velX *= -1;
        }

        new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.07f, handler);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x, (int) y, 16, 16);
    }
}

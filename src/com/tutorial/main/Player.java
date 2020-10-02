package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        handler.addObject(this);
    }

    @Override
    // We set an invisible boundary as a collision mask at whatever point the player is at
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    @Override
    // moves the player every tick
    public void tick() {
        x += velX;
        y += velY;

        // Prevents player from running off screen by restraining it with Game.clamp() method
        x = Game.clamp(x, 0, Game.WIDTH - 48);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);
        new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.1f, handler);
        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss) {
                // intersects() is how to check if two objects collide (at their bounds of course)
                if (getBounds().intersects(tempObject.getBounds())) {
                    // collision code
                    // Decrease by 2 because our health is 200 width (and it is supposed to be 100 health, make the connection...)
                    HUD.HEALTH -= 2;
                    AudioPlayer.playSound("res/Hit.wav");
                }
            }
        }
    }

    @Override
    // What you will see after the tick!
    public void render(Graphics g) {
       /* // Casting our graphics g to 2D graphics to draw the collision border
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.red);
        g2D.draw(getBounds());*/

        // g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, 32, 32);
    }
}

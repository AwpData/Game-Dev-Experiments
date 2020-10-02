package com.tutorial.main;

import java.awt.*;
import java.util.Random;

// Enemy boss shoots projectiles at the player
public class EnemyBoss extends GameObject {

    Random r = new Random();
    private final Handler handler;

    // Timers are for boss movement sequence
    private int timer = 55; // Time to move down
    private int timer2 = 50; // Time to wait until moving left and right

    public EnemyBoss(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 0;
        velY = 2;
        this.handler = handler;
        handler.addObject(this);
    }

    @Override

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

    @Override

    public void tick() {
        x += velX;
        y += velY;

        // This moves the boss down until the timer is out
        if (timer <= 0) {
            velY = 0;
        } else {
            timer--;
        }
        // Then we decrease a timer to pause the boss for a second
        if (timer <= 0) {
            timer2--;
        }
        // Then the boss starts moving left and right, shooting bullets in random directions
        if (timer2 <= 0) {
            if (velX == 0) {
                velX = 2;
            }
            // The below code before spawn just gradually speeds the boss up
            if (velX > 0) {

                velX += 0.01f;
            } else if (velX < 0) {
                velX -= 0.01f;
            }
            // Clamping max speed
            velX = Game.clamp(velX, -10, 10);
            int spawn = r.nextInt(10);
            if (spawn == 0) {
                AudioPlayer.playSound("res/shot.wav");
                new EnemyBossBullet((int) x + 48, (int) y + 48, ID.BasicEnemy, handler);
            }
        }
      /*  if (y <= 0 || y >= Game.HEIGHT - 50) {
            velY *= -1;
        }
        */

        // Boss will continuously bounce left and right
        if (x <= 0 || x >= Game.WIDTH - 64) {
            AudioPlayer.playSound("res/boss_bounce.wav");
            velX *= -1;
        }

        /* new Trail(x, y, ID.Trail, Color.blue, 64, 64, 0.007f, handler);*/
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int) x, (int) y, 64, 64);
    }

}

package com.tutorial.main;

import java.awt.*;
import java.util.Random;

// This class spawns the enemies / score and level management
public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    // We get a copy of score from HUD to track the same score but are able to control the game with this one
    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        // Increment it the same as HUD
        scoreKeep++;
        // This makes it easier to manage the score because we don't want to modify the HUD score
        // Every 100 points we increment the level and reset scoreKeep to keep progressing the game
        if (scoreKeep >= 100) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            // Spawns basic enemies that are not on level multiples of 5
            if (hud.getLevel() % 5 != 0) {
                new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
            }
            // Spawns fast enemies that are on level multiples of 5
            if (hud.getLevel() % 5 == 0) {
                new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
            }
            if (hud.getLevel() == 3) {
                new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler);
            }
        }
    }
}

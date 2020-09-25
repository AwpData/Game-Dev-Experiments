package com.tutorial.main;

import java.awt.*;
import java.util.Random;

// This class spawns the enemies / score and level management
public class Spawn {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private Game game;

    // We get a copy of score from HUD to track the same score but are able to control the game with this one
    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick() {
        // Increment it the same as HUD
        scoreKeep++;
        // This makes it easier to manage the score because we don't want to modify the HUD score
        // Every 100 points we increment the level and reset scoreKeep to keep progressing the game
        if (scoreKeep >= 500) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            if (game.diff == 0) {
                if (hud.getLevel() == 2) {
                    new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                }
                if (hud.getLevel() == 3) {
                    handler.clearEnemies();
                    new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                    new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                }
                if (hud.getLevel() == 4) {
                    handler.clearEnemies();
                    new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                    new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                }
                if (hud.getLevel() == 5) {
                    new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                    new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler);
                }
                if (hud.getLevel() == 6) {
                    handler.clearEnemies();
                    AudioPlayer.playSound("res/boss_laugh.wav");
                    new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler);
                    new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler);
                }
            } else if (game.diff == 1) {
                if (hud.getLevel() == 2) {
                    new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                }
                if (hud.getLevel() == 3) {
                    handler.clearEnemies();
                    new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                    new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler);
                    new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                }
                if (hud.getLevel() == 4) {
                    handler.clearEnemies();
                    new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                    new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                    new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                }
                if (hud.getLevel() == 5) {
                    new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                    new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
                    new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler);
                }
                if (hud.getLevel() == 6) {
                    handler.clearEnemies();
                    AudioPlayer.playSound("res/boss_laugh.wav");
                    new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler);
                    new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler);
                }
            }
        }
    }
}
// Alternative progression (endless spawns until next boss
/*  if (hud.getLevel() % 5 != 0) {
                new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
            }
            if (hud.getLevel() % 5 == 0) {
                new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
            }
            if (hud.getLevel() % 3 == 0) {
                new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler);
            }*/

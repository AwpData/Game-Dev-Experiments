package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

// Java can only handle up to 16 bit sound files... please check this before adding
// StateHandler manages the current screen shown (but doesn't update Game class)
public class StateHandler extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    public StateHandler(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mousePressed(MouseEvent e) {
        // Get's the x and y positions of the mouse where it clicks
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            // Play button
            if (mouseOver(mx, my, 220, 100, 200, 64)) {
                AudioPlayer.playSound("res/click.wav");
                AudioPlayer.playMusic("res/game_music.wav");
                game.gameState = Game.STATE.Game;
                handler.clearParticles();
                // Places character in the middle of the screen
                new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler);
                // Level 1 enemy
                new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
            }
            // Help button
            if (mouseOver(mx, my, 220, 200, 200, 64)) {
                AudioPlayer.playSound("res/click.wav");
                handler.clearParticles();
                game.gameState = Game.STATE.Help;
            }

            // Quit button
            if (mouseOver(mx, my, 220, 300, 200, 64)) {
                AudioPlayer.playSound("res/click.wav");
                System.exit(0);
            }

        } else if (game.gameState == Game.STATE.Help) {
            // Play button
            if (mouseOver(mx, my, 220, 200, 200, 64)) {
                AudioPlayer.playSound("res/click.wav");
                AudioPlayer.playMusic("res/game_music.wav");
                game.gameState = Game.STATE.Game;
                // Places character in the middle of the screen
                new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler);
                // Level 1 enemy
                new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler);
            }
            // Menu button
            if (mouseOver(mx, my, 220, 300, 200, 64)) {
                AudioPlayer.playSound("res/click.wav");
                game.gameState = Game.STATE.Menu;
                for (int i = 0; i < 10; i++) {
                    new Particle(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Particle, handler);
                }
            }

        } else if (game.gameState == Game.STATE.GameOver) {
            // If the user wants to play again, we reset everything pretty much (even menu particles)
            // Menu Button
            if (mouseOver(mx, my, 220, 200, 200, 64)) {
                AudioPlayer.playSound("res/click.wav");
                AudioPlayer.playMusic("res/menu_music.wav");
                game.reset();
            }
            // Quit Button
            if (mouseOver(mx, my, 220, 300, 200, 64)) {
                AudioPlayer.playSound("res/click.wav");
                System.exit(0);
            }
        }
    }

    // Checks if the mouse is over a clickable button
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        // If we are within a box's widths and heights, it is clickable
        // We do + width and + height because we need to be within bounds of the box
        if (mx > x && mx < x + width) {
            return my > y && my < y + height;
        }
        return false;
    }

    public void render(Graphics g) {
        // Draws the menu with a font!
        Font fnt = new Font("ariel", Font.BOLD, 50);
        Font fntSmall = new Font("ariel", Font.BOLD, 30);

        g.setColor(Color.white);

        // Menu or help screen state
        if (game.gameState == Game.STATE.Menu) {
            g.setFont(fnt);
            g.drawString("Avoid Them...", 160, 75);

            g.setFont(fntSmall);
            g.drawRect(220, 100, 200, 64);
            g.drawString("Play", 292, 140);

            g.drawRect(220, 200, 200, 64);
            g.drawString("Help", 292, 240);

            g.drawRect(220, 300, 200, 64);
            g.drawString("Quit", 292, 340);
        } else if (game.gameState == Game.STATE.Help) {
            g.setFont(fntSmall);
            g.setColor(Color.red);
            g.drawString("Avoid touching anything at all costs...", 20, 75);
            g.setColor(Color.white);
            g.drawString("WASD or Arrow keys to move", 20, 115);
            g.drawString("10 Levels so far; boss is at level 10", 20, 155);

            g.drawRect(220, 200, 200, 64);
            g.drawString("Play", 292, 240);

            g.drawRect(220, 300, 200, 64);
            g.drawString("Menu", 292, 340);

        } else if (game.gameState == Game.STATE.GameOver) {
            // clears the screen if game is over and shows game over screen
            handler.object.clear();
            g.setFont(fnt);
            g.setColor(Color.red);
            g.drawString("Game Over", 195, 75);

            g.setFont(fntSmall);
            g.setColor(Color.white);
            g.drawString("Score: " + hud.getScore(), 205, 115);

            g.drawRect(220, 200, 200, 64);
            g.drawString("Menu", 282, 240);

            g.drawRect(220, 300, 200, 64);
            g.drawString("Quit", 292, 340);
        }
    }
}

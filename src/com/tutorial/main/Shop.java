package com.tutorial.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Speed, health, and health refills
public class Shop extends MouseAdapter {

    private final Handler handler;
    private final HUD hud;
    private final Game game;
    private int healthCost = 1000;
    private int speedCost = 1000;
    private final int refillHealthCost = 1000;

    public Shop(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Ariel", Font.PLAIN, 48));
        g.drawString("SHOP", Game.WIDTH / 2 - 80, 50);

        // Health Box
        g.setFont(new Font("Ariel", Font.PLAIN, 12));
        g.drawRect(100, 100, 100, 80);
        g.drawString("Upgrade Health", 110, 120);
        g.drawString("Cost: " + healthCost, 110, 140);

        // Speed Box
        g.setFont(new Font("Ariel", Font.PLAIN, 12));
        g.drawRect(250, 100, 100, 80);
        g.drawString("Upgrade Speed", 260, 120);
        g.drawString("Cost: " + speedCost, 260, 140);

        // Refill Health Box
        g.setFont(new Font("Ariel", Font.PLAIN, 12));
        g.drawRect(400, 100, 100, 80);
        g.drawString("Refill Health", 410, 120);
        g.drawString("Cost: " + refillHealthCost, 410, 140);

        g.setFont(new Font("Ariel", Font.PLAIN, 24));
        g.drawString("Score: " + hud.getScore(), Game.WIDTH / 2 - 50, 300);
        g.setFont(new Font("Ariel", Font.PLAIN, 12));
        g.drawString("Press SPACE to go back to game", Game.WIDTH / 2 - 50, 350);
    }

    public void mousePressed(MouseEvent e) {
        if (game.gameState == Game.STATE.Shop) {
            int mx = e.getX();
            int my = e.getY();

            // health box (dimensions are between 100 and 200 because width is 100, same logic for height)
            if (mx >= 100 && mx <= 200) {
                if (my >= 100 && my <= 180) {
                    if (hud.getScore() >= healthCost) {
                        AudioPlayer.playSound("res/purchase.wav");
                        hud.setScore(hud.getScore() - healthCost);
                        healthCost += 1000;
                        // Bounds is how much our health increase by
                        hud.bounds += 40;
                        HUD.HEALTH = (100 + (hud.bounds / 2));
                        HUD.maxHealth = (100 + (hud.bounds / 2));
                    }
                }
            }
            // Speed box
            if (mx >= 250 && mx <= 350) {
                if (my >= 100 && my <= 180) {
                    if (hud.getScore() >= speedCost) {
                        AudioPlayer.playSound("res/purchase.wav");
                        hud.setScore(hud.getScore() - speedCost);
                        speedCost += 1000;
                        handler.spd++;
                    }
                }
            }
            // Refill health box
            if (mx >= 400 && mx <= 500) {
                if (my >= 100 && my <= 180) {
                    // Makes sure you are not at full health already
                    if (hud.getScore() >= 1000) {
                        if (HUD.HEALTH != HUD.maxHealth) {
                            AudioPlayer.playSound("res/purchase.wav");
                            hud.setScore(hud.getScore() - refillHealthCost);
                            HUD.HEALTH = (100 + (hud.bounds / 2));
                        }
                    }
                }
            }
        }
    }

    public void reset() {
        healthCost = 1000;
        speedCost = 1000;
        hud.bounds = 0;
    }
}

package com.tutorial.main;

import javax.sound.sampled.Clip;
import java.awt.event.*;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Game game;

    // Each element this array responds to WASD (and checks if the key is down) [W, S, D, A]
    // true = key is being pressed; false = key is released
    private boolean[] keyDown = {false, false, false, false};

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    // Checks which key is pressed and gets the Player from our handler to tick it
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            // key events for player (VK_W stores 87 for example, the keycode)
            // We set keyDown to true because it is being pressed
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    tempObject.setVelY(-5);
                    keyDown[0] = true;
                } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    tempObject.setVelY(5);
                    keyDown[1] = true;
                } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(5);
                    keyDown[2] = true;
                } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-5);
                    keyDown[3] = true;
                }
            }
        }

        // BUG: AFTER UNPAUSING, AND MUSIC LOOPS AGAIN, IT WILL KEEP RESTARTING SONG INSTEAD OF RESUMING WHERE IT IS
        // Pauses the game if in the game state
        if (key == KeyEvent.VK_ESCAPE && game.gameState == Game.STATE.Game) {
            if (Game.paused) {
                AudioPlayer.playSound("res/pause.wav");
                // Plays the music again
                AudioPlayer.playMusic.start();
                AudioPlayer.playMusic.loop(Clip.LOOP_CONTINUOUSLY);
                Game.paused = false;
            } else {
                // Gets the position of our music so we can pause it
                AudioPlayer.playSound("res/pause.wav");
                long clipTimePosition = AudioPlayer.playMusic.getMicrosecondPosition();
                AudioPlayer.playMusic.setMicrosecondPosition(clipTimePosition);
                AudioPlayer.stopMusic();
                Game.paused = true;
            }
        }
        // Key to go to shop and game depending on state (does the pause and unpause audio trick too)
        else if (key == KeyEvent.VK_SPACE && game.gameState == Game.STATE.Game && !Game.paused) {
            AudioPlayer.playSound("res/shop.wav");
            game.gameState = Game.STATE.Shop;
            long clipTimePosition = AudioPlayer.playMusic.getMicrosecondPosition();
            AudioPlayer.playMusic.setMicrosecondPosition(clipTimePosition);
            AudioPlayer.stopMusic();
        } else if (key == KeyEvent.VK_SPACE && game.gameState == Game.STATE.Shop) {
            AudioPlayer.playSound("res/shop.wav");
            game.gameState = Game.STATE.Game;
            AudioPlayer.playMusic.start();
        }

        // FOR INSTANT QUIT
        if (key == KeyEvent.VK_L && game.gameState == Game.STATE.Game) {
            HUD.HEALTH = 0;
            if (Game.paused) {
                Game.paused = false;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            // false = key is released
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    keyDown[0] = false;
                } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    keyDown[1] = false;
                } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    keyDown[2] = false;
                } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    keyDown[3] = false;
                }

                // Both of these if statements set the vel Y to zero if none of the keys are pressed (prevents player from being unable to move)
                // The extra if statements within each movement help with bugs when releasing one key but holding on to the other
                // vertical movement
                if (!keyDown[0] && !keyDown[1]) {
                    tempObject.setVelY(0);
                } else if (keyDown[0] && !keyDown[1]) {
                    tempObject.setVelY(-(handler.spd));
                } else if (!keyDown[0]) {
                    tempObject.setVelY(handler.spd);
                }
                // horizontal movement
                if (!keyDown[2] && !keyDown[3]) {
                    tempObject.setVelX(0);
                } else if (keyDown[2] && !keyDown[3]) {
                    tempObject.setVelX(handler.spd);
                } else if (!keyDown[2]) {
                    tempObject.setVelX(-(handler.spd));
                }
            }
        }
    }
}

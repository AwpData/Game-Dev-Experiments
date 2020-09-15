package com.tutorial.main;

import java.awt.event.*;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            // key events for player (VK_W stores 87 for example, the keycode)
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-5);
                } else if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(5);
                } else if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(5);
                } else if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-5);
                }
            }
        }

        // Quits the game if ESC key is pressed
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            // Stops all movement on key press for player
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(0);
                } else if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(0);
                } else if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(0);
                } else if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(0);
                }
            }
        }
    }
}

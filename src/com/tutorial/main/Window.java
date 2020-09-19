package com.tutorial.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class Window extends Canvas implements Serializable {

    private static final long serialVersionUID = -552287688775572810L;

    public Window(int width, int height, String title, Game game) {
        // Creates the frame of the window
        JFrame frame = new JFrame(title);

        // Setting window size (and making it un-resizable)
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        // Setting window operations (closes on x-button; can't resize, window starts in the middle; adds our game to frame; can see it)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        // This is here to stop the window from opening in the top-left
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        // Run the game!
        game.start();
    }
}

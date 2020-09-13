package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serializable;

public class Game extends Canvas implements Runnable, Serializable {

    // Needed because idk
    private static final long serialVersionUID = -7760947298623491080L;
    // This is the aspect ratio calculator!
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    // The game will run on a single thread (Threads run programs splitting the tasks between threads [Multi-threaded CPUS!])
    private Thread thread;
    // This tells if the program is running or not (based on thread)
    private boolean running = false;

    public Game() {
        new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
    }

    // Starts up the thread (Synchronized means that everything stops until this is finished running)
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            // Updated thread.stop(), it literally kills the thread
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    // Updates the game constantly (game loop) -> this one is very popular (FPS)
    public void run() {
        long lastTime = System.nanoTime(); // gets current time to the nanosecond
        double amountOfTicks = 60.0; // sets the number of ticks
        double ns = 1000000000 / amountOfTicks; // determines how many times we can divide one second (in nanosecond) into 60 ticks
        double delta = 0; // Recall that delta means change in X (in this case, time)
        long timer = System.currentTimeMillis(); // Gets current time (in ms)
        int frames = 0; // sets the frame variable

        while (running) {
            long now = System.nanoTime(); // gets current time in ns during current loop
            delta += (now - lastTime) / ns; // add the amount of change since last loop
            lastTime = now; // sets the last time to now
            while (delta >= 1) { // Ensures that one tick has passed because the game will run too fast!
                tick(); // Then we can render the next frame
                delta--; // and set delta back to zero
            }
            if (running) {
                render(); // renders the visuals of the game
            }
            frames++; // notes that a frame has now passed
            if (System.currentTimeMillis() - timer > 1000) { // If one second has passed
                timer += 1000; // add 1 second to our timer for next time
                System.out.println("FPS: " + frames); // prints out how many frames happened in the last second
                frames = 0; // resets our frames for the next second
            }
        }
        stop(); // Stopped running program so KILL THE THREAD!
}

    private void tick() {

    }

    private void render() {
        // Lowers the FPS significantly by "slowing" what is shown on screen. It is set to NULL initially
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3); // Triple Buffered V-Sync?
            return;
        }

        // Sets the graphics for the game -> first it runs through the buffer to "slow" it
        Graphics g = bs.getDrawGraphics();
        // This stops the flashing
        g.setColor(Color.black);
        // But we must also make a rectangle to hide it
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // Then we can get rid of graphics since we don't need it anymore (so it doesn't add to memory)
        g.dispose();
        // And finally we can show it to the screen through the buffer!
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }


}

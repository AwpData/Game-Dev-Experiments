package com.tutorial.main;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// BUG: music is not looping
// This is a static class, so all instances of AudioPlayer must be referenced in static context
// Audio code that does not use Slick library
public class AudioPlayer {

    // The clip is how we play the audio (I initialized it before so that I could change songs)
    public static Clip playMusic;
    // I have a separate clip for the sounds because they don't loop and thus don't need to close like playMusic
    private static Clip playSound;

    // Initializing playMusic so I can close it
    static {
        try {
            playMusic = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(String location) {
        File file = new File(location);
        try {
            // Stops the current song before starting the next song
            playMusic.close();
            // Creates the sound, and plays it at a certain volume while looping it
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            // This creates a DAEMON thread that we need to open before starting it
            playMusic.open(sound);
            FloatControl volume = (FloatControl) playMusic.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(0.1f);
            playMusic.start();
            playMusic.loop(Clip.LOOP_CONTINUOUSLY);
            // This is also how we can stop something that has .loop or .start on the clip
            // JOptionPane.showMessageDialog(null, "Press to stop playing");
            // clip.stop();
            // clip.setFrameSecond(0) to reset back to beginning
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    // Stops music for some screens like the game over screen
    public static void stopMusic() {
        playMusic.stop();
    }

    // Separate method for sounds since they don't loop
    public static void playSound(String location) {
        File file = new File(location);
        try {
            AudioInputStream gameSound = AudioSystem.getAudioInputStream(file);
            playSound = AudioSystem.getClip();
            playSound.open(gameSound);
            FloatControl volume = (FloatControl) playSound.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(0.01f);
            // Plays it once with .start
            playSound.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}

// Commented out Slick Library code because it was buggy
/*
public class AudioPlayer {

    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    // Loads in all the sounds
    public static void load() throws SlickException {
        soundMap.put("click", new Sound("res/click.wav"));
        musicMap.put("menu_music", new Music("res/game_music.wav"));
        musicMap.put("game_music", new Music("res/game_music.wav"));
    }

    public static Music getMusic(String key) {
        return musicMap.get(key);
    }

    public static Sound getSound(String key) {
        return soundMap.get(key);
    }
}
*/

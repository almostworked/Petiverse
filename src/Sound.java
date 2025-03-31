import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

/**
 * The purpose of this class is to play audio during gameplay
 */
public class Sound {
    /** attributes */
    private static Clip clip;
    private static boolean isSoundEnabled = false;

    /**
     * Given a filepath passed as parameter, loop and play tue audio recording
     * Additionally, this method parses user input in the event they wish to pause/resume music
     *
     * @param location is the filepath of the audio recording
     */
    public static void playMusic(String location) {
        if (!isSoundEnabled) return;
        try {
            File path = new File(location);
            if (path.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
                
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                // Continuously loop the audio clip
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to play a sound effect. Given a filepath, it plays the clip once
     *
     * @param location is the filepath of the audio recording
     */
    public static void playEffect(String location) {
        if (!isSoundEnabled) return;
        try {
            File path = new File(location);
            if (path.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * Method to track if sound is enabled
    *
    */
    public static boolean isSoundEnabled() {
        return isSoundEnabled;
    }

    /**
    * Method to toggle sound between on and off
    *
    */
    public static void toggleSound() {
        // Changes current sound state to it's opposite
        isSoundEnabled = !isSoundEnabled;
        if (!isSoundEnabled && clip != null && clip.isRunning()) {
            clip.stop();
        } else if (isSoundEnabled) {
            playMusic("temp_assets/background_music.wav");
        }
    }


}


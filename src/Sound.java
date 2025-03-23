package src;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

/**
 * The purpose of this class is to play audio during gameplay
 */
public class Sound {
    /** attributes */
    private Clip clip;
    private long clipTimePosition = 0;
    private boolean isPaused = false;

    /**
     * Initialize and play a given audio file
     *
     */
    public static void main(String[] args) {
        // ADD THESE 2 LINES OF CODE TO THE MAIN MENU TO GET SOUND 
        Sound sound = new Sound();
        sound.playMusic("temp_assets/background_music.wav");
    }

    /**
     * Given a filepath passed as parameter, loop and play tue audio recording
     * Additionally, this method parses user input in the event they wish to pause/resume music
     *
     * @param location is the filepath of the audio recording
     */
    public void playMusic(String location) {
        try {
            File path = new File(location);
            if (path.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);

                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                // Continuously loop the audio clip
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                System.out.println("Music is playing. Enter the 'P' key to pause or the 'R' key to resume");

                // Parse any user input
                new Thread(() -> {
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        String input = scanner.nextLine();
                        // Click P to pause
                        if (input.equalsIgnoreCase("P")) {
                            pauseMusic();
                            // Click R to resume
                        } else if (input.equalsIgnoreCase("R")) {
                            resumeMusic();
                            break;
                        }
                    }
                    scanner.close();
                }).start();
            } else {
                System.out.println("File not found");
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to pause audio
     */
    public void pauseMusic() {
        if (clip != null && clip.isRunning()) {
            isPaused = true;

            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    /**
     * Method to resume paused audio
     */
    public void resumeMusic() {
        if (clip != null && isPaused) {
            isPaused = false;

            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

}


import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for playing audio during gameplay
 */
public class Sound {
    /**
     * This method takes a filepath as input and plays the audio stored in that file
     *
     * @param location is the filepath to be played
     */
    void playMusic(String location) {
        try {
            File path = new File(location);

            if (path.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                // loop the audio clip continuously
                clip.loop(Clip.LOOP_CONTINUOUSLY);

//                JOptionPane.showMessageDialog(null, "Hit ok");
//                long clipTimePosition = clip.getMicrosecondPosition();
//                clip.stop();
//
//                JOptionPane.showMessageDialog(null,"hit ok");
//                clip.setMicrosecondPosition(clipTimePosition);
//                clip.start();
//
//                JOptionPane.showMessageDialog(null,"Press ok");

            }
            else {
                System.out.println("Error finding the music path");
            }
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}

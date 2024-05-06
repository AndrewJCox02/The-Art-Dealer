/* File: ApplicationGUI.java
 * Author(s): Robert Reinholdt
 * Date: 5/4/2024
 * Purpose: This class creates and handles an audio clip using the javax.sound.sampled api
 */

import javax.sound.sampled.*;
import java.io.File;

public class NoiseMaker implements LineListener{

    static private NoiseMaker main = null;
    private Clip audioClip = null;
    private AudioInputStream audioStream = null;

    // singleton class retries the existing sound object, or creates it if it does not exist
    public static NoiseMaker getNoiseMaker() {
        if (main != null) {
            return main;
        }

        return new NoiseMaker();
    }

    // private constructor
    private NoiseMaker() {
        String fileName = "Confirmation.wav";
        File audioFile = new File(fileName);

        try {
            // create an audioInputStream from the audio file
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            // get a line from the audio system
            audioClip = AudioSystem.getClip();
            // reserve a spot in the audio system
            audioClip.addLineListener(this);
            // load the audioFile into memory
            audioClip.open(audioStream);
            // lower the volume
            FloatControl volume = (FloatControl)audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-18.0f);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        main = this;
    }

    // plays the winNoise
    public void playWinNoise() {
        if (audioClip != null) {
            audioClip.setFramePosition(0);
            audioClip.start();
        } else {
            System.out.println("can't play audio clip as it isn't in memory");
        }

    }

    // allows us to close any resources that are open
    public void close() {
        try {
            if (audioClip != null) {
                audioClip.close();
            }
            if (audioStream != null) {
                audioStream.close();
            }
        }
        catch(Exception e) {
            System.out.println("Error on NoiseMaker Cleanup:[" + e.getMessage() + "]");
        }
    }

    // required for the functionality of LineListener
    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.START) {
            System.out.println("NoiseMaker Playing Clip");
        }
        else if (event.getType() == LineEvent.Type.STOP) {
            System.out.println("NoiseMaker stopping Clip");
        }
    }
}
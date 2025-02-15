package com.wibotron.game.assets.audio;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class GameSound {
    private Clip clip;

    public void playSound() {
        try {
            if (this.clip == null || !this.clip.isRunning()) {
                File file = new File("D:\\JAVA-SIMPLE-TURN-BASED-GAME\\WarOfGreed\\src\\main\\java\\com\\wibotron\\game\\assets\\audio\\Avabel Online MMORPG OST - Opening Theme.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                this.clip = AudioSystem.getClip();
                this.clip.open(audioInputStream);
                this.clip.loop(Clip.LOOP_CONTINUOUSLY);
                this.clip.start();
            }
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopSound() {
        if (this.clip != null && this.clip.isRunning()) {
            this.clip.stop();
        }
    }

}

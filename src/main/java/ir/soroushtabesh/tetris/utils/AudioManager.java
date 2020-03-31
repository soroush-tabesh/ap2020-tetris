package ir.soroushtabesh.tetris.utils;

import javax.sound.sampled.Clip;

public class AudioManager {
    public static void startAmbientSound() {
        ResourcePool.getInstance().getAmbientSound().loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void playScoreSit() {
        Clip clip = ResourcePool.getInstance().getScoreSitSound();
        clip.stop();
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public static void playScoreRow() {
        Clip clip = ResourcePool.getInstance().getScoreRowSound();
        clip.stop();
        clip.setMicrosecondPosition(0);
        clip.start();
    }
}

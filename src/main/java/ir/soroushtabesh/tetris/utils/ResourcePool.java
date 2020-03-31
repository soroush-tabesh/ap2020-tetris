package ir.soroushtabesh.tetris.utils;

import ir.soroushtabesh.tetris.models.LeaderBoard;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class ResourcePool {
    private static ResourcePool resourcePool;

    private boolean loaded = false;
    private ImageIcon loadingImage;
    private LeaderBoard leaderBoard;
    private Clip ambientSound;
    private Clip scoreRowSound;
    private Clip scoreSitSound;
    private ImageIcon[] blockIcons = new ImageIcon[8];

    private ResourcePool() {
    }

    public static ResourcePool getInstance() {
        if (resourcePool == null) {
            resourcePool = new ResourcePool();
            resourcePool.load();
        }
        return resourcePool;
    }

    public LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }

    public void load() {
        if (loaded)
            return;
        try {
            loadingImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader()
                    .getResourceAsStream("tetris_logo.png"))));
            leaderBoard = LeaderBoard.loadFile();
            ambientSound = AudioSystem.getClip();
            scoreRowSound = AudioSystem.getClip();
            scoreSitSound = AudioSystem.getClip();
            ambientSound.open(AudioSystem.getAudioInputStream(
                    getClass().getClassLoader().getResourceAsStream("ambient.wav")));
            scoreRowSound.open(AudioSystem.getAudioInputStream(
                    getClass().getClassLoader().getResourceAsStream("score_row.wav")));
            scoreSitSound.open(AudioSystem.getAudioInputStream(
                    getClass().getClassLoader().getResourceAsStream("score_sit.wav")));
            for (int i = 0; i < blockIcons.length; i++) {
                blockIcons[i] = new ImageIcon(ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader()
                        .getResourceAsStream(String.format("blocks/block_%d.png", i)))));
            }
            loaded = true;
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public ImageIcon getBlockIcon(int id) {
        return blockIcons[id];
    }

    public Clip getAmbientSound() {
        return ambientSound;
    }

    public Clip getScoreRowSound() {
        return scoreRowSound;
    }

    public Clip getScoreSitSound() {
        return scoreSitSound;
    }

    public ImageIcon getLoadingImage() {
        return loadingImage;
    }

    public void dispose() {
        if (!loaded)
            return;
        loaded = false;
    }
}

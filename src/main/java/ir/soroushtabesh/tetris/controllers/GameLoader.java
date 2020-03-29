package ir.soroushtabesh.tetris.controllers;

import ir.soroushtabesh.tetris.utils.ResourcePool;
import ir.soroushtabesh.tetris.views.GameFrame;

import javax.swing.*;
import java.awt.*;

public class GameLoader {
    private static GameLoader gameLoader;
    private GameFrame gameFrame;
    private JFrame loadingFrame;
    private Thread resourceLoader;
    private Thread timerThread;

    private GameLoader() {
    }

    public static GameLoader getInstance() {
        if (gameLoader == null) {
            gameLoader = new GameLoader();
        }
        return gameLoader;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void start() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        showLoading();
        SwingUtilities.invokeLater(() -> {
            gameFrame = new GameFrame();
            hideLoading();
        });
    }

    private void hideLoading() {
        if (loadingFrame != null) {
            Thread hiderThread = new Thread(() -> {
                try {
                    if (timerThread != null)
                        timerThread.join();
                    if (resourceLoader != null)
                        resourceLoader.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(() -> {
                    gameFrame.expose();
                    loadingFrame.setVisible(false);
                    loadingFrame.dispose();
                    loadingFrame = null;
                });
            });
            hiderThread.start();
        }
    }

    private void showLoading() {
        loadingFrame = new JFrame();
        loadingFrame.setUndecorated(true);
//        loadingFrame.setLocationRelativeTo(null);
        loadingFrame.setLocationByPlatform(true);
        loadingFrame.setPreferredSize(new Dimension(100, 100));
        loadingFrame.setLayout(new BorderLayout());

        JLabel pic = new JLabel("Loading...");
        pic.setFont(new Font("Monospaced", Font.PLAIN, 24));

        resourceLoader = new Thread(() -> {
            ImageIcon icon = ResourcePool.getInstance().getLoadingImage();
            pic.setIcon(icon);
            loadingFrame.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
            loadingFrame.setBackground(new Color(0, 0, 0, 0));
            loadingFrame.pack();
        });
        resourceLoader.start();

        loadingFrame.add(pic, BorderLayout.CENTER);
        loadingFrame.setAlwaysOnTop(true);
        loadingFrame.pack();
        loadingFrame.setVisible(true);

        timerThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerThread.start();
    }
}

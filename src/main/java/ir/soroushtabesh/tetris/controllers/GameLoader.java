package ir.soroushtabesh.tetris.controllers;

import ir.soroushtabesh.tetris.views.GameFrame;
import ir.soroushtabesh.tetris.views.LoaderFrame;

import javax.swing.*;

public class GameLoader {
    private static GameLoader gameLoader;
    private GameFrame gameFrame;
    private LoaderFrame loaderFrame;

    private GameLoader() {
    }

    public static GameLoader getInstance() {
        if (gameLoader == null)
            gameLoader = new GameLoader();
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
        SwingUtilities.invokeLater(() -> {
            showLoading();
            gameFrame = new GameFrame();
            hideLoading();
        });
    }

    private void hideLoading() {
        if (loaderFrame != null)
            loaderFrame.hideLoading(gameFrame);
    }

    private void showLoading() {
        loaderFrame = new LoaderFrame();
        loaderFrame.showLoading();
    }
}

package ir.soroushtabesh.tetris.views;

import ir.soroushtabesh.tetris.controllers.GameController;
import ir.soroushtabesh.tetris.utils.AudioManager;
import ir.soroushtabesh.tetris.utils.Constants;
import ir.soroushtabesh.tetris.views.game.GameOverPanel;
import ir.soroushtabesh.tetris.views.game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    //shall contain no layout so we add panels over each other!
    private GamePanel gamePanel;
    private BackgroundPanel backgroundPanel;
    private MenuPanel menuPanel;
    private GameOverPanel gameOverPanel;
    private GameController gameController;

    public GameFrame() throws HeadlessException {
        super("Tetris (Soroush Tabesh)");
        initFrame();
        initGUI();
        initListeners();
        initControllers();

        setPreferredSize(getSize());
        setMinimumSize(getSize());
        pack();
    }

    private void initControllers() {
        gameController = GameController.getInstance();
    }

    public void showEndOfGame() {
        gameOverPanel.showGameOver();
    }

    private void initListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                componentResized(e);
            }

            @Override
            public void componentResized(ComponentEvent e) {
                menuPanel.setSize(getSize());
                gamePanel.setSize(getSize());
                backgroundPanel.setSize(getSize());
                gameOverPanel.setSize(getSize());
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameController.keyPressed(e);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gameController.saveGame();
            }
        });
    }

    private void initFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new Dimension(Constants.WINDOW_MIN_WIDTH, Constants.WINDOW_MIN_HEIGHT));
        setLayout(null);
    }

    private void initGUI() {
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        backgroundPanel = new BackgroundPanel();
        gameOverPanel = new GameOverPanel();
        add(gameOverPanel);
        add(menuPanel);
        add(gamePanel);
        add(backgroundPanel);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public BackgroundPanel getBackgroundPanel() {
        return backgroundPanel;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public GameOverPanel getGameOverPanel() {
        return gameOverPanel;
    }

    public void expose() {
        setVisible(true);
        AudioManager.startAmbientSound();
        requestFocus();
    }

}

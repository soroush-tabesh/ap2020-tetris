package ir.soroushtabesh.tetris.views;

import ir.soroushtabesh.tetris.utils.Constants;
import ir.soroushtabesh.tetris.views.game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    //shall contain no layout so we add panels over each other!
    private GamePanel gamePanel;
    private BackgroundPanel backgroundPanel;
    private MenuPanel menuPanel;

    public GameFrame() throws HeadlessException {
        super("Tetris (Soroush Tabesh)");
        initFrame();
        initGUI();
        initListeners();
        initControllers();

        pack();
        setPreferredSize(getSize());
        setMinimumSize(getSize());
    }

    private void initControllers() {

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
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //todo: handle keys
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                //todo: save game
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
        //add(menuPanel);
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

    public void expose() {
        setVisible(true);
    }

}

package ir.soroushtabesh.tetris.views;

import ir.soroushtabesh.tetris.views.game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameFrame extends JFrame {
    //shall contain no layout so we add panels over each other!
    private GamePanel gamePanel;
    private BackgroundPanel backgroundPanel;
    private MenuPanel menuPanel;

    public GameFrame() throws HeadlessException {
        super("Tetris (Soroush Tabesh)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
//        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(400, 600));
        setLayout(null);
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        backgroundPanel = new BackgroundPanel();
        add(menuPanel);
        add(gamePanel);
        add(backgroundPanel);
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
    }

    public void expose() {
        setVisible(true);
    }

}

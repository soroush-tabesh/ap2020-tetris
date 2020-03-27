package ir.soroushtabesh.tetris.views;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() throws HeadlessException {
        super("Tetris (Soroush Tabesh)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(200, 300));
    }

    public void expose() {
        setVisible(true);
    }
}

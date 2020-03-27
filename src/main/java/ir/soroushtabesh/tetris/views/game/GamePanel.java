package ir.soroushtabesh.tetris.views.game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    //grid bag centered. allowing BoardPanel reach its maximum size. maintaining boardPanel centered.

    public GamePanel() {
        super();
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
    }
}

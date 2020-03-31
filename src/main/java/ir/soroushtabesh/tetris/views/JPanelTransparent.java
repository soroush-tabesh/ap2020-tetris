package ir.soroushtabesh.tetris.views;

import javax.swing.*;
import java.awt.*;

public class JPanelTransparent extends JPanel {

    @Override
    public void paint(Graphics g) {
        ((Graphics2D) g).setPaint(new RadialGradientPaint(getWidth() / 2f, getHeight() / 2f, getHeight()
                , new float[]{0.1f, 1f}
                , new Color[]{new Color(0, 0, 0, 0.6f), new Color(0, 0, 0, 0.2f)}));
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paint(g);
    }
}

package ir.soroushtabesh.tetris.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BackgroundPanel extends JPanel {

    private static final float HUE_MIN = 0.2f;
    private static final float HUE_MAX = 0.8f;
    private final Timer timer;
    private float hue = HUE_MIN;
    private Color color1 = Color.white;
    private Color color2 = Color.black;
    private float delta = 0.0007f;

    public BackgroundPanel() {
        super();
        setOpaque(true);
        ActionListener action = evt -> {
            hue += delta;
            if (hue > HUE_MAX) {
                hue = HUE_MAX;
                delta = -delta;
            }
            if (hue < HUE_MIN) {
                hue = HUE_MIN;
                delta = -delta;
            }
            color1 = Color.getHSBColor(hue, 1, 1);
            color2 = Color.getHSBColor(hue, 3f / 4 + delta, 3f / 4 + delta);
            repaint();
        };
        timer = new Timer(10, action);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint p = new GradientPaint(
                0, 0, color1, getWidth(), 0, color2);
        g2d.setPaint(p);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

}

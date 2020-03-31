package ir.soroushtabesh.tetris.views;

import ir.soroushtabesh.tetris.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BackgroundPanel extends JPanel {

    private final Timer timer;
    private float hue = 0.4f;
    private Color color1 = Color.white;
    private Color color2 = Color.black;
    private float delta = Constants.BG_DELTA;
    private float theta = 0;

    public BackgroundPanel() {
        super();
        setOpaque(false);
        setFocusable(false);
        timer = new Timer(10, this::onTimerCycle);
        timer.start();
    }

    private void onTimerCycle(ActionEvent evt) {
        hue += delta;
        theta += 15f * delta;
        if (hue > Constants.BG_HUE_MAX) {
            hue = Constants.BG_HUE_MAX;
            delta = -delta;
        }
        if (hue < Constants.BG_HUE_MIN) {
            hue = Constants.BG_HUE_MIN;
            delta = -delta;
        }
        color1 = Color.getHSBColor(hue, 1, 1);
        color2 = Color.getHSBColor(hue, 3f / 4 + delta, 3f / 4 + delta);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint p = new GradientPaint(
                0, (float) (g.getClipBounds().height / 2f * (1 + Math.sin(theta))), color1, getWidth(), 0, color2);
        g2d.setPaint(p);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

}

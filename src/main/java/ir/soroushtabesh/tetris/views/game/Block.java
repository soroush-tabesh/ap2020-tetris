package ir.soroushtabesh.tetris.views.game;

import ir.soroushtabesh.tetris.utils.Constants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Block extends JButton {

    private Color color;

    public Block(String text) {
        super(text);
        setMargin(new Insets(0, 0, 0, 0));
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(Constants.BOARD_BORDER_COLOR);
//        setOpaque(true);
//        setBackground(new Color(0,0,0,0));
        setColor(Constants.COLOR_LIST[0]);
        setFocusPainted(false);
        setFocusable(false);

    }

    @Override
    public Dimension getPreferredSize() {
        Dimension preferredSize = super.getPreferredSize();
        int sz = Math.max(preferredSize.width, preferredSize.height);
        preferredSize = new Dimension(sz, sz);
        return preferredSize;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int roundness = (int) (getHeight() * Constants.BLOCK_ROUNDNESS);

        //draw round dots
        g.setColor(color);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);

        //draw border
        g.setColor(Constants.BOARD_BORDER_COLOR);
        g.drawRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
    }
}

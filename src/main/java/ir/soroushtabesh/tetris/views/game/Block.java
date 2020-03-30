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
        //super.paintComponent(g);

        int roundness = (int) (getHeight() * Constants.BLOCK_ROUNDNESS);
        int dotSize = (int) (getHeight() * Constants.DOT_SIZE_COEFFICIENT);

        if (Constants.DRAW_BORDER) {
            //draw bg
            g.setColor(Constants.BOARD_BORDER_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());

            //draw round base
            g.setColor(color);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);

            //draw border
            g.setColor(Constants.BOARD_BORDER_COLOR);
            g.drawRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
        } else if (Constants.DRAW_DOT) {
            //draw base
            g.setColor(color);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Constants.BOARD_BORDER_COLOR);
            g.fillOval(0, 0, dotSize, dotSize);
            g.fillOval(0, getHeight(), dotSize, dotSize);
            g.fillOval(getWidth(), 0, dotSize, dotSize);
            g.fillOval(getWidth(), getHeight(), dotSize, dotSize);

            if (color != Constants.BOARD_BASE_COLOR) {
                g.setColor(color);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }
}

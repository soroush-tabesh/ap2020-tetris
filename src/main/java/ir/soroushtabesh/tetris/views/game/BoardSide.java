package ir.soroushtabesh.tetris.views.game;

import ir.soroushtabesh.tetris.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class BoardSide extends JPanel {
    public BoardSide() {
        super(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel("Salam"));
        setFocusable(false);
        setBackground(Constants.BOARD_BASE_COLOR);
    }

    @Override
    public Dimension getPreferredSize() {
        Board board = ((BoardPanel) getParent()).getBoard();
        int res = board.getBlock(0, 0).getHeight() * board.getM_height();
        Dimension dimension = super.getPreferredSize();
        dimension.height = res;
        return dimension;
    }
}

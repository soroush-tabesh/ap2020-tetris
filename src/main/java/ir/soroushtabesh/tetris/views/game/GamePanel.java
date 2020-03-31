package ir.soroushtabesh.tetris.views.game;

import ir.soroushtabesh.tetris.utils.Constants;
import ir.soroushtabesh.tetris.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    //grid bag centered. allowing BoardPanel reach its maximum size. maintaining boardPanel centered.
    private BoardPanel boardPanel;

    public GamePanel() {
        super();
        setOpaque(false);
        setFocusable(false);
        initLayout();
        initGUI();
    }

    private void initLayout() {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);
        layout.setVgap(0);
        layout.setHgap(0);
        setLayout(layout);
    }

    private void initGUI() {
        Component board = SwingUtils.wrapInMargin(getBoardPanel(), Constants.GAME_BOARD_MARGIN);
        add(board);
    }

    public BoardPanel getBoardPanel() {
        if (boardPanel == null) {
            boardPanel = new BoardPanel();
        }
        return boardPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

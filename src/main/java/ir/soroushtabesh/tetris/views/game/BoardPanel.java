package ir.soroushtabesh.tetris.views.game;

import ir.soroushtabesh.tetris.utils.Constants;
import ir.soroushtabesh.tetris.views.JPanelAspect;

import javax.swing.border.LineBorder;
import java.awt.*;

public class BoardPanel extends JPanelAspect {

    private Board board;
    private BoardSide boardSide;

    public BoardPanel() {
        initLayout();
        initGUI();
        setFocusable(false);
        setBorder(new LineBorder(Constants.BOARD_BORDER_COLOR, 5, true));
    }

    private void initGUI() {
        add(getBoard(), BorderLayout.CENTER);
        add(getBoardSide(), BorderLayout.EAST);
    }

    public Board getBoard() {
        if (board == null)
            board = new Board();
        return board;
    }

    public BoardSide getBoardSide() {
        if (boardSide == null)
            boardSide = new BoardSide();
        return boardSide;
    }

    private void initLayout() {
        setLayout(new BorderLayout());
    }

}

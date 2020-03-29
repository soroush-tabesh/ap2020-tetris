package ir.soroushtabesh.tetris.views.game;

import ir.soroushtabesh.tetris.views.JPanelAspect;

import java.awt.*;

public class BoardPanel extends JPanelAspect {

    private Board board;
    private BoardSide boardSide;

    public BoardPanel() {
        initLayout();
        initGUI();
    }

    private void initGUI() {
        add(getBoard(), BorderLayout.CENTER);
//        add(getBoardSide(),BorderLayout.LINE_END);
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

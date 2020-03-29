package ir.soroushtabesh.tetris.controllers;

import ir.soroushtabesh.tetris.utils.Constants;

public class GameController {
    // implement timer tick. add an interface for timer assignment
    private static GameController gameController;

    private int boardWidth = Constants.BOARD_MIN_WIDTH;
    private int boardHeight = Constants.BOARD_MIN_HEIGHT;

    private GameController() {
    }

    public static GameController getInstance() {
        if (gameController == null) {
            gameController = new GameController();
        }
        return gameController;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }
}

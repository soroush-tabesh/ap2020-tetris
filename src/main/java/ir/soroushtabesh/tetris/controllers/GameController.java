package ir.soroushtabesh.tetris.controllers;

import ir.soroushtabesh.tetris.models.BlockShape;
import ir.soroushtabesh.tetris.models.GameState;
import ir.soroushtabesh.tetris.models.Vector2D;
import ir.soroushtabesh.tetris.utils.Constants;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    // implement timer tick. add an interface for timer assignment
    private static GameController gameController;

    private int boardWidth = Constants.BOARD_MIN_WIDTH;
    private int boardHeight = Constants.BOARD_MIN_HEIGHT;
    private Timer timer;
    private boolean running = false;
    private GameState gameState;

    private GameController() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (running)
                    onTick();
            }
        }, (long) (Constants.REFRESH_RATE * 1000L));
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (running)
                    onFixedTick();
            }
        }, 0, (long) (Constants.REFRESH_RATE * 1000L));
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

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void initiateGameState() {
        gameState = new GameState(boardWidth, boardHeight);

    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    private void onTick() {
        //todo
    }

    private void onFixedTick() {
        //todo
    }

    public void keyPressed(KeyEvent e) {
        //todo
        new BlockController(gameState, BlockShape.BLOCK_SHAPES[2], new Vector2D(3, 3), 2).initiateBlock();
        GameLoader.getInstance()
                .getGameFrame()
                .getGamePanel()
                .getBoardPanel()
                .getBoard()
                .boardUpdate(gameState);
    }

}

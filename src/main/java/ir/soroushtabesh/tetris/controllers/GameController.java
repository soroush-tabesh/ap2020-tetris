package ir.soroushtabesh.tetris.controllers;

import ir.soroushtabesh.tetris.models.BlockShape;
import ir.soroushtabesh.tetris.models.GameState;
import ir.soroushtabesh.tetris.models.Vector2D;
import ir.soroushtabesh.tetris.utils.Constants;
import ir.soroushtabesh.tetris.utils.ResourcePool;

import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {
    // implement timer tick. add an interface for timer assignment
    private static GameController gameController;
    private final int frameDelay = (int) (Constants.REFRESH_RATE * 1000L);
    private int boardWidth = Constants.BOARD_MIN_WIDTH;
    private int boardHeight = Constants.BOARD_MIN_HEIGHT;
    private Timer timer;
    private boolean running = false;
    private GameState gameState;
    private BlockController blockController;
    private int passedTime = 0;
    private int blockMovePeriod = Constants.BLOCK_MOVE_PERIOD_INITIAL;
    private Random random;
    private int nxtShape;

    private GameController() {
        timer = new Timer();
        random = new Random(System.nanoTime());
        nxtShape = random.nextInt(BlockShape.BLOCK_SHAPES.length - 1) + 1;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (running)
                    onTick();
            }
        }, frameDelay);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (running)
                    onFixedTick();
            }
        }, 0, frameDelay);
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

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void initiateGameState() {
//        gameState = new GameState(boardWidth, boardHeight);
//        spawnBlock();
        gameState = GameState.loadFromFile();
        blockController = gameState.getCurBlockController();
        if (blockController == null) {
            spawnBlock();
        }
    }

    public boolean spawnBlock() {
        blockController = new BlockController(gameState
                , BlockShape.BLOCK_SHAPES[nxtShape]
                , getInitialAnchor(), nxtShape);
        gameState.setCurBlockController(blockController);
        nxtShape = random.nextInt(BlockShape.BLOCK_SHAPES.length - 1) + 1;
        return blockController.initiateBlock();
    }

    private void onTick() {

    }

    private void onFixedTick() {
        if (blockController == null)
            return;
        passedTime += frameDelay;
        if (passedTime >= blockMovePeriod) {
            passedTime = 0;
            if (!blockController.moveBlock(Vector2D.DOWN)) {
                endOfRound();
            }
            updateUI();
        }
    }

    private void decreasePeriod() {
        blockMovePeriod -= Constants.BLOCK_MOVE_PERIOD_DEC;
    }

    private void addScoreAce() {
        gameState.addScore(Constants.SCORE_ACE);
    }

    private void addScoreRound() {
        gameState.addScore(Constants.SCORE_ROUND);
    }

    private void aceCheck() {
        int r;
        while ((r = getFullRow()) >= 0) {
            gameState.addCountHidden(1);
            clearRow(r);
            decreasePeriod();
            addScoreAce();
        }
    }

    private void clearRow(int row) {
        for (int j = 0; j < gameState.getWidth(); j++) {
            gameState.getBlockState(j, 0).setColorNum(0);
        }
        for (int i = row; i > 0; i--) {
            for (int j = 0; j < gameState.getWidth(); j++) {
                gameState.getBlockState(j, i).setColorNum(gameState.getBlockState(j, i - 1).getColorNum());
            }
        }
    }

    private int getFullRow() {
        for (int i = 0; i <= gameState.getHeight(); i++) {
            int cnt = 0;
            for (int j = 0; j < gameState.getWidth(); j++) {
                if (gameState.getBlockState(j, i).getColorNum() > 0)
                    ++cnt;
            }
            if (cnt == gameState.getWidth())
                return i;
        }
        return -1;
    }


    private void endOfRound() {
        aceCheck();
        addScoreRound();
        if (!spawnBlock()) {
            endOfGame();
        }
    }

    private void endOfGame() {
        if (gameState.isGameOver())
            return;
        setRunning(false);
        gameState.setGameOver(true);
        ResourcePool.getInstance().getLeaderBoard().addToList(gameState.getScore());
        GameLoader.getInstance().getGameFrame().showEndOfGame();
    }

    private void updateUI() {
        GameLoader.getInstance()
                .getGameFrame()
                .getGamePanel()
                .getBoardPanel()
                .getBoard()
                .boardUpdate(gameState);
    }

    public void keyPressed(KeyEvent e) {
        if (!isRunning())
            setRunning(true);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                handleKeyUp();
                break;
            case KeyEvent.VK_DOWN:
                handleKeyDown();
                break;
            case KeyEvent.VK_LEFT:
                handleKeyLeft();
                break;
            case KeyEvent.VK_RIGHT:
                handleKeyRight();
                break;
            case KeyEvent.VK_SPACE:
                handleKeySpace();
                break;
            case KeyEvent.VK_ENTER:
                handleKeyEnter();
                break;
        }
        updateUI();
    }

    private void handleKeyDown() {
        if (!blockController.moveBlock(Vector2D.DOWN)) {
            endOfRound();
        }
    }

    private void handleKeyUp() {
        blockController.translateBlock(getInitialAnchor());
    }

    private Vector2D getInitialAnchor() {
        return new Vector2D(getBoardWidth() / 2, 1);
    }

    private void handleKeyLeft() {
        blockController.moveBlock(Vector2D.LEFT);
    }

    private void handleKeyRight() {
        blockController.moveBlock(Vector2D.RIGHT);
    }

    private void handleKeySpace() {
        blockController.rotateCCW();
    }

    private void handleKeyEnter() {
        blockController.rotateCW();
    }

    public void saveGame() {
        gameState.saveGame();
    }
}

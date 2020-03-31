package ir.soroushtabesh.tetris.models;

import ir.soroushtabesh.tetris.controllers.BlockController;
import ir.soroushtabesh.tetris.controllers.GameController;

import java.io.*;

public class GameState implements Serializable {
    private static final long serialVersionUID = -1993659733360268186L;

    private BlockState[][] blockStates;
    private BlockController curBlockController;
    private int score;
    private boolean gameOver;
    private int width;
    private int height;
    private int countHidden;

    public GameState(int width, int height) {
        this.width = width;
        this.height = height;
        this.blockStates = new BlockState[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                blockStates[i][j] = new BlockState();
            }
        }
    }

    public static GameState loadFromFile() {
        GameController gameController = GameController.getInstance();
        GameState gameState = null;
        File file = new File("auto_save.dat");
        if (!file.exists())
            return new GameState(gameController.getBoardWidth(), gameController.getBoardHeight());
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream iis = new ObjectInputStream(fis);
            gameState = (GameState) iis.readObject();
            iis.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameState;
    }

    public BlockController getCurBlockController() {
        return curBlockController;
    }

    public void setCurBlockController(BlockController curBlockController) {
        this.curBlockController = curBlockController;
    }

    public int getCountHidden() {
        return countHidden;
    }

    public void addCountHidden(int change) {
        this.countHidden += change;
    }

    public int getWidth() {
        return width;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getHeight() {
        return height;
    }

    public BlockState getBlockState(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return new BlockState(-1);
        return blockStates[y][x];
    }

    public BlockState getBlockState(Vector2D pos) {
        return getBlockState(pos.getX(), pos.getY());
    }

    public void addScore(int change) {
        score += change;
    }

    public void saveGame() {
        try {
            File file = new File("auto_save.dat");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            if (!isGameOver())
                oos.writeObject(this);
            else
                oos.writeObject(new GameState(GameController.getInstance().getBoardWidth()
                        , GameController.getInstance().getBoardHeight()));
            oos.flush();
            fos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

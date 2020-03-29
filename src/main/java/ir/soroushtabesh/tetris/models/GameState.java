package ir.soroushtabesh.tetris.models;

import java.io.Serializable;

public class GameState implements Serializable {
    private BlockState[][] blockStates;
    private Vector2D curPos;
    private BlockShape curShape;
    private int score;
    private boolean gameOver;

    public GameState(int width, int height) {
        this.blockStates = new BlockState[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                blockStates[i][j] = new BlockState();
            }
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public BlockState getBlockState(int x, int y) {
        return blockStates[y][x];
    }

    public BlockState getBlockState(Vector2D pos) {
        return blockStates[pos.getY()][pos.getX()];
    }
}

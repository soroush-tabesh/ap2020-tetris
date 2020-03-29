package ir.soroushtabesh.tetris.models;

public class Game {
    private BlockState[][] blockStates;

    public Game(int width, int height) {
        this.blockStates = new BlockState[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                blockStates[i][j] = new BlockState();
            }
        }
    }

    public BlockState getBlockState(int x, int y) {
        return blockStates[y][x];
    }
}

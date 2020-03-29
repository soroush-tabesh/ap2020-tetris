package ir.soroushtabesh.tetris.controllers;

import ir.soroushtabesh.tetris.models.BlockShape;
import ir.soroushtabesh.tetris.models.GameState;
import ir.soroushtabesh.tetris.models.Vector2D;

public class BlockController {
    private GameState gameState;
    private BlockShape blockShape;
    private Vector2D anchor;
    private int color;

    public BlockController(GameState gameState, BlockShape blockShape, Vector2D anchor, int color) {
        this.gameState = gameState;
        this.blockShape = blockShape;
        this.anchor = anchor;
        this.color = color;
    }

    private void applyToState() {
        for (Vector2D vector2D : blockShape.getGraph()) {
            gameState.getBlockState(vector2D.add(anchor)).setColorNum(color);
        }
    }

    private void removeFromState() {
        for (Vector2D vector2D : blockShape.getGraph()) {
            gameState.getBlockState(vector2D.add(anchor)).setColorNum(0);
        }
    }

    public void initiateBlock() {
        applyToState();
    }

    public void moveBlock(Vector2D dir) {
        removeFromState();
        anchor = anchor.add(dir);
        applyToState();
    }

    public void destroyBlock() {
        removeFromState();
    }

}

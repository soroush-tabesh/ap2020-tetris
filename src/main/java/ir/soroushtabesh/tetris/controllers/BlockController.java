package ir.soroushtabesh.tetris.controllers;

import ir.soroushtabesh.tetris.models.BlockShape;
import ir.soroushtabesh.tetris.models.GameState;
import ir.soroushtabesh.tetris.models.Vector2D;

import java.io.Serializable;

public class BlockController implements Serializable {
    private static final Vector2D[] ROT_CHECK_POS = new Vector2D[]{
            new Vector2D(0, 0)
            , new Vector2D(-1, 0)
            , new Vector2D(1, 0)
            , new Vector2D(0, -1)
            , new Vector2D(0, -2)
            , new Vector2D(-1, -1)
            , new Vector2D(1, -1)
            , new Vector2D(-2, 0)
            , new Vector2D(2, 0)
    };
    private static final long serialVersionUID = 7717241074433177074L;
    private GameState gameState;
    private BlockShape blockShape;
    private Vector2D anchor;
    private int color;
    private boolean isInitiated;
    private int blockShapeId;

    public BlockController(GameState gameState, int blockShapeId, Vector2D anchor, int color) {
        this.gameState = gameState;
        this.blockShape = BlockShape.BLOCK_SHAPES[blockShapeId].clone();
        this.anchor = anchor;
        this.color = color;
        this.blockShapeId = blockShapeId;
    }

    public int getBlockShapeId() {
        return blockShapeId;
    }

    private void applyToState() {
        if (!isInitiated)
            return;
        for (Vector2D vector2D : blockShape.getGraph()) {
            gameState.getBlockState(anchor.add(vector2D)).setColorNum(color);
        }
    }

    private void removeFromState() {
        if (!isInitiated)
            return;
        for (Vector2D vector2D : blockShape.getGraph()) {
            gameState.getBlockState(anchor.add(vector2D)).setColorNum(0);
        }
    }

    public boolean initiateBlock() {
        if (isInitiated || !checkFreeRoom(anchor))
            return false;
        isInitiated = true;
        applyToState();
        return true;
    }

    public void destroyBlock() {
        if (isInitiated)
            return;
        removeFromState();
        isInitiated = false;
    }

    public boolean moveBlock(Vector2D dir) {
        return translateBlock(anchor.add(dir));
    }

    public void rotateCW() {
        removeFromState();
        blockShape.rotateCW();
        Vector2D newPos = vastCheckFreeRoom(anchor);
        if (newPos == null) {
            blockShape.rotateCCW();
            applyToState();
            return;
        }
        anchor = newPos;
        applyToState();
    }

    public void rotateCCW() {
        removeFromState();
        blockShape.rotateCCW();
        Vector2D newPos = vastCheckFreeRoom(anchor);
        if (newPos == null) {
            blockShape.rotateCW();
            applyToState();
            return;
        }
        anchor = newPos;
        applyToState();
    }

    public boolean translateBlock(Vector2D pos) {
        removeFromState();
        if (!checkFreeRoom(pos)) {
            applyToState();
            return false;
        }
        anchor = pos;
        applyToState();
        return true;
    }

    private boolean checkFreeRoom(Vector2D tarAnchor) {
        boolean res = true;
        for (Vector2D vector2D : blockShape.getGraph()) {
            res &= gameState.getBlockState(tarAnchor.add(vector2D)).getColorNum() == 0;
        }
        return res;
    }

    private Vector2D vastCheckFreeRoom(Vector2D tarAnchor) {
        for (Vector2D vector2D : ROT_CHECK_POS) {
            if (checkFreeRoom(tarAnchor.add(vector2D))) {
                return tarAnchor.add(vector2D);
            }
        }
        return null;
    }
}

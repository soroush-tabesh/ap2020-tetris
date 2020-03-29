package ir.soroushtabesh.tetris.views.game;

import ir.soroushtabesh.tetris.controllers.GameController;
import ir.soroushtabesh.tetris.models.GameState;
import ir.soroushtabesh.tetris.utils.Constants;
import ir.soroushtabesh.tetris.views.JPanelAspect;

import java.awt.*;

public class Board extends JPanelAspect {
    //grid layout containing Blocks

    int width;
    int height;
    private Block[][] blocks;

    public Board() {
        super(new GridLayout(GameController.getInstance().getBoardHeight()
                , GameController.getInstance().getBoardWidth()));
        width = GameController.getInstance().getBoardWidth();
        height = GameController.getInstance().getBoardHeight();
        blocks = new Block[height][width];
        initBlocks();
    }

    private void initBlocks() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; j++) {
                blocks[i][j] = new Block(i + "," + j);
                add(blocks[i][j]);
            }
        }
    }

    public void boardUpdate(GameState gameState) {
        int colorNum, colorListSize = Constants.COLOR_LIST.length;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; j++) {
                colorNum = gameState.getBlockState(j, i).getColorNum();
                blocks[i][j].setColor(Constants.COLOR_LIST[colorNum]);
            }
        }
    }
}

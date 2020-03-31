package ir.soroushtabesh.tetris.views.game;

import ir.soroushtabesh.tetris.controllers.GameController;
import ir.soroushtabesh.tetris.models.GameState;
import ir.soroushtabesh.tetris.utils.Constants;
import ir.soroushtabesh.tetris.views.JPanelAspect;

import java.awt.*;

public class Board extends JPanelAspect {
    //grid layout containing Blocks

    private int m_width;
    private int m_height;
    private Block[][] blocks;

    public Board() {
        super(new GridLayout(GameController.getInstance().getBoardHeight()
                , GameController.getInstance().getBoardWidth()));
        m_width = GameController.getInstance().getBoardWidth();
        m_height = GameController.getInstance().getBoardHeight();
        blocks = new Block[m_height][m_width];
        setFocusable(false);
        initBlocks();
    }

    private void initBlocks() {
        for (int i = 0; i < m_height; ++i) {
            for (int j = 0; j < m_width; j++) {
                blocks[i][j] = new Block(i + "," + j);
                add(blocks[i][j]);
            }
        }
    }

    public void boardUpdate(GameState gameState) {
        int colorNum;
        for (int i = 0; i < m_height; ++i) {
            for (int j = 0; j < m_width; j++) {
                colorNum = gameState.getBlockState(j, i).getColorNum();
                blocks[i][j].setColor(Constants.COLOR_LIST[colorNum]);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Constants.BOARD_BASE_COLOR);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        super.paintComponent(g);
    }
}

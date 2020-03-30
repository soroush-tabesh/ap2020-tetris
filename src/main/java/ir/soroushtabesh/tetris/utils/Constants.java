package ir.soroushtabesh.tetris.utils;

import java.awt.*;

public class Constants {
    public static final int WINDOW_MIN_WIDTH = 400;
    public static final int WINDOW_MIN_HEIGHT = 600;
    public static final Insets GAME_BOARD_MARGIN = new Insets(80, 40, 120, 40);
    public static final float BG_HUE_MIN = 0.2f;
    public static final float BG_HUE_MAX = 0.8f;
    public static final float BG_DELTA = 0.001f;
    public static final int BOARD_MIN_WIDTH = 10;
    public static final int BOARD_MIN_HEIGHT = 18;
    public static final Color[] COLOR_LIST = new Color[]{
            new Color(14, 27, 69)
            , new Color(51, 97, 173)
            , new Color(241, 143, 60)
            , new Color(56, 134, 157)
            , new Color(236, 203, 68)
            , new Color(88, 52, 184)
            , new Color(203, 54, 84)
            , new Color(51, 119, 104)
    };
    public static final Color BOARD_BORDER_COLOR = new Color(100, 60, 120);
    public static final Color BOARD_BASE_COLOR = COLOR_LIST[0];
    public static final float BLOCK_ROUNDNESS = 0.16f;
    public static final float REFRESH_RATE = 0.04f;
    public static final boolean DRAW_BORDER = true;
    public static final boolean DRAW_DOT = false;
    public static final float DOT_SIZE_COEFFICIENT = 0.12f;
    public static final int BLOCK_MOVE_PERIOD_INITIAL = 800;
    public static final int BLOCK_MOVE_PERIOD_DEC = 5;
    public static final int SCORE_ACE = 10;
    public static final int SCORE_ROUND = 1;

}

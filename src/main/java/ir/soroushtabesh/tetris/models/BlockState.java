package ir.soroushtabesh.tetris.models;

import java.io.Serializable;

public class BlockState implements Serializable {
    private static final long serialVersionUID = 5643062165372604292L;
    //zero is transparent
    private int colorNum;

    public BlockState() {
    }

    public BlockState(int colorNum) {
        this.colorNum = colorNum;
    }

    public int getColorNum() {
        return colorNum;
    }

    public void setColorNum(int colorNum) {
        this.colorNum = colorNum;
    }
}

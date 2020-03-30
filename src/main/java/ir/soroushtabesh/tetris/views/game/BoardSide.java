package ir.soroushtabesh.tetris.views.game;

import ir.soroushtabesh.tetris.views.JPanelAspect;

import javax.swing.*;
import java.awt.*;

public class BoardSide extends JPanelAspect {
    public BoardSide() {
        super(new GridBagLayout());
        add(new JLabel("salam"));
        setFocusable(false);

    }

}

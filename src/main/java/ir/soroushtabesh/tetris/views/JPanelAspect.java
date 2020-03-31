package ir.soroushtabesh.tetris.views;

import ir.soroushtabesh.tetris.controllers.GameLoader;

import javax.swing.*;
import java.awt.*;

public class JPanelAspect extends JPanel {
    public JPanelAspect(LayoutManager layout) {
        super(layout);
        setOpaque(false);
    }

    public JPanelAspect() {
        super();
        setOpaque(false);
        setFocusable(false);
    }

    @Override
    public final Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        float aspect = d.width * 1f / d.height;
        Dimension prefSize = new Dimension();
        Component c = GameLoader.getInstance().getGameFrame();
        if (c == null) {
            prefSize = new Dimension(d.width, d.height);
        } else {
            float caspect = 1f * c.getWidth() / c.getHeight();
            if (caspect > aspect) { // parent is wider
                prefSize.height = c.getHeight();
                prefSize.width = (int) (prefSize.height * aspect);
            } else {
                prefSize.width = c.getWidth();
                prefSize.height = (int) (prefSize.width / aspect);
            }
        }
        return prefSize;
    }
}

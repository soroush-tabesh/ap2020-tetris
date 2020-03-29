package ir.soroushtabesh.tetris.utils;

import ir.soroushtabesh.tetris.views.JPanelAspect;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SwingUtils {
    private SwingUtils() {
    }

    public static Component wrapInMargin(Component component, Insets insets) {
        return wrapInMargin(component, insets, new BorderLayout());
    }

    public static Component wrapInMargin(Component component, Insets insets, LayoutManager layoutManager) {
        JPanel jPanel = new JPanelAspect(layoutManager);
        jPanel.setBorder(new EmptyBorder(insets));
        jPanel.add(component);
        return jPanel;
    }
}

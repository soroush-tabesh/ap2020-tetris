package ir.soroushtabesh.tetris.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class ResourcePool {
    private static ResourcePool resourcePool;

    private boolean loaded = false;
    private ImageIcon loadingImage;

    private ResourcePool() {
    }

    public static ResourcePool getInstance() {
        if (resourcePool == null) {
            resourcePool = new ResourcePool();
            resourcePool.load();
        }
        return resourcePool;
    }

    public void load() {
        if (loaded)
            return;
        try {
            loadingImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader()
                    .getResourceAsStream("tetris_logo.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        loaded = true;
    }

    public ImageIcon getLoadingImage() {
        return loadingImage;
    }

    public void dispose() {
        if (!loaded)
            return;

        loaded = false;
    }
}

package ir.soroushtabesh.tetris.views;

import ir.soroushtabesh.tetris.utils.ResourcePool;

import javax.swing.*;
import java.awt.*;

public class LoaderFrame extends JFrame {

    private Thread timerThread;
    private Thread resourceLoader;

    public LoaderFrame() {
        super();
        setUndecorated(true);
        setLocationByPlatform(true);
        setPreferredSize(new Dimension(100, 100));
        setLayout(new BorderLayout());
        setFocusable(false);

        JLabel pic = new JLabel("Loading...");
        pic.setFont(new Font("Monospaced", Font.PLAIN, 24));

        resourceLoader = new Thread(() -> {
            ImageIcon icon = ResourcePool.getInstance().getLoadingImage();
            pic.setIcon(icon);
            setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
            setBackground(new Color(0, 0, 0, 0));
            pack();
        });
        resourceLoader.start();
        add(pic, BorderLayout.CENTER);
        setAlwaysOnTop(true);
        pack();
    }

    public void showLoading() {
        setVisible(true);
        timerThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerThread.start();
        getBoardSize();
    }

    private void getBoardSize() {
        //todo
    }

    public void hideLoading(GameFrame toLoad) {
        Thread hiderThread = new Thread(() -> {
            try {
                if (timerThread != null)
                    timerThread.join();
                if (resourceLoader != null)
                    resourceLoader.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> {
                if (toLoad != null)
                    toLoad.expose();
                setVisible(false);
                dispose();
            });
        });
        hiderThread.start();
    }
}

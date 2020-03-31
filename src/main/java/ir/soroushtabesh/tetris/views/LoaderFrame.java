package ir.soroushtabesh.tetris.views;

import ir.soroushtabesh.tetris.controllers.GameController;
import ir.soroushtabesh.tetris.utils.ResourcePool;

import javax.swing.*;
import java.awt.*;

public class LoaderFrame extends JFrame {

    private Thread timerThread;
    private Thread resourceLoader;
    private Thread inputThread;

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
        getBoardSize();
        timerThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerThread.start();
    }

    private void getBoardSize() {
        String width = "0";
        while (width == null || Integer.parseInt(width) < 6 || Integer.parseInt(width) > 20)
            System.out.println((width = JOptionPane.showInputDialog(
                    "Enter width: (Integer between 6 and 20, 10 is good!)"
                    , "10")));
        String height = "0";
        while (height == null || Integer.parseInt(height) < 15 || Integer
                .parseInt(height) > 28)
            System.out.println((height = JOptionPane.showInputDialog(
                    "Enter height: (Integer between 15 and 28, 18 is good!)",
                    "18")));
        GameController gameController = GameController.getInstance();
        gameController.setBoardWidth(Integer.parseInt(width));
        gameController.setBoardHeight(Integer.parseInt(height));
    }

    public void hideLoading(GameFrame toLoad) {
        Thread hiderThread = new Thread(() -> {
            try {
                if (timerThread != null)
                    timerThread.join();
                if (resourceLoader != null)
                    resourceLoader.join();
                if (inputThread != null)
                    inputThread.join();
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

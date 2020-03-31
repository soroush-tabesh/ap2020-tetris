package ir.soroushtabesh.tetris.views.game;

import ir.soroushtabesh.tetris.controllers.GameController;
import ir.soroushtabesh.tetris.controllers.GameLoader;
import ir.soroushtabesh.tetris.views.JPanelTransparent;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanelTransparent {
    private JLabel text;

    public GameOverPanel() {
        super();
        setVisible(false);
        setOpaque(false);
        setFocusable(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        text = new JLabel("Good Job!");
        text.setHorizontalTextPosition(JLabel.CENTER);
        add(text, gbc);

        JButton button = new JButton("Main Menu");

        add(button, gbc);
        button.addActionListener(e -> {
            closeGameOver();
            GameLoader.getInstance().getGameFrame().getMenuPanel().showMenu();
        });

        button.setFocusable(false);
    }

    public void showGameOver() {
        text.setText(String.format("<html>" +
                        "<center>" +
                        "<font color =\"rgb(137,98,47)\" size=\"6\">" +
                        "Good Job!" +
                        "</font>" +
                        "<br>" +
                        "<font color =\"white\" >" +
                        "Score: %d" +
                        "</font>" +
                        "</center>" +
                        "</html>"
                , GameController.getInstance().getGameState().getScore()));
        setVisible(true);
        setEnabled(true);
    }

    private void closeGameOver() {
        setVisible(false);
        setEnabled(false);
    }

}

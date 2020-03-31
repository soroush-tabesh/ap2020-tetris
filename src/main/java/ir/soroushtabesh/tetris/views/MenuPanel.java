package ir.soroushtabesh.tetris.views;

import ir.soroushtabesh.tetris.controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanelTransparent {

    public MenuPanel() {
        super();
        setOpaque(false);
        setFocusable(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton btn_continue = new JButton("Continue");
        JButton btn_newGame = new JButton("New Game");
        JButton btn_help = new JButton("Help");

        btn_continue.setMargin(new Insets(10, 10, 10, 10));
        btn_newGame.setMargin(new Insets(10, 10, 10, 10));
        btn_help.setMargin(new Insets(10, 10, 10, 10));

        btn_continue.setFocusable(false);
        btn_newGame.setFocusable(false);
        btn_help.setFocusable(false);

        btn_continue.addActionListener(this::onContinue);
        btn_newGame.addActionListener(this::onNewGame);
        btn_help.addActionListener(this::showHelp);

        add(btn_continue, gbc);
        add(btn_newGame, gbc);
        add(btn_help, gbc);
    }

    private void showHelp(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, "- Arrow Right and Left for rotations\n" +
                "- Arrow Down for speeding up\n" +
                "- Arrow Up for the Regret ability\n" +
                "- Space and Enter keys are for rotations");
    }

    private void onNewGame(ActionEvent evt) {
        hideMenu();
        GameController.getInstance().initiateGameState(false);
    }

    private void onContinue(ActionEvent evt) {
        hideMenu();
        GameController.getInstance().initiateGameState(true);
    }

    public void hideMenu() {
        setVisible(false);
    }

    public void showMenu() {
        setVisible(true);
    }

}

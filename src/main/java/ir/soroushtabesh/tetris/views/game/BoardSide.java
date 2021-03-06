package ir.soroushtabesh.tetris.views.game;

import ir.soroushtabesh.tetris.controllers.GameController;
import ir.soroushtabesh.tetris.models.GameState;
import ir.soroushtabesh.tetris.models.LeaderBoard;
import ir.soroushtabesh.tetris.utils.Constants;
import ir.soroushtabesh.tetris.utils.ResourcePool;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BoardSide extends JPanel {

    private JLabel score;
    private JLabel hidden;
    private JLabel nxtBlock;
    private JLabel leaderLabel;

    public BoardSide() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setFocusable(false);
        setBackground(Constants.BOARD_BASE_COLOR);
        setBorder(new LineBorder(Constants.BOARD_BORDER_COLOR, 2, false));

        initGUI();
    }

    private void initGUI() {
        score = new JLabel("", JLabel.LEADING);
        hidden = new JLabel("", JLabel.LEADING);
        nxtBlock = new JLabel("", JLabel.CENTER);
        leaderLabel = new JLabel("", JLabel.LEADING);

        score.setForeground(Color.WHITE);
        hidden.setForeground(Color.LIGHT_GRAY);
        nxtBlock.setForeground(Color.LIGHT_GRAY);
        leaderLabel.setForeground(Color.LIGHT_GRAY);

        score.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        hidden.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        nxtBlock.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        leaderLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        setScore(0);
        setHidden(0);
        setNxtBlock(0);
        updateLeaderBoard(ResourcePool.getInstance().getLeaderBoard());

        add(score);
        add(hidden);
        add(leaderLabel);
        add(nxtBlock);
        setMinimumSize(getSize());
    }

    public void updateSidePanel(GameState gameState) {
        setScore(gameState.getScore());
        setHidden(gameState.getCountHidden());
        setNxtBlock(GameController.getInstance().getNxtShape());
        updateLeaderBoard(ResourcePool.getInstance().getLeaderBoard());
        repaint();
    }

    private void setScore(int num) {
        score.setText("☆ " + num);
    }

    private void setHidden(int num) {
        hidden.setText("Hidden Lines: " + num);
    }

    private void setNxtBlock(int id) {
        nxtBlock.setText("<html>Next Block:<br></html>");
        nxtBlock.setIcon(ResourcePool.getInstance().getBlockIcon(id));
        nxtBlock.setVerticalTextPosition(JLabel.TOP);
        nxtBlock.setHorizontalTextPosition(JLabel.CENTER);
    }

    private void updateLeaderBoard(LeaderBoard leaderBoard) {
        StringBuilder message = new StringBuilder("<html>Leader Board<br>");
        for (int i = 0; i < leaderBoard.getList().length - 1; i++) {
            String[] colors = {"rgb(230,191,67)", "rgb(230,230,230)", "rgb(211,127,56)", "rgb(238,224,174)"};
            message.append(String.format("%d. <font color='%s'>%d</font><br>"
                    , i + 1
                    , colors[Math.min(i, 3)]
                    , leaderBoard.getList()[i]));
        }
        message.append("</html>");
        leaderLabel.setText(message.toString());
    }

}

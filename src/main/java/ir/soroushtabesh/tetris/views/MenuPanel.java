package ir.soroushtabesh.tetris.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class MenuPanel extends JPanel {

    private BufferedImage buffer;
    private long start;
    private float alpha = 1.0f;
    private boolean isFading = false;

    public MenuPanel() {
        super();
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton btn_continue = new JButton("Continue");
        JButton btn_newgame = new JButton("New Game");

        btn_continue.setMargin(new Insets(10, 10, 10, 10));
        btn_newgame.setMargin(new Insets(10, 10, 10, 10));

        btn_continue.addActionListener(this::onContinue);
        btn_newgame.addActionListener(this::onNewGame);

        btn_continue.setOpaque(false);
        btn_continue.setContentAreaFilled(false);
        btn_continue.setBackground(new Color(1, 1, 0, 0.2f));

        add(btn_continue, gbc);
        add(btn_newgame, gbc);
    }

    private void onNewGame(ActionEvent evt) {
        hideMenu();
    }

    private void onContinue(ActionEvent evt) {
        hideMenu();
    }

    public void hideMenu() {
        fade(0.5, false);

    }

    public void showMenu() {
        fade(0.5, true);
    }

    @Override
    public void paint(Graphics g) {
        if (isFading) {
            ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g.drawImage(buffer, 0, 0, this);
        } else {
            ((Graphics2D) g).setPaint(new RadialGradientPaint(getWidth() / 2f, getHeight() / 2f, getHeight()
                    , new float[]{0.1f, 0.6f}
                    , new Color[]{new Color(0, 0, 0, 0.6f), new Color(0, 0, 0, 0.2f)}));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        super.paint(g);
    }

    public void fade(double time, boolean fadein) {
        start = System.currentTimeMillis();
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.print(buffer.getGraphics());

        setEnabled(fadein);
        isFading = true;
        final double timeInMillis = time * 1000;
        final Timer t = new Timer(50, null);
        t.addActionListener(e -> {
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed > timeInMillis) {
                start = 0;
                buffer = null;
                t.stop();
                alpha = (fadein ? 0 : 1);
                setVisible(fadein);
                isFading = false;
            } else {
                alpha = (float) Math.cos(elapsed / timeInMillis * Math.PI / 2 * (fadein ? -1 : 1));
            }
            repaint();
        });
        t.start();
    }

}

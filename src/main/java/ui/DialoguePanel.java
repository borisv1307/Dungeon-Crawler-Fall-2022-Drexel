package ui;

import engine.GameEngine;

import java.awt.*;

public class DialoguePanel extends Panel {
    private GameEngine gameEngine;
    private Image dbImage;

    public DialoguePanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.add(new Button("Choice One"));
        this.add(new Button("Choice Two"));
        this.add(new Button("Choice Three"));
        repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paintComponents(graphics);
        requestFocusInWindow();
    }

    @Override
    public void update(Graphics graphics) {
        if (dbImage == null) {
            dbImage = createImage(getWidth(), getHeight());
        }
        Graphics dbg = dbImage.getGraphics();
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, getWidth(), getHeight());
        dbg.setColor(getForeground());
        paint(dbg);
        graphics.drawImage(dbImage, 0, 0, this);
    }
}

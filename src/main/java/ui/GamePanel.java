package ui;

import engine.GameEngine;
import tiles.TileType;

import java.awt.*;

public class GamePanel extends Panel {

    private static final long serialVersionUID = 1L;
    private final GameEngine gameEngine;
    private final TilePainter tilePainter;
    private Image dbImage;
    private int tileWidth;
    private int tileHeight;

    public GamePanel(GameEngine gameEngine, TilePainter tilePainter) {
        this.gameEngine = gameEngine;
        this.tilePainter = tilePainter;
        repaint();
    }

    void init() {
        tileWidth = this.getWidth() / gameEngine.getLevelHorizontalDimension();
        tileHeight = this.getHeight() / gameEngine.getLevelVerticalDimension();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        requestFocusInWindow();
        tilePainter.paintTiles(graphics, gameEngine, tileWidth, tileHeight);
        tilePainter.paintPlayer(graphics, gameEngine.getPlayerXCoordinate(), gameEngine.getPlayerYCoordinate(),
                tileWidth, tileHeight, TileType.PLAYER);
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

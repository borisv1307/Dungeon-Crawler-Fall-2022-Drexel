package ui;

import engine.GameEngine;
import tiles.TileType;
import values.TileColorMap;

import java.awt.*;

public class TilePainter {

    void paintTiles(Graphics graphics, GameEngine game, int tileWidth, int tileHeight) {
        for (int x = 0; x < game.getLevelHorizontalDimension(); x++) {
            for (int y = 0; y < game.getLevelVerticalDimension(); y++) {
                TileType tileType = game.getTileFromCoordinates(x, y);
                paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
            }
        }
    }

    void paintPlayer(int hitpoints, Graphics graphics, int x, int y, int tileWidth, int tileHeight) {
        graphics.setColor(Color.white);
        graphics.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
        graphics.setColor(Color.green);
        graphics.fillRect(x * tileWidth, y * tileHeight + 5 * (10 - hitpoints) + 5, tileWidth, tileHeight - 5 * (10 - hitpoints) - 5);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.blue);
        g2d.drawRect(x * tileWidth + 2, y * tileHeight + 2, tileWidth - 5, tileHeight - 5);

        graphics.setColor(Color.black);
        graphics.drawString(hitpoints + "", x * tileWidth + 5, y * tileHeight + 15);
    }

    private void paintTile(Graphics graphics, int tileWidth, int tileHeight, int x, int y, TileType tileType) {
        handleTile(graphics, tileType);
        graphics.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }

    private void handleTile(Graphics graphics, TileType tileType) {
        graphics.setColor(TileColorMap.get(tileType));
    }

}
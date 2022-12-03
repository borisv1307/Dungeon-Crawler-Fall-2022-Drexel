package ui;

import engine.GameEngine;
import projectile.Projectile;
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

    void paintPlayer(Graphics graphics, int x, int y, int tileWidth, int tileHeight, TileType tileType) {
        paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
    }

    void paintProjectiles(Graphics graphics, int tileWidth, int tileHeight, GameEngine game) {
        int x;
        int y;
        graphics.setColor(TileColorMap.projectileColor());
        for (Projectile i : game.getProjectiles()) {
            x = i.getX();
            y = i.getY();
            int diff = tileHeight - tileWidth;
            graphics.fillOval(x * tileWidth, y * tileHeight + (diff / 2), tileWidth, tileWidth);
        }
    }

    private void paintTile(Graphics graphics, int tileWidth, int tileHeight, int x, int y, TileType tileType) {
        handleTile(graphics, tileType);
        graphics.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }

    private void handleTile(Graphics graphics, TileType tileType) {
        graphics.setColor(TileColorMap.get(tileType));
    }

}

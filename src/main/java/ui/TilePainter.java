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

    void paintPlayer(Graphics graphics, int x, int y, int tileWidth, int tileHeight, TileType tileType, int currentHitPoints, int maxHitPoints, boolean regenStatus, boolean drainStatus) {
        paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
        paintLostPlayerHitPoints(graphics, tileWidth, tileHeight, x, y, currentHitPoints, maxHitPoints);
        paintPlayerStatus(graphics, tileWidth, tileHeight, x, y, currentHitPoints, regenStatus, drainStatus);
        if (currentHitPoints == 0) {
            paintPlayerGravestone(graphics, tileWidth, tileHeight, x, y);
        }
    }

    private void paintPlayerGravestone(Graphics graphics, int tileWidth, int tileHeight, int x, int y) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
        graphics.setColor(Color.BLACK);
        graphics.drawString("RIP", x * tileWidth + 10, y * tileHeight + 20);
        graphics.drawString("Press 'Enter' to reset", x * tileWidth, y * tileHeight + 40);
    }

    private void paintPlayerStatus(Graphics graphics, int tileWidth, int tileHeight, int x, int y, int currentHitPoints, boolean regenStatus, boolean drainStatus) {
        graphics.setColor(Color.BLACK);
        graphics.drawString("HP:" + currentHitPoints, x * tileWidth, y * tileHeight + 10);
        if (regenStatus) {
            graphics.drawString("Regen", x * tileWidth, y * tileHeight + 20);
        }
        if (drainStatus) {
            graphics.drawString("Drain", x * tileWidth, y * tileHeight + 30);
        }
    }

    private void paintLostPlayerHitPoints(Graphics graphics, int tileWidth, int tileHeight, int x, int y, int currentHitPoints, int maxHitPoints) {
        double lostHitPointsPercentage = (maxHitPoints - currentHitPoints) / ((double) maxHitPoints);
        graphics.setColor(Color.RED);
        graphics.fillRect(x * tileWidth, y * tileHeight, tileWidth, (int) (tileHeight * lostHitPointsPercentage));

    }

    private void paintTile(Graphics graphics, int tileWidth, int tileHeight, int x, int y, TileType tileType) {
        handleTile(graphics, tileType);
        graphics.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }

    private void handleTile(Graphics graphics, TileType tileType) {
        graphics.setColor(TileColorMap.get(tileType));
    }

}

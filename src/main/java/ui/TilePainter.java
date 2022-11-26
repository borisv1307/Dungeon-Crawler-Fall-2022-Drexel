package ui;

import java.awt.*;

import engine.GameEngine;
import tiles.TileType;
import values.TileColorMap;

public class TilePainter {

	void paintTiles(Graphics graphics, GameEngine game, int tileWidth, int tileHeight) {
		for (int x = 0; x < game.getLevelHorizontalDimension(); x++) {
			for (int y = 0; y < game.getLevelVerticalDimension(); y++) {
				TileType tileType = game.getTileFromCoordinates(x, y);
				if (tileType == TileType.PROJECTILE) {
					this.advanceProjectile(game, x, y);
				}
				paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
			}
		}
	}

	void paintPlayer(Graphics graphics, int x, int y, int tileWidth, int tileHeight, TileType tileType) {
		paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
	}

	void advanceProjectile(GameEngine gameEngine, int x, int y) {
		int newY = y - 1;
		TileType nextTile = gameEngine.getTileFromCoordinates(x, newY);
		if (nextTile == TileType.PASSABLE) {
			gameEngine.addTile(x, y, TileType.PASSABLE);
			gameEngine.addTile(x, newY, TileType.PROJECTILE);
		} else if (nextTile == TileType.NOT_PASSABLE) {
			gameEngine.addTile(x, y, TileType.PASSABLE);
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

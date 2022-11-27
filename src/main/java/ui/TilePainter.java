package ui;

import java.awt.*;

import engine.GameEngine;
import tiles.TileType;
import values.TileColorMap;
import values.TunableParameters;
import wrappers.RandomWrapper;

public class TilePainter {

	private RandomWrapper randomWrapper;

	public void setRandomWrapper(RandomWrapper randomWrapper) {
		this.randomWrapper = randomWrapper;
	}

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

		this.addRandomEnemies(game);
	}

	void paintPlayer(Graphics graphics, int x, int y, int tileWidth, int tileHeight, TileType tileType) {
		paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
	}

	void advanceProjectile(GameEngine gameEngine, int x, int y) {
		int newY = y - 1;
		TileType nextTile = gameEngine.getTileFromCoordinates(x, newY);
		if (nextTile == TileType.PASSABLE || nextTile == TileType.PROJECTILE) {
			gameEngine.addTile(x, y, TileType.PASSABLE);
			gameEngine.addTile(x, newY, TileType.PROJECTILE);
		} else if (nextTile == TileType.NOT_PASSABLE) {
			gameEngine.addTile(x, y, TileType.PASSABLE);
		}
	}

	void addRandomEnemies(GameEngine game) {
		int lowerBound = TunableParameters.RANDOM_ENEMY_SPAWN_LOWER_BOUND;
		int upperBoundHorizontal = game.getLevelHorizontalDimension()
				- TunableParameters.HORIZONTAL_RANDOM_ENEMY_SPAWN_OFFSET;
		int upperBoundVertical = game.getLevelVerticalDimension()
				- TunableParameters.VERTICAL_RANDOM_ENEMY_SPAWN_OFFSET;

		int x = randomWrapper.getRandomNumberInRange(lowerBound, upperBoundHorizontal);
		int y = randomWrapper.getRandomNumberInRange(lowerBound, upperBoundVertical);

		int playerX = game.getPlayerXCoordinate();
		int playerY = game.getPlayerYCoordinate();

		double d = Math.sqrt(Math.pow((x - playerX), 2) + Math.pow((y - playerY), 2));

		TileType currentTile = game.getTileFromCoordinates(x, y);
		if (currentTile == TileType.PASSABLE && d > TunableParameters.DISTANCE_ALLOWED_FROM_PLAYER) {
			game.addTile(x, y, TileType.ENEMY);
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

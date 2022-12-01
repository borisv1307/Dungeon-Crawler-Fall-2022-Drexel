package ui;

import java.awt.*;

import engine.GameEngine;
import tiles.ProjectileHandler;
import tiles.TileType;
import values.TileColorMap;
import values.TunableParameters;
import wrappers.RandomWrapper;

public class TilePainter {

	public ProjectileHandler projectileHandler = new ProjectileHandler();
	private RandomWrapper randomWrapper;
	private int enemyCountDown = 0;

	public void setRandomWrapper(RandomWrapper randomWrapper) {
		this.randomWrapper = randomWrapper;
	}

	public int getEnemyCountDown() {
		return enemyCountDown;
	}

	public void incrementEnemySpawnCountDown() {
		enemyCountDown++;
	}

	public void resetEnemySpawnCountDown() {
		enemyCountDown = 0;
	}

	void paintTiles(Graphics graphics, GameEngine game, int tileWidth, int tileHeight) {
		for (int x = 0; x < game.getLevelHorizontalDimension(); x++) {
			for (int y = 0; y < game.getLevelVerticalDimension(); y++) {
				TileType tileType = game.getTileFromCoordinates(x, y);
				if (tileType == TileType.PROJECTILE) {
					projectileHandler.advanceProjectile(game, x, y);
				}
				paintTile(graphics, tileWidth, tileHeight, x, y, tileType, game);
			}
		}
		projectileHandler.moveEnemyProjectiles(game);
		paintEnemies(game);
	}

	void paintPlayer(Graphics graphics, int x, int y, int tileWidth, int tileHeight, TileType tileType,
			GameEngine gameEngine) {
		paintTile(graphics, tileWidth, tileHeight, x, y, tileType, gameEngine);
	}

	void addRandomEnemy(GameEngine game) {
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

	void handleTile(Graphics graphics, TileType tileType, GameEngine gameEngine) {
		if (tileType == TileType.PLAYER) {
			graphics.setColor(TileColorMap.getPlayerColor(gameEngine.getScorePanel().getPlayerLives()));
		} else {
			graphics.setColor(TileColorMap.get(tileType));
		}
	}

	private void paintTile(Graphics graphics, int tileWidth, int tileHeight, int x, int y, TileType tileType,
			GameEngine gameEngine) {
		handleTile(graphics, tileType, gameEngine);
		graphics.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
	}

	private void paintEnemies(GameEngine game) {
		if (!game.isLost()) {
			if (enemyCountDown == TunableParameters.ENEMY_SPAWN_EVERY_N_FRAMES) {
				addRandomEnemy(game);
				resetEnemySpawnCountDown();
			} else {
				incrementEnemySpawnCountDown();
			}
		}
	}
}

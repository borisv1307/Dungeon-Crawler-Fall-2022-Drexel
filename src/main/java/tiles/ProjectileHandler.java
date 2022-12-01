package tiles;

import engine.GameEngine;
import values.TunableParameters;

public class ProjectileHandler {
	private int enemyFireCountDown = 0;

	public void incrementEnemyFireCountDown() {
		enemyFireCountDown++;
	}

	public void resetEnemyFireCountDown() {
		enemyFireCountDown = 0;
	}

	public void moveEnemyProjectiles(GameEngine game) {
		for (int y = game.getLevelVerticalDimension(); y >= 0; y--) {
			for (int x = game.getLevelHorizontalDimension(); x >= 0; x--) {
				TileType tileType = game.getTileFromCoordinates(x, y);
				if (tileType == TileType.ENEMY_PROJECTILE) {
					advanceEnemyProjectile(game, x, y);
				}

				if (tileType == TileType.ENEMY) {
					enemyFire(game, x, y);
				}
			}
		}
	}

	public void advanceEnemyProjectile(GameEngine gameEngine, int x, int y) {
		int newY = y + 1;
		TileType nextTile = gameEngine.getTileFromCoordinates(x, newY);
		if (x == gameEngine.getPlayerXCoordinate() && newY == gameEngine.getPlayerYCoordinate()) {
			gameEngine.addTile(x, y, TileType.PASSABLE);
			gameEngine.decrementLives();
		} else if (nextTile == TileType.PASSABLE || nextTile == TileType.PROJECTILE
				|| nextTile == TileType.ENEMY_PROJECTILE) {
			gameEngine.addTile(x, y, TileType.PASSABLE);
			gameEngine.addTile(x, newY, TileType.ENEMY_PROJECTILE);
		} else if (nextTile == TileType.NOT_PASSABLE || nextTile == TileType.ENEMY) {
			gameEngine.addTile(x, y, TileType.PASSABLE);
		}
	}

	public void advanceProjectile(GameEngine gameEngine, int x, int y) {
		int newY = y - 1;
		TileType nextTile = gameEngine.getTileFromCoordinates(x, newY);
		if (nextTile == TileType.PASSABLE || nextTile == TileType.PROJECTILE || nextTile == TileType.ENEMY_PROJECTILE) {
			gameEngine.addTile(x, y, TileType.PASSABLE);
			gameEngine.addTile(x, newY, TileType.PROJECTILE);
		} else if (nextTile == TileType.NOT_PASSABLE) {
			gameEngine.addTile(x, y, TileType.PASSABLE);
		} else if (nextTile == TileType.ENEMY) {
			gameEngine.addTile(x, newY, TileType.PASSABLE);
			gameEngine.addTile(x, y, TileType.PASSABLE);
			gameEngine.incrementScore();
		}
	}

	public void enemyFire(GameEngine game, int x, int y) {
		if (!game.isLost()) {
			if (enemyFireCountDown == TunableParameters.ENEMY_FIRE_EVERY_N_FRAMES) {
				game.addTile(x, y + 1, TileType.ENEMY_PROJECTILE);
				resetEnemyFireCountDown();
			} else {
				incrementEnemyFireCountDown();
			}
		}
	}
}

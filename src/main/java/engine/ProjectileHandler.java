package engine;

import tiles.TileType;
import wrappers.RandomWrapper;

public class ProjectileHandler {

	RandomWrapper randomWrapper;
	GameEngine gameEngine;

	public ProjectileHandler(GameEngine gameEngine, RandomWrapper randomWrapper) {
		this.gameEngine = gameEngine;
		this.randomWrapper = randomWrapper;
	}

	public void createLeftEdgeProjectile() {
		int maxY = gameEngine.getLevelVerticalDimension() - 2;
		int yValue = randomWrapper.nextInt(maxY) + 1;
		gameEngine.addTile(0, yValue, TileType.PROJECTILE);
	}

	public void createRightEdgeProjectile() {
		int maxY = gameEngine.getLevelVerticalDimension() - 2;
		int yValue = randomWrapper.nextInt(maxY) + 1;
		gameEngine.addTile(gameEngine.getLevelHorizontalDimension(), yValue, TileType.PROJECTILE);
	}

	public void createTopEdgeProjectile() {
		int maxX = gameEngine.getLevelHorizontalDimension() - 2;
		int xValue = randomWrapper.nextInt(maxX) + 1;
		gameEngine.addTile(xValue, 0, TileType.PROJECTILE);
	}

	public void createBottomEdgeProjectile() {
		int maxX = gameEngine.getLevelHorizontalDimension() - 2;
		int xValue = randomWrapper.nextInt(maxX) + 1;
		gameEngine.addTile(xValue, gameEngine.getLevelVerticalDimension(), TileType.PROJECTILE);
	}
}

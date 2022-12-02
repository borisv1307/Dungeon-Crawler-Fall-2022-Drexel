package engine;

import tiles.TileType;
import wrappers.RandomWrapper;

public class ProjectileHandler {

	RandomWrapper randomWrapper;
	GameEngine gameEngine;
	private int maxX;
	private int maxY;

	public ProjectileHandler(GameEngine gameEngine, RandomWrapper randomWrapper) {
		this.gameEngine = gameEngine;
		this.randomWrapper = randomWrapper;
	}

	int getYValue() {
		maxY = gameEngine.getLevelVerticalDimension() - 2;
		return randomWrapper.nextInt(maxY) + 1;
	}

	int getXValue() {
		maxX = gameEngine.getLevelHorizontalDimension() - 2;
		return randomWrapper.nextInt(maxX) + 1;
	}

	public void createLeftEdgeProjectile() {
		int yValue = getYValue();
		gameEngine.addTile(0, yValue, TileType.PROJECTILE);
	}

	public void createRightEdgeProjectile() {
		int yValue = getYValue();
		gameEngine.addTile(gameEngine.getLevelHorizontalDimension(), yValue, TileType.PROJECTILE);
	}

	public void createTopEdgeProjectile() {
		int xValue = getXValue();
		gameEngine.addTile(xValue, 0, TileType.PROJECTILE);
	}

	public void createBottomEdgeProjectile() {
		int xValue = getXValue();
		gameEngine.addTile(xValue, gameEngine.getLevelVerticalDimension(), TileType.PROJECTILE);
	}
}

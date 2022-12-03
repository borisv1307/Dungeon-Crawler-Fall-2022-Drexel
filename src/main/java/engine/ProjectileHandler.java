package engine;

import tiles.TileType;
import wrappers.RandomWrapper;

public class ProjectileHandler {

	RandomWrapper randomWrapper;
	GameEngine gameEngine;
	private int frameTimer = 0;
	private int projectileTimer = 0;

	public ProjectileHandler(GameEngine gameEngine, RandomWrapper randomWrapper) {
		this.gameEngine = gameEngine;
		this.randomWrapper = randomWrapper;
	}

	int getYValue() {
		int maxY = gameEngine.getLevelVerticalDimension() - 2;
		return randomWrapper.nextInt(maxY) + 1;
	}

	int getXValue() {
		int maxX = gameEngine.getLevelHorizontalDimension() - 2;
		return randomWrapper.nextInt(maxX) + 1;
	}

	public void createLeftEdgeProjectile() {
		int yValue = getYValue();
		gameEngine.addTile(0, yValue, TileType.PROJECTILE);
	}

	public void createRightEdgeProjectile() {
		int yValue = getYValue();
		gameEngine.addTile(gameEngine.getLevelHorizontalDimension() - 1, yValue, TileType.PROJECTILE);
	}

	public void createTopEdgeProjectile() {
		int xValue = getXValue();
		gameEngine.addTile(xValue, 0, TileType.PROJECTILE);
	}

	public void createBottomEdgeProjectile() {
		int xValue = getXValue();
		gameEngine.addTile(xValue, gameEngine.getLevelVerticalDimension() - 1, TileType.PROJECTILE);
	}

//	void handleProjectiles() {
//		createRightEdgeProjectile();
//		createBottomEdgeProjectile();
//		createTopEdgeProjectile();
//		createLeftEdgeProjectile();
////		projectileTimer++;
//
//	}

	public void runProjectileTimer() {
//		frameTimer++;
//		if (frameTimer % 45 == 0) {
//			handleProjectiles();
//			frameTimer = 0;
//		}
	}
}

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
		gameEngine.addTile(0, yValue, TileType.PROJECTILE, "right");
	}

	public void createRightEdgeProjectile() {
		int yValue = getYValue();
		gameEngine.addTile(gameEngine.getLevelHorizontalDimension() - 1, yValue, TileType.PROJECTILE, "left");
	}

	public void createTopEdgeProjectile() {
		int xValue = getXValue();
		gameEngine.addTile(xValue, 0, TileType.PROJECTILE, "down");
	}

	public void createBottomEdgeProjectile() {
		int xValue = getXValue();
		gameEngine.addTile(xValue, gameEngine.getLevelVerticalDimension() - 1, TileType.PROJECTILE, "up");
	}

	public void createProjectiles() {
		createRightEdgeProjectile();
		createBottomEdgeProjectile();
		createTopEdgeProjectile();
		createLeftEdgeProjectile();
	}

	public void moveProjectiles() {
		gameEngine.moveProjectileUp();
		gameEngine.moveProjectileLeft();
		gameEngine.moveProjectileDown();
		gameEngine.moveProjectileRight();
	}

	public void runProjectileTimer() {
		frameTimer++;
		if (frameTimer % 45 == 0) {
			this.moveProjectiles();
			frameTimer = 0;
		}
	}

	public void setFrameTimer() {
		this.frameTimer++;
	}

	public void resetFrameTimer() {
		this.frameTimer = 0;
	}

	public int getFrameTimer() {
		return this.frameTimer;
	}
}

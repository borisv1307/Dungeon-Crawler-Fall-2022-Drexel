package engine;

import tiles.TileType;
import values.TunableParameters;
import wrappers.RandomWrapper;

public class ProjectileHandler {

	RandomWrapper randomWrapper;
	GameEngine gameEngine;
	private int frameTimer = 0;
	private int moveCount = 0;
	private double difficultyFactor = 1;
	private double projectileSpeed;

	public ProjectileHandler(GameEngine gameEngine, RandomWrapper randomWrapper) {
		this.gameEngine = gameEngine;
		this.randomWrapper = randomWrapper;
		this.projectileSpeed = difficultyFactor * TunableParameters.TARGET_FPS;
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
		gameEngine.addTile(0, yValue, TileType.PROJECTILE, TunableParameters.RIGHT);
	}

	public void createRightEdgeProjectile() {
		int yValue = getYValue();
		gameEngine.addTile(gameEngine.getLevelHorizontalDimension() - 1, yValue, TileType.PROJECTILE,
				TunableParameters.LEFT);
	}

	public void createTopEdgeProjectile() {
		int xValue = getXValue();
		gameEngine.addTile(xValue, 0, TileType.PROJECTILE, TunableParameters.DOWN);
	}

	public void createBottomEdgeProjectile() {
		int xValue = getXValue();
		gameEngine.addTile(xValue, gameEngine.getLevelVerticalDimension() - 1, TileType.PROJECTILE,
				TunableParameters.UP);
	}

	public void createProjectiles() {
		createRightEdgeProjectile();
		createBottomEdgeProjectile();
		createTopEdgeProjectile();
		createLeftEdgeProjectile();
	}

	public void increaseDifficulty() {
		this.difficultyFactor = this.difficultyFactor / 2;
		projectileSpeed = this.difficultyFactor * TunableParameters.TARGET_FPS;
	}

	public void moveProjectiles() {
		moveCount++;
		gameEngine.moveProjectileUp();
		gameEngine.moveProjectileLeft();
		gameEngine.moveProjectileDown();
		gameEngine.moveProjectileRight();
		if (moveCount > gameEngine.getLevelHorizontalDimension()) {
			increaseDifficulty();
			createProjectiles();
			moveCount = 0;
		}
	}

	public void runProjectileTimer() {
		frameTimer++;
		if (frameTimer > projectileSpeed) {
			this.moveProjectiles();
			frameTimer = 0;
		}
	}

	double getDifficultyFactor() {
		return difficultyFactor;
	}

	void setDifficultyFactor(double difficultyFactor) {
		this.difficultyFactor = difficultyFactor;
	}

}

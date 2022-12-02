package engine;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

public class GameEngine {

	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private final int level;
	private boolean exit;
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private Point projectile;
//	private int projectileFrequency = 1 * TunableParameters.TARGET_FPS;
//	private int framesCount = 0;

	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		level = 1;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
	}

	public void run(GameFrame gameFrame) {
//		framesCount++;
//		if (framesCount % projectileFrequency == 0) {
//			System.out.println("Tick");
//			moveProjectileRight();
//			framesCount = 0;
//		}
		for (Component component : gameFrame.getComponents()) {
			component.repaint();
		}
	}

	public void addTile(int x, int y, TileType tileType) {
		if (tileType.equals(TileType.PLAYER)) {
			setPlayer(x, y);
			tiles.put(new Point(x, y), TileType.PASSABLE);
		} else if (tileType.equals(TileType.PROJECTILE)) {
			setProjectile(x, y);
			tiles.put(new Point(x, y), TileType.PASSABLE);
		} else {
			tiles.put(new Point(x, y), tileType);
		}
	}

	public int getLevelHorizontalDimension() {
		return levelHorizontalDimension;
	}

	public void setLevelHorizontalDimension(int levelHorizontalDimension) {
		this.levelHorizontalDimension = levelHorizontalDimension;
	}

	public int getLevelVerticalDimension() {
		return levelVerticalDimension;
	}

	public void setLevelVerticalDimension(int levelVerticalDimension) {
		this.levelVerticalDimension = levelVerticalDimension;
	}

	public TileType getTileFromCoordinates(int x, int y) {
		return tiles.get(new Point(x, y));
	}

	private void setPlayer(int x, int y) {
		player = new Point(x, y);
	}

	private void setProjectile(int x, int y) {
		projectile = new Point(x, y);
	}

	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}

	public void keyLeft() {
		movement(-1, 0);
	}

	public void keyRight() {
		movement(1, 0);
	}

	public void keyUp() {
		movement(0, -1);
	}

	public void keyDown() {
		movement(0, 1);
	}

	private void movement(int deltaX, int deltaY) {
		TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX,
				getPlayerYCoordinate() + deltaY);
		if (attemptedLocation.equals(TileType.PASSABLE)) {
			setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
		}
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public int getProjectileXCoordinate() {
		return (int) projectile.getX();
	}

	public int getProjectileYCoordinate() {
		return (int) projectile.getY();
	}

	public void moveProjectileRight() {
		setProjectile(getProjectileXCoordinate() + 1, getProjectileYCoordinate());
	}

	public void moveProjectileLeft() {
		setProjectile(getProjectileXCoordinate() - 1, getProjectileYCoordinate());
	}

	public void moveProjectileDown() {
		setProjectile(getProjectileXCoordinate(), getProjectileYCoordinate() + 1);
	}

	public void moveProjectileUp() {
		setProjectile(getProjectileXCoordinate(), getProjectileYCoordinate() - 1);
	}
}

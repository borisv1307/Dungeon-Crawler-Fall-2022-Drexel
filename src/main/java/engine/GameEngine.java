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
	private Map<String, Point> projectiles = new HashMap<>();

	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		level = 1;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
	}

	public void run(GameFrame gameFrame, ProjectileHandler projectileHandler) {
		projectileHandler.runProjectileTimer();
		for (Component component : gameFrame.getComponents()) {
			component.repaint();
		}
	}

	public void addTile(int x, int y, TileType tileType) {
		if (tileType.equals(TileType.PLAYER)) {
			setPlayer(x, y);
			tiles.put(new Point(x, y), TileType.PASSABLE);
		}
//		else if (tileType.equals(TileType.PROJECTILE)) {
//			setProjectile(x, y);
//			tiles.put(new Point(x, y), TileType.PASSABLE);
//		} 
		else {
			tiles.put(new Point(x, y), tileType);
		}
	}

	public void addTile(int x, int y, TileType tileType, String direction) {
		if (tileType.equals(TileType.PROJECTILE)) {
			setProjectile(x, y, direction);
//			tiles.put(new Point(x, y), TileType.PASSABLE);
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

	private void setProjectile(int x, int y, String direction) {
		projectiles.put(direction, new Point(x, y));
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

	public int getProjectileXCoordinate(String direction) {
		return (int) projectiles.get(direction).getX();
	}

	public int getProjectileYCoordinate(String direction) {
		return (int) projectiles.get(direction).getY();
	}

	public void moveProjectileRight() {
		setProjectile(getProjectileXCoordinate("right") + 1, getProjectileYCoordinate("right"), "right");
	}

	public void moveProjectileLeft() {
		setProjectile(getProjectileXCoordinate("left") - 1, getProjectileYCoordinate("left"), "left");
	}

	public void moveProjectileDown() {
		setProjectile(getProjectileXCoordinate("down"), getProjectileYCoordinate("down") + 1, "down");
	}

	public void moveProjectileUp() {
		setProjectile(getProjectileXCoordinate("up"), getProjectileYCoordinate("up") - 1, "up");
	}
}

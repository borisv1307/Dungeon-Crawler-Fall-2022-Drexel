package engine;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

	private final Map<Point, TileType> tiles = new HashMap<>();
	private final LevelCreator levelCreator;
	private int level = 1;
	private boolean exit;
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private Point goal;

	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
	}

	public void run(GameFrame gameFrame) {
		for (Component component : gameFrame.getComponents()) {
			component.repaint();
		}
	}

	public void addTile(int x, int y, TileType tileType) {
		if (tileType.equals(TileType.PLAYER)) {
			setPlayer(x, y);
			tiles.put(new Point(x, y), TileType.PASSABLE);
		} else if (tileType.equals(TileType.GOAL)) {
			setGoal(x, y);
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

	private void setGoal(int x, int y) {
		goal = new Point(x, y);
	}

	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}

	public int getGoalXCoordinate() {
		return (int) goal.getX();
	}

	public int getGoalYCoordinate() {
		return (int) goal.getY();
	}

	public void keyLeft() {
		if (tileIsPassable(getPlayerXCoordinate() - 1, getPlayerYCoordinate())) {
			setPlayer(getPlayerXCoordinate() - 1, getPlayerYCoordinate());
		}
		if (playerHasReachedGoal()) {
			loadNextLevel();
		}
	}

	public void keyRight() {
		if (tileIsPassable(getPlayerXCoordinate() + 1, getPlayerYCoordinate())) {
			setPlayer(getPlayerXCoordinate() + 1, getPlayerYCoordinate());
		}
		if (playerHasReachedGoal()) {
			loadNextLevel();
		}
	}

	public void keyUp() {
		if (tileIsPassable(getPlayerXCoordinate(), getPlayerYCoordinate() - 1)) {
			setPlayer(getPlayerXCoordinate(), getPlayerYCoordinate() - 1);
		}
		if (playerHasReachedGoal()) {
			loadNextLevel();
		}
	}

	public void keyDown() {
		if (tileIsPassable(getPlayerXCoordinate(), getPlayerYCoordinate() + 1)) {
			setPlayer(getPlayerXCoordinate(), getPlayerYCoordinate() + 1);
		}
		if (playerHasReachedGoal()) {
			loadNextLevel();
		}
	}

	private boolean tileIsPassable(int xCoordinate, int yCoordinate) {
		return getTileFromCoordinates(xCoordinate, yCoordinate).equals(TileType.PASSABLE);
	}

	public boolean playerHasReachedGoal() {
		return getGoalYCoordinate() == getPlayerYCoordinate() && getGoalXCoordinate() == getPlayerXCoordinate();
	}

	public void loadNextLevel() {
		level++;
		levelCreator.createLevel(this, level);
	}

	public int getLevel() {
		return level;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

}

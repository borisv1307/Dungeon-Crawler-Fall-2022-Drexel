package engine;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import ui.ScorePanel;
import values.TunableParameters;

public class GameEngine {

	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private final ScorePanel scorePanel;
	private int level;
	private boolean exit;
	private boolean lost;
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;

	public GameEngine(LevelCreator levelCreator, ScorePanel scorePanel) {
		exit = false;
		lost = false;
		level = 1;
		this.levelCreator = levelCreator;
		this.scorePanel = scorePanel;
		this.levelCreator.createLevel(this, level);
	}

	public void setLevel(int level) {
		this.level = level;
		levelCreator.createLevel(this, level);
	}

	public void incrementScore() {
		this.scorePanel.incrementScore();
	}

	public void decrementLives() {
		this.scorePanel.decrementLives();
	}

	public ScorePanel getScorePanel() {
		return scorePanel;
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
		int nextX = getPlayerXCoordinate() + deltaX;
		int nextY = getPlayerYCoordinate() + deltaY;
		TileType attemptedLocation = getTileFromCoordinates(nextX, nextY);
		if (attemptedLocation.equals(TileType.ENEMY) || attemptedLocation.equals(TileType.ENEMY_PROJECTILE)) {
			addTile(nextX, nextY, TileType.PASSABLE);
			setPlayer(nextX, nextY);
			scorePanel.decrementLives();
		}

		if (scorePanel.getPlayerLives() == 0) {
			setLost(true);
		}

		if (attemptedLocation.equals(TileType.PASSABLE) || attemptedLocation.equals(TileType.PROJECTILE)) {
			setPlayer(nextX, nextY);
		}
	}

	public void shoot() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate() - 1;

		TileType nextTile = getTileFromCoordinates(x, y);
		if (nextTile == TileType.ENEMY) {
			incrementScore();
			addTile(x, y, TileType.PASSABLE);
			return;
		}

		if (nextTile != TileType.NOT_PASSABLE) {
			addTile(x, y, TileType.PROJECTILE);
		}
	}

	public void restart() {
		if (lost) {
			lost = false;
			setLevel(1);
			scorePanel.setPlayerLives(TunableParameters.STARTING_AND_MAXIMUM_LIVES);
			scorePanel.setPlayerScore(TunableParameters.STARTING_SCORE);
			scorePanel.setLivesIncrementCountDown(0);
		}
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}
}

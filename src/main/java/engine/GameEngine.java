package engine;


import parser.LevelCreator;
import tiles.TileType;
import ui.DialogueSystem;
import ui.GameFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static values.TunableParameters.PLAYER_SPEED;

public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private Point nonPlayableCharacter;
	private final int level;
	private final DialogueSystem dialogueSystem;


	public GameEngine(LevelCreator levelCreator, DialogueSystem dialogueSystem) {

		exit = false;
		level = 1;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
		this.dialogueSystem = dialogueSystem;

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
		} else if (tileType.equals(TileType.NON_PLAYABLE_CHARACTER)) {
			setNonPlayableCharacter(x, y);
			tiles.put(new Point(x, y), TileType.NOT_PASSABLE);
		} else {
			tiles.put(new Point(x, y), tileType);
		}
	}

	public void setLevelHorizontalDimension(int levelHorizontalDimension) {
		this.levelHorizontalDimension = levelHorizontalDimension;
	}

	public void setLevelVerticalDimension(int levelVerticalDimension) {
		this.levelVerticalDimension = levelVerticalDimension;
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

	private void setNonPlayableCharacter(int x, int y) {
		nonPlayableCharacter = new Point(x, y);
	}


	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}

	public int getNonPlayableCharacterXCoordinate() {
		return (int) nonPlayableCharacter.getX();
	}

	public int getNonPlayableCharacterYCoordinate() {
		return (int) nonPlayableCharacter.getY();
	}

	public void keyLeft() {
		movePlayer(getPlayerXCoordinate() - PLAYER_SPEED, getPlayerYCoordinate());
	}

	public void keyRight() {
		movePlayer(getPlayerXCoordinate() + PLAYER_SPEED, getPlayerYCoordinate());
	}

	public void keyUp() {
		movePlayer(getPlayerXCoordinate(), getPlayerYCoordinate() - PLAYER_SPEED);
	}

	public void keyDown() {
		movePlayer(getPlayerXCoordinate(), getPlayerYCoordinate() + PLAYER_SPEED);
	}

	public void keyEnter() {
		if (!dialogueSystem.isDialogueActive()) {
			dialogueSystem.initiateDialogueFrame();
		}
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
  
	private void movePlayer(int destinationX, int destinationY) {
		if (!dialogueSystem.isDialogueActive()) {
			TileType destinationTile = getTileFromCoordinates(destinationX, destinationY);
			if (isTilePassable(destinationTile)) {
				setPlayer(destinationX, destinationY);
			}
		}
	}

	private boolean isTilePassable(TileType tile) {
		boolean isPassable = false;
		if (tile.equals(TileType.PASSABLE)) {
			isPassable = true;
		}
		return isPassable;
	}
}

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

	private final Map<Point, TileType> visited = new HashMap<>();

	private  int level;

	private final int finalLevel ;
	private boolean exit;
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;

	private int potionCounter;



	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		level = 1;
		finalLevel = 2;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
	}

	public int getLevel(){
		return level;
	}

	public int getPotionCounter(){
		return potionCounter;
	}

	public void setLevel(int newLevel){
		this.level = newLevel;
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
		int attemptedXCoordinate = getPlayerXCoordinate() + deltaX;
		int attemptedYCoordinate = getPlayerYCoordinate() + deltaY;
		TileType attemptedLocation = getTileFromCoordinates(attemptedXCoordinate,
				attemptedYCoordinate);
		if(!attemptedLocation.equals(TileType.NOT_PASSABLE)){
			int newX = getPlayerXCoordinate();
			int newY = getPlayerYCoordinate();
			visited.put(new Point(newX, newY), TileType.VISITED);
			setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
		}
		if(attemptedLocation.equals(TileType.POTION) && !checkTileVisited(attemptedXCoordinate,attemptedYCoordinate)){
			potionCounter++;
		}
		if(attemptedLocation.equals(TileType.DRAGON)){
			exit = true;
		}
		if(attemptedLocation.equals(TileType.EXIT)){
			if(getLevel() == finalLevel){
				setExit(true);
			}
			if(potionCounter == 3){
				level++;
				potionCounter =0;
				visited.clear();
				tiles.clear();
				levelCreator.createLevel(this,level);

			}
		}

	}

	public boolean checkTileVisited(int x, int y){
		return visited.containsKey(new Point(x,y));
	}
	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
}

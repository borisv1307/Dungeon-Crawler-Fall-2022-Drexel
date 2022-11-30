package engine;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private int lastLevel;
    private int level;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;
    private boolean playerHasKey;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        lastLevel = 5;
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
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        if (isTilePassable(attemptedLocation)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        }
        if (isTileKey(attemptedLocation)) {
            setPlayerHasKey(true);
            setTilePassableAndMovePlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        }
        if (isTileDoor(attemptedLocation) && isPlayerHasKey()) {
            setTilePassableAndMovePlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        }
        if (isTileGoal(attemptedLocation)) {
            loadNextLevel();
        }
        if (isTileEnemy(attemptedLocation)) {
            restartCurrentLevel();
        }
    }

    private boolean isTilePassable(TileType attemptedTile) {
        return attemptedTile.equals(TileType.PASSABLE);
    }

    private boolean isTileKey(TileType attemptedTile) {
        return attemptedTile.equals(TileType.KEY);
    }

    private boolean isTileDoor(TileType attemptedTile) {
        return attemptedTile.equals(TileType.DOOR);
    }

    private boolean isTileGoal(TileType attemptedTile) {
        return attemptedTile.equals(TileType.GOAL);
    }

    private boolean isTileEnemy(TileType attemptedTile) {
        return attemptedTile.equals(TileType.ENEMY);
    }

    private void setTilePassableAndMovePlayer(int deltaX, int deltaY) {
        tiles.put(new Point(deltaX, deltaY), TileType.PASSABLE);
        setPlayer(deltaX, deltaY);
    }

    public boolean isPlayerHasKey() {
        return playerHasKey;
    }

    public void setPlayerHasKey(boolean playerHasKey) {
        this.playerHasKey = playerHasKey;
    }

    public int getCurrentLevel() {
        return level;
    }

    public void setCurrentLevel(int level) {
        this.level = level;
    }

    public void loadNextLevel() {
        setPlayerHasKey(false);
        if (getCurrentLevel() < lastLevel) {
            levelCreator.createLevel(this, ++level);
        } else {
            this.setExit(true);
        }
    }

    public void restartCurrentLevel() {
        setPlayerHasKey(false);
        levelCreator.createLevel(this, getCurrentLevel());
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}

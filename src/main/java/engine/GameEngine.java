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
    private final int level;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;
    private Point enemy;
    private Point goal;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
    }

    public void run(GameFrame gameFrame) {
        for (Component component : gameFrame.getComponents()) {
            component.repaint();
        }
    }

    public void addTile(int x, int y, TileType tileType) {
        switch (tileType) {
            case GOAL:
            case ENEMY:
            case PLAYER:
                setPoint(tileType, x, y);
                tiles.put(new Point(x, y), TileType.PASSABLE);
                break;
            default:
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

    private void setPoint(TileType tileType, int x, int y) {
        Point point = new Point(x, y);

        switch (tileType) {
            case PLAYER:
                player = point;
                break;
            case GOAL:
                goal = point;
                break;
            case ENEMY:
                enemy = point;
                break;
            default:
                throw new IllegalArgumentException("Get Coordinate not applicable for TileType" + tileType);
        }
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
        int attemptedX = getXCoordinate(TileType.PLAYER) + deltaX;
        int attemptedY = getYCoordinate(TileType.PLAYER) + deltaY;
        TileType attemptedLocation = getTileFromCoordinates(attemptedX, attemptedY);
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setPoint(TileType.PLAYER, attemptedX, attemptedY);
        }
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public int getXCoordinate(TileType tileType) {
        return getPoint(tileType).x;
    }

    public int getYCoordinate(TileType tileType) {
        return getPoint(tileType).y;
    }

    private Point getPoint(TileType tileType) {
        switch (tileType) {
            case PLAYER:
                return player;
            case GOAL:
                return goal;
            case ENEMY:
                return enemy;
            default:
                throw new IllegalArgumentException("Get Coordinate not applicable for TileType" + tileType);
        }
    }
}

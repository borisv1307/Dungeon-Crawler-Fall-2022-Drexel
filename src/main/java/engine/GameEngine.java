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
        Point tileLocation = new Point(x, y);
        if (tileType.equals(TileType.PLAYER)) {
            setPlayerLocation(tileLocation);
            tiles.put(tileLocation, TileType.PASSABLE);
        } else {
            tiles.put(tileLocation, tileType);
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

    private void setPlayerLocation(Point newLocation) {
        player = newLocation;
    }

    public int getPlayerXCoordinate() {
        return (int) player.getX();
    }

    public int getPlayerYCoordinate() {
        return (int) player.getY();
    }

    public void keyLeft() {
        Point attemptedLocation = moveStepValueAlongXAxis(-1);
        moveToPoint(attemptedLocation);
    }

    public void keyRight() {
        Point attemptedLocation = moveStepValueAlongXAxis(1);
        moveToPoint(attemptedLocation);
    }

    public void keyUp() {
        Point attemptedLocation = moveStepValueAlongYAxis(-1);
        moveToPoint(attemptedLocation);
    }

    public void keyDown() {
        Point attemptedLocation = moveStepValueAlongYAxis(1);
        moveToPoint(attemptedLocation);
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    private Point moveStepValueAlongXAxis(int stepValue) {
        return new Point(getPlayerXCoordinate() + stepValue, getPlayerYCoordinate());
    }

    private Point moveStepValueAlongYAxis(int stepValue) {
        return new Point(getPlayerXCoordinate(), getPlayerYCoordinate() + stepValue);
    }

    private void moveToPoint(Point attemptedLocation) {
        TileType attemptedLocationTileType = tiles.get(attemptedLocation);
        if (attemptedLocationTileType.equals(TileType.PASSABLE)) {
            setPlayerLocation(attemptedLocation);
        }
    }
}

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

    private void setPlayer(Point point) {
        player = point;
    }

    public int getPlayerXCoordinate() {
        return (int) player.getX();
    }

    public int getPlayerYCoordinate() {
        return (int) player.getY();
    }

    public void keyLeft() {
        Point possibleMove = getPoint("left");
        moveIfPossible(possibleMove);
    }

    public void keyRight() {
        Point possibleMove = getPoint("right");
        moveIfPossible(possibleMove);
    }


    public void keyUp() {
        Point possibleMove = getPoint("up");
        moveIfPossible(possibleMove);
    }

    public void keyDown() {
        Point possibleMove = getPoint("down");
        moveIfPossible(possibleMove);
    }

    public boolean isPassable(Point possibleMove) {
        return getTileFromCoordinates(possibleMove.x, possibleMove.y).equals(TileType.PASSABLE);
    }

    public Point getPoint(String direction) {
        switch (direction) {
            case "up":
                return new Point(getPlayerXCoordinate(), getPlayerYCoordinate() - 1);
            case "down":
                return new Point(getPlayerXCoordinate(), getPlayerYCoordinate() + 1);
            case "left":
                return new Point(getPlayerXCoordinate() - 1, getPlayerYCoordinate());
            default:
                return new Point(getPlayerXCoordinate() + 1, getPlayerYCoordinate());
        }
    }

    public void moveIfPossible(Point possibleMove) {
        if (isPassable(possibleMove)) {
            setPlayer(possibleMove);
        }
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}

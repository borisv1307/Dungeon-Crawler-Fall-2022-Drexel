package engine;

import objects.CollectableObject;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomWrapper;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private final int level;

    private ArrayList<CollectableObject> collectableObjects = new ArrayList<>();
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;

    private RandomWrapper randomWrapper;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
        this.randomWrapper = new RandomWrapper();
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

    private void setObject(CollectableObject collectableObject, int x, int y) {
        //collectableObject = new Point(x, y);
    }

    public int getPlayerXCoordinate() {
        return (int) player.getX();
    }

    public int getPlayerYCoordinate() {
        return (int) player.getY();
    }

    public boolean isThereAnObjectHere(int x, int y) {
        return true;
    }

    public void keyLeft() {
        int attemptedPlayerXCoordinate = getPlayerXCoordinate() - 1;
        int attemptedPlayerYCoordinate = getPlayerYCoordinate();
        TileType attemptedLocation = getTileFromCoordinates(attemptedPlayerXCoordinate, attemptedPlayerYCoordinate);
        moveIfValid(attemptedLocation, attemptedPlayerXCoordinate, attemptedPlayerYCoordinate);
    }

    public void keyRight() {
        int attemptedPlayerXCoordinate = getPlayerXCoordinate() + 1;
        int attemptedPlayerYCoordinate = getPlayerYCoordinate();
        TileType attemptedLocation = getTileFromCoordinates(attemptedPlayerXCoordinate, attemptedPlayerYCoordinate);
        moveIfValid(attemptedLocation, attemptedPlayerXCoordinate, attemptedPlayerYCoordinate);
    }

    public void keyUp() {
        int attemptedPlayerXCoordinate = getPlayerXCoordinate();
        int attemptedPlayerYCoordinate = getPlayerYCoordinate() - 1;
        TileType attemptedLocation = getTileFromCoordinates(attemptedPlayerXCoordinate, attemptedPlayerYCoordinate);
        moveIfValid(attemptedLocation, attemptedPlayerXCoordinate, attemptedPlayerYCoordinate);
    }

    public void keyDown() {
        int attemptedPlayerXCoordinate = getPlayerXCoordinate();
        int attemptedPlayerYCoordinate = getPlayerYCoordinate() + 1;
        TileType attemptedLocation = getTileFromCoordinates(attemptedPlayerXCoordinate, attemptedPlayerYCoordinate);
        moveIfValid(attemptedLocation, attemptedPlayerXCoordinate, attemptedPlayerYCoordinate);
    }

    private void moveIfValid(TileType attemptedLocation, int attempedXCoordinate, int attemptedYCoordinate) {
        if (isSpacePassable(attemptedLocation)) {
            if (attemptedLocation.equals(TileType.OBJECT)) {
                removeObjectFromTile(new Point(attempedXCoordinate, attemptedYCoordinate));
            }
            setPlayer(attempedXCoordinate, attemptedYCoordinate);
        }
    }

    private boolean isSpacePassable(TileType attemptedLocation) {
        return attemptedLocation.equals(TileType.PASSABLE) || attemptedLocation.equals(TileType.OBJECT);
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void initializeRandomObjects() {
        int min = 1;
        int max = 4;
        int numberOfRandomObjects = randomWrapper.getRandomIntInRange(min, max);
        for (int i = 0; i < numberOfRandomObjects; i++) {
            collectableObjects.add(new CollectableObject());
        }
    }

    public void addObjectToTile() {
        Point passableTile = getRandomPassableTile();
        TileType oldTileType = tiles.get(passableTile);
        tiles.replace(passableTile, oldTileType, TileType.OBJECT);
        System.out.println("added object to tile");
    }

    public ArrayList<Point> getAllPassableTiles() {
        ArrayList<Point> emptyTiles = new ArrayList<>();
        for (int x = 0; x < levelVerticalDimension; x++) {
            for (int y = 0; y < levelHorizontalDimension; y++) {
                if (getTileFromCoordinates(x, y).equals(TileType.PASSABLE)) {
                    Point passablePoint = new Point(x, y);
                    emptyTiles.add(passablePoint);
                }
            }
        }
        return emptyTiles;
    }

    public Point getRandomPassableTile() {
        ArrayList<Point> passableTiles = getAllPassableTiles();
        int min = 0;
        int max = passableTiles.size();
        if (min != max) {
            int randomIndex = randomWrapper.getRandomIntInRange(min, max);
            return passableTiles.get(randomIndex);
        } else {
            return null;
        }
    }

    public CollectableObject getCollectableObject() {
        if (collectableObjects.size() > 0) {
            CollectableObject object = collectableObjects.get(0);
            collectableObjects.remove(object);
            return object;
        }
        return null;
    }

    public void removeObjectFromTile(Point objectTile) {
        TileType oldTileType = tiles.get(objectTile);
        tiles.replace(objectTile, oldTileType, TileType.PASSABLE);
    }
}

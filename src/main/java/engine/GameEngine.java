package engine;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomWrapper;

import java.awt.*;
import java.util.*;

public class GameEngine {

    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private final int level;
    private final RandomWrapper randomWrapper;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;
    private ArrayList<Point> collectableObjects;
    //private ArrayList<Point> passableTiles;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
        this.randomWrapper = new RandomWrapper();
        collectableObjects = new ArrayList<>();
        //passableTiles = getAllPassableTiles();
        activateGameTimer(10);

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

    public ArrayList<Point> getPassableTiles(int numberOfTilesNeeded) {
        ArrayList<Point> tilesToBeUpdated = new ArrayList<>();
        ArrayList<Point> passableTiles = getAllPassableTiles();
        for (int i = 0; i < numberOfTilesNeeded; i++) {
            Point passableTile = getRandomPassableTile(passableTiles);
            if (!tilesToBeUpdated.contains(passableTile)) {
                tilesToBeUpdated.add(passableTile);
            }
        }
        return tilesToBeUpdated;
    }

    public void addObjectsToTheGame(ArrayList<Point> tilesToBeUpdated) {
        for (Point tileToBeUpdated : tilesToBeUpdated) {
            addObjectToTile(tileToBeUpdated);
        }
    }

    public void addObjectToTile(Point tileToBeUpdated) {
        TileType oldTileType = tiles.get(tileToBeUpdated);
        tiles.replace(tileToBeUpdated, oldTileType, TileType.OBJECT);
        collectableObjects.add(tileToBeUpdated);
    }

    public ArrayList<Point> getAllPassableTiles() {
        ArrayList<Point> emptyTiles = new ArrayList<>();
        for (Map.Entry<Point, TileType> singleTile : tiles.entrySet()) {
            TileType singleTileType = singleTile.getValue();
            if (singleTileType.equals(TileType.PASSABLE)) {
                emptyTiles.add(singleTile.getKey());
            }
        }
        return emptyTiles;
    }

    public Point getRandomPassableTile(ArrayList<Point> allPassableTiles) {
        int numberOfPassableTiles = allPassableTiles.size();
        if (numberOfPassableTiles > 0) {
            int randomIndex = randomWrapper.getRandomIntInRange(0, numberOfPassableTiles);
            return allPassableTiles.get(randomIndex);
        } else {
            return null;
        }
    }

    public void removeObjectFromTile(Point objectTile) {
        TileType oldTileType = tiles.get(objectTile);
        tiles.replace(objectTile, oldTileType, TileType.PASSABLE);
    }

    public void removePreviouslyAddedObjects() {
        for (Point pointToRemove : collectableObjects) {
            removeObjectFromTile(pointToRemove);
        }
        collectableObjects.clear();
    }

    public void refreshCollectableObjects() {
        int randomNumberOfObjects = randomWrapper.getRandomNumberOfObjects();
        ArrayList<Point> tilesToBeUpdated = getPassableTiles(randomNumberOfObjects);
        if (collectableObjects != null) {
            removePreviouslyAddedObjects();
        }
        addObjectsToTheGame(tilesToBeUpdated);
    }

    public void activateGameTimer(int numberOfSeconds) {
        TimerTask objectTimer = new TimerTask() {
            @Override
            public void run() {
                refreshCollectableObjects();
            }
        };

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(objectTimer, 1000, numberOfSeconds * 1000L);
    }
}

package engine;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import values.TileColorMap;
import wrappers.RandomWrapper;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private final int level;
    RandomWrapper randomWrapper;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;
    private int playerCoins;
    private int playerLevel;
    private int coinCount;
    private int latestCoinX;
    private int latestCoinY;
    private Map<Integer, Color> playerLevels = new HashMap<>();

    public GameEngine(LevelCreator levelCreator, RandomWrapper randomWrapper) {
        this.randomWrapper = randomWrapper;
        exit = false;
        level = 1;
        playerCoins = 0;
        playerLevel = 1;
        coinCount = 0;
        initializePlayerLevels();
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
        randomCoinGeneration();
    }

    public void initializePlayerLevels() {
        playerLevels.put(2, Color.BLUE);
        playerLevels.put(3, Color.MAGENTA);
        playerLevels.put(4, Color.CYAN);
        playerLevels.put(5, Color.ORANGE);
        playerLevels.put(6, Color.PINK);
        playerLevels.put(7, Color.RED);
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

    public void randomCoinGeneration() {

        if (getLevelHorizontalDimension() == 0 || getLevelVerticalDimension() == 0) {
            return;
        }

        int randomCoordX = randomWrapper.generateCoordinate(getLevelHorizontalDimension());
        int randomCoordY = randomWrapper.generateCoordinate(getLevelVerticalDimension());

        while (!getTileFromCoordinates(randomCoordX, randomCoordY).equals(TileType.PASSABLE)) {
            randomCoordX = randomWrapper.generateCoordinate(getLevelHorizontalDimension());
            randomCoordY = randomWrapper.generateCoordinate(getLevelVerticalDimension());
        }

        addTile(randomCoordX, randomCoordY, TileType.COIN);
        latestCoinX = randomCoordX;
        latestCoinY = randomCoordY;
        coinCount++;
    }

    public int getCoinX() {
        return latestCoinX;
    }

    public int getCoinY() {
        return latestCoinY;
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

    public int getPlayerCoins() {
        return playerCoins;
    }

    public void setPlayerCoins(int coinValue) { // for testing
        playerCoins = coinValue;
    }

    public void incrementPlayerCoins() {
        playerCoins += 1;
        if (playerCoins == playerLevel * 5 && playerLevel != 7) {
            incrementPlayerLevel();
            playerCoins = 0;
        }
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int levelValue) { // for testing
        playerLevel = levelValue;
    }

    public void incrementPlayerLevel() {
        playerLevel += 1;
        //randomCoinGeneration();
        TileColorMap.updatePlayerColor(playerLevels.get(playerLevel));
    }

    public int getCoinCount() {
        return coinCount;
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
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX,
                getPlayerYCoordinate() + deltaY);
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
        } else if (attemptedLocation.equals(TileType.COIN)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
            addTile(getPlayerXCoordinate(), getPlayerYCoordinate(), TileType.PASSABLE);
            incrementPlayerCoins();
            coinCount--;

            if (coinCount == 0) {
                randomCoinGeneration();
                randomCoinGeneration();
            }
        }
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}

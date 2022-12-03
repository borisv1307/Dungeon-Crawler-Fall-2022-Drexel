package engine;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomWrapper;
import wrappers.SystemWrapper;

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

    private Point bomb;

    private int ticker;

    private double randomNumber;

    private SystemWrapper systemWrapper;

    private RandomWrapper randomWrapper;

    public GameEngine(LevelCreator levelCreator, RandomWrapper randomWrapper) {
        this.randomWrapper = randomWrapper;
        randomNumber = randomWrapper.mathRandom();
        systemWrapper = new SystemWrapper();
        exit = false;
        level = 1;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
        bomb = new Point((int) (randomNumber * 17) + 1, 1);
        ticker = 0;
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
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
            if (collision()) {
                systemWrapper.printLn("COLLISION");
            }
        }
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public int getBombXCoordinate() {
        return (int) bomb.getX();
    }

    public int getBombYCoordinate() {
        ticker++;
        int y = (int) bomb.getY();
        if (ticker % 30 == 0) {
            y++;
            int x = (int) bomb.getX();
            TileType attemptedLocation = getTileFromCoordinates(x, y);
            if (attemptedLocation.equals(TileType.NOT_PASSABLE)) {
                randomNumber = randomWrapper.mathRandom();
                x = (int) (randomNumber * 17) + 1;
                y = 1;
            }
            bomb = new Point(x, y);
        }
        return y;
    }

    public boolean collision() {
        return player.equals(bomb);
    }
}

package engine;

import javafx.util.Pair;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static values.TunableParameters.PLAYER_SPEED;

public class GameEngine {

    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private final int level;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;

    private Pair<Integer, Integer> movement;

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

    public int getPlayerXCoordinate() {
        return (int) player.getX();
    }

    public int getPlayerYCoordinate() {
        return (int) player.getY();
    }

    public void keyLeft() {
        movement = new Pair<>(getPlayerXCoordinate() - PLAYER_SPEED, getPlayerYCoordinate());
        movePlayer(movement);
    }

    public void keyRight() {
        movement = new Pair<>(getPlayerXCoordinate() + PLAYER_SPEED, getPlayerYCoordinate());
        movePlayer(movement);
    }

    public void keyUp() {
        movement = new Pair<>(getPlayerXCoordinate(), getPlayerYCoordinate() - PLAYER_SPEED);
        movePlayer(movement);
    }

    public void keyDown() {
        movement = new Pair<>(getPlayerXCoordinate(), getPlayerYCoordinate() + PLAYER_SPEED);
        movePlayer(movement);
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    private void movePlayer(Pair<Integer, Integer> destinationXY) {
        TileType destinationTile = getTileFromCoordinates(destinationXY.getKey(), destinationXY.getValue());
        if (isTilePassable(destinationTile)) {
            setPlayer(destinationXY.getKey(), destinationXY.getValue());
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

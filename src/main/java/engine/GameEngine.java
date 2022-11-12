package engine;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameEngine {

    private final LevelCreator levelCreator;
    private final Map<Point, TileType> tiles = new HashMap<>();
    private final int level;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;
    private TileType attemptedLocation;

    private String leftRight = "leftRight";

    private String upDown = "upDown";

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

    public void isPassableMovePlayer(TileType attemptedLocation, int direction, String keyDirection) {
        if (attemptedLocation.equals(TileType.PASSABLE) && Objects.equals(keyDirection, upDown)) {
            setPlayer(getPlayerXCoordinate(), getPlayerYCoordinate() + direction);
        } else if (attemptedLocation.equals(TileType.PASSABLE) && Objects.equals(keyDirection, leftRight)) {
            setPlayer(getPlayerXCoordinate() + direction, getPlayerYCoordinate());
        }
    }

    public void keyLeft() {
        attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() - 1, getPlayerYCoordinate());
        isPassableMovePlayer(attemptedLocation, -1, leftRight);
    }

    public void keyRight() {
        attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + 1, getPlayerYCoordinate());
        isPassableMovePlayer(attemptedLocation, 1, leftRight);
    }

    public void keyUp() {
        attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate(), getPlayerYCoordinate() - 1);
        isPassableMovePlayer(attemptedLocation, -1, upDown);
    }

    public void keyDown() {
        attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate(), getPlayerYCoordinate() + 1);
        isPassableMovePlayer(attemptedLocation, 1, upDown);
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}

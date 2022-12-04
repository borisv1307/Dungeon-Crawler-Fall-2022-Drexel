package engine;

import parser.LevelCreator;
import tiles.PlayerPoint;
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
    private PlayerPoint player;

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
            player = new PlayerPoint(x, y, 10);
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

    public void setTileByCoordinates(int x, int y, TileType tileType) {
        tiles.put(new Point(x, y), tileType);
    }

    private void setPlayer(int x, int y) {
        player.setPosition(x, y);
    }

    public int getPlayerHitpoints() {
        return player.getHitpoints();
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
        TileType attemptedLocation = getTileFromCoordinates(getPlayerXCoordinate() + deltaX,
                getPlayerYCoordinate() + deltaY);
        if (attemptedLocation.equals(TileType.PASSABLE) || attemptedLocation.equals(TileType.PASSABLE_HARMFUL) || attemptedLocation.equals(TileType.PASSABLE_HELPFUL)) {
            setPlayer(getPlayerXCoordinate() + deltaX, getPlayerYCoordinate() + deltaY);
            revealAdjacentTiles(getPlayerXCoordinate(), getPlayerYCoordinate());
        }
        if (attemptedLocation.equals(TileType.PASSABLE_HARMFUL)) {
            player.damage();
        }
        if (attemptedLocation.equals(TileType.PASSABLE_HELPFUL)) {
            player.heal();
        }
    }

    public void revealAdjacentTiles(int x, int y) {
        int[] adjacentPoints = {x - 1, y, x + 1, y, x, y - 1, x, y + 1};
        for (int i = 0; i < 8; i = i + 2) {
            TileType adjacentTile = getTileFromCoordinates(adjacentPoints[i], adjacentPoints[i + 1]);
            switch (adjacentTile) {
                case PASSABLE_HIDDEN:
                    setTileByCoordinates(adjacentPoints[i], adjacentPoints[i + 1], TileType.PASSABLE);
                    break;
                case PASSABLE_HARMFUL_HIDDEN:
                    setTileByCoordinates(adjacentPoints[i], adjacentPoints[i + 1], TileType.PASSABLE_HARMFUL);
                    break;
                case PASSABLE_HELPFUL_HIDDEN:
                    setTileByCoordinates(adjacentPoints[i], adjacentPoints[i + 1], TileType.PASSABLE_HELPFUL);
                    break;
                case NOT_PASSABLE:
                case PLAYER:
                case PASSABLE:
                case PASSABLE_HARMFUL:
                case PASSABLE_HELPFUL:
                    break;
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
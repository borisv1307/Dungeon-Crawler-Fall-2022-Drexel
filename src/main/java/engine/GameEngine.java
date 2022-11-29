package engine;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;

public class GameEngine {
    private final LevelCreator levelCreator;
    private final int losses;
    private int level;
    private int wins;
    private TileType[][] gameBoard;
    private boolean exit;
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;
    private Point enemy;
    private Point goal;

    public GameEngine(LevelCreator levelCreator) {
        exit = false;
        level = 1;
        wins = 0;
        losses = 0;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
    }

    public void run(GameFrame gameFrame) {
        for (Component component : gameFrame.getComponents()) {
            component.repaint();
        }
    }

    public int getLevelHorizontalDimension() {
        return levelHorizontalDimension;
    }

    public int getLevelVerticalDimension() {
        return levelVerticalDimension;
    }

    public TileType getTileFromCoordinates(int x, int y) {
        return gameBoard[x][y];
    }

    private void setPoint(TileType tileType, int x, int y) {
        Point point = new Point(x, y);
        switch (tileType) {
            case PLAYER:
                gameBoard[player.x][player.y] = TileType.PASSABLE;
                player = point;
                break;
            case GOAL:
                gameBoard[goal.x][goal.y] = TileType.PASSABLE;
                goal = point;
                break;
            case ENEMY:
                gameBoard[enemy.x][enemy.y] = TileType.PASSABLE;
                enemy = point;
                break;
            default:
                throw new IllegalArgumentException("Get Coordinate not applicable for TileType" + tileType);
        }
    }

    public void keyLeft(TileType tileType) {
        movement(tileType, -1, 0);
    }

    public void keyRight(TileType tileType) {
        movement(tileType, 1, 0);
    }

    public void keyUp(TileType tileType) {
        movement(tileType, 0, -1);
    }

    public void keyDown(TileType tileType) {
        movement(tileType, 0, 1);
    }

    private void movement(TileType tileType, int deltaX, int deltaY) {
        int attemptedX = getXCoordinate(tileType) + deltaX;
        int attemptedY = getYCoordinate(tileType) + deltaY;
        TileType attemptedLocation = getTileFromCoordinates(attemptedX, attemptedY);
        if (attemptedLocation.equals(TileType.PASSABLE)) {
            setPoint(tileType, attemptedX, attemptedY);
        }
        if (tileType == TileType.PLAYER && attemptedLocation == TileType.GOAL) {
            wins++;
            level++;
            regenerateLevel();
        }
    }

    private void regenerateLevel() {
        levelCreator.createLevel(this, level);
    }

    public void regenerateLevel(int level) {
        this.level = level;
        levelCreator.createLevel(this, level);
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

    public void setBoard(TileType[][] gameBoard) {
        this.gameBoard = gameBoard;
        levelHorizontalDimension = gameBoard.length;
        levelVerticalDimension = gameBoard[0].length;
    }

    public void setPlayableObject(int x, int y, TileType tileType) {
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

    public int getLevel() {
        return level;
    }

    public int getWinCount() {
        return wins;
    }

    public int getLossCount() {
        return losses;
    }
}

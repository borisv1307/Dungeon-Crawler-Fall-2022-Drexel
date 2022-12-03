package engine;

import board.GameBoard;
import enums.Direction;
import enums.Result;
import enums.TileType;
import parser.LevelCreator;
import ui.GameFrame;

import java.awt.*;

public class GameEngine {
    private final LevelCreator levelCreator;
    private GameBoard gameBoard;
    private int losses;
    private int level;
    private int wins;
    private boolean exit;
    private int playerMovesSinceLastEnemyMove;

    public GameEngine(final LevelCreator levelCreator) {
        exit = false;
        level = 1;
        wins = 0;
        losses = 0;
        playerMovesSinceLastEnemyMove = 0;
        this.levelCreator = levelCreator;
        this.levelCreator.createLevel(this, level);
    }

    public void run(final GameFrame gameFrame) {
        for (Component component : gameFrame.getComponents()) {
            component.repaint();
        }
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getLevelHorizontalDimension() {
        return gameBoard.getLevelHorizontalDimension();
    }

    public int getLevelVerticalDimension() {
        return gameBoard.getLevelVerticalDimension();
    }

    public TileType getTileFromCoordinates(final int x, final int y) {
        return gameBoard.getBoardPieceFromCoordinates(x, y).getTileType();
    }

    public void movement(final TileType tileType, final Direction direction) {
        if (tileType == TileType.PLAYER) {
            playerMovesSinceLastEnemyMove++;
        }
        Result result = gameBoard.moveBoardPiece(tileType, direction);
        handleLevelCompletion(result);
        if (playerMovesSinceLastEnemyMove == 2) {
            moveEnemy();
        }
    }

    public void moveEnemy() {
        Result result = gameBoard.moveEnemy();
        handleLevelCompletion(result);
        playerMovesSinceLastEnemyMove = 0;
    }

    public void regenerateLevel(final int level) {
        this.level = level;
        regenerateLevel();
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(final boolean exit) {
        this.exit = exit;
    }

    public int getXCoordinate(final TileType tileType) {
        return gameBoard.getMovableBoardPiece(tileType).getLocation().x;
    }

    public int getYCoordinate(final TileType tileType) {
        return gameBoard.getMovableBoardPiece(tileType).getLocation().y;
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

    public void handleLevelCompletion(Result result) {
        if (result == Result.WIN) {
            wins++;
            level++;
            regenerateLevel();
        } else if (result == Result.LOSE) {
            losses++;
            regenerateLevel();
        }
    }

    private void regenerateLevel() {
        levelCreator.createLevel(this, level);
    }
}
package parser;

import engine.GameEngine;
import tiles.TileType;

import java.util.Random;

public class RandomLevelCreator implements LevelCreator {
    private final Random random;
    private final int xDimension;
    private final int yDimension;
    private final int seed;

    public RandomLevelCreator(final Random random, final int seed, final int xDimension, final int yDimension) {
        this.random = random;
        this.seed = seed;
        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    @Override
    public void createLevel(final GameEngine gameEngine, final int level) {
        final TileType[][] gameBoard = new TileType[xDimension][yDimension];
        random.setSeed((long) seed + level);
        for (int x = 0; x < gameBoard.length; x++) {
            for (int y = 0; y < gameBoard[x].length; y++) {
                if (x == 0 || y == 0 || x == xDimension - 1 || y == yDimension - 1) {
                    gameBoard[x][y] = TileType.NOT_PASSABLE;
                } else {
                    gameBoard[x][y] = TileType.PASSABLE;
                }
            }
        }

        generateAndAddCoordinate(gameBoard, gameEngine, TileType.PLAYER);
        generateAndAddCoordinate(gameBoard, gameEngine, TileType.ENEMY);
        generateAndAddCoordinate(gameBoard, gameEngine, TileType.GOAL);

        gameEngine.setBoard(gameBoard);
    }

    private void generateAndAddCoordinate(final TileType[][] gameBoard, final GameEngine gameEngine, final TileType tileType) {
        int xCoordinate;
        int yCoordinate;
        do {
            xCoordinate = random.nextInt(xDimension - 2) + 1;
            yCoordinate = random.nextInt(yDimension - 2) + 1;
        } while (gameBoard[xCoordinate][yCoordinate] != TileType.PASSABLE);

        gameBoard[xCoordinate][yCoordinate] = tileType;
        gameEngine.setPlayableObject(xCoordinate, yCoordinate, tileType);
    }
}

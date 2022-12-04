package parser;

import board.GameBoard;
import board.piece.BoardPieceFactory;
import engine.GameEngine;
import enums.TileType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RandomLevelCreator extends LevelCreator {
    private static final Logger LOGGER = Logger.getLogger(RandomLevelCreator.class.getName());
    private final int xDimension;
    private final int yDimension;
    private final long seed;

    private final RandomWrapper random;

    private static final int NUMBER_OF_DEFAULT_OBJECTS = 3;

    public RandomLevelCreator(final BoardPieceFactory boardPieceFactory, RandomWrapper randomWrapper, final long seed, final int xDimension, final int yDimension) {
        super(boardPieceFactory);
        this.seed = seed;
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.random = randomWrapper;
    }

    @Override
    public void createLevel(final GameEngine gameEngine, final int level) {
        final GameBoard gameBoard = new GameBoard(boardPieceFactory, xDimension, yDimension);
        random.reset();
        random.setSeed(seed + level);

        generateBasicBoard(gameBoard);
        List<Point> emptyLocations = getEmptyLocations(gameBoard);
        int numberOfWalls = getNumberOfWall(level);

        if (enoughEmptyLocations(emptyLocations, numberOfWalls)) {
            generateBoardPiece(gameBoard, emptyLocations, TileType.PLAYER);
            generateBoardPiece(gameBoard, emptyLocations, TileType.ENEMY);
            generateBoardPiece(gameBoard, emptyLocations, TileType.GOAL);
            for (int wallCount = 0; wallCount < numberOfWalls; wallCount++) {
                generateBoardPiece(gameBoard, emptyLocations, TileType.WALL);
            }
        } else {
            LOGGER.log(Level.SEVERE, "Not enough space on board to generate objects");
            gameEngine.setExit(true);
        }
        gameEngine.setGameBoard(gameBoard);
    }

    private boolean enoughEmptyLocations(List<Point> emptyLocations, int numberOfWalls) {
        return emptyLocations.size() >= NUMBER_OF_DEFAULT_OBJECTS + numberOfWalls;
    }

    private void generateBoardPiece(GameBoard gameBoard, List<Point> emptyLocations, final TileType tileType) {
        int emptyLocationIndex = 0;
        if (emptyLocations.size() > 1) {
            emptyLocationIndex = generateNumberBetweenInclusive(0, emptyLocations.size());
        }
        Point location = emptyLocations.remove(emptyLocationIndex);
        gameBoard.addBoardPiece(tileType, location);
    }

    private void generateBasicBoard(GameBoard gameBoard) {
        for (int x = 0; x < xDimension; x++) {
            for (int y = 0; y < yDimension; y++) {
                Point point = new Point(x, y);
                TileType tileType = isEdge(x, y) ? TileType.WALL : TileType.EMPTY;
                gameBoard.addBoardPiece(tileType, point);
            }
        }
    }

    private List<Point> getEmptyLocations(GameBoard gameBoard) {
        List<Point> emptyLocations = new ArrayList<>();
        for (int x = 0; x < xDimension; x++) {
            for (int y = 0; y < yDimension; y++) {
                if (gameBoard.getBoardPieceFromCoordinates(x, y).getTileType() == TileType.EMPTY) {
                    emptyLocations.add(new Point(x, y));
                }
            }
        }
        return emptyLocations;
    }

    private boolean isEdge(int x, int y) {
        return x == 0 || y == 0 || x == xDimension - 1 || y == yDimension - 1;
    }

    private int generateNumberBetweenInclusive(int lower, int upper) {
        return random.nextInt(lower, upper - 1);
    }

    private int getNumberOfWall(int level) {
        return (int) ((xDimension * yDimension) * .04 * (level - 1));
    }
}

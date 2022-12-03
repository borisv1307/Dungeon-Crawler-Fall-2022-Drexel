package parser;

import board.GameBoard;
import board.piece.BoardPieceFactory;
import engine.GameEngine;
import enums.TileType;

import java.awt.*;
import java.security.SecureRandom;
import java.util.Random;

public class RandomLevelCreator extends LevelCreator {
    private final int xDimension;
    private final int yDimension;
    private final long seed;

    public RandomLevelCreator(final BoardPieceFactory boardPieceFactory, final long seed, final int xDimension, final int yDimension) {
        super(boardPieceFactory);
        this.seed = seed;
        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    @Override
    public void createLevel(final GameEngine gameEngine, final int level) {
        final GameBoard gameBoard = new GameBoard(boardPieceFactory, xDimension, yDimension);
        Random random = new SecureRandom();
        random.setSeed(seed + level);

        generateBasicBoard(gameBoard);
        generateBoardPiece(gameBoard, TileType.PLAYER, random);
        generateBoardPiece(gameBoard, TileType.ENEMY, random);
        generateBoardPiece(gameBoard, TileType.GOAL, random);

        gameEngine.setGameBoard(gameBoard);
    }

    private void generateBoardPiece(GameBoard gameBoard, final TileType tileType, Random random) {
        int xCoordinate;
        int yCoordinate;

        do {
            xCoordinate = generateNumberBetweenInclusive(1, xDimension - 1, random);
            yCoordinate = generateNumberBetweenInclusive(1, yDimension - 1, random);
        } while (gameBoard.getBoardPieceFromCoordinates(xCoordinate, yCoordinate).getTileType() != TileType.EMPTY);

        Point location = new Point(xCoordinate, yCoordinate);
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

    private boolean isEdge(int x, int y) {
        return x == 0 || y == 0 || x == xDimension - 1 || y == yDimension - 1;
    }

    private int generateNumberBetweenInclusive(int lower, int upper, Random random) {
        return random.nextInt(upper - lower) + lower;
    }
}

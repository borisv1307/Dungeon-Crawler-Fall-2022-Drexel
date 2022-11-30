package parser;

import board.GameBoard;
import board.piece.BoardPiece;
import board.piece.BoardPieceFactory;
import board.piece.Empty;
import board.piece.Wall;
import engine.GameEngine;
import enums.TileType;

import java.awt.*;
import java.util.Random;

public class RandomLevelCreator extends LevelCreator {
    private final Random random;
    private final int xDimension;
    private final int yDimension;
    private final int seed;

    public RandomLevelCreator(final BoardPieceFactory boardPieceFactory, final Random random, final int seed, final int xDimension, final int yDimension) {
        super(boardPieceFactory);
        this.random = random;
        this.seed = seed;
        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    @Override
    public void createLevel(final GameEngine gameEngine, final int level) {
        GameBoard gameBoard = new GameBoard();
        final BoardPiece[][] boardPieces = new BoardPiece[xDimension][yDimension];
        gameBoard.setBoardPieces(boardPieces);
        gameEngine.setGameBoard(gameBoard);
        random.setSeed((long) seed + level);
        for (int x = 0; x < boardPieces.length; x++) {
            for (int y = 0; y < boardPieces[x].length; y++) {
                if (x == 0 || y == 0 || x == xDimension - 1 || y == yDimension - 1) {
                    boardPieces[x][y] = new Wall(new Point(x, y));
                } else {
                    boardPieces[x][y] = new Empty(new Point(x, y));
                }
            }
        }

        generateAndAddCoordinate(boardPieces, gameBoard, TileType.PLAYER);
        generateAndAddCoordinate(boardPieces, gameBoard, TileType.ENEMY);
        generateAndAddCoordinate(boardPieces, gameBoard, TileType.GOAL);

        gameBoard.setBoardPieces(boardPieces);
    }

    private void generateAndAddCoordinate(final BoardPiece[][] boardPieces, final GameBoard gameBoard, final TileType tileType) {
        int xCoordinate;
        int yCoordinate;

        do {
            xCoordinate = random.nextInt(xDimension - 2) + 1;
            yCoordinate = random.nextInt(yDimension - 2) + 1;
        } while (boardPieces[xCoordinate][yCoordinate].getTileType() != TileType.EMPTY);

        final BoardPiece boardPiece = boardPieceFactory.getBoardPiece(tileType, new Point(xCoordinate, yCoordinate));
        boardPieces[xCoordinate][yCoordinate] = boardPiece;
        setBoardPiece(gameBoard, boardPiece);
    }
}

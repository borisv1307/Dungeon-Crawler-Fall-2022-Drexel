package board;

import board.piece.*;
import enums.Direction;
import enums.Result;
import enums.TileType;

import java.awt.*;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class GameBoard {
    private final BoardPiece[][] boardPieces;
    private final BoardPieceFactory boardPieceFactory;
    private final Map<TileType, MovableBoardPiece> movableBoardPieces;

    private Point goal;

    public GameBoard(BoardPieceFactory boardPieceFactory, int xDimension, int yDimension) {
        this.boardPieces = new BoardPiece[xDimension][yDimension];
        this.movableBoardPieces = new EnumMap<>(TileType.class);
        this.boardPieceFactory = boardPieceFactory;
    }

    public int getLevelHorizontalDimension() {
        return boardPieces.length;
    }

    public int getLevelVerticalDimension() {
        return boardPieces[0].length;
    }

    public BoardPiece getBoardPieceFromCoordinates(final int x, final int y) {
        return boardPieces[x][y];
    }

    public Result moveBoardPiece(final TileType tileType, final Direction direction) {
        final MovableBoardPiece movableBoardPiece = getMovableBoardPiece(tileType);
        final Point startingLocation = movableBoardPiece.getLocation();
        final Point attemptedLocation = new Point(startingLocation.x + direction.getDeltaX(), startingLocation.y + direction.getDeltaY());

        return attemptMovement(movableBoardPiece, attemptedLocation);
    }

    public MovableBoardPiece getMovableBoardPiece(final TileType tileType) {
        if (movableBoardPieces.containsKey(tileType)) {
            return movableBoardPieces.get(tileType);
        }
        throw new IllegalArgumentException(String.format("Movable Tile type %s not found on board", tileType));
    }

    private Result attemptMovement(MovableBoardPiece movableBoardPiece, Point attemptedLocation) {
        final List<Point> validMoves = movableBoardPiece.getValidMoves(boardPieces);
        Result result = Result.CONTINUE;
        if (validMoves.contains(attemptedLocation)) {
            result = move(movableBoardPiece, attemptedLocation);
        }
        return result;
    }

    private Result move(MovableBoardPiece movableBoardPiece, Point attemptedLocation) {
        Point startingLocation = movableBoardPiece.getLocation();
        TileType displacedTileType = movableBoardPiece.setLocation(attemptedLocation, boardPieces).getTileType();
        addBoardPiece(TileType.EMPTY, startingLocation);
        Result result = handleLevelCompletion(movableBoardPiece.getTileType(), displacedTileType);
        return result;
    }

    public Result moveEnemy() {
        Enemy enemy = (Enemy) getMovableBoardPiece(TileType.ENEMY);
        Point startingLocation = enemy.getLocation();

        Point attemptedLocation = enemy.getNextMove(boardPieces, getMovableBoardPiece(TileType.PLAYER).getLocation(), goal);

        Result result = Result.CONTINUE;
        if (attemptedLocation != startingLocation) {
            result = move(enemy, attemptedLocation);
        }
        return result;
    }

    private Result handleLevelCompletion(TileType tileType, TileType displacedTileType) {
        Result result = Result.CONTINUE;

        if (tileType == TileType.PLAYER && displacedTileType == TileType.GOAL) {
            result = Result.WIN;
        } else if (tileType == TileType.ENEMY && displacedTileType == TileType.PLAYER) {
            result = Result.LOSE;
        } else if (tileType == TileType.PLAYER && displacedTileType == TileType.ENEMY) {
            result = Result.LOSE;
        }

        return result;
    }

    public void addBoardPiece(TileType tileType, Point location) {
        final BoardPiece boardPiece = boardPieceFactory.getBoardPiece(tileType, location);
        boardPieces[location.x][location.y] = boardPieceFactory.getBoardPiece(tileType, location);
        if (boardPiece instanceof MovableBoardPiece) {
            movableBoardPieces.put(tileType, (MovableBoardPiece) boardPiece);
        } else if (boardPiece instanceof Goal) {
            goal = boardPiece.getLocation();
        }
    }
}

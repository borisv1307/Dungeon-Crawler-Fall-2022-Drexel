package board.piece;

import enums.TileType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public abstract class MovableBoardPiece extends BoardPiece {
    protected MovableBoardPiece(Point location, TileType tileType) {
        super(location, tileType);
    }

    public BoardPiece setLocation(Point location, BoardPiece[][] gameBoard) {
        this.location = location;
        BoardPiece displacedPiece = gameBoard[location.x][location.y];
        gameBoard[location.x][location.y] = this;

        return displacedPiece;
    }

    public List<Point> getValidMoves(BoardPiece[][] gameBoard) {
        return getSurroundingMoves(gameBoard, location);
    }

    protected List<Point> getSurroundingMoves(BoardPiece[][] gameBoard, Point point) {
        List<Point> validMoves = new ArrayList<>();
        validMoves.add(keyLeft(point));
        validMoves.add(keyRight(point));
        validMoves.add(keyDown(point));
        validMoves.add(keyUp(point));

        validMoves.removeIf(attemptedLocation -> !isValidMove(gameBoard, attemptedLocation));
        return validMoves;
    }

    protected abstract boolean isValidMove(BoardPiece[][] gameBoard, Point attemptedLocation);

    protected TileType getTileType(BoardPiece[][] gameBoard, Point location) {
        return gameBoard[location.x][location.y].getTileType();
    }

    private Point keyLeft(Point point) {
        return getPoint(point, -1, 0);
    }

    private Point keyRight(Point point) {
        return getPoint(point, 1, 0);
    }

    private Point keyUp(Point point) {
        return getPoint(point, 0, -1);
    }

    private Point keyDown(Point point) {
        return getPoint(point, 0, 1);
    }

    private Point getPoint(Point point, int deltaX, int deltaY) {
        return new Point(point.x + deltaX, point.y + deltaY);
    }
}

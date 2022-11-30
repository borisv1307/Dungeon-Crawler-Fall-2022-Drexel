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
        List<Point> validMoves = new ArrayList<>();
        validMoves.add(keyLeft());
        validMoves.add(keyRight());
        validMoves.add(keyDown());
        validMoves.add(keyUp());

        validMoves.removeIf(attemptedLocation -> !isValidMove(gameBoard, attemptedLocation));
        return validMoves;
    }

    protected abstract boolean isValidMove(BoardPiece[][] gameBoard, Point attemptedLocation);

    protected TileType getTileType(BoardPiece[][] gameBoard, Point location) {
        return gameBoard[location.x][location.y].getTileType();
    }

    private Point keyLeft() {
        return getPoint(-1, 0);
    }

    private Point keyRight() {
        return getPoint(1, 0);
    }

    private Point keyUp() {
        return getPoint(0, -1);
    }

    private Point keyDown() {
        return getPoint(0, 1);
    }

    private Point getPoint(int deltaX, int deltaY) {
        return new Point(location.x + deltaX, location.y + deltaY);
    }
}

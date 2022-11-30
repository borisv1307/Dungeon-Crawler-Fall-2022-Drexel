package board.piece;

import enums.TileType;

import java.awt.*;

public class Player extends MovableBoardPiece {
    private static final TileType TILE_TYPE = TileType.PLAYER;

    public Player(Point location) {
        super(location, TILE_TYPE);
    }

    @Override
    protected boolean isValidMove(BoardPiece[][] gameBoard, Point attemptedLocation) {
        TileType attemptedLocationType = getTileType(gameBoard, attemptedLocation);
        return attemptedLocationType != TileType.WALL;
    }
}

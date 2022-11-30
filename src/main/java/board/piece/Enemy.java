package board.piece;

import enums.TileType;

import java.awt.*;

public class Enemy extends MovableBoardPiece {
    private static final TileType TILE_TYPE = TileType.ENEMY;

    public Enemy(Point location) {
        super(location, TILE_TYPE);
    }

    @Override
    protected boolean isValidMove(BoardPiece[][] gameBoard, Point attemptedLocation) {
        TileType attemptedLocationType = getTileType(gameBoard, attemptedLocation);
        return attemptedLocationType != TileType.WALL && attemptedLocationType != TileType.GOAL;
    }
}

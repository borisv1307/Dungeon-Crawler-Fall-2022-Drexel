package board.piece;

import enums.TileType;

import java.awt.*;

public class BoardPieceFactory {
    private static final String INVALID_TILE_TYPE_PROVIDED_MESSAGE = "Invalid character provided: ";

    public BoardPiece getBoardPiece(TileType tileType, Point location) {
        switch (tileType) {
            case EMPTY:
                return new Empty(location);
            case PLAYER:
                return new Player(location);
            case GOAL:
                return new Goal(location);
            case ENEMY:
                return new Enemy(location);
            case WALL:
                return new Wall(location);
        }
        throw new IllegalArgumentException(INVALID_TILE_TYPE_PROVIDED_MESSAGE + tileType);
    }
}

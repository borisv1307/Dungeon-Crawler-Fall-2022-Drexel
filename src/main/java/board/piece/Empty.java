package board.piece;

import enums.TileType;

import java.awt.*;

public class Empty extends BoardPiece {
    private static final TileType TILE_TYPE = TileType.EMPTY;

    public Empty(Point location) {
        super(location, TILE_TYPE);
    }
}

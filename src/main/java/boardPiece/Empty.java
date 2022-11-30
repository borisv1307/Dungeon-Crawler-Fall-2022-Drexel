package boardPiece;

import enums.TileType;

import java.awt.*;

public class Empty extends BoardPiece {
    private final static TileType TILE_TYPE = TileType.EMPTY;

    public Empty(Point location) {
        super(location, TILE_TYPE);
    }
}

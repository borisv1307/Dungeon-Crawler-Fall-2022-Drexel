package BoardPiece;

import enums.TileType;

import java.awt.*;

public class Empty extends BoardPiece {
    final private static TileType TILE_TYPE = TileType.EMPTY;

    public Empty(Point location) {
        super(location, TILE_TYPE);
    }
}

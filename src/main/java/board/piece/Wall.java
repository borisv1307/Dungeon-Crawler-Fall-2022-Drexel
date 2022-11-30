package board.piece;

import enums.TileType;

import java.awt.*;

public class Wall extends BoardPiece {
    private static final TileType TILE_TYPE = TileType.WALL;

    public Wall(Point location) {
        super(location, TILE_TYPE);
    }
}

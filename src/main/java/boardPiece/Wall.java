package boardPiece;

import enums.TileType;

import java.awt.*;

public class Wall extends BoardPiece {
    private final static TileType TILE_TYPE = TileType.WALL;

    public Wall(Point location) {
        super(location, TILE_TYPE);
    }
}

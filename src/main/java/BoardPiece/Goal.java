package BoardPiece;

import enums.TileType;

import java.awt.*;

public class Goal extends BoardPiece {
    final private static TileType TILE_TYPE = TileType.GOAL;

    public Goal(Point location) {
        super(location, TILE_TYPE);
    }
}

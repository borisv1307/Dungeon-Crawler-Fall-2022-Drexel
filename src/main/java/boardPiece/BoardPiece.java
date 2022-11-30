package boardPiece;

import enums.TileType;

import java.awt.*;

public abstract class BoardPiece {
    protected final TileType tileType;
    protected Point location;

    public BoardPiece(Point location, TileType tileType) {
        this.location = location;
        this.tileType = tileType;
    }

    public Point getLocation() {
        return location;
    }

    public TileType getTileType() {
        return tileType;
    }
}

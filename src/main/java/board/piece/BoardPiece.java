package board.piece;

import enums.TileType;

import java.awt.*;

public abstract class BoardPiece {
    protected final TileType tileType;
    protected Point location;

    protected BoardPiece(Point location, TileType tileType) {
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

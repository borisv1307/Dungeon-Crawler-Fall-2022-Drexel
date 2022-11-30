package entities;

import tiles.TileType;

public class Kobold extends Enemy {
    public Kobold(int x, int y) {
        super(x, y);
        setStartingStats(3, 1, 3);
        setTileType(TileType.KOBOLD);
    }
}

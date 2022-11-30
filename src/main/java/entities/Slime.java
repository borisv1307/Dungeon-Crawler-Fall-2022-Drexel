package entities;

import tiles.TileType;

public class Slime extends Enemy {
    public Slime(int x, int y) {
        super(x, y);
        setStartingStats(2, 0, 2);
        setTileType(TileType.SLIME);
    }
}

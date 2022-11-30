package entities;

import tiles.TileType;

public class Orc extends Enemy {
    public Orc(int x, int y) {
        super(x, y);
        setStartingStats(5, 2, 4);
        setTileType(TileType.ORC);
    }
}

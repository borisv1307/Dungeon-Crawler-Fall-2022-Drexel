package entities;

import tiles.TileType;

public class Kobold extends Enemy{
    public Kobold(int x, int y){
        super(x, y);
        setStartingStats(7, 1, 2);
        setTileType(TileType.KOBOLD);
    }
}

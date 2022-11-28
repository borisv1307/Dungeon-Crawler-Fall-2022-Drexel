package entities;

import tiles.TileType;

public class Slime extends Enemy{
    public Slime(int x, int y){
        super(x, y);
        setStartingStats(5, 0, 1);
        setTileType(TileType.SLIME);
    }
}

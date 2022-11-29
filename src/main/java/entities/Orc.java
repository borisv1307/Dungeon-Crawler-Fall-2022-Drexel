package entities;

import tiles.TileType;

public class Orc extends Enemy{
    public Orc(int x, int y){
        super(x, y);
        setStartingStats(5, 2, 3);
        setTileType(TileType.ORC);
    }
}

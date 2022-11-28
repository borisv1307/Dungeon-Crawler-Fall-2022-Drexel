package entities;

import tiles.TileType;

public class Orc extends Enemy{
    public Orc(int x, int y){
        super(x, y);
        setStartingStats(10, 3, 3);
        setTileType(TileType.ORC);
    }
}

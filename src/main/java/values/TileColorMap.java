package values;

import tiles.TileType;

import java.awt.*;
import java.util.EnumMap;

public final class TileColorMap {
    private static final EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

    static {
        tileColors.put(TileType.PASSABLE, Color.WHITE);
        tileColors.put(TileType.NOT_PASSABLE, Color.BLACK);
        tileColors.put(TileType.PLAYER, Color.GREEN);
        tileColors.put(TileType.DOOR, Color.ORANGE);
        tileColors.put(TileType.KEY, Color.BLUE);
        tileColors.put(TileType.COLLECTIBLE, Color.PINK);
        tileColors.put(TileType.PORTAL, Color.GRAY);
        tileColors.put(TileType.ENEMY, Color.RED);
    }


    private TileColorMap() {
    }


    public static Color get(TileType key) {
        return tileColors.get(key);
    }

}
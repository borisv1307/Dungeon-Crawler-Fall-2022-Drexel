package values;

import enums.TileType;

import java.awt.*;
import java.util.EnumMap;

public final class TileColorMap {
    private static final EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

    static {
        tileColors.put(TileType.EMPTY, Color.WHITE);
        tileColors.put(TileType.WALL, Color.BLACK);
        tileColors.put(TileType.PLAYER, Color.GREEN);
    }

    private TileColorMap() {
    }

    public static Color get(TileType key) {
        return tileColors.get(key);
    }
}
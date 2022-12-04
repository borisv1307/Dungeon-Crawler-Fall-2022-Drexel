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
        tileColors.put(TileType.PASSABLE_HELPFUL, Color.pink);
        tileColors.put(TileType.PASSABLE_HARMFUL, Color.red);
        tileColors.put(TileType.PASSABLE_HIDDEN, Color.black);
        tileColors.put(TileType.PASSABLE_HARMFUL_HIDDEN, Color.black);
        tileColors.put(TileType.PASSABLE_HELPFUL_HIDDEN, Color.black);
    }

    private TileColorMap() {
    }

    public static Color get(TileType key) {
        return tileColors.get(key);
    }
}
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
        tileColors.put(TileType.HEALING, Color.CYAN);
        tileColors.put(TileType.TRANSIENT_HEALING, Color.BLUE);
        tileColors.put(TileType.DAMAGE, Color.RED);
        tileColors.put(TileType.TRANSIENT_DAMAGE, Color.DARK_GRAY);
        tileColors.put(TileType.REGEN, Color.MAGENTA);
        tileColors.put(TileType.TRANSIENT_REGEN, Color.PINK);
        tileColors.put(TileType.DRAIN, Color.ORANGE);
        tileColors.put(TileType.TRANSIENT_DRAIN, Color.YELLOW);
    }

    private TileColorMap() {
    }

    public static Color get(TileType key) {
        return tileColors.get(key);
    }
}
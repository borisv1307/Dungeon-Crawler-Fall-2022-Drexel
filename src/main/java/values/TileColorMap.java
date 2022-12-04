package values;

import tiles.TileType;

import java.awt.*;
import java.util.EnumMap;

public final class TileColorMap {
    private static EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

    static {
        tileColors.put(TileType.PASSABLE, Color.WHITE);
        tileColors.put(TileType.NOT_PASSABLE, Color.BLACK);
        tileColors.put(TileType.PLAYER, Color.GREEN);
        tileColors.put(TileType.COIN, Color.YELLOW);
    }

    private TileColorMap() {
    }

    public static Color get(TileType key) {
        return tileColors.get(key);
    }

    public static void updatePlayerColor(Color newPlayerColor) {
        tileColors.put(TileType.PLAYER, newPlayerColor);
    }
}
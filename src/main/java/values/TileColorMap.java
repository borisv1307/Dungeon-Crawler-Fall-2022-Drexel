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
        tileColors.put(TileType.RIGHT_SHOOTER, Color.red);
        tileColors.put(TileType.LEFT_SHOOTER, Color.red);
        tileColors.put(TileType.UP_SHOOTER, Color.red);
        tileColors.put(TileType.DOWN_SHOOTER, Color.red);
    }

    private TileColorMap() {
    }

    public static Color get(TileType key) {
        return tileColors.get(key);
    }

    public static Color projectileColor() {
        return Color.blue;
    }
}
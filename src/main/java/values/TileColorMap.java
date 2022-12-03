package values;

import entity.Player;
import tiles.TileType;

import java.awt.*;
import java.util.EnumMap;

public final class TileColorMap {
    private static final EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

    static {
        tileColors.put(TileType.PASSABLE, Color.WHITE);
        tileColors.put(TileType.NOT_PASSABLE, Color.BLACK);
        tileColors.put(TileType.PLAYER, Color.GREEN);
        tileColors.put(TileType.ENEMY, Color.RED);
        tileColors.put(TileType.NEXT_LEVEL, Color.pink);
    }

    private TileColorMap() {
    }

    public static Color get(TileType key) {
        return tileColors.get(key);
    }

    public static void changePlayerHpBar(Player player) {
        int playerCurrentHealth = player.getCurrentHealthPoint();
        int maxHealthPoint = player.getMaxHealthPoint();
        int quarter = maxHealthPoint / 4;
        int half = maxHealthPoint / 2;
        int threeQuarter = quarter * 3;

        if (threeQuarter < playerCurrentHealth && playerCurrentHealth <= maxHealthPoint) {
            tileColors.put(TileType.PLAYER, Color.GREEN);
        }
        if (half < playerCurrentHealth && playerCurrentHealth <= threeQuarter) {
            tileColors.put(TileType.PLAYER, Color.ORANGE);
        }
        if (quarter < playerCurrentHealth && playerCurrentHealth <= half) {
            tileColors.put(TileType.PLAYER, Color.BLUE);
        }
        if (playerCurrentHealth <= quarter) {
            tileColors.put(TileType.PLAYER, Color.LIGHT_GRAY);
        }

    }

}

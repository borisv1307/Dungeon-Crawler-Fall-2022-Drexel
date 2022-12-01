package values;

import java.awt.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import tiles.TileType;

public final class TileColorMap {
	private static final EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);
	private static final Map<Integer, Color> playerColors = new HashMap<>();

	static {
		tileColors.put(TileType.PASSABLE, Color.WHITE);
		tileColors.put(TileType.NOT_PASSABLE, Color.BLACK);
		tileColors.put(TileType.PLAYER, Color.GREEN);
		tileColors.put(TileType.PROJECTILE, Color.BLUE);
		tileColors.put(TileType.ENEMY, Color.RED);
		tileColors.put(TileType.ENEMY_PROJECTILE, Color.GRAY);
	}

	static {
		playerColors.put(5, Color.GREEN);
		playerColors.put(4, Color.ORANGE);
		playerColors.put(3, Color.PINK);
		playerColors.put(2, Color.YELLOW);
		playerColors.put(1, Color.MAGENTA);
		playerColors.put(0, Color.DARK_GRAY);
	}

	private TileColorMap() {
	}

	private TileColorMap() {
	}

	public static Color get(TileType key) {
		return tileColors.get(key);
	}

	public static Color getPlayerColor(int lives) {
		if (playerColors.containsKey(lives)) {
			return playerColors.get(lives);
		}

		return Color.DARK_GRAY;
	}
}
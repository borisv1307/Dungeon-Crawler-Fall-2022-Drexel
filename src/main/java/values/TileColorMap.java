package values;

import java.awt.*;
import java.util.EnumMap;

import tiles.TileType;

public final class TileColorMap {
	private static final EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

	static {
		tileColors.put(TileType.PASSABLE, Color.WHITE);
		tileColors.put(TileType.NOT_PASSABLE, Color.BLACK);
		tileColors.put(TileType.PLAYER, Color.GREEN);
	}

	private TileColorMap() {
	}

	public static Color get(TileType key) {
		return tileColors.get(key);
	}
}
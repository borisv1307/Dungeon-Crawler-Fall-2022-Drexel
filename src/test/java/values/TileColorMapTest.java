package values;

import static org.junit.Assert.assertSame;

import java.awt.*;

import org.junit.Test;

import tiles.TileType;

public class TileColorMapTest {

	@Test
	public void passable() {
		assertSame(Color.WHITE, TileColorMap.get(TileType.PASSABLE));
	}

	@Test
	public void not_passable() {
		assertSame(Color.BLACK, TileColorMap.get(TileType.NOT_PASSABLE));
	}

	@Test
	public void player() {
		assertSame(Color.BLUE, TileColorMap.get(TileType.PLAYER));
	}

	@Test
	public void goal() {
		assertSame(Color.YELLOW, TileColorMap.get(TileType.GOAL));
	}

}

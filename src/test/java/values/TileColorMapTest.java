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
		assertSame(Color.GREEN, TileColorMap.get(TileType.PLAYER));
	}

	@Test
	public void dragon() {
		assertSame(Color.RED, TileColorMap.get(TileType.DRAGON));
	}

	@Test
	public void potion() {
		assertSame(Color.CYAN, TileColorMap.get(TileType.POTION));
	}

	@Test
	public void near() {
		assertSame(Color.MAGENTA, TileColorMap.get(TileType.NEAR));
	}

	@Test
	public void visited() {
		assertSame(Color.yellow, TileColorMap.get(TileType.VISITED));
	}

}

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
	public void projectile() {
		assertSame(Color.BLUE, TileColorMap.get(TileType.PROJECTILE));
	}

	@Test
	public void enemy() {
		assertSame(Color.RED, TileColorMap.get(TileType.ENEMY));
	}

	@Test
	public void enemy_projectile() {
		assertSame(Color.GRAY, TileColorMap.get(TileType.ENEMY_PROJECTILE));
	}

	@Test
	public void fives_lives_is_green() {
		assertSame(Color.GREEN, TileColorMap.getPlayerColor(5));
	}

	@Test
	public void non_existent_play_lives_is_dark_gray() {
		assertSame(Color.DARK_GRAY, TileColorMap.getPlayerColor(-12));
	}
}

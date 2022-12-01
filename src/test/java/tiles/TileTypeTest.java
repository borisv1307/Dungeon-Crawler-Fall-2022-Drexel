package tiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TileTypeTest {

	private static final char INVALID_CHAR = 'Z';
	private static final char VALID_CHAR = ' ';
	private static final char PROJECTILE = 'O';
	private static final char ENEMY = 'E';
	private static final char ENEMY_PROJECTILE = 'I';

	@Test
	public void value_of() {
		assertThat(TileType.valueOf(TileType.PASSABLE.name()), equalTo(TileType.PASSABLE));
	}

	@Test
	public void get_tile_type_by_char_valid_char() {
		TileType actual = TileType.getTileTypeByChar(VALID_CHAR);
		assertEquals(TileType.PASSABLE, actual);
	}

	@Test
	public void get_tile_type_by_char_invalid_char() {
		try {
			TileType.getTileTypeByChar(INVALID_CHAR);
		} catch (IllegalArgumentException exception) {
			assertEquals(exception.getMessage(), TileType.INVALID_CHARACTER_PROVIDED_MESSAGE + "Z");
		}
	}

	@Test
	public void projectile_is_O() {
		TileType projectile = TileType.getTileTypeByChar(PROJECTILE);
		assertEquals(TileType.PROJECTILE, projectile);
	}

	@Test
	public void enemy_is_E() {
		TileType enemy = TileType.getTileTypeByChar(ENEMY);
		assertEquals(TileType.ENEMY, enemy);
	}

	@Test
	public void enemy_projectile_is_I() {
		TileType enemy_projectile = TileType.getTileTypeByChar(ENEMY_PROJECTILE);
		assertEquals(TileType.ENEMY_PROJECTILE, enemy_projectile);
	}
}

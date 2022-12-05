package tiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TileTypeTest {

	private static final char INVALID_CHAR = 'Q';
	private static final char VALID_CHAR = ' ';

	private static final char POWERBALL = 'Z';

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
			assertEquals(exception.getMessage(), TileType.INVALID_CHARACTER_PROVIDED_MESSAGE + "Q");
		}
	}

	@Test
	public void get_power_ball_tile_type_by_char_Z(){
		TileType actual = TileType.getTileTypeByChar(POWERBALL);
		assertEquals(TileType.POWERBALL, actual);
	}
}

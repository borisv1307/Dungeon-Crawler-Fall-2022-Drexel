package tiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TileTypeTest {

	private static final char INVALID_CHAR = 'Z';
	private static final char VALID_CHAR = ' ';

	private static final char DRAGON_CHAR = 'D';
	private static final char POTION_CHAR = 'M';
	private static final char BRICK_CHAR = 'B';



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
	public void get_tile_type_by_char_dragon(){
		TileType actual = TileType.getTileTypeByChar(DRAGON_CHAR);
		assertEquals(TileType.DRAGON, actual);
	}

	@Test
	public void get_tile_type_by_char_potion(){
		TileType actual = TileType.getTileTypeByChar(POTION_CHAR);
		assertEquals(TileType.POTION, actual);
	}
	@Test
	public void get_tile_type_by_char_brick(){
		TileType actual = TileType.getTileTypeByChar(BRICK_CHAR);
		assertEquals(TileType.BRICK, actual);
	}

	@Test
	public void get_tile_type_by_char_invalid_char() {
		try {
			TileType.getTileTypeByChar(INVALID_CHAR);
		} catch (IllegalArgumentException exception) {
			assertEquals(exception.getMessage(), TileType.INVALID_CHARACTER_PROVIDED_MESSAGE + "Z");
		}
	}
}

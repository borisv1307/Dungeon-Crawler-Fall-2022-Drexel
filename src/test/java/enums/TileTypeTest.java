package enums;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class TileTypeTest {

    private static final char INVALID_CHAR = 'Z';
    private static final char VALID_CHAR = ' ';

    @Test
    public void value_of() {
        assertThat(TileType.valueOf(TileType.EMPTY.name()), equalTo(TileType.EMPTY));
    }

    @Test
    public void get_tile_type_by_char_valid_char() {
        TileType actual = TileType.getTileTypeByChar(VALID_CHAR);
        assertEquals(TileType.EMPTY, actual);
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

package tiles;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class TileTypeTest {

    private static final char INVALID_CHAR = 'Z';
    private static final char VALID_CHAR = ' ';
    private static final char DOOR = 'D';
    private static final char KEY = 'K';
    private static final char COLLECTIBLE = 'C';
    //private static final char ENEMY = 'E';

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
    public void get_door_tile_type_by_char_D() {
        TileType actual = TileType.getTileTypeByChar(DOOR);
        assertEquals(TileType.DOOR, actual);
    }

    @Test
    public void get_key_tile_type_by_char_K() {
        TileType actual = TileType.getTileTypeByChar(KEY);
        assertEquals(TileType.KEY, actual);
    }

    @Test
    public void get_collectible_tile_type_by_char_C() {
        TileType actual = TileType.getTileTypeByChar(COLLECTIBLE);
        assertEquals(TileType.COLLECTIBLE, actual);
    }

//    @Test
//    public void get_enemy_tile_type_by_char_E() {
//        TileType actual = TileType.getTileTypeByChar(ENEMY);
//        assertEquals(TileType.ENEMY, actual);
//    }
}

package values;

import enums.TileType;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertSame;

public class TileColorMapTest {

    @Test
    public void passable() {
        assertSame(Color.WHITE, TileColorMap.get(TileType.EMPTY));
    }

    @Test
    public void not_passable() {
        assertSame(Color.BLACK, TileColorMap.get(TileType.WALL));
    }

    @Test
    public void player() {
        assertSame(Color.GREEN, TileColorMap.get(TileType.PLAYER));
    }

}

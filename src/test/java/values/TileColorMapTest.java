package values;

import org.junit.Test;
import tiles.TileType;

import java.awt.*;

import static org.junit.Assert.assertSame;

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
    public void left_shooter() {
        assertSame(Color.red, TileColorMap.get(TileType.LEFT_SHOOTER));
    }

    @Test
    public void right_shooter() {
        assertSame(Color.red, TileColorMap.get(TileType.RIGHT_SHOOTER));
    }

    @Test
    public void up_shooter() {
        assertSame(Color.red, TileColorMap.get(TileType.UP_SHOOTER));
    }

    @Test
    public void down_shooter() {
        assertSame(Color.red, TileColorMap.get(TileType.DOWN_SHOOTER));
    }

}

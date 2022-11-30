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
    public void healing() {
        assertSame(Color.CYAN, TileColorMap.get(TileType.HEALING));
    }

    @Test
    public void transient_healing() {
        assertSame(Color.BLUE, TileColorMap.get(TileType.TRANSIENT_HEALING));
    }

    @Test
    public void damage() {
        assertSame(Color.RED, TileColorMap.get(TileType.DAMAGE));
    }

    @Test
    public void transient_damage() {
        assertSame(Color.DARK_GRAY, TileColorMap.get(TileType.TRANSIENT_DAMAGE));
    }

    @Test
    public void regen() {
        assertSame(Color.MAGENTA, TileColorMap.get(TileType.REGEN));
    }

    @Test
    public void transient_regen() {
        assertSame(Color.PINK, TileColorMap.get(TileType.TRANSIENT_REGEN));
    }

    @Test
    public void drain() {
        assertSame(Color.ORANGE, TileColorMap.get(TileType.DRAIN));
    }

    @Test
    public void transient_drain() {
        assertSame(Color.YELLOW, TileColorMap.get(TileType.TRANSIENT_DRAIN));
    }

}

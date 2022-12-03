package tiles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerPointTest {

    @Test
    public void harmful_tile() {
        PlayerPoint player = new PlayerPoint(3, 2, 10);
        player.damage();
        assertEquals(9, player.getHitpoints());
    }

    @Test
    public void helpful_tile() {
        PlayerPoint player = new PlayerPoint(3, 2, 0);
        player.heal();
        assertEquals(1, player.getHitpoints());
    }

    @Test
    public void harmful_tile_lower_limit() {
        PlayerPoint player = new PlayerPoint(3, 2, 0);
        player.damage();
        assertEquals(0, player.getHitpoints());
    }

    @Test
    public void helpful_tile_upper_limit() {
        PlayerPoint player = new PlayerPoint(3, 2, 10);
        player.heal();
        assertEquals(10, player.getHitpoints());
    }
}

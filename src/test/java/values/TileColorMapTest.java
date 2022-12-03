package values;

import entity.Player;
import org.junit.Before;
import org.junit.Test;
import tiles.TileType;

import java.awt.*;

import static org.junit.Assert.assertSame;

public class TileColorMapTest {

    Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

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
    public void enemy() {
        assertSame(Color.RED, TileColorMap.get(TileType.ENEMY));
    }

    @Test
    public void next_level() {
        assertSame(Color.PINK, TileColorMap.get(TileType.NEXT_LEVEL));
    }

    @Test
    public void player_hp_color_no_change() {
        player.takeDamage(0);
        TileColorMap.changePlayerHpBar(player);
        assertSame(Color.GREEN, TileColorMap.get(TileType.PLAYER));
    }

    @Test
    public void player_hp_color_took_one_damage() {
        player.takeDamage(1);
        TileColorMap.changePlayerHpBar(player);
        assertSame(Color.GREEN, TileColorMap.get(TileType.PLAYER));
    }

    @Test
    public void player_hp_color_change_orange() {
        player.takeDamage(4);
        TileColorMap.changePlayerHpBar(player);
        assertSame(Color.ORANGE, TileColorMap.get(TileType.PLAYER));
    }

    @Test
    public void player_hp_color_change_blue() {
        player.takeDamage(5);
        TileColorMap.changePlayerHpBar(player);
        assertSame(Color.BLUE, TileColorMap.get(TileType.PLAYER));
    }

    @Test
    public void player_hp_color_change_light_gray() {
        player.takeDamage(8);
        TileColorMap.changePlayerHpBar(player);
        assertSame(Color.LIGHT_GRAY, TileColorMap.get(TileType.PLAYER));
    }

    @Test
    public void player_hp_color_in_negative() {
        player.takeDamage(11);
        TileColorMap.changePlayerHpBar(player);
        assertSame(Color.LIGHT_GRAY, TileColorMap.get(TileType.PLAYER));
    }
}

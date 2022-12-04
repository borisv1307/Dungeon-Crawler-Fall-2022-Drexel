package entity;

import org.junit.Before;
import org.junit.Test;
import tiles.TileType;
import values.TileColorMap;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void create_basic_player_object() {
        assertEquals(10, player.getMaxHealthPoint());
        assertEquals(10, player.getAttackPoint());
        assertEquals(player.getMaxHealthPoint(), player.getCurrentHealthPoint());
    }

    @Test
    public void player_hp_color_change_when_level_up() {
        player.levelUp();
        TileColorMap.changePlayerHpBar(player);
        assertEquals(Color.BLUE, TileColorMap.get(TileType.PLAYER));
    }

    @Test
    public void player_level_up_1_time() {
        player.levelUp();
        assertEquals(2, player.getLevel());
    }

    @Test
    public void player_level_up_2_times() {
        levelUpXTime(2);
        assertEquals(3, player.getLevel());
    }

    @Test
    public void player_level_up_10_times() {
        levelUpXTime(10);
        assertEquals(11, player.getLevel());
    }

    @Test
    public void player_level_up_100_times() {
        levelUpXTime(100);
        assertEquals(101, player.getLevel());
    }

    @Test
    public void level_1_player_stats() {
        assertEquals(10, player.getAttackPoint());
        assertEquals(10, player.getMaxHealthPoint());
        assertEquals(10, player.getCurrentHealthPoint());

    }

    @Test
    public void level_3_player_stats() {
        levelUpXTime(2);
        assertEquals(25, player.getMaxHealthPoint());
        assertEquals(16, player.getAttackPoint());
    }

    @Test
    public void level_100_player_stats() {
        levelUpXTime(99);
        assertEquals(510, player.getMaxHealthPoint());
        assertEquals(210, player.getAttackPoint());
    }

    @Test
    public void player_take_damage() {
        player.takeDamage(5);
        assertEquals(5, player.getCurrentHealthPoint());
    }

    @Test
    public void reset_player_status_level() {
        player.resetPlayerStatus();
        assertEquals(1, player.getLevel());
    }

    @Test
    public void reset_player_status_attack() {
        player.resetPlayerStatus();
        assertEquals(10, player.getAttackPoint());
    }

    @Test
    public void reset_player_status_max_health_point() {
        player.resetPlayerStatus();
        assertEquals(10, player.getMaxHealthPoint());
    }

    @Test
    public void reset_player_status_current_health_point() {
        player.resetPlayerStatus();
        assertEquals(10, player.getCurrentHealthPoint());
    }

    @Test
    public void reset_player_status_player_color_green() {
        player.resetPlayerStatus();
        assertEquals(Color.GREEN, TileColorMap.get(TileType.PLAYER));
    }

    private void levelUpXTime(int level) {
        for (int time = 0; time < level; time++) {
            player.levelUp();
        }
    }
}


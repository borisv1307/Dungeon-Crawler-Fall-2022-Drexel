package entity;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void create_basic_player() {
        assertEquals(10, player.getHealthPoint());
        assertEquals(10, player.getAttackPoint());
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
        assertEquals(10, player.getHealthPoint());
    }

    @Test
    public void level_3_player_stats() {
        levelUpXTime(2);
        assertEquals(25, player.getHealthPoint());
        assertEquals(16, player.getAttackPoint());
    }

    @Test
    public void level_100_player_stats() {
        levelUpXTime(99);
        assertEquals(510, player.getHealthPoint());
        assertEquals(210, player.getAttackPoint());
    }

    private void levelUpXTime(int level) {
        for (int time = 0; time < level; time++) {
            player.levelUp();
        }
    }
}


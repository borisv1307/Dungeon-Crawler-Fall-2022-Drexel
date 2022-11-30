package entities;

import org.junit.Before;
import org.junit.Test;
import tiles.TileType;
import values.TileColorMap;

import java.awt.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class EntityTest {
    Entity enemy;
    Entity player;

    @Before
    public void setUp() throws Exception {
        enemy = new Kobold(0, 0);
        player = new Player(1, 1);
    }

    @Test
    public void deal_damage_enemy() {
        int currentHitPoints = enemy.getHitPoints();
        int enemyArmorClass = enemy.getArmorClass();
        int attackValue = 3;
        int newHitPoints = currentHitPoints - (attackValue - enemyArmorClass);
        int actualHitPoints = enemy.receiveDamage(attackValue);
        assertThat(newHitPoints, equalTo(actualHitPoints));
    }

    @Test
    public void deal_damage_player() {
        int currentHitPoints = player.getHitPoints();
        int playerArmorClass = player.getArmorClass();
        int attackValue = 3;
        int newHitPoints = currentHitPoints - (attackValue - playerArmorClass);
        int actualHitPoints = player.receiveDamage(attackValue);
        assertThat(newHitPoints, equalTo(actualHitPoints));
    }

    @Test
    public void copy_player_to_new_location() {
        int x = 2;
        int y = 2;
        Player newPlayer = ((Player) player).copyPlayerToNewLocation(x, y);
        assertEquals(true, newPlayer.equals(player));
        assertNotEquals(player.getX(), newPlayer.getX());
        assertNotEquals(player.getY(), newPlayer.getY());
    }

    @Test
    public void kobold_not_equal_to_orc() {
        Entity kobold = new Kobold(0, 0);
        Entity orc = new Orc(0, 0);
        assertEquals(false, kobold.equals(orc));
    }

    @Test
    public void kobold_equals_kobold() {
        Entity kobold = new Kobold(0, 0);
        Entity koboldOther = new Kobold(0, 0);
        assertEquals(true, kobold.equals(koboldOther));
    }

    @Test
    public void get_tile_type_from_enemy_object() {
        Color tileColor = TileColorMap.get(TileType.KOBOLD);
        Color actual = TileColorMap.get(enemy.getTileType());
        assertThat(tileColor, equalTo(actual));
    }
}

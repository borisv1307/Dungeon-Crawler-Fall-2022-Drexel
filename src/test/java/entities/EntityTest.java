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
    Player player;

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
        Player player = new Player(0, 0);
        String playerUniqueId = player.uniqueId.toString();
        player.move(x, y);
        String playerMovedUniqueId = player.uniqueId.toString();
        assertNotEquals(0, player.getX());
        assertNotEquals(0, player.getY());
        assertEquals(playerUniqueId, playerMovedUniqueId);
    }

    @Test
    public void kobold_not_equal_to_orc() {
        Entity kobold = new Kobold(0, 0);
        Entity orc = new Orc(0, 0);
        assertEquals(false, kobold.equals(orc));
    }

    @Test
    public void kobold_does_not_equal_new_kobold() {
        Entity kobold = new Kobold(0, 0);
        Entity koboldOther = new Kobold(0, 0);
        assertEquals(false, kobold.equals(koboldOther));
    }

    @Test
    public void player_not_equal_to_new_player() {
        Entity player = new Player(0, 0);
        Entity playerOther = new Player(0, 0);
        assertEquals(false, player.equals(playerOther));
    }

    @Test
    public void get_tile_type_from_enemy_object() {
        Color tileColor = TileColorMap.get(TileType.KOBOLD);
        Color actual = TileColorMap.get(enemy.getTileType());
        assertThat(tileColor, equalTo(actual));
    }

    @Test
    public void kobold_name_is_kobold() {
        assertEquals("Kobold", enemy.getName());
    }

}

package creatures;

import org.junit.Before;
import org.junit.Test;
import tiles.TileType;
import values.TileColorMap;

import java.awt.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class CreatureTest {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private Creature enemy;
    private Player player;

    @Before
    public void setUp() throws Exception {
        enemy = new Kobold(ZERO, ZERO);
        player = new Player(ONE, ONE);
    }

    @Test
    public void deal_damage_enemy() {
        int currentHitPoints = enemy.getHitPoints();
        int enemyArmorClass = enemy.getArmorClass();
        int attackValue = player.getAttackValue();
        int newHitPoints = currentHitPoints - (attackValue - enemyArmorClass);
        int actualHitPoints = enemy.receiveDamage(attackValue);
        assertThat(newHitPoints, equalTo(actualHitPoints));
    }

    @Test
    public void deal_damage_player() {
        int currentHitPoints = player.getHitPoints();
        int playerArmorClass = player.getArmorClass();
        int attackValue = enemy.getAttackValue();
        int newHitPoints = currentHitPoints - (attackValue - playerArmorClass);
        int actualHitPoints = player.receiveDamage(attackValue);
        assertThat(newHitPoints, equalTo(actualHitPoints));
    }

    @Test
    public void copy_player_to_new_location() {
        int x = ONE;
        int y = ONE;
        Player player = new Player(ZERO, ZERO);
        String playerUniqueId = player.uniqueId.toString();
        player.move(x, y);
        String playerMovedUniqueId = player.uniqueId.toString();
        assertNotEquals(ZERO, (int) player.getX());
        assertNotEquals(ZERO, (int) player.getY());
        assertEquals(playerUniqueId, playerMovedUniqueId);
    }

    @Test
    public void kobold_not_equal_to_orc() {
        Creature kobold = new Kobold(ZERO, ZERO);
        Creature orc = new Orc(ZERO, ZERO);
        assertEquals(false, kobold.equals(orc));
    }

    @Test
    public void kobold_does_not_equal_new_kobold() {
        Creature kobold = new Kobold(ZERO, ZERO);
        Creature koboldOther = new Kobold(ZERO, ZERO);
        assertEquals(false, kobold.equals(koboldOther));
    }

    @Test
    public void player_not_equal_to_new_player() {
        Creature player = new Player(ZERO, ZERO);
        Creature playerOther = new Player(ZERO, ZERO);
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

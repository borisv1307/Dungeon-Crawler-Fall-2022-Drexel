package entities;

import gherkin.lexer.En;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Or;
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
        enemy = new Kobold(0,0);
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
    public void kobold_not_equal_to_orc(){
        Entity kobold = new Kobold(0, 0);
        Entity orc = new Orc(0,0);
        assertFalse(kobold.equals(orc));
    }

    @Test
    public void get_tile_type_from_enemy_object(){
        Color tileColor = TileColorMap.get(TileType.KOBOLD);
        Color actual = TileColorMap.get(enemy.getTileType());
        assertThat(tileColor, equalTo(actual));
    }
}

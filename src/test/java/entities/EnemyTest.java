package entities;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import values.TileColorMap;

import java.awt.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class EnemyTest {
    Enemy enemy;

    @Before
    public void setUp() throws Exception {
        enemy = new Kobold(0,0);
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
    public void get_tile_type_from_enemy_object(){
        Color tileColor = TileColorMap.get(TileType.KOBOLD);
        Color actual = TileColorMap.get(enemy.getTileType());
        assertThat(tileColor, equalTo(actual));
    }
}

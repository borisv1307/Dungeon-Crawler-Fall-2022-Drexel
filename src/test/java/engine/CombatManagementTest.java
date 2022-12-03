package engine;

import entity.Enemy;
import entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import values.TileColorMap;
import values.TunableParameters;
import wrappers.EnemyRandomWrapper;
import wrappers.ReaderWrapper;

import java.awt.*;

import static junit.framework.TestCase.assertSame;

public class CombatManagementTest {
    CombatManagement combatManagement;
    Enemy enemy;

    Player player;

    @Before
    public void setUp() {
        LevelCreator levelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper());
        GameEngine gameEngine = new GameEngine(levelCreator);
        player = new Player();
        player.setAttackPoint(0);
        enemy = new Enemy(1);
        combatManagement = new CombatManagement(gameEngine, levelCreator, player);
    }

    @Test
    public void attack_by() {
        combatManagement = Mockito.mock(CombatManagement.class);
        enemy.createEnemy(new EnemyRandomWrapper());
        enemy.attackedBy(combatManagement);
        Mockito.verify(combatManagement).attack(enemy);
    }

    @Test
    public void player_hp_color_no_change() {
        enemy.setAttackPoint(0);
        combatManagement.managePlayerHealth(enemy);
        assertSame(Color.GREEN, TileColorMap.get(TileType.PLAYER));
    }

    @Test
    public void player_hp_color_change_orange() {
        enemy.setAttackPoint(4);
        combatManagement.managePlayerHealth(enemy);
        assertSame(Color.ORANGE, TileColorMap.get(TileType.PLAYER));
    }

    @Test
    public void player_hp_color_change_blue() {
        enemy.setAttackPoint(6);
        combatManagement.managePlayerHealth(enemy);
        assertSame(Color.BLUE, TileColorMap.get(TileType.PLAYER));
    }


    @Test
    public void player_hp_color_change_light_gray() {
        enemy.setAttackPoint(10);
        combatManagement.managePlayerHealth(enemy);
        assertSame(Color.LIGHT_GRAY, TileColorMap.get(TileType.PLAYER));
    }

}

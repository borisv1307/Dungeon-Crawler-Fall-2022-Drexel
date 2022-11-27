package entity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import wrappers.EnemyRandomWrapper;

import static junit.framework.TestCase.assertTrue;

public class EnemyTest {
    Enemy enemy;

    EnemyRandomWrapper enemyRandomWrapper;

    @Before
    public void setUp() {
        enemyRandomWrapper = new EnemyRandomWrapper();
    }

    @Test
    public void create_enemy() {
        enemy = new Enemy(3);
        enemyRandomWrapper = Mockito.mock(EnemyRandomWrapper.class);
        enemy.createEnemy(enemyRandomWrapper);
        Mockito.verify(enemyRandomWrapper).generateIntValue(enemy);

    }

    @Test
    public void level_5_enemy_attack_is_between_max_and_min() {
        enemy = new Enemy(5);
        enemy.createEnemy(enemyRandomWrapper);

        int maxExpected = enemy.getLevel() * 10;
        int minExpected = (int) (maxExpected * 0.8);

        assertTrue(enemy.getAttackPoint() < maxExpected);
        assertTrue(enemy.getAttackPoint() > minExpected);
    }
}

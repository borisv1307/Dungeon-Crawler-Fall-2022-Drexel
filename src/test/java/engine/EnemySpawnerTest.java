package engine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import wrappers.RandomizerWrapper;
import wrappers.SystemWrapper;

public class EnemySpawnerTest {
    private EnemySpawner enemySpawner;
    private GameEngine gameEngine;

    @Before
    public void setup() {
        RandomizerWrapper randomizerWrapper = new RandomizerWrapper(new SystemWrapper());
        gameEngine = Mockito.mock(GameEngine.class);
        enemySpawner = new EnemySpawner(randomizerWrapper, gameEngine);
    }

    @Test
    public void game_engine_location_is_checked() {
        enemySpawner.newPointIsOpen(1, 1);
        Mockito.verify(gameEngine, Mockito.atLeastOnce()).getTileFromCoordinates(1, 1);
    }
}
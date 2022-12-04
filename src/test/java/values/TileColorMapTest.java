package values;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import wrappers.RandomWrapper;

import java.awt.*;

import static org.junit.Assert.assertSame;

public class TileColorMapTest {

    GameEngine gameEngine;


    @Before
    public void setUp() {
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        gameEngine = new GameEngine(levelCreator, new RandomWrapper());

    }

    @Test
    public void passable() {
        assertSame(Color.WHITE, TileColorMap.get(TileType.PASSABLE));
    }

    @Test
    public void not_passable() {
        assertSame(Color.BLACK, TileColorMap.get(TileType.NOT_PASSABLE));
    }

    @Test
    public void player() {
        gameEngine.initializePlayerLevels();
        gameEngine.setPlayerLevel(1);
        assertSame(Color.GREEN, TileColorMap.get(TileType.PLAYER));
    }

}

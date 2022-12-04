package main;

import engine.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import values.TestingTunableParameters;
import wrappers.RandomWrapper;
import wrappers.ReaderWrapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyInt;

public class CoinGenerationTest {
    GameEngine gameEngine;
    private RandomWrapper randomWrapper;

    @Before
    public void setUp() {
        randomWrapper = Mockito.mock(RandomWrapper.class);
        Mockito.when(randomWrapper.generateCoordinate(anyInt())).thenReturn(2);
        Mockito.when(randomWrapper.generateCoordinate(anyInt())).thenReturn(2);
        gameEngine = new GameEngine(
                new LevelCreator(
                        TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), randomWrapper);
        gameEngine.addTile(2, 2, TileType.PASSABLE);
    }

    @Test
    public void add_coin_tile() {
        TileType tileType = TileType.COIN;
        gameEngine.addTile(0, 1, TileType.COIN);
        TileType actual = gameEngine.getTileFromCoordinates(0, 1);
        assertThat(actual, equalTo(tileType));
    }

    @Test
    public void coin_exists_from_generation() {
        int initialCoinCount = gameEngine.getCoinCount();
        gameEngine.randomCoinGeneration();
        assertThat(gameEngine.getCoinCount(), equalTo(initialCoinCount + 1));
    }

    @Test
    public void coin_created_on_tile() {
        gameEngine.randomCoinGeneration();
        assertThat(gameEngine.getTileFromCoordinates(2, 2), equalTo(TileType.COIN));
    }


}

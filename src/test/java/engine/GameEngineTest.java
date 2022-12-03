package engine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomWrapper;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GameEngineTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private final String FILE_LOCATION_PREFIX = "FILE_LOCATION_PREFIX";
    GameEngine gameEngine;
    RandomWrapper randomWrapper;

    @Before
    public void setUp() throws Exception {
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        randomWrapper = Mockito.mock(RandomWrapper.class);
        Mockito.when(randomWrapper.mathRandom()).thenReturn(.2);
        gameEngine = new GameEngine(levelCreator, randomWrapper);
        int level = 1;
        Mockito.verify(levelCreator, Mockito.times(level)).createLevel(gameEngine, level);
    }

    @Test
    public void run() {
        GameFrame gameFrame = Mockito.mock(GameFrame.class);
        Component component = Mockito.mock(Component.class);
        Mockito.when(gameFrame.getComponents()).thenReturn(new Component[]{component});
        gameEngine.run(gameFrame);
        Mockito.verify(component, Mockito.times(1)).repaint();
    }

    @Test
    public void add_and_get_tile() {
        TileType tileType = TileType.PASSABLE;
        gameEngine.addTile(ZERO, ONE, TileType.PASSABLE);
        TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
        assertThat(actual, equalTo(tileType));
    }

    @Test
    public void set_and_get_horizontal_dimension() {
        gameEngine.setLevelHorizontalDimension(ONE);
        int actual = gameEngine.getLevelHorizontalDimension();
        assertThat(actual, equalTo(ONE));
    }

    @Test
    public void set_and_get_vertical_dimension() {
        gameEngine.setLevelVerticalDimension(ONE);
        int actual = gameEngine.getLevelVerticalDimension();
        assertThat(actual, equalTo(ONE));
    }

    @Test
    public void add_and_get_player_coordinates() {
        TileType tileType = TileType.PLAYER;
        gameEngine.addTile(ZERO, ONE, tileType);
        int actualX = gameEngine.getPlayerXCoordinate();
        int actualY = gameEngine.getPlayerYCoordinate();
        assertThat(actualX, equalTo(ZERO));
        assertThat(actualY, equalTo(ONE));
    }

    @Test
    public void set_and_get_exit() {
        boolean exit = true;
        gameEngine.setExit(exit);
        boolean actual = gameEngine.isExit();
        assertThat(actual, equalTo(exit));
    }

    @Test
    public void random_number() {
        assertEquals(4, gameEngine.getBombXCoordinate());
    }


}

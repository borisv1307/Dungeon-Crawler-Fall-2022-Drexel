package engine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import values.TunableParameters;
import wrappers.ReaderWrapper;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GameEngineTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;

    GameEngine gameEngine;

    @Before
    public void setUp() {
        LevelCreator levelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX,
                new ReaderWrapper());

        gameEngine = new GameEngine(levelCreator);
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
    public void set_enemy_count() {
        int level = 1;
        int range = 5 * level;
        int levelenemycount = (int) (Math.random() * range) + 1;
        boolean created = gameEngine.setEnemyCount(levelenemycount);
        assertThat(created, equalTo(true));
    }

    @Test
    public void set_enemy_random_moves() {
        int ret = gameEngine.randomPosition();
        assertThat(ret, equalTo(0));
    }

    @Test
    public void check_player_captured() {
        int x = 10;
        int y = 5;
        gameEngine.setEnemyPos(10, 5);
        boolean captured = gameEngine.meetEnemy(x, y);
        assertThat(captured, equalTo(true));
    }

    @Test
    public void check_zero_or_one() {
        Assert.assertTrue(gameEngine.checkZeroOrOne(0));
        Assert.assertTrue(gameEngine.checkZeroOrOne(1));
        Assert.assertFalse(gameEngine.checkZeroOrOne(3));
    }

    @Test
    public void validate_type_coordinates() {
        Assert.assertFalse(gameEngine.validateTypeCoordinates(TileType.PASSABLE, 1, 1));
        Assert.assertTrue(gameEngine.validateTypeCoordinates(TileType.PASSABLE, 2, 2));
        Assert.assertTrue(gameEngine.validateTypeCoordinates(TileType.PASSABLE, 0, 2));
        Assert.assertFalse(gameEngine.validateTypeCoordinates(TileType.NOT_PASSABLE, 1, 1));
        Assert.assertFalse(gameEngine.validateTypeCoordinates(TileType.NOT_PASSABLE, 2, 2));
        Assert.assertTrue(gameEngine.validateTypeCoordinates(TileType.PASSABLE, 2, 0));
    }

    @Test
    public void create_enemies() {
        Assert.assertEquals(1, gameEngine.createEnemies(0));
        Assert.assertNotEquals(1, gameEngine.createEnemies(1));
    }

}

package engine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class GameEngineTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    GameEngine gameEngine;

    @Mock
    private Point player;

    @Before
    public void setUp() throws Exception {
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        gameEngine = new GameEngine(levelCreator);
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
    public void get_all_empty_tiles() {
        ArrayList<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 1));
        expected.add(new Point(1, 1));
        gameEngine.setLevelVerticalDimension(2);
        gameEngine.setLevelHorizontalDimension(2);
        gameEngine.addTile(0, 0, TileType.NOT_PASSABLE);
        gameEngine.addTile(0, 1, TileType.PASSABLE);
        gameEngine.addTile(1, 1, TileType.PASSABLE);
        gameEngine.addTile(1, 0, TileType.NOT_PASSABLE);
        ArrayList<Point> actual = gameEngine.getAllPassableTiles();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void no_random_passable_tile() {
        gameEngine.addTile(0, 0, TileType.NOT_PASSABLE);
        Point randomPassablePoint = gameEngine.getRandomPassableTile();
        assertNull(randomPassablePoint);
    }

    @Test
    public void get_random_passable_tile() {
        gameEngine.setLevelVerticalDimension(1);
        gameEngine.setLevelHorizontalDimension(1);
        gameEngine.addTile(0, 0, TileType.PASSABLE);
        Point randomPassablePoint = gameEngine.getRandomPassableTile();
        assertNotNull(randomPassablePoint);
    }

    @Test
    public void remove_object_from_tile() {
        gameEngine.setLevelVerticalDimension(1);
        gameEngine.setLevelHorizontalDimension(1);
        gameEngine.addTile(0, 0, TileType.OBJECT);
        gameEngine.removeObjectFromTile(new Point(0, 0));
        assertEquals(TileType.PASSABLE, gameEngine.getTileFromCoordinates(0, 0));
    }

    @Test
    public void add_object_to_tile() {
        gameEngine.setLevelVerticalDimension(1);
        gameEngine.setLevelHorizontalDimension(1);
        gameEngine.addTile(0, 0, TileType.OBJECT);
        assertEquals(TileType.OBJECT, gameEngine.getTileFromCoordinates(0, 0));
    }
}
package engine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomWrapper;

import java.awt.*;
import java.util.ArrayList;

import static engine.GameEngineTestFactory.initializeSimpleGameEngine;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameEngineTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    GameEngine gameEngine;
    RandomWrapper randomWrapper;

    @Before
    public void setUp() throws Exception {
        randomWrapper = mock(RandomWrapper.class);
        LevelCreator levelCreator = mock(LevelCreator.class);
        gameEngine = new GameEngine(levelCreator);
        int level = 1;
        Mockito.verify(levelCreator, Mockito.times(level)).createLevel(gameEngine, level);
    }

    @Test
    public void run() {
        randomWrapper = Mockito.mock(RandomWrapper.class);
        GameFrame gameFrame = mock(GameFrame.class);
        Component component = mock(Component.class);
        when(gameFrame.getComponents()).thenReturn(new Component[]{component});
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
        initializeSimpleGameEngine(gameEngine);
        ArrayList<Point> actual = gameEngine.getAllPassableTiles();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void no_random_passable_tile() {
        ArrayList<Point> allPassableTiles = new ArrayList<>();
        Point randomPassablePoint = randomWrapper.getRandomPassableTile(allPassableTiles);
        assertNull(randomPassablePoint);
    }

    @Test
    public void get_random_passable_tile() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(0, 0));
        Point randomPassablePoint = randomWrapper.getRandomPassableTile(points);
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

    @Test
    public void remove_previously_added_objects() {
        gameEngine.addTile(1, 1, TileType.PASSABLE);
        gameEngine.addObjectToTile(new Point(1, 1));
        gameEngine.removePreviouslyAddedObjects();

        TileType actualTileType = gameEngine.getTileFromCoordinates(1, 1);
        assertEquals(TileType.PASSABLE, actualTileType);
    }

    @Test
    public void refresh_collectable_objects() {
        initializeSimpleGameEngine(gameEngine);
        gameEngine.addObjectToTile(new Point(1, 1));
        gameEngine.refreshCollectableObjects();
        assertEquals(TileType.PASSABLE, gameEngine.getTileFromCoordinates(1, 1));
    }

}
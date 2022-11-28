package engine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GameEngineTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    GameEngine gameEngine;

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
    public void add_and_get_door() {
        TileType tileType = TileType.DOOR;
        gameEngine.addTile(ZERO, ONE, TileType.DOOR);
        TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
        assertThat(actual, equalTo(tileType));

    }

    @Test
    public void add_and_get_key() {
        TileType tileType = TileType.KEY;
        gameEngine.addTile(ZERO, ONE, TileType.KEY);
        TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
        assertThat(actual, equalTo(tileType));

    }

    @Test
    public void add_and_get_collectible() {
        TileType tileType = TileType.COLLECTIBLE;
        gameEngine.addTile(ZERO, ONE, TileType.COLLECTIBLE);
        TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
        assertThat(actual, equalTo(tileType));

    }

    @Test
    public void collectible_collected() {
        gameEngine.addTile(ZERO, ONE, TileType.KEY);
        gameEngine.addTile(ZERO, TWO, TileType.PLAYER);
        gameEngine.addTile(TWO, THREE, TileType.COLLECTIBLE);
        gameEngine.keyUp();
        gameEngine.keyUp();
        gameEngine.keyLeft();
        assertThat(gameEngine.playerHasKey(), equalTo(true));
        assertThat(gameEngine.getTileFromCoordinates(ZERO, ONE), equalTo(TileType.PASSABLE));
    }

    @Test
    public void key_passable_when_collected() {
        gameEngine.addTile(ZERO, ONE, TileType.KEY);
        gameEngine.addTile(ZERO, TWO, TileType.PLAYER);
        gameEngine.keyUp();
        gameEngine.keyUp();
        assertThat(gameEngine.playerHasKey(), equalTo(true));
        assertThat(gameEngine.getTileFromCoordinates(ZERO, ONE), equalTo(TileType.PASSABLE));
    }


    @Test
    public void door_passable_when_key_collected() {
        gameEngine.addTile(ZERO, ONE, TileType.KEY);
        gameEngine.addTile(ZERO, TWO, TileType.PLAYER);
        gameEngine.addTile(ONE, ONE, TileType.DOOR);
        gameEngine.keyUp();
        gameEngine.keyUp();
        gameEngine.keyRight();
        assertThat(gameEngine.getTileFromCoordinates(ONE, ONE), equalTo(TileType.PASSABLE));
    }

}

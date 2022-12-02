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
import static org.junit.Assert.*;

public class GameEngineTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;

    GameEngine gameEngine;
    CombatEngine combatEngine;

    @Before
    public void setUp() throws Exception {
        LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
        gameEngine = new GameEngine(levelCreator);
        combatEngine = new CombatEngine(gameEngine);
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
        createPlayerTile();
        int actualX = gameEngine.getPlayerXCoordinate();
        int actualY = gameEngine.getPlayerYCoordinate();
        assertThat(actualX, equalTo(ZERO));
        assertThat(actualY, equalTo(ONE));
    }

    @Test
    public void add_and_get_enemy_coordinates() {
        createSlimeTile();
        int actualX = gameEngine.getEnemyXCoordinate();
        int actualY = gameEngine.getEnemyYCoordinate();
        assertThat(actualX, equalTo(ZERO));
        assertThat(actualY, equalTo(TWO));
    }

    @Test
    public void despawn_enemy_tile() {
        createSlimeTile();
        gameEngine.removeTile(ZERO, TWO);
        TileType actualTileType = gameEngine.getTileFromCoordinates(ZERO, TWO);
        assertNotEquals(actualTileType, TileType.KOBOLD);
    }

    @Test
    public void spawn_new_enemy_on_defeat() {
        gameEngine.setLevelVerticalDimension(TWO);
        gameEngine.setLevelHorizontalDimension(TWO);
        createPlayerTile();
        createSlimeTile();
        int previousX = gameEngine.getEnemyXCoordinate();
        int previousY = gameEngine.getEnemyYCoordinate();
        gameEngine.enemyKilled();
        int actualX = gameEngine.getEnemyXCoordinate();
        int actualY = gameEngine.getEnemyYCoordinate();
        assertFalse(actualX == previousX && actualY == previousY);
    }

    @Test
    public void player_respawns_on_death() {
        createPlayerTile();
        int previousX = gameEngine.getPlayerXCoordinate();
        int previousY = gameEngine.getPlayerYCoordinate();
        gameEngine.playerKilled();
        int actualX = gameEngine.getPlayerXCoordinate();
        int actualY = gameEngine.getPlayerXCoordinate();
        assertThat(actualX, equalTo(ZERO));
        assertThat(actualY, equalTo(ZERO));
        assertFalse(actualX == previousX && actualY == previousY);
    }

    @Test
    public void set_and_get_exit() {
        boolean exit = true;
        gameEngine.setExit(exit);
        boolean actual = gameEngine.isExit();
        assertThat(actual, equalTo(exit));
    }

    @Test
    public void player_dies_updates_status() {
        createPlayerTile();
        gameEngine.playerKilled();
        assertEquals(GameStatus.PLAYER_DEFEATED, gameEngine.getGameStatus());
    }

    @Test
    public void enemy_dies_updates_status() {
        gameEngine.setLevelVerticalDimension(TWO);
        gameEngine.setLevelHorizontalDimension(TWO);
        createPlayerTile();
        createSlimeTile();
        gameEngine.enemyKilled();
        String expected = String.format(GameStatus.ENEMY_DEFEATED, "Slime");
        assertEquals(expected, gameEngine.getGameStatus());
    }

    private void createPlayerTile() {
        TileType tileType = TileType.PLAYER;
        gameEngine.addTile(ZERO, ONE, tileType);
    }

    private void createSlimeTile() {
        TileType tileType = TileType.SLIME;
        gameEngine.addTile(ZERO, TWO, tileType);
    }
}

package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.awt.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;

	private static final int TWO = 2;


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
		Mockito.when(gameFrame.getComponents()).thenReturn(new Component[] { component });
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
	public void add_and_get_dragon_tile() {
		TileType tileType = TileType.DRAGON;
		gameEngine.addTile(ZERO, ONE, TileType.DRAGON);
		TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
		assertThat(actual, equalTo(tileType));
	}

	@Test
	public void add_and_get_potion_tile() {
		TileType tileType = TileType.POTION;
		gameEngine.addTile(ZERO, ONE, TileType.POTION);
		TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
		assertThat(actual, equalTo(tileType));
	}

	@Test
	public void add_and_get_near_tile() {
		TileType tileType = TileType.NEAR;
		gameEngine.addTile(ZERO, ONE, TileType.NEAR);
		TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
		assertThat(actual, equalTo(tileType));
	}

	@Test
	public void add_and_get_visited_tile() {
		TileType tileType = TileType.VISITED;
		gameEngine.addTile(ZERO, ONE, TileType.VISITED);
		TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
		assertThat(actual, equalTo(tileType));
	}

	@Test
	public void add_and_get_exit_tile() {
		TileType tileType = TileType.EXIT;
		gameEngine.addTile(ZERO, ONE, TileType.EXIT);
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
	public void next_level_on_exit_condition(){
		gameEngine.addTile(ZERO, ZERO, TileType.PLAYER);
		gameEngine.addTile(ZERO, ONE, TileType.POTION);
		gameEngine.addTile(ZERO, TWO, TileType.POTION);
		gameEngine.addTile(ONE, TWO, TileType.POTION);
		gameEngine.addTile(TWO, TWO, TileType.EXIT);
		gameEngine.keyDown();
		gameEngine.keyDown();
		gameEngine.keyRight();
		gameEngine.keyRight();
		assertThat(gameEngine.getLevel(), equalTo(2));
	}

	@Test
	public void game_over_on_dragon(){
		int potionCount = 0;
		gameEngine.addTile(ZERO, ZERO, TileType.PLAYER);
		gameEngine.addTile(ZERO, ONE, TileType.POTION);
		gameEngine.addTile(ZERO, TWO, TileType.POTION);
		gameEngine.keyDown();
		gameEngine.keyDown();
		assertThat(gameEngine.getPotionCounter(), equalTo(2));
	}

	@Test
	public void collect_potion(){
		int potionCount = 0;
		gameEngine.addTile(ZERO, ZERO, TileType.PLAYER);
		gameEngine.addTile(ZERO, ONE, TileType.POTION);
		gameEngine.addTile(ZERO, TWO, TileType.DRAGON);
		gameEngine.keyDown();
		gameEngine.keyDown();
		assertThat(gameEngine.isExit(), equalTo(true));
	}

	@Test
	public void exit_game_on_final_level(){
		gameEngine.setLevel(2);
		gameEngine.addTile(ZERO, ZERO, TileType.PLAYER);
		gameEngine.addTile(ZERO, ONE, TileType.POTION);
		gameEngine.addTile(ZERO, TWO, TileType.POTION);
		gameEngine.addTile(ONE, TWO, TileType.POTION);
		gameEngine.addTile(TWO, TWO, TileType.EXIT);
		gameEngine.keyDown();
		gameEngine.keyDown();
		gameEngine.keyRight();
		gameEngine.keyRight();
		assertThat(gameEngine.isExit(), equalTo(true));
	}

	@Test
	public void set_and_get_exit() {
		boolean exit = true;
		gameEngine.setExit(exit);
		boolean actual = gameEngine.isExit();
		assertThat(actual, equalTo(exit));
	}

}

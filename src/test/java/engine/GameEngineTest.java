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
	public void add_and_get_non_playable_character_coordinates() {
		TileType tileType = TileType.NON_PLAYABLE_CHARACTER;
		gameEngine.addTile(ZERO, ONE, tileType);
		int actualX = gameEngine.getNonPlayableCharacterXCoordinate();
		int actualY = gameEngine.getNonPlayableCharacterYCoordinate();
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
	public void enter_key_creates_new_frame_from_dialogue_system() {
		gameEngine = Mockito.mock(GameEngine.class, Mockito.CALLS_REAL_METHODS);
		gameEngine.keyEnter();
		Mockito.verify(gameEngine, Mockito.times(1)).keyEnter();
		Mockito.verify(gameEngine, Mockito.times(1)).setIsDialogueActive(true);
	}

	@Test
	public void while_dialogue_is_active_player_should_not_move() {
		gameEngine.addTile(ONE, ONE, TileType.PLAYER);
		boolean isDialogueActive = true;
		gameEngine.setIsDialogueActive(isDialogueActive);
		gameEngine.keyRight();
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(ONE));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(ONE));
	}
}

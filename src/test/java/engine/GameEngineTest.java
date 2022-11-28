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
import ui.ScorePanel;
import values.TunableParameters;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;

	GameEngine gameEngine;
	ScorePanel scorePanel;
	LevelCreator levelCreator;

	@Before
	public void setUp() {
		levelCreator = Mockito.mock(LevelCreator.class);
		scorePanel = Mockito.mock(ScorePanel.class);
		gameEngine = new GameEngine(levelCreator, scorePanel);
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
	public void set_and_get_exit() {
		boolean exit = true;
		gameEngine.setExit(exit);
		boolean actual = gameEngine.isExit();
		assertThat(actual, equalTo(exit));
	}

	@Test
	public void add_projectile_above_player() {
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ZERO, tileType);
		gameEngine.shoot();

		int x = gameEngine.getPlayerXCoordinate();
		int y = gameEngine.getPlayerYCoordinate();
		assertThat(gameEngine.getTileFromCoordinates(x, y - 1), equalTo(TileType.PROJECTILE));
	}

	@Test
	public void cannot_add_a_projectile_above_player_if_player_against_unpassable_tile() {
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		gameEngine.addTile(ZERO, ZERO, TileType.NOT_PASSABLE);
		gameEngine.shoot();

		int x = gameEngine.getPlayerXCoordinate();
		int y = gameEngine.getPlayerYCoordinate();
		assertThat(gameEngine.getTileFromCoordinates(x, y - 1), equalTo(TileType.NOT_PASSABLE));
	}

	@Test
	public void firing_a_projectile_against_an_enemy_increments_score() {
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		gameEngine.addTile(ZERO, ZERO, TileType.ENEMY);
		gameEngine.shoot();

		Mockito.verify(scorePanel, Mockito.times(1)).incrementScore();
	}

	@Test
	public void firing_projectile_right_in_front_of_enemy_vanquishes_projectile() {
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		gameEngine.addTile(ZERO, ZERO, TileType.ENEMY);
		gameEngine.shoot();

		int x = gameEngine.getPlayerXCoordinate();
		int y = gameEngine.getPlayerYCoordinate();
		assertThat(gameEngine.getTileFromCoordinates(x, y - 1), equalTo(TileType.PASSABLE));
	}

	@Test
	public void walking_into_an_enemy_loses_a_life() {
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		gameEngine.addTile(ZERO, ZERO, TileType.ENEMY);
		gameEngine.keyUp();

		Mockito.verify(scorePanel, Mockito.times(1)).decrementLives();
	}

	@Test
	public void walking_into_an_enemy_vanquishes_enemy() {
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		gameEngine.addTile(ZERO, ZERO, TileType.ENEMY);
		gameEngine.keyUp();

		int x = gameEngine.getPlayerXCoordinate();
		int y = gameEngine.getPlayerYCoordinate();
		assertThat(x, equalTo(ZERO));
		assertThat(y, equalTo(ZERO));
	}

	@Test
	public void when_player_lives_hits_zero_lost_is_set_to_true() {
		Mockito.when(scorePanel.getPlayerLives()).thenReturn(0);
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		gameEngine.addTile(ZERO, ZERO, TileType.ENEMY);
		gameEngine.keyUp();

		assertThat(gameEngine.isLost(), equalTo(true));
	}

	@Test
	public void restarting_the_game_sets_lost_true() {
		gameEngine.setLost(true);
		gameEngine.restart();
		assertThat(gameEngine.isLost(), equalTo(false));
	}

	@Test
	public void restarting_the_game_resets_the_level_to_level_1() {
		gameEngine.setLost(true);
		gameEngine.restart();

		Mockito.verify(levelCreator, Mockito.times(2)).createLevel(gameEngine, 1);
	}

	@Test
	public void can_only_restart_the_game_when_lost_is_true() {
		gameEngine.setLost(false);
		gameEngine.restart();

		Mockito.verify(levelCreator, Mockito.times(1)).createLevel(gameEngine, 1);
	}

	@Test
	public void restarting_the_game_sets_lives_back_to_default() {
		gameEngine.setLost(true);
		gameEngine.restart();

		Mockito.verify(scorePanel, Mockito.times(1)).setPlayerLives(TunableParameters.STARTING_AND_MAXIMUM_LIVES);
	}

	@Test
	public void restarting_the_game_sets_score_back_to_default() {
		gameEngine.setLost(true);
		gameEngine.restart();

		Mockito.verify(scorePanel, Mockito.times(1)).setPlayerScore(TunableParameters.STARTING_SCORE);
	}

	@Test
	public void restarting_game_sets_lives_increment_to_zero() {
		gameEngine.setLost(true);
		gameEngine.restart();

		Mockito.verify(scorePanel, Mockito.times(1)).setLivesIncrementCountDown(0);
	}
}

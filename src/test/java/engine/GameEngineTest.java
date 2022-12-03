package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.awt.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import values.TunableParameters;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;

	GameEngine gameEngine;
	ProjectileHandler projectileHandler;

	@Before
	public void setUp() throws Exception {
		LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
		gameEngine = new GameEngine(levelCreator);
		projectileHandler = Mockito.mock(ProjectileHandler.class);
		int level = 1;
		Mockito.verify(levelCreator, Mockito.times(level)).createLevel(gameEngine, level);
	}

	@Test
	public void run() {
		GameFrame gameFrame = Mockito.mock(GameFrame.class);
		Component component = Mockito.mock(Component.class);
		Mockito.when(gameFrame.getComponents()).thenReturn(new Component[] { component });
		gameEngine.run(gameFrame, projectileHandler);
		Mockito.verify(component, Mockito.times(1)).repaint();
		Mockito.verify(projectileHandler, Mockito.times(1)).runProjectileTimer();
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
	public void add_and_get_up_projectile_coordinates() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, TunableParameters.UP);
		int actualX = gameEngine.getProjectileXCoordinate(TunableParameters.UP);
		int actualY = gameEngine.getProjectileYCoordinate(TunableParameters.UP);
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void add_and_get_down_projectile_coordinates() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, TunableParameters.DOWN);
		int actualX = gameEngine.getProjectileXCoordinate(TunableParameters.DOWN);
		int actualY = gameEngine.getProjectileYCoordinate(TunableParameters.DOWN);
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void add_and_get_left_projectile_coordinates() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, TunableParameters.LEFT);
		int actualX = gameEngine.getProjectileXCoordinate(TunableParameters.LEFT);
		int actualY = gameEngine.getProjectileYCoordinate(TunableParameters.LEFT);
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void add_and_get_right_projectile_coordinates() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, TunableParameters.RIGHT);
		int actualX = gameEngine.getProjectileXCoordinate(TunableParameters.RIGHT);
		int actualY = gameEngine.getProjectileYCoordinate(TunableParameters.RIGHT);
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void projectile_move_right() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, TunableParameters.RIGHT);
		gameEngine.moveProjectileRight();
		int actualX = gameEngine.getProjectileXCoordinate(TunableParameters.RIGHT);
		int actualY = gameEngine.getProjectileYCoordinate(TunableParameters.RIGHT);
		assertThat(actualX, equalTo(ONE));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void projectile_move_left() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ONE, ONE, tileType, TunableParameters.LEFT);
		gameEngine.moveProjectileLeft();
		int actualX = gameEngine.getProjectileXCoordinate(TunableParameters.LEFT);
		int actualY = gameEngine.getProjectileYCoordinate(TunableParameters.LEFT);
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void projectile_move_down() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ZERO, tileType, TunableParameters.DOWN);
		gameEngine.moveProjectileDown();
		int actualX = gameEngine.getProjectileXCoordinate(TunableParameters.DOWN);
		int actualY = gameEngine.getProjectileYCoordinate(TunableParameters.DOWN);
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void projectile_move_up() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, TunableParameters.UP);
		gameEngine.moveProjectileUp();
		int actualX = gameEngine.getProjectileXCoordinate(TunableParameters.UP);
		int actualY = gameEngine.getProjectileYCoordinate(TunableParameters.UP);
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ZERO));
	}

	@Test
	public void projectile_hits_player() {
		Point projectile = new Point(ONE, ZERO);
		Point player = new Point(ONE, ZERO);
		boolean collision = gameEngine.checkCollision(player, projectile);
		assertEquals(true, collision);
	}

	@Test
	public void set_and_get_exit() {
		boolean exit = true;
		gameEngine.setExit(exit);
		boolean actual = gameEngine.isExit();
		assertThat(actual, equalTo(exit));
	}

}

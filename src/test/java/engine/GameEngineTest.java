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
		ProjectileHandler projectileHandler = Mockito.mock(ProjectileHandler.class);
		Mockito.when(gameFrame.getComponents()).thenReturn(new Component[] { component });
		gameEngine.setProjectileHandler(projectileHandler);
		gameEngine.run(gameFrame);
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
		gameEngine.addTile(ZERO, ONE, tileType, "up");
		int actualX = gameEngine.getProjectileXCoordinate("up");
		int actualY = gameEngine.getProjectileYCoordinate("up");
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void add_and_get_down_projectile_coordinates() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, "down");
		int actualX = gameEngine.getProjectileXCoordinate("down");
		int actualY = gameEngine.getProjectileYCoordinate("down");
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void add_and_get_left_projectile_coordinates() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, "left");
		int actualX = gameEngine.getProjectileXCoordinate("left");
		int actualY = gameEngine.getProjectileYCoordinate("left");
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void add_and_get_right_projectile_coordinates() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, "right");
		int actualX = gameEngine.getProjectileXCoordinate("right");
		int actualY = gameEngine.getProjectileYCoordinate("right");
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void projectile_move_right() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, "right");
		gameEngine.moveProjectileRight();
		int actualX = gameEngine.getProjectileXCoordinate("right");
		int actualY = gameEngine.getProjectileYCoordinate("right");
		assertThat(actualX, equalTo(ONE));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void projectile_move_left() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ONE, ONE, tileType, "left");
		gameEngine.moveProjectileLeft();
		int actualX = gameEngine.getProjectileXCoordinate("left");
		int actualY = gameEngine.getProjectileYCoordinate("left");
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void projectile_move_down() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ZERO, tileType, "down");
		gameEngine.moveProjectileDown();
		int actualX = gameEngine.getProjectileXCoordinate("down");
		int actualY = gameEngine.getProjectileYCoordinate("down");
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void projectile_move_up() {
		TileType tileType = TileType.PROJECTILE;
		gameEngine.addTile(ZERO, ONE, tileType, "up");
		gameEngine.moveProjectileUp();
		int actualX = gameEngine.getProjectileXCoordinate("up");
		int actualY = gameEngine.getProjectileYCoordinate("up");
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ZERO));
	}

	@Test
	public void set_and_get_exit() {
		boolean exit = true;
		gameEngine.setExit(exit);
		boolean actual = gameEngine.isExit();
		assertThat(actual, equalTo(exit));
	}

}

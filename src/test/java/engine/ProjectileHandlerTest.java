package engine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tiles.TileType;
import values.TunableParameters;
import wrappers.RandomWrapper;

public class ProjectileHandlerTest {

	GameEngine gameEngine;
	RandomWrapper randomWrapper;
	ProjectileHandler projectileHandler;
	int maxX;
	int maxY;

	@Before
	public void setUp() {
		gameEngine = Mockito.mock(GameEngine.class);
		randomWrapper = Mockito.mock(RandomWrapper.class);
		projectileHandler = new ProjectileHandler(gameEngine, randomWrapper);
		gameEngine.setLevelVerticalDimension(10);
		gameEngine.setLevelHorizontalDimension(20);
		maxY = gameEngine.getLevelVerticalDimension() - 2;
		maxX = gameEngine.getLevelHorizontalDimension() - 2;
	}

	@Test
	public void generate_projectile_left_side() {
		Mockito.when(randomWrapper.nextInt(maxY)).thenReturn(5);
		projectileHandler.createLeftEdgeProjectile();
		Mockito.verify(gameEngine).addTile(0, 6, TileType.PROJECTILE, TunableParameters.RIGHT);
	}

	@Test
	public void generate_projectile_right_side() {
		Mockito.when(randomWrapper.nextInt(maxY)).thenReturn(5);
		Mockito.when(gameEngine.getLevelHorizontalDimension()).thenReturn(20);
		projectileHandler.createRightEdgeProjectile();
		Mockito.verify(gameEngine).addTile(19, 6, TileType.PROJECTILE, TunableParameters.LEFT);
	}

	@Test
	public void generate_projectile_top_side() {
		Mockito.when(randomWrapper.nextInt(maxX)).thenReturn(9);
		projectileHandler.createTopEdgeProjectile();
		Mockito.verify(gameEngine).addTile(10, 0, TileType.PROJECTILE, TunableParameters.DOWN);
	}

	@Test
	public void generate_projectile_bottom_side() {
		Mockito.when(randomWrapper.nextInt(maxX)).thenReturn(9);
		Mockito.when(gameEngine.getLevelVerticalDimension()).thenReturn(10);
		projectileHandler.createBottomEdgeProjectile();
		Mockito.verify(gameEngine).addTile(10, 9, TileType.PROJECTILE, TunableParameters.UP);
	}

	@Test
	public void random_vertical_value_is_greater_than_zero() {
		Mockito.when(randomWrapper.nextInt(maxY)).thenReturn(0);
		projectileHandler.createLeftEdgeProjectile();
		Mockito.verify(gameEngine).addTile(0, 1, TileType.PROJECTILE, TunableParameters.RIGHT);
	}

	@Test
	public void random_vertical_value_is_less_than_max_dimension() {
		Mockito.when(gameEngine.getLevelVerticalDimension()).thenReturn(10);
		projectileHandler.getYValue();
		Mockito.verify(randomWrapper).nextInt(8);
	}

	@Test
	public void random_horizontal_value_is_greater_than_zero() {
		Mockito.when(randomWrapper.nextInt(maxX)).thenReturn(0);
		projectileHandler.createTopEdgeProjectile();
		Mockito.verify(gameEngine).addTile(1, 0, TileType.PROJECTILE, TunableParameters.DOWN);
	}

	@Test
	public void random_horizontal_value_is_less_than_max_dimension() {
		Mockito.when(gameEngine.getLevelHorizontalDimension()).thenReturn(20);
		projectileHandler.getXValue();
		Mockito.verify(randomWrapper).nextInt(18);
	}

	@Test
	public void move_projectiles() {
		projectileHandler.moveProjectiles();
		Mockito.verify(gameEngine).moveProjectileRight();
		Mockito.verify(gameEngine).moveProjectileLeft();
		Mockito.verify(gameEngine).moveProjectileDown();
		Mockito.verify(gameEngine).moveProjectileUp();
	}

}

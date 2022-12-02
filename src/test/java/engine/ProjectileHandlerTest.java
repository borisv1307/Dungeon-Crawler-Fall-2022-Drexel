package engine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import tiles.TileType;
import wrappers.RandomWrapper;

public class ProjectileHandlerTest {

	GameEngine gameEngine;
	RandomWrapper randomWrapper;
	ProjectileHandler projectileHandler;

	@Before
	public void setUp() {
		gameEngine = Mockito.mock(GameEngine.class);
		randomWrapper = Mockito.mock(RandomWrapper.class);
		projectileHandler = new ProjectileHandler(gameEngine, randomWrapper);
		gameEngine.setLevelVerticalDimension(10);
		gameEngine.setLevelHorizontalDimension(20);
	}

	@Test
	public void generate_projectile_left_side() {
		Mockito.when(randomWrapper.nextInt(gameEngine.getLevelVerticalDimension())).thenReturn(6);
		projectileHandler.createLeftEdgeProjectile();
		Mockito.verify(gameEngine).addTile(0, 6, TileType.PROJECTILE);
	}

	@Test
	public void generate_projectile_right_side() {
		Mockito.when(randomWrapper.nextInt(gameEngine.getLevelVerticalDimension())).thenReturn(6);
		Mockito.when(gameEngine.getLevelHorizontalDimension()).thenReturn(20);
		projectileHandler.createRightEdgeProjectile();
		Mockito.verify(gameEngine).addTile(20, 6, TileType.PROJECTILE);
	}

	@Test
	public void generate_projectile_top_side() {
		Mockito.when(randomWrapper.nextInt(gameEngine.getLevelHorizontalDimension())).thenReturn(10);
		projectileHandler.createTopEdgeProjectile();
		Mockito.verify(gameEngine).addTile(10, 0, TileType.PROJECTILE);
	}

	@Test
	public void generate_projectile_bottom_side() {
		Mockito.when(randomWrapper.nextInt(gameEngine.getLevelHorizontalDimension())).thenReturn(10);
		Mockito.when(gameEngine.getLevelVerticalDimension()).thenReturn(10);
		projectileHandler.createBottomEdgeProjectile();
		Mockito.verify(gameEngine).addTile(10, 10, TileType.PROJECTILE);
	}
}

package engine;

import org.junit.Test;
import org.mockito.Mockito;

import tiles.TileType;
import wrappers.RandomWrapper;

public class ProjectileHandlerTest {
	@Test
	public void generate_random_value() {
		GameEngine gameEngine = Mockito.mock(GameEngine.class);
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);
		ProjectileHandler projectileHandler = new ProjectileHandler(gameEngine, randomWrapper);
		gameEngine.setLevelVerticalDimension(10);
		Mockito.when(randomWrapper.nextInt(gameEngine.getLevelVerticalDimension())).thenReturn(6);
		projectileHandler.createLeftEdgeProjectile();
		Mockito.verify(gameEngine).addTile(0, 6, TileType.PROJECTILE);

	}
}

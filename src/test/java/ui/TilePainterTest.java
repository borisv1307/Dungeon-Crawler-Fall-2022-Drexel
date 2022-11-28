package ui;

import static org.junit.Assert.assertEquals;

import java.awt.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.InOrder;
import org.mockito.Matchers;
import org.mockito.Mockito;

import engine.GameEngine;
import tiles.TileType;
import values.TileColorMap;
import values.TunableParameters;
import wrappers.RandomWrapper;

public class TilePainterTest {

	private final int TILE_WIDTH = 10;
	private final int TILE_HEIGHT = 20;
	private final int X = 2;
	private final int Y = 3;

	Graphics graphics;
	TilePainter tilePainter;

	@Before
	public void setUp() {
		tilePainter = new TilePainter();
		graphics = Mockito.mock(Graphics.class);
	}

	@Test
	public void paint_tiles() {
		GameEngine game = Mockito.mock(GameEngine.class);
		Mockito.when(game.getLevelHorizontalDimension()).thenReturn(X);
		Mockito.when(game.getLevelVerticalDimension()).thenReturn(Y);
		Mockito.when(game.getTileFromCoordinates(1, 1)).thenReturn(TileType.NOT_PASSABLE);
		Mockito.when(game.getTileFromCoordinates(AdditionalMatchers.not(Matchers.eq(1)),
				AdditionalMatchers.not(Matchers.eq(1)))).thenReturn(TileType.PASSABLE);

		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);
		Mockito.when(randomWrapper.getRandomNumberInRange(Mockito.anyInt(), Mockito.anyInt())).thenReturn(0);
		tilePainter.setRandomWrapper(randomWrapper);

		tilePainter.paintTiles(graphics, game, TILE_WIDTH, TILE_HEIGHT);

		InOrder inOrder = Mockito.inOrder(graphics);
		inOrder.verify(graphics).setColor(TileColorMap.get(TileType.PASSABLE));
		inOrder.verify(graphics).fillRect(0, 0, 10, 20);
		inOrder.verify(graphics).fillRect(0, 20, 10, 20);
		inOrder.verify(graphics).fillRect(0, 40, 10, 20);
		inOrder.verify(graphics).fillRect(10, 0, 10, 20);
		inOrder.verify(graphics).setColor(TileColorMap.get(TileType.NOT_PASSABLE));
		inOrder.verify(graphics).fillRect(10, 20, 10, 20);
		inOrder.verify(graphics).fillRect(10, 40, 10, 20);

	}

	@Test
	public void paint_player() {
		tilePainter.paintPlayer(graphics, X, Y, TILE_WIDTH, TILE_HEIGHT, TileType.PLAYER);
		Mockito.verify(graphics).fillRect(20, 60, 10, 20);
	}

	@Test
	public void advance_a_projectile_every_frame() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);
		Mockito.when(randomWrapper.getRandomNumberInRange(Mockito.anyInt(), Mockito.anyInt())).thenReturn(0);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.paintTiles(graphics, game, TILE_WIDTH, TILE_HEIGHT);

		Mockito.verify(game, Mockito.times(1)).addTile(1, 2, TileType.PASSABLE);
		Mockito.verify(game, Mockito.times(1)).addTile(1, 2 - 1, TileType.PROJECTILE);
	}

	@Test
	public void projectile_cannot_go_through_wall() {
		GameEngine game = Mockito.mock(GameEngine.class);
		Mockito.when(game.getLevelHorizontalDimension()).thenReturn(X);
		Mockito.when(game.getLevelVerticalDimension()).thenReturn(Y);
		Mockito.when(game.getTileFromCoordinates(0, 0)).thenReturn(TileType.PASSABLE);
		Mockito.when(game.getTileFromCoordinates(1, 0)).thenReturn(TileType.NOT_PASSABLE);

		Mockito.when(game.getTileFromCoordinates(0, 1)).thenReturn(TileType.PASSABLE);
		Mockito.when(game.getTileFromCoordinates(1, 1)).thenReturn(TileType.NOT_PASSABLE);

		Mockito.when(game.getTileFromCoordinates(0, 2)).thenReturn(TileType.PASSABLE);
		Mockito.when(game.getTileFromCoordinates(1, 2)).thenReturn(TileType.PROJECTILE);

		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);
		Mockito.when(randomWrapper.getRandomNumberInRange(Mockito.anyInt(), Mockito.anyInt())).thenReturn(0);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.paintTiles(graphics, game, TILE_WIDTH, TILE_HEIGHT);

		Mockito.verify(game, Mockito.times(1)).addTile(1, 2, TileType.PASSABLE);
	}

	@Test
	public void projectile_can_go_through_other_projectile() {
		GameEngine game = setup_game();

		Mockito.when(game.getLevelHorizontalDimension()).thenReturn(10);
		Mockito.when(game.getLevelVerticalDimension()).thenReturn(10);
		Mockito.when(game.getPlayerXCoordinate()).thenReturn(0);
		Mockito.when(game.getPlayerYCoordinate()).thenReturn(3);
		Mockito.when(game.getTileFromCoordinates(1, 1)).thenReturn(TileType.PROJECTILE);

		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);
		Mockito.when(randomWrapper.getRandomNumberInRange(Mockito.anyInt(), Mockito.anyInt())).thenReturn(0);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.advanceProjectile(game, 1, 2);

		Mockito.verify(game, Mockito.times(1)).addTile(1, 2, TileType.PASSABLE);
	}

	@Test
	public void spawn_an_enemy_randomly() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

		Mockito.when(game.getLevelHorizontalDimension()).thenReturn(10);
		Mockito.when(game.getLevelVerticalDimension()).thenReturn(10);
		Mockito.when(game.getPlayerXCoordinate()).thenReturn(0);
		Mockito.when(game.getPlayerYCoordinate()).thenReturn(3);

		Mockito.when(randomWrapper.getRandomNumberInRange(Mockito.anyInt(), Mockito.anyInt())).thenReturn(0);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.addRandomEnemy(game);

		Mockito.verify(game, Mockito.times(1)).addTile(0, 0, TileType.ENEMY);
	}

	@Test
	public void random_coordinates() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

		Mockito.when(game.getLevelHorizontalDimension()).thenReturn(10);
		Mockito.when(game.getLevelVerticalDimension()).thenReturn(10);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.addRandomEnemy(game);

		Mockito.verify(randomWrapper, Mockito.times(2)).getRandomNumberInRange(Mockito.anyInt(), Mockito.anyInt());
	}

	@Test
	public void enemies_can_only_spawn_on_passable_tiles() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

		Mockito.when(game.getTileFromCoordinates(0, 0)).thenReturn(TileType.NOT_PASSABLE);
		Mockito.when(game.getLevelHorizontalDimension()).thenReturn(10);
		Mockito.when(game.getLevelVerticalDimension()).thenReturn(10);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.addRandomEnemy(game);

		Mockito.verify(game, Mockito.times(0)).addTile(0, 0, TileType.ENEMY);
	}

	@Test
	public void enemies_cannot_spawn_within_n_tiles_of_the_player() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

		Mockito.when(game.getTileFromCoordinates(0, 2)).thenReturn(TileType.PLAYER);
		Mockito.when(game.getLevelHorizontalDimension()).thenReturn(10);
		Mockito.when(game.getLevelVerticalDimension()).thenReturn(10);
		Mockito.when(game.getPlayerXCoordinate()).thenReturn(0);
		Mockito.when(game.getPlayerYCoordinate()).thenReturn(2);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.addRandomEnemy(game);

		Mockito.verify(game, Mockito.times(0)).addTile(0, 0, TileType.ENEMY);
	}

	@Test
	public void colliding_with_enemy_kills_the_enemy() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

		Mockito.when(game.getTileFromCoordinates(1, 1)).thenReturn(TileType.ENEMY);
		Mockito.when(game.getPlayerXCoordinate()).thenReturn(0);
		Mockito.when(game.getPlayerYCoordinate()).thenReturn(2);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.advanceProjectile(game, 1, 2);

		Mockito.verify(game, Mockito.times(1)).addTile(1, 1, TileType.PASSABLE);
	}

	@Test
	public void killing_an_enemy_increments_score() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

		Mockito.when(game.getTileFromCoordinates(1, 1)).thenReturn(TileType.ENEMY);
		Mockito.when(game.getPlayerXCoordinate()).thenReturn(0);
		Mockito.when(game.getPlayerYCoordinate()).thenReturn(2);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.advanceProjectile(game, 1, 2);

		Mockito.verify(game, Mockito.times(1)).incrementScore();
	}

	@Test
	public void increment_count_down() {
		tilePainter.incrementCountDown();
		assertEquals(1, tilePainter.getEnemyCountDown());
	}

	@Test
	public void reset_count_down() {
		tilePainter.incrementCountDown();
		tilePainter.incrementCountDown();
		tilePainter.incrementCountDown();

		tilePainter.resetCountDown();
		assertEquals(0, tilePainter.getEnemyCountDown());
	}

	@Test
	public void does_not_spawn_enemy_when_count_down_is_not_at_the_set_value() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

		Mockito.when(randomWrapper.getRandomNumberInRange(Mockito.anyInt(), Mockito.anyInt())).thenReturn(0);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.paintTiles(graphics, game, TILE_WIDTH, TILE_HEIGHT);

		Mockito.verify(game, Mockito.times(0)).addTile(0, 0, TileType.ENEMY);
	}

	@Test
	public void enemy_spawns_when_count_down_is_reached() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

		Mockito.when(randomWrapper.getRandomNumberInRange(Mockito.anyInt(), Mockito.anyInt())).thenReturn(0);

		tilePainter.setRandomWrapper(randomWrapper);
		this.incrementCountDown(TunableParameters.ENEMY_SPAWN_EVERY_N_FRAMES);
		tilePainter.paintTiles(graphics, game, TILE_WIDTH, TILE_HEIGHT);

		Mockito.verify(randomWrapper, Mockito.times(2)).getRandomNumberInRange(Mockito.anyInt(), Mockito.anyInt());
	}

	@Test
	public void reset_count_down_after_spawning_an_enemy() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

		Mockito.when(randomWrapper.getRandomNumberInRange(Mockito.anyInt(), Mockito.anyInt())).thenReturn(0);

		tilePainter.setRandomWrapper(randomWrapper);
		this.incrementCountDown(TunableParameters.ENEMY_SPAWN_EVERY_N_FRAMES);
		tilePainter.paintTiles(graphics, game, TILE_WIDTH, TILE_HEIGHT);

		assertEquals(0, tilePainter.getEnemyCountDown());
	}

	@Test
	public void increment_count_down_when_paint_tiles_is_called() {
		GameEngine game = setup_game();
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

		tilePainter.setRandomWrapper(randomWrapper);
		tilePainter.paintTiles(graphics, game, TILE_WIDTH, TILE_HEIGHT);

		assertEquals(1, tilePainter.getEnemyCountDown());
	}

	private void incrementCountDown(int numTimes) {
		for (int i = 0; i < numTimes; i++) {
			tilePainter.incrementCountDown();
		}
	}

	private GameEngine setup_game() {
		GameEngine game = Mockito.mock(GameEngine.class);
		Mockito.when(game.getLevelHorizontalDimension()).thenReturn(X);
		Mockito.when(game.getLevelVerticalDimension()).thenReturn(Y);
		Mockito.when(game.getTileFromCoordinates(0, 0)).thenReturn(TileType.PASSABLE);
		Mockito.when(game.getTileFromCoordinates(1, 0)).thenReturn(TileType.NOT_PASSABLE);

		Mockito.when(game.getTileFromCoordinates(0, 1)).thenReturn(TileType.PASSABLE);
		Mockito.when(game.getTileFromCoordinates(1, 1)).thenReturn(TileType.PASSABLE);

		Mockito.when(game.getTileFromCoordinates(0, 2)).thenReturn(TileType.PASSABLE);
		Mockito.when(game.getTileFromCoordinates(1, 2)).thenReturn(TileType.PROJECTILE);
		return game;
	}
}

package ui;

import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.InOrder;
import org.mockito.Matchers;
import org.mockito.Mockito;

import engine.GameEngine;
import tiles.TileType;
import values.TileColorMap;

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

}

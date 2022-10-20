package ui;

import static org.junit.Assert.assertSame;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import engine.GameEngine;
import tiles.TileType;

public class GamePanelTest {

	int width = 50;
	int horizontalDimension = 5;
	int height = 60;
	int verticalDimension = 6;
	int tileWidth = width / horizontalDimension;
	int tileHeight = height / verticalDimension;

	GamePanel gamePanel;
	GameEngine gameEngine;
	TilePainter tilePainter;

	@Before
	public void setUp() throws Exception {
		gameEngine = Mockito.mock(GameEngine.class);
		tilePainter = Mockito.mock(TilePainter.class);
		Mockito.when(gameEngine.getLevelHorizontalDimension()).thenReturn(horizontalDimension);
		Mockito.when(gameEngine.getLevelVerticalDimension()).thenReturn(verticalDimension);
		gamePanel = new GamePanel(gameEngine, tilePainter);
		gamePanel.setSize(width, height);
		gamePanel.init();
	}

	@Test
	public void paint() {
		Graphics graphics = Mockito.mock(Graphics.class);
		int playerXCoordinate = 2;
		int playerYCoordinate = 3;
		Mockito.when(gameEngine.getPlayerXCoordinate()).thenReturn(playerXCoordinate);
		Mockito.when(gameEngine.getPlayerYCoordinate()).thenReturn(playerYCoordinate);
		gamePanel.paint(graphics);
		Mockito.verify(tilePainter).paintTiles(graphics, gameEngine, tileWidth, tileHeight);
		Mockito.verify(tilePainter).paintPlayer(graphics, playerXCoordinate, playerYCoordinate, tileWidth, tileHeight,
				TileType.PLAYER);
	}

	@Test
	public void update() {
		Graphics dbg = Mockito.mock(Graphics.class);
		Image dbImage = Mockito.mock(Image.class);
		Mockito.when(dbImage.getGraphics()).thenReturn(dbg);

		gamePanel = Mockito.mock(GamePanel.class, Mockito.CALLS_REAL_METHODS);
		Mockito.when(gamePanel.getWidth()).thenReturn(width);
		Mockito.when(gamePanel.getHeight()).thenReturn(height);
		Mockito.when(gamePanel.createImage(width, height)).thenReturn(dbImage);
		Mockito.doNothing().when(gamePanel).paint(dbg);
		Graphics graphics = Mockito.mock(Graphics.class);
		gamePanel.update(graphics);
		gamePanel.update(graphics);
		Mockito.verify(gamePanel, Mockito.times(1)).createImage(width, height);
		Mockito.verify(gamePanel, Mockito.times(3)).getWidth();
		Mockito.verify(gamePanel, Mockito.times(3)).getHeight();
		Mockito.verify(gamePanel, Mockito.times(2)).paint(dbg);
		Mockito.verify(dbImage, Mockito.times(2)).getGraphics();

		Mockito.verify(graphics, Mockito.times(2)).drawImage(dbImage, 0, 0, gamePanel);
	}

	@Test
	public void key_left() {
		gamePanel.keyDown(null, Event.LEFT);
		Mockito.verify(gameEngine, Mockito.times(1)).keyLeft();
	}

	@Test
	public void key_right() {
		gamePanel.keyDown(null, Event.RIGHT);
		Mockito.verify(gameEngine, Mockito.times(1)).keyRight();
	}

	@Test
	public void key_up() {
		gamePanel.keyDown(null, Event.UP);
		Mockito.verify(gameEngine, Mockito.times(1)).keyUp();
	}

	@Test
	public void key_down() {
		gamePanel.keyDown(null, Event.DOWN);
		Mockito.verify(gameEngine, Mockito.times(1)).keyDown();
	}

	@Test
	public void key_escape() {
		boolean actual = gamePanel.keyDown(null, Event.ESCAPE);
		assertSame(true, actual);
	}
}

package ui;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.KeyEvent;

import engine.GameEngine;
import tiles.TileType;

public class GamePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Image dbImage;
	private final GameEngine gameEngine;
	private final TilePainter tilePainter;
	private int tileWidth;
	private int tileHeight;

	public GamePanel(GameEngine gameEngine, TilePainter tilePainter) {
		this.gameEngine = gameEngine;
		this.tilePainter = tilePainter;
		repaint();
	}

	void init() {
		tileWidth = this.getWidth() / gameEngine.getLevelHorizontalDimension();
		tileHeight = this.getHeight() / gameEngine.getLevelVerticalDimension();
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		requestFocusInWindow();
		tilePainter.paintTiles(graphics, gameEngine, tileWidth, tileHeight);
		tilePainter.paintPlayer(graphics, gameEngine.getPlayerXCoordinate(), gameEngine.getPlayerYCoordinate(),
				tileWidth, tileHeight, TileType.PLAYER);

		if(gameEngine.hasPowerBall())
			tilePainter.paintPowerBall(graphics, gameEngine.getPlayerXCoordinate(), gameEngine.getPlayerYCoordinate(),
				tileWidth, tileHeight, TileType.POWERBALL);
	}

	@Override
	public void update(Graphics graphics) {
		if (dbImage == null) {
			dbImage = createImage(getWidth(), getHeight());
		}
		Graphics dbg = dbImage.getGraphics();
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, getWidth(), getHeight());
		dbg.setColor(getForeground());
		paint(dbg);
		graphics.drawImage(dbImage, 0, 0, this);
	}

	boolean isKeyZ(int key){
		return key == 122 || key == 90;
	}

	boolean isKeyX(int key){
		return key == 120 || key == 88;
	}

	@Override
	public boolean keyDown(Event evt, int key) {
		if (key == Event.LEFT) {
			gameEngine.keyLeft();
		} else if (key == Event.RIGHT) {
			gameEngine.keyRight();
		} else if (key == Event.UP) {
			gameEngine.keyUp();
		} else if (key == Event.DOWN) {
			gameEngine.keyDown();
		} else if (isKeyZ(key)) {
			gameEngine.keyZ();
		} else if (isKeyX(key)) {
			gameEngine.keyX();
		}


		return true;
	}
}

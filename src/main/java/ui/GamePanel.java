package ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import engine.GameEngine;
import main.EnemyHandler;
import main.LaserHandler;
import tiles.TileType;
import values.TunableParameters;


public class GamePanel extends Panel {

	private static final long serialVersionUID = 1L;
	private final GameEngine gameEngine;
	private final TilePainter tilePainter;
	private Image dbImage;
	private int tileWidth;
	private int tileHeight;
	private int laserWidth;
	private int laserHeight;
	private final List<EnemyHandler.Enemy> enemies;
	private final List<LaserHandler.Laser> lasers;

	public GamePanel(GameEngine gameEngine, TilePainter tilePainter) {
		this.gameEngine = gameEngine;
		this.tilePainter = tilePainter;
		this.enemies = EnemyHandler.getEnemies();
		this.lasers = this.gameEngine.getLasers();


		repaint();
	}

	void init() {
		tileWidth = this.getWidth() / gameEngine.getLevelHorizontalDimension();
		tileHeight = this.getHeight() / gameEngine.getLevelVerticalDimension();
		laserWidth = tileWidth / TunableParameters.TILE_TO_LASER_WIDTH;
		laserHeight = tileHeight / TunableParameters.TILE_TO_LASER_HEIGHT;
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		requestFocusInWindow();
		tilePainter.paintTiles(graphics, gameEngine, tileWidth, tileHeight);
		tilePainter.paintPlayer(graphics, gameEngine.getPlayerXCoordinate(), gameEngine.getPlayerYCoordinate(),
				tileWidth, tileHeight, TileType.PLAYER);
		if(!lasers.isEmpty()){
			tilePainter.paintLasers(graphics, lasers, laserWidth, laserHeight, tileWidth);
		}
		if(!enemies.isEmpty()){
			tilePainter.paintEnemies(graphics, enemies);
		}
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
		} else if (key == KeyEvent.VK_SPACE) {
			gameEngine.keySpace();
		}

		return true;
	}
}

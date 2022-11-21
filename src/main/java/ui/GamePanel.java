package ui;

import engine.GameEngine;
import tiles.TileType;
import values.TunableParameters;

import java.awt.*;

public class GamePanel extends Panel {

	private static final long serialVersionUID = 1L;
	private final Button buttonOne;
	private final Button buttonTwo;
	private final Button buttonThree;
	private Component[] buttons;
	private Image dbImage;
	private final GameEngine gameEngine;
	private final TilePainter tilePainter;
	private int tileWidth;
	private int tileHeight;

	public GamePanel(GameEngine gameEngine, TilePainter tilePainter) {
		this.gameEngine = gameEngine;
		this.tilePainter = tilePainter;

		buttonOne = new Button("Choice One");
		buttonOne.setBounds(100, 100, TunableParameters.BUTTON_WIDTH, TunableParameters.BUTTON_HEIGHT);
		add(buttonOne);

		buttonTwo = new Button("Choice Two");
		buttonTwo.setBounds(150, 100, TunableParameters.BUTTON_WIDTH, TunableParameters.BUTTON_HEIGHT);
		add(buttonTwo);

		buttonThree = new Button("Choice Three");
		buttonThree.setBounds(200, 100, TunableParameters.BUTTON_WIDTH, TunableParameters.BUTTON_HEIGHT);
		add(buttonThree);

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
		tilePainter.paintNonPlayableCharacter(graphics, gameEngine.getNonPlayableCharacterXCoordinate(),
				gameEngine.getNonPlayableCharacterYCoordinate(), tileWidth,
				tileHeight, TileType.NON_PLAYABLE_CHARACTER);
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
		}
		return true;
	}

	public Component[] getButtons() {
		buttons = this.getComponents();
		return buttons;
	}
}

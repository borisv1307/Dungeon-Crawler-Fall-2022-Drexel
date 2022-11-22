package ui;

import values.TunableParameters;

import java.awt.*;

public class GameFrame extends Frame {

	private static final long serialVersionUID = 1L;

	public GameFrame(GamePanel gamePanel, WindowAdapterSystemExit windowAdapterSystemExit) {
		setResizable(false);
		addWindowListener(windowAdapterSystemExit);

		gamePanel.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));
		add(gamePanel);

		pack();
		gamePanel.init();
		setVisible(true);
	}
}

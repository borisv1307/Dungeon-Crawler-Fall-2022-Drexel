package ui;

import java.awt.*;

import values.TunableParameters;

public class GameFrame extends Frame {

	private static final long serialVersionUID = 1L;

	public GameFrame(GamePanel gamePanel, WindowAdapterSystemExit windowAdapterSystemExit, ScorePanel scorePanel) {
		setResizable(false);
		addWindowListener(windowAdapterSystemExit);
		gamePanel.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));
		add(gamePanel);
		add(scorePanel.getPanel(), BorderLayout.SOUTH);
		pack();
		gamePanel.init();
		setVisible(true);
	}
}

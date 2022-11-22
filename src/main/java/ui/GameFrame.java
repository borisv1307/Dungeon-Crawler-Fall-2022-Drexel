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

		Panel buttonPanel = new Panel();
		buttonPanel.setBackground(Color.CYAN);
		Button button = new Button("Click Me");
		button.setBounds(60, 60, 100, 100);
		buttonPanel.add(button);
		add(buttonPanel, BorderLayout.NORTH);

		pack();
		gamePanel.init();
		setVisible(true);
	}
}

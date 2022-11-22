package ui;

import values.TunableParameters;

import java.awt.*;

import static values.TunableParameters.CHOICE_BUTTONS_LABELS;

public class GameFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private Panel buttonPanel;

	public GameFrame(GamePanel gamePanel, WindowAdapterSystemExit windowAdapterSystemExit) {
		setResizable(false);
		addWindowListener(windowAdapterSystemExit);

		gamePanel.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));
		add(gamePanel);

		buttonPanel = createButtonsPanel();
		buttonPanel.setBackground(Color.CYAN);
		
		add(buttonPanel, BorderLayout.NORTH);

		pack();
		gamePanel.init();
		setVisible(true);
	}

	public Panel getButtonPanel() {
		return buttonPanel;
	}

	Panel createButtonsPanel() {
		Panel panel = new Panel();
		for (String label : CHOICE_BUTTONS_LABELS) {
			Button button = new Button(label);
			button.setBounds(60, 60, 100, 100);
			panel.add(button);
		}
		return panel;
	}

	Button getButton(int index) {
		return (Button) buttonPanel.getComponent(index);
	}

	Component[] getAllComponents() {
		return buttonPanel.getComponents();
	}
}
